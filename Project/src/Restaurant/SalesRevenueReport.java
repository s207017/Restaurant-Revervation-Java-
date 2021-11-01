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
        for(Payment p: restaurant.getTransactionHistory){
            if(p.getTables().get(0).getOrder().getDate().compareTo(startDate) >0 ){
                
            }
        }

    }
    public void printReport(){
        for(Payment p: paymentList){
            System.out.println("Period: " + startDate + " to " + endDate);
            System.out.println("-".repeat(90));
            for()
        }
    }
}
