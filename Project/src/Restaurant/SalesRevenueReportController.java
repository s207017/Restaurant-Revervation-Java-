package Restaurant;

/**
 * Used to control Sales Revenue Report operations
 */
public class SalesRevenueReportController {
    /**
     * Declaring a restaurant reference for use in the classes methods
     */
    private Restaurant restaurant;

    /**
     * Constructor takes restaurant as an argument and assigns it to this class's restaurant reference
     * @param restaurant Restaurant object to be added to this SalesRevenueReportController
     */
    public SalesRevenueReportController(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    /**
     * Facilitates options for sales revenue report generation
     * Sales reports generated can be either daily or by period
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
