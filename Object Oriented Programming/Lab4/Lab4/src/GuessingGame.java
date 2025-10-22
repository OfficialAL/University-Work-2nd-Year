import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private final int correctNumber;
    private int guessesRemaining;
    private boolean gameWon;
    private boolean gameOver;
    
    // Constructor
    public GuessingGame(int maxGuesses) {
        Random random = new Random();
        this.correctNumber = random.nextInt(100) + 1; // Random number between 1-100
        this.guessesRemaining = maxGuesses;
        this.gameWon = false;
        this.gameOver = false;
    }
    
    // Get the number of guesses remaining
    public int getGuessesRemaining() {
        return guessesRemaining;
    }
    
    // See whether or not the game is over
    public boolean isGameOver() {
        return gameOver;
    }
    
    // See whether or not the game has been won
    public boolean isGameWon() {
        return gameWon;
    }
    
    // Make a guess
    public void makeGuess(int guess) {
        if (gameOver) {
            System.out.println("Game is already over! Cannot make more guesses.");
            return;
        }
        
        guessesRemaining--;
        
        if (guess == correctNumber) {
            gameWon = true;
            gameOver = true;
            System.out.println("Correct! You won!");
        } else if (guess < correctNumber) {
            System.out.println("Too low!");
        } else {
            System.out.println("Too high!");
        }
        
        // Check if game is over due to no more guesses
        if (guessesRemaining <= 0 && !gameWon) {
            gameOver = true;
        }
    }
    
    // Print the correct number - but only if the game is over
    public void printCorrectNumber() {
        if (gameOver) {
            System.out.println("The correct number was: " + correctNumber);
        } else {
            System.out.println("Game is not over yet! Cannot reveal the answer.");
        }
    }
    
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            GuessingGame game = new GuessingGame(5);
            
            System.out.println("Welcome to the Guessing Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have " + game.getGuessesRemaining() + " guesses.");
            
            // Game loop with user input
            while (!game.isGameOver()) {
                System.out.print("Enter your guess: ");
                
                try {
                    int userGuess = scanner.nextInt();
                    game.makeGuess(userGuess);
                    
                    if (!game.isGameOver()) {
                        System.out.println("Guesses remaining: " + game.getGuessesRemaining());
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a valid number!");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
            
            if (game.isGameWon()) {
                System.out.println("Congratulations! You won the game!");
            } else {
                System.out.println("Game over! You ran out of guesses.");
            }
            
            game.printCorrectNumber();
        }
    }
}
