package Restaurant;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Order {
    private int orderID;
    private int staffID;
    private int tableNum;
    //orderItems
    private Timestamp timestamp;
    private double total;

    public Order(int orderID, int staffID, int tableNum) {
        this.orderID = orderID;
        this.staffID = staffID;
        this.tableNum = tableNum;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    }

}
