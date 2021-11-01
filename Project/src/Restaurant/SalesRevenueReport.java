package Restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Scanner;

public class SalesRevenueReport {
    private Date startDate;
    private Date endDate;
    private ArrayList<Payment> paymentList;
    public SalesRevenueReport(Date startDate,Date endDate,TransactionHistory TransHist){
        this.startDate = startDate;
        this.endDate = endDate;
        paymentList = new ArrayList<>();
        for(Payment p: TransHist.getArrayList()){

        }

    }
}
