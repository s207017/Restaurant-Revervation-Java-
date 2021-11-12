package Restaurant;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDateTime;

//When you want a report you will create a new object of this class
//Sales report consists of: Total sales, Total quantity of each item sold

public class SalesRevenueDay {
    protected LocalDateTime date;
    protected ArrayList<TransHistDay> transHist;
    protected ArrayList<TransHistItem> summaryList;

    public SalesRevenueDay(){
        this.date = null;
        transHist = new ArrayList<TransHistDay>();
        summaryList = new ArrayList<TransHistItem>();
    }

    public void createReport(ArrayList<TransHistDay> transHistAll){
        this.date = GetPeriod.getDate();
        for(TransHistDay x: transHistAll){//Find specific date
            if(x.getDate().truncatedTo(ChronoUnit.DAYS).isEqual(date.truncatedTo(ChronoUnit.DAYS))) { //truncate time component of LocalDateTime
                transHist.add(x); // Add the matching entry to the local arraylist of days of transhist
                break;
            }
        }
        generateReport();
    }

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
     * This method is only used in the generate() method of this class
     * @param name Name of item to be checked in summaryList of salesRevenueReport
     * @param price Price of item to be checked in summaryList of salesRevenueReport
     * @return
     */
    public TransHistItem itemExists(String name, double price){
        for(TransHistItem x: this.summaryList){
            if(x.getItem().equals(name)  && x.getPrice() == price){
                return x;
            }
        }
        return null;
    }

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

class SalesRevenuePeriod extends SalesRevenueDay{
    private LocalDateTime endDate; //"date" in SalesRevenueDay is the start date for SalesRevenuePeriod

    public SalesRevenuePeriod(){
        super();
    }

    public void createReport(ArrayList<TransHistDay> transHistAll){
        do{
            System.out.println("-ENTERING START DATE OF REPORT-");
            this.date = GetPeriod.getDate();
            System.out.println("-ENTERING END DATE OF REPORT-");
            this.endDate = GetPeriod.getDate();
            if (endDate.isBefore(date)) {
                System.out.printf("End date (%s) is before start date (%s), please try again\n", endDate, date);
            }
        }while(endDate.isBefore(date) && endDate.isEqual(date));
        //add to transHist
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
