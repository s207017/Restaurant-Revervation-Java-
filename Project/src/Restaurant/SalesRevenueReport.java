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
            if(choice == 1) {
                this.startDate = GetPeriod.getDate();
                this.endDate = GetPeriod.getDate();
                if (endDate.isBefore(startDate)) {
                    System.out.printf("End date (%s) is before start date (%s), please try again\n", endDate, startDate);
                }
            }
            else if(choice == 2){
                this.startDate = GetPeriod.getDate();
                this.endDate = startDate;
            }
        }while(endDate.isBefore(startDate) && endDate.isEqual(startDate));
        for(TransHistDay x: transHistAll){//Narrow down to intended dates
            if(startDate.isBefore(x.getDate()) || startDate.isEqual(x.getDate())){//Check if transHistDay is for that date
                transHist.add(x); // Add the matching entry to the local arraylist of days of transhist
            }
            if(endDate.isBefore(x.getDate())){
                break;
            }
        }
        generateReport();
    }

    public void printReport(int choice){
        double tempSum;
        double fullSum = 0;
        switch(choice) {
            case 1:
                System.out.printf("Summary of sales on %s: \n", startDate);
                for (TransHistItem x : this.summaryList) {
                    tempSum = x.getPrice() * x.getQuantity();
                    fullSum += tempSum;
                    System.out.printf("Item: %s   |   Price: %f   |   Quantity: %d   |   Revenue: %f\n",
                            x.getItem(), x.getPrice(), x.getQuantity(), tempSum);
                }
                System.out.printf("Total revenue: %f\n", fullSum);
                break;
            case 2:
                System.out.printf("Summary of sales between %s and %s: \n", startDate, endDate);
                for (TransHistItem x : this.summaryList) {
                    tempSum = x.getPrice() * x.getQuantity();
                    fullSum += tempSum;
                    System.out.printf("Item: %s   |   Price: %f   |   Quantity: %d   |   Revenue: %f\n",
                            x.getItem(), x.getPrice(), x.getQuantity(), tempSum);
                }
                System.out.printf("Total revenue: %f\n", fullSum);
                break;
        }
    }

    public TransHistItem itemExists(String name, double price){
        for(TransHistItem x: this.summaryList){
            if(x.getItem() == name && x.getPrice() == price){
                return x;
            }
        }
        return null;
    }

    public void generateReport(){
        TransHistItem temp;
        for(TransHistDay t: this.transHist){
            for(TransHistItem i: t.getTransList()){
                temp = itemExists(i.getItem(),i.getPrice());
                if(temp == null){//Item doesnt exist
                    this.summaryList.add(new TransHistItem(i.getItem(),i.getQuantity(),i.getPrice()));
                }else{
                    temp.setQuantity(i.getQuantity());
                }
            }
        }
    }

}
