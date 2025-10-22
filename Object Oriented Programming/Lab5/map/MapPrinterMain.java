package map;
import java.util.HashMap;
import java.util.Map;

public class MapPrinterMain {
    public static void main(String[] args) {
        // Create a HashMap to store contact telephone numbers
        Map<String, String> phoneContacts = new HashMap<>();
        
        // Populate the map with contact names and telephone numbers
        phoneContacts.put("John Smith", "07123456789");
        phoneContacts.put("Sarah Johnson", "07987654321");
        phoneContacts.put("Mike Brown", "07555123456");
        phoneContacts.put("Emma Wilson", "07777888999");
        phoneContacts.put("David Davis", "07111222333");
        phoneContacts.put("Lisa Taylor", "07444555666");
        phoneContacts.put("Tom Anderson", "07888999000");
        phoneContacts.put("Amy Roberts", "07333444555");
        
        System.out.println("=== Testing MapPrinter Implementation ===\n");
        
        // Test printKeys method
        System.out.println("1. Printing all contact names (keys):");
        System.out.println("-------------------------------------");
        MapPrinter.printKeys(phoneContacts);
        
        System.out.println("\n2. Printing all telephone numbers (values):");
        System.out.println("--------------------------------------------");
        MapPrinter.printValues(phoneContacts);
        
        System.out.println("\n3. Printing all contact pairs (key : value):");
        System.out.println("---------------------------------------------");
        MapPrinter.printPairs(phoneContacts);
        
        System.out.println("\n=== Testing Complete ===");
    }
}