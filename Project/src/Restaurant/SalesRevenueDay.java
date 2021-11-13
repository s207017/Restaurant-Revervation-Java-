package Restaurant;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDateTime;

//When you want a report you will create a new object of this class
//Sales report consists of: Total sales, Total quantity of each item sold

/**
 * An instance of this object represents a Sales Report for the specified date
 */
public class SalesRevenueDay {
    /**
     * Date of this sales report
     */
    protected LocalDateTime date;
    /**
     * Reference to an ArrayList of TransHistDay to hold the relevant dates
     */
    protected ArrayList<TransHistDay> transHist;
    /**
     * Reference to an ArrayList of TransHistItems as a record of all items ordered
     */
    protected ArrayList<TransHistItem> summaryList;

    /**
     * Constructor instantiates two ArrayList for transHist and summaryList, setting date to null
     */
    public SalesRevenueDay(){
        this.date = null;
        transHist = new ArrayList<TransHistDay>();
        summaryList = new ArrayList<TransHistItem>();
    }

    /**
     * Creates report for the period
     * @param transHistAll ArrayList of TransHistDay that this sales report will subset based on its date
     */
    public void createReport(ArrayList<TransHistDay> transHistAll,LocalDateTime date){
        this.date = date;
        for(TransHistDay x: transHistAll){//Find specific date
            if(x.getDate().truncatedTo(ChronoUnit.DAYS).isEqual(date.truncatedTo(ChronoUnit.DAYS))) { //truncate time component of LocalDateTime
                transHist.add(x); // Add the matching entry to the local arraylist of days of transhist
                break;
            }
        }
        generateReport();
    }

    /**
     * Formats and prints the sales report, displaying the quantity of all items sold and the total revenue for the period
     */
    public void printReport(){
        double tempSum;
        double fullSum = 0;
        System.out.println();
        System.out.println("=".repeat(73));
        System.out.printf("Summary of sales on %s: \n", date);
        System.out.println("-".repeat(73));
        System.out.printf(" %-30s| %-8s| %-7s| %-10s\n","ITEM","PRICE","QTY","REVENUE");
        System.out.println("-".repeat(73));
        for (TransHistItem x : this.summaryList) {
            tempSum = x.getPrice() * x.getQuantity();
            fullSum += tempSum;
            System.out.printf(" %-30s| %-8.2f| %-7d| %-10.2f\n",
                    x.getItem(), x.getPrice(), x.getQuantity(), tempSum);
        }
        System.out.println("-".repeat(73));
        System.out.printf("TOTAL REVENUE FOR DAY: %.2f\n", fullSum);
        System.out.println("=".repeat(73));
    }

    /**
     * Checks if item exists in summary list of this Sales report
     * @param name Name of item to be checked in summaryList of salesRevenueReport
     * @param price Price of item to be checked in summaryList of salesRevenueReport
     * @return Returns the TransHistItem that matches the string name and price, null if it doesnt exist in the list
     */
    public TransHistItem itemExists(String name, double price){
        for(TransHistItem x: this.summaryList){
            if(x.getItem().equals(name)  && x.getPrice() == price){
                return x;
            }
        }
        return null;
    }

    /**
     * Generates this sales revenue report for the period by consolidating all the TransHistItems
     * If item already exists in summary list, added to the quantity
     * If not, method creates a new TransHistItem to store the record
     */
    public void generateReport(){
        TransHistItem temp;
        for(TransHistDay t: this.transHist){
            for(TransHistItem i: t.getTransList()){
                temp = itemExists(i.getItem(),i.getPrice()); // Checking if item already exists
                if(temp == null){//Item doesnt exist
                    this.summaryList.add(new TransHistItem(i.getItem(),i.getQuantity(),i.getPrice()));
                }else{
                    temp.setQuantity(i.getQuantity());
                }
            }
        }
        printReport();
    }

}

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
    public void createReport(ArrayList<TransHistDay> transHistAll,LocalDateTime startDate,LocalDateTime endDate){
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
