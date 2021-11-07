package Restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Scanner;

public class SalesRevenueReport {
    private Date startDate;
    private Date endDate;
    private ArrayList<Payment> paymentList;
    public SalesRevenueReport(Date startDate,Date endDate,Restaurant restaurant){
        this.startDate = startDate;
        this.endDate = endDate;
        paymentList = new ArrayList<>();
        for(Payment p: restaurant.getTransactionHistory()){
            if(p.getTables().get(0).getOrder().getDate().compareTo(startDate) > 0){
                paymentList.add(p);
            }
            if(p.getTables().get(0).getOrder().getDate().compareTo(endDate) > 0){
                break;
            }
        }

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
