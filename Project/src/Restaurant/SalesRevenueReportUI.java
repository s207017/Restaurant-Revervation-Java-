package Restaurant;

public class SalesRevenueReportUI {
    private Restaurant restaurant;
    public SalesRevenueReportUI(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    /**
     * creates a new sales report for a specified period
     * choice == 1 in SaleRevenueReport constructor will prompt user to enter 2 dates
     * to get the period for which they wish to view the report
     */
    public void createPeriodSalesReport(){
        SalesRevenueReport salesReport = new SalesRevenueReport(restaurant.getTransactionHistory(), 1);
    }

    /**
     * creates a new sales report for a specified day
     * choice == 2 in SaleRevenueReport constructor prompts user to enter one date
     */
    public void createDaySalesReport(){
        SalesRevenueReport salesReport = new SalesRevenueReport(restaurant.getTransactionHistory(), 2);
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
        do{
            System.out.print("Enter option: ");
            choice = GetInput.getIntFromRange(1,3);
        }while(choice != 1 && choice != 2 && choice != -1);
        switch(choice){
            case 1:
                createPeriodSalesReport();
                break;
            case 2:
                createDaySalesReport();
                break;
            case -1:
                break;
        }
        return;
    }
}
