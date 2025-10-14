// @author Student

public class VendingMachine {
    static final int CANDY_PRICE = 3;
    static final int CANDY_CAPACITY = 20;
    
    int candyBars;
    int balance;
    int revenue;
    
    VendingMachine() {
        candyBars = CANDY_CAPACITY;
        balance = 0;
        revenue = 0;
    }
    
    int getBalance() {
        return balance;
    }
    
    int getRevenue() {
        return revenue;
    }
    
    void insertCoin() {
        balance++;
    }
    
    int refund() {
        int amount = balance;
        balance = 0;
        return amount;
    }
    
    boolean vendCandyBar() {
        if(candyBars >= 1 && balance >= CANDY_PRICE) {
            candyBars--;
            balance -= CANDY_PRICE;
            revenue += CANDY_PRICE;
            
            // Check if restock is needed after vending
            if (isLowInventory()) {
                System.out.println("Warning: Low inventory!");
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    void restock() {
        candyBars = CANDY_CAPACITY;
    }

    // Add inventory management methods
    boolean isLowInventory() {
        return candyBars < (CANDY_CAPACITY / 4); // Low when less than 25% capacity
    }

    void checkAndRestock() {
        if (isLowInventory()) {
            System.out.println("Low inventory detected. Restocking...");
            restock();
        }
    }

    public static int getCandyPrice() {
        return CANDY_PRICE;
    }

    public static int getCandyCapacity() {
        return CANDY_CAPACITY;
    }

    public int getCandyBars() {
        return candyBars;
    }

    public void setCandyBars(int candyBars) {
        this.candyBars = candyBars;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public static void main(String[] args) {
        System.out.println("=== VENDING MACHINE COMPREHENSIVE TESTS ===\n");
        
        // Test 1: Initial balance is zero
        System.out.println("TEST 1: Initial balance is zero");
        VendingMachine vm1 = new VendingMachine();
        System.out.println("Initial balance: " + vm1.getBalance());
        System.out.println("Expected: 0, Actual: " + vm1.getBalance());
        System.out.println("PASS: " + (vm1.getBalance() == 0) + "\n");
        
        // Test 2: Initial revenue is zero
        System.out.println("TEST 2: Initial revenue is zero");
        VendingMachine vm2 = new VendingMachine();
        System.out.println("Initial revenue: " + vm2.getRevenue());
        System.out.println("Expected: 0, Actual: " + vm2.getRevenue());
        System.out.println("PASS: " + (vm2.getRevenue() == 0) + "\n");
        
        // Test 3: Inserting coins increases balance but not revenue
        System.out.println("TEST 3: Inserting coins increases balance but not revenue");
        VendingMachine vm3 = new VendingMachine();
        int initialBalance = vm3.getBalance();
        int initialRevenue = vm3.getRevenue();
        
        // Insert 2 coins
        vm3.insertCoin();
        vm3.insertCoin();
        
        System.out.println("After inserting 2 coins:");
        System.out.println("Balance - Expected: 2, Actual: " + vm3.getBalance());
        System.out.println("Revenue - Expected: 0, Actual: " + vm3.getRevenue());
        boolean balanceIncreased = (vm3.getBalance() == initialBalance + 2);
        boolean revenueUnchanged = (vm3.getRevenue() == initialRevenue);
        System.out.println("PASS: " + (balanceIncreased && revenueUnchanged) + "\n");
        
        // Test 4: Refund returns correct amount and resets balance
        System.out.println("TEST 4: Refund returns correct amount and resets balance");
        VendingMachine vm4 = new VendingMachine();
        
        // Insert 5 coins
        for (int i = 0; i < 5; i++) {
            vm4.insertCoin();
        }
        
        int balanceBeforeRefund = vm4.getBalance();
        int refundAmount = vm4.refund();
        int balanceAfterRefund = vm4.getBalance();
        
        System.out.println("Balance before refund: " + balanceBeforeRefund);
        System.out.println("Refund amount: " + refundAmount);
        System.out.println("Balance after refund: " + balanceAfterRefund);
        System.out.println("Expected refund: " + balanceBeforeRefund + ", Actual: " + refundAmount);
        System.out.println("Expected final balance: 0, Actual: " + balanceAfterRefund);
        boolean correctRefund = (refundAmount == balanceBeforeRefund);
        boolean balanceReset = (balanceAfterRefund == 0);
        System.out.println("PASS: " + (correctRefund && balanceReset) + "\n");
        
        // Test 5: No coins inserted means no candy bar vended
        System.out.println("TEST 5: No coins inserted means no candy bar vended");
        VendingMachine vm5 = new VendingMachine();
        boolean vendResult = vm5.vendCandyBar();
        
        System.out.println("Attempted to vend with 0 coins (price is " + CANDY_PRICE + "):");
        System.out.println("Vend result: " + vendResult);
        System.out.println("Expected: false, Actual: " + vendResult);
        System.out.println("PASS: " + (!vendResult) + "\n");
        
        // Test 6: Three coins inserted allows candy bar vending (transfers coins from balance to revenue)
        System.out.println("TEST 6: Three coins allows vending and transfers coins to revenue");
        VendingMachine vm6 = new VendingMachine();

        // Record initial state
        int initialBalance6 = vm6.getBalance();
        int initialRevenue6 = vm6.getRevenue();
        int initialCandyBars = vm6.getCandyBars();

        System.out.println("Initial state:");
        System.out.println("Balance: " + initialBalance6);
        System.out.println("Revenue: " + initialRevenue6);
        System.out.println("Candy bars: " + initialCandyBars);

        // Insert exactly 3 coins (the candy price)
        for (int i = 0; i < 3; i++) {
            vm6.insertCoin();
        }

        System.out.println("After inserting 3 coins:");
        System.out.println("Balance: " + vm6.getBalance());
        System.out.println("Revenue: " + vm6.getRevenue());

        // Now vend a candy bar
        boolean vendResult6 = vm6.vendCandyBar();

        System.out.println("After vending:");
        System.out.println("Balance: " + vm6.getBalance());
        System.out.println("Revenue: " + vm6.getRevenue());
        System.out.println("Candy bars: " + vm6.getCandyBars());

        // Verify the changes using initial values
        System.out.println("Verification:");
        System.out.println("Balance changed by: " + (vm6.getBalance() - initialBalance6));
        System.out.println("Revenue changed by: " + (vm6.getRevenue() - initialRevenue6));
        System.out.println("Candy bars changed by: " + (vm6.getCandyBars() - initialCandyBars));
        System.out.println("Vend successful: " + vendResult6);

        // Test assertions
        boolean balanceCorrect = (vm6.getBalance() == initialBalance6); // Should be 0 after vending
        boolean revenueCorrect = (vm6.getRevenue() == initialRevenue6 + CANDY_PRICE);
        boolean candyBarsCorrect = (vm6.getCandyBars() == initialCandyBars - 1);

        System.out.println("PASS - Balance reset: " + balanceCorrect);
        System.out.println("PASS - Revenue increased: " + revenueCorrect);
        System.out.println("PASS - Candy bars decreased: " + candyBarsCorrect);
        System.out.println("Overall test PASS: " + (balanceCorrect && revenueCorrect && candyBarsCorrect && vendResult6));

        // Summary
        System.out.println("=== TEST SUMMARY ===");
        System.out.println("All tests verify the vending machine behavior as specified.");
        System.out.println("- Initial states are properly set to zero");
        System.out.println("- Coin insertion affects only balance, not revenue");
        System.out.println("- Refunds work correctly and reset balance");
        System.out.println("- Insufficient funds prevent vending");
        System.out.println("- Successful vending transfers coins from balance to revenue");

        // Demonstrate usage of checkAndRestock()
        System.out.println("\nTEST 7: Demonstrate checkAndRestock usage");
        VendingMachine vm7 = new VendingMachine();
        vm7.setCandyBars(3); // Set low inventory
        vm7.checkAndRestock(); // Should trigger restock
        System.out.println("Candy bars after checkAndRestock: " + vm7.getCandyBars());
        System.out.println("Expected: " + CANDY_CAPACITY + ", Actual: " + vm7.getCandyBars());
        System.out.println("PASS: " + (vm7.getCandyBars() == CANDY_CAPACITY));
    }
}
