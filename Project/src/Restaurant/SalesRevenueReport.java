package Restaurant;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
// When you want a report you will create a new object of this class
//Sales report consists of: Total sales, Total quantity of each item sold
public class SalesRevenueReport {
    Scanner sc = new Scanner(System.in);
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ArrayList<TransHistDay> transHist;
    private ArrayList<TransHistItem> summaryList;
    public SalesRevenueReport(ArrayList<TransHistDay> transHistAll){
        transHist = new ArrayList<TransHistDay>();
        summaryList = new ArrayList<TransHistItem>();
        do {
            this.startDate = GetPeriod.getDate();
            this.endDate = GetPeriod.getDate();
            if(endDate.isBefore(startDate)){
                System.out.printf("End date (%s) is before start date (%s), please try again\n",endDate,startDate);
            }
        }while(endDate.isBefore(startDate));
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

    public void printReport(){
        double tempSum;
        double fullSum = 0;
        System.out.printf("Summary of sales between %s and %s: \n",startDate,endDate);
        for(TransHistItem x: this.summaryList){
            tempSum = x.getPrice() * x.getQuantity();
            fullSum += tempSum;
            System.out.printf("Item: %s   |   Price: %f   |   Quantity: %d   |   Revenue: %f\n",
                                x.getItem(),x.getPrice(),x.getQuantity(),tempSum);
        }
        System.out.printf("Total revenue: %f\n",fullSum);
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
