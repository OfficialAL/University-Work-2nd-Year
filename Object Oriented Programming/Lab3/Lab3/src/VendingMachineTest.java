public class VendingMachineTest {
    
    public static void main(String[] args) {
        System.out.println("=== VENDING MACHINE UNIT TESTS ===\n");
        
        VendingMachineTest tester = new VendingMachineTest();
        tester.testInitialBalance();
        tester.testInitialRevenue();
        tester.testInsertCoins();
        tester.testRefund();
        tester.testVendFailure();
        tester.testVendSuccess();
        
        System.out.println("=== ALL TESTS COMPLETED ===");
    }

    public void testInitialBalance() {
        System.out.println("TEST: Initial balance is zero");
        VendingMachine vm = new VendingMachine();
        boolean pass = (vm.getBalance() == 0);
        System.out.println("Expected: 0, Actual: " + vm.getBalance());
        System.out.println("PASS: " + pass + "\n");
    }

    public void testInitialRevenue() {
        System.out.println("TEST: Initial revenue is zero");
        VendingMachine vm = new VendingMachine();
        boolean pass = (vm.getRevenue() == 0);
        System.out.println("Expected: 0, Actual: " + vm.getRevenue());
        System.out.println("PASS: " + pass + "\n");
    }

    public void testInsertCoins() {
        System.out.println("TEST: Inserting coins increases balance but not revenue");
        VendingMachine vm = new VendingMachine();
        int initialRevenue = vm.getRevenue();
        
        // Insert 2 coins
        vm.insertCoin();
        vm.insertCoin();
        
        boolean balanceCorrect = (vm.getBalance() == 2);
        boolean revenueUnchanged = (vm.getRevenue() == initialRevenue);
        boolean pass = balanceCorrect && revenueUnchanged;
        
        System.out.println("Balance - Expected: 2, Actual: " + vm.getBalance());
        System.out.println("Revenue - Expected: " + initialRevenue + ", Actual: " + vm.getRevenue());
        System.out.println("PASS: " + pass + "\n");
    }

    public void testRefund() {
        System.out.println("TEST: Refund returns correct amount and resets balance");
        VendingMachine vm = new VendingMachine();
        
        // Insert 5 coins
        for (int i = 0; i < 5; i++) {
            vm.insertCoin();
        }
        
        int balanceBeforeRefund = vm.getBalance();
        int refundAmount = vm.refund();
        
        boolean refundCorrect = (refundAmount == balanceBeforeRefund);
        boolean balanceReset = (vm.getBalance() == 0);
        boolean pass = refundCorrect && balanceReset;
        
        System.out.println("Refund amount - Expected: " + balanceBeforeRefund + ", Actual: " + refundAmount);
        System.out.println("Final balance - Expected: 0, Actual: " + vm.getBalance());
        System.out.println("PASS: " + pass + "\n");
    }

    public void testVendFailure() {
        System.out.println("TEST: No candy bar vended with insufficient coins");
        VendingMachine vm = new VendingMachine();
        boolean vendResult = vm.vendCandyBar();
        
        boolean pass = (!vendResult);
        System.out.println("Vend result - Expected: false, Actual: " + vendResult);
        System.out.println("PASS: " + pass + "\n");
    }

    public void testVendSuccess() {
        System.out.println("TEST: Successful vending with exact coins");
        VendingMachine vm = new VendingMachine();
        
        // Record initial state
        int initialBalance = vm.getBalance();
        int initialRevenue = vm.getRevenue();
        int initialCandyBars = vm.getCandyBars();
        
        // Insert exactly 3 coins (the candy price)
        for (int i = 0; i < VendingMachine.CANDY_PRICE; i++) {
            vm.insertCoin();
        }
        
        // Vend a candy bar
        boolean vendResult = vm.vendCandyBar();
        
        // Verify successful vending
        boolean vendSuccessful = vendResult;
        boolean balanceCorrect = (vm.getBalance() == initialBalance); // Should be 0 after vending
        boolean revenueCorrect = (vm.getRevenue() == initialRevenue + VendingMachine.CANDY_PRICE);
        boolean candyBarsCorrect = (vm.getCandyBars() == initialCandyBars - 1);
        boolean pass = vendSuccessful && balanceCorrect && revenueCorrect && candyBarsCorrect;
        
        System.out.println("Vend successful - Expected: true, Actual: " + vendResult);
        System.out.println("Final balance - Expected: " + initialBalance + ", Actual: " + vm.getBalance());
        System.out.println("Final revenue - Expected: " + (initialRevenue + VendingMachine.CANDY_PRICE) + ", Actual: " + vm.getRevenue());
        System.out.println("Candy bars - Expected: " + (initialCandyBars - 1) + ", Actual: " + vm.getCandyBars());
        System.out.println("PASS: " + pass + "\n");
    }
}