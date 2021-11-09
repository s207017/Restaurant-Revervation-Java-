package Restaurant;

public class SalesRevenueReportInterface {
    private Restaurant restaurant;
    public SalesRevenueReportInterface(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void createPeriodSalesReport(){
        SalesRevenueReport salesReport = new SalesRevenueReport(restaurant.getTransactionHistory(), 1);
    }

    public void createDaySalesReport(){
        SalesRevenueReport salesReport = new SalesRevenueReport(restaurant.getTransactionHistory(), 2);
    }

    /**
     * method to be called in the main app for generating sales revenue reports
     */
    public void printSalesRevenueReport(){
        int choice;
        System.out.println("Select the type of report you wish to view:");
        System.out.println("(1)\tPeriod report");
        System.out.println("(2)\tDay report");
        System.out.println("(Enter -1 to go back)");
        choice = GetInput.getInt();
        while(choice != 1 && choice != 2 && choice != -1) {
            System.out.println("Invalid choice, please enter your choice again.");
            System.out.println("(1)\tPeriod report");
            System.out.println("(2)\tDay report");
            System.out.println("(Enter -1 to go back)");
            choice = GetInput.getInt();
        }
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
