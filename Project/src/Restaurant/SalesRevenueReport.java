package Restaurant;

import java.util.ArrayList;
import java.time.LocalDateTime;

// When you want a report you will create a new object of this class
//Sales report consists of: Total sales, Total quantity of each item sold

public class SalesRevenueReport {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ArrayList<TransHistDay> transHist;
    private ArrayList<TransHistItem> summaryList;

    public SalesRevenueReport(ArrayList<TransHistDay> transHistAll, int choice){
        transHist = new ArrayList<TransHistDay>();
        summaryList = new ArrayList<TransHistItem>();
        do {
            if(choice == 1) {//Creating period report
                System.out.println("-ENTERING START DATE OF REPORT-");
                this.startDate = GetPeriod.getDate();
                System.out.println("-ENTERING END DATE OF REPORT-");
                this.endDate = GetPeriod.getDate();
                if (endDate.isBefore(startDate)) {
                    System.out.printf("End date (%s) is before start date (%s), please try again\n", endDate, startDate);
                }
            }
            else if(choice == 2){//Creating a day sales report
                this.startDate = GetPeriod.getDate();
                this.endDate = startDate.plusHours(23);
            }
        }while(endDate.isBefore(startDate) && endDate.isEqual(startDate));
        switch(choice){
            case 1://period report
                for(TransHistDay x: transHistAll){//Narrow down to intended dates
                    if((startDate.isBefore(x.getDate()) && endDate.isAfter(x.getDate())) || startDate.isEqual(x.getDate())){//Check if transHistDay is for that date
                        transHist.add(x); // Add the matching entry to the local arraylist of days of transhist
                    }
                    if(endDate.isBefore(x.getDate())){
                        break;
                    }
                }
                break;
            case 2://day report
                for(TransHistDay x: transHistAll){//Find specific date
                    if(startDate.isBefore(x.getDate()) && endDate.isAfter(x.getDate())){//Check if transHistDay is for that date
                        transHist.add(x); // Add the matching entry to the local arraylist of days of transhist
                        break;
                    }
                }
                break;
        }
        generateReport(choice);
    }

    /**
     *
     * @param choice 1 and 2 indicate period and daily summary respectively
     */
    public void printReport(int choice){
        double tempSum;
        double fullSum = 0;
        switch(choice) {
            case 1://FOR PERIOD REPORT
                System.out.println();
                System.out.println("=".repeat(62));
                System.out.printf("Summary of sales between %s and %s: \n", startDate,endDate);
                System.out.println("-".repeat(62));
                System.out.printf(" %-30s| %-8s| %-6s| %-10s\n","ITEM","PRICE","QTY","REVENUE");
                System.out.println("-".repeat(62));
                for (TransHistItem x : this.summaryList) {
                    tempSum = x.getPrice() * x.getQuantity();
                    fullSum += tempSum;
                    System.out.printf(" %-30s| %-8.2f| %-6d| %-10.2f\n",
                            x.getItem(), x.getPrice(), x.getQuantity(), tempSum);
                }
                System.out.println("-".repeat(62));
                System.out.printf("TOTAL REVENUE FOR PERIOD: %.2f\n", fullSum);
                System.out.println("=".repeat(62));
                break;
            case 2:// FOR  DAY REPORT
                System.out.println();
                System.out.println("=".repeat(62));
                System.out.printf("Summary of sales on %s: \n", startDate);
                System.out.printf(" %-30s| %-8s| %-6s| %-10s\n","ITEM","PRICE","QTY","REVENUE");
                System.out.println("-".repeat(62));
                for (TransHistItem x : this.summaryList) {
                    tempSum = x.getPrice() * x.getQuantity();
                    fullSum += tempSum;
                    System.out.printf(" %-30s| %-8.2f| %-6d| %-10.2f\n",
                            x.getItem(), x.getPrice(), x.getQuantity(), tempSum);
                }
                System.out.println("-".repeat(62));
                System.out.printf("TOTAL REVENUE FOR DAY: %.2f\n", fullSum);
                System.out.println("=".repeat(62));
                break;
        }
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

    public void generateReport(int choice){
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
        printReport(choice);
    }

}
