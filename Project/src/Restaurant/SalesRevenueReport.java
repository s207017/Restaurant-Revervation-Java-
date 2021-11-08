package Restaurant;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
// When you want a report you will create a new object of this class
public class SalesRevenueReport {
    Scanner sc = new Scanner(System.in);
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ArrayList<TransHistDay> transHist;
    public SalesRevenueReport(TransactionHistory transactionHistory){
        do {
            this.startDate = PeriodGetter.getDate();
            this.endDate = PeriodGetter.getDate();
        }while(endDate.isBefore(startDate));
    }
    public void generateReport(){

    }
    /*public void printReport(Menu menu){
        System.out.println("Period: " + startDate + " to " + endDate);
        System.out.println("-".repeat(90));
        for(Payment p: paymentList){
            for(Table t: p.getTables()){
                for(OrderItem o: t.getOrder().getOrderItemList()){
                    for(MenuItem m: menu){

                    }
                }
            }
        }
    }*/
}
