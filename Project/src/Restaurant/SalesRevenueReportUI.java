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
        do{
            System.out.println("(1)\tPeriod report");
            System.out.println("(2)\tDay report");
            System.out.println("[Enter -1 to go back]");
            choice = GetInput.getInt();
            if(choice != 1 && choice != 2 && choice != -1){
                System.out.println("Invalid input, please enter your choice again");
            }
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
