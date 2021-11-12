package Restaurant;

public class SalesRevenueReportController {
    private Restaurant restaurant;
    public SalesRevenueReportController(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    /**
     * method to be called in the main app for generating sales revenue reports
     */
    public void printSalesRevenueReport(){
        if(restaurant.getTransactionHistory().isEmpty()){
            System.out.println("No sales records found");
            return;
        }
        int choice;
        System.out.println("Select the type of report you wish to view:");
        System.out.println("(1) Period report");
        System.out.println("(2) Day report");
        System.out.println("(3) Return to main app");
        System.out.print("Enter option: ");
        choice = GetInput.getIntFromRange(1,3);
        switch(choice){
            case 1:
                SalesRevenuePeriod sp = new SalesRevenuePeriod();
                sp.createReport(restaurant.getTransactionHistory());
                break;
            case 2:
                SalesRevenueDay sd = new SalesRevenueDay();
                sd.createReport(restaurant.getTransactionHistory());
                break;
            case 3:
                break;
        }
    }
}
