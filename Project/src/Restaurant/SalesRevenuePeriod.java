package Restaurant;


import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An instance of this class represents a sales revenue period
 * Subclass of SalesRevenueDay; Contains end date
 */
class SalesRevenuePeriod extends SalesRevenueDay{
    /**
     * End date of sales period for this report
     */
    private LocalDateTime endDate; //"date" in SalesRevenueDay is the start date for SalesRevenuePeriod

    /**
     * Constructor for this Sales Revenue Report; uses the constructor from super class
     */
    public SalesRevenuePeriod(){
        super();
    }

    /**
     * Creates Sales Revenue Report based on period between start and end dates
     * Stops iterating once end date is before the date in question
     * Add all dates to the local transHist
     * @param transHistAll ArrayList of TransHistDay that this sales report will subset based on its date
     */
    public void createReport(ArrayList<TransHistDay> transHistAll, LocalDateTime startDate, LocalDateTime endDate){
//        do{
//            System.out.println("-ENTERING START DATE OF REPORT-");
//            this.date = GetPeriod.getDate();
//            System.out.println("-ENTERING END DATE OF REPORT-");
//            this.endDate = GetPeriod.getDate();
//            if (endDate.isBefore(date)) {
//                System.out.printf("End date (%s) is before start date (%s), please try again\n", endDate, date);
//            }
//        }while(endDate.isBefore(date) && endDate.isEqual(date));
        //add to transHist
        this.date = startDate;
        this.endDate = endDate;
        for(TransHistDay x: transHistAll){//Narrow down to intended dates
            if((date.isBefore(x.getDate()) && endDate.isAfter(x.getDate()))
                    || date.isEqual(x.getDate())
                    || endDate.isEqual(x.getDate())){ //Get date of transhist item is in between (inclusive)
                transHist.add(x); // Add the matching entry to the local arraylist of days of transhist
            }
            else if(endDate.isBefore(x.getDate())){
                break;
            }
        }
        generateReport();
    }

    /**
     * Prints this sales revenue report generated from other method
     */
    public void printReport() {
        double tempSum;
        double fullSum = 0;
        System.out.println();
        System.out.println("=".repeat(73));
        System.out.printf("Summary of sales between %s and %s: \n", date, endDate);
        System.out.println("-".repeat(73));
        System.out.printf(" %-30s| %-8s| %-7s| %-10s\n", "ITEM", "PRICE", "QTY", "REVENUE");
        System.out.println("-".repeat(73));
        for (TransHistItem x : this.summaryList) {
            tempSum = x.getPrice() * x.getQuantity();
            fullSum += tempSum;
            System.out.printf(" %-30s| %-8.2f| %-7d| %-10.2f\n",
                    x.getItem(), x.getPrice(), x.getQuantity(), tempSum);
        }
        System.out.println("-".repeat(73));
        System.out.printf("TOTAL REVENUE FOR PERIOD: %.2f\n", fullSum);
        System.out.println("=".repeat(73));
    }
}
