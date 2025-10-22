import java.util.Scanner;

public class Tamagotchi {
    private int hungriness;
    private int happiness;
    private int cleanness;
    private int tiredness;
    
    // Constructor
    public Tamagotchi() {
        this.hungriness = 5;  // Start with moderate values
        this.happiness = 5;
        this.cleanness = 5;
        this.tiredness = 0;
    }
    
    // Constructor with initial values
    public Tamagotchi(int hungriness, int happiness, int cleanness, int tiredness) {
        this.hungriness = clamp(hungriness, 0, 10);
        this.happiness = clamp(happiness, 0, 10);
        this.cleanness = clamp(cleanness, 0, 10);
        this.tiredness = clamp(tiredness, 0, 10);
    }
    
    // Helper method to ensure values stay within 0-10 range
    private int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
    
    // Getter methods
    public int getHungriness() {
        return hungriness;
    }
    
    public int getHappiness() {
        return happiness;
    }
    
    public int getCleanness() {
        return cleanness;
    }
    
    public int getTiredness() {
        return tiredness;
    }
    
    // Feed the Tamagotchi - hungriness goes down by 5
    public void feed() {
        hungriness = clamp(hungriness - 5, 0, 10);
    }
    
    // Take for a walk - happiness up by 3, tiredness up by 2
    public void walk() {
        happiness = clamp(happiness + 3, 0, 10);
        tiredness = clamp(tiredness + 2, 0, 10);
    }
    
    // Clean the Tamagotchi - cleanness goes up by 10
    public void clean() {
        cleanness = clamp(cleanness + 10, 0, 10);
    }
    
    // Pet the Tamagotchi - happiness goes up by 5
    public void pet() {
        happiness = clamp(happiness + 5, 0, 10);
    }
    
    // Get current mood based on priority order specified
    public String getMood() {
        if (tiredness == 10) {
            return "asleep";
        }
        if (tiredness >= 8) {
            return "tired";
        }
        if (hungriness >= 7) {
            return "hungry";
        }
        if (cleanness < 3) {
            return "dirty";
        }
        if (happiness >= 8) {
            return "happy";
        }
        if (happiness >= 4) {
            return "Ok";
        }
        return "sad";
    }
    
    // Pass time - affects all stats
    public void passTime() {
        // Hungriness goes up by 1
        hungriness = clamp(hungriness + 1, 0, 10);
        
        // Cleanness goes down by 1
        cleanness = clamp(cleanness - 1, 0, 10);
        
        // Tiredness logic
        if (tiredness == 10) {
            // Tamagotchi wakes up
            tiredness = 0;
        } else {
            // Tiredness goes up by 1
            tiredness = clamp(tiredness + 1, 0, 10);
        }
        
        // Happiness decreases based on conditions
        if (hungriness >= 7) {
            happiness = clamp(happiness - 1, 0, 10);
        }
        if (cleanness < 3) {
            happiness = clamp(happiness - 1, 0, 10);
        }
    }
    
    // Helper method to display current status
    public void displayStatus() {
        System.out.println("=== Tamagotchi Status ===");
        System.out.println("Hungriness: " + hungriness + "/10");
        System.out.println("Happiness: " + happiness + "/10");
        System.out.println("Cleanness: " + cleanness + "/10");
        System.out.println("Tiredness: " + tiredness + "/10");
        System.out.println("Mood: " + getMood());
        System.out.println("========================");
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Tamagotchi pet = new Tamagotchi();
            
            System.out.println("Welcome to Tamagotchi!");
            System.out.println("Take care of your virtual pet!");
            
            boolean running = true;
            while (running) {
                pet.displayStatus();
                System.out.println("\nWhat would you like to do?");
                System.out.println("1. Feed");
                System.out.println("2. Walk");
                System.out.println("3. Clean");
                System.out.println("4. Pet");
                System.out.println("5. Pass Time");
                System.out.println("6. Exit");
                System.out.print("Choose an option (1-6): ");
                
                try {
                    int choice = scanner.nextInt();
                    System.out.println();
                    
                    switch (choice) {
                        case 1 -> {
                            pet.feed();
                            System.out.println("You fed your Tamagotchi!");
                        }
                        case 2 -> {
                            pet.walk();
                            System.out.println("You took your Tamagotchi for a walk!");
                        }
                        case 3 -> {
                            pet.clean();
                            System.out.println("You cleaned your Tamagotchi!");
                        }
                        case 4 -> {
                            pet.pet();
                            System.out.println("You petted your Tamagotchi!");
                        }
                        case 5 -> {
                            pet.passTime();
                            System.out.println("Time passes...");
                        }
                        case 6 -> {
                            running = false;
                            System.out.println("Goodbye! Thanks for taking care of your Tamagotchi!");
                        }
                        default -> System.out.println("Invalid choice! Please choose 1-6.");
                    }
                    
                    if (running && choice >= 1 && choice <= 5) {
                        System.out.println("Your Tamagotchi is feeling: " + pet.getMood());
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine(); // consume the newline
                        scanner.nextLine(); // wait for user input
                    }
                    
                } catch (Exception e) {
                    System.out.println("Please enter a valid number!");
                    scanner.nextLine(); // clear invalid input
                }
            }
        }
    }
}
