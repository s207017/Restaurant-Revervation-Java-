package Restaurant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.Date;

public class Receipt {
    private Payment payment;
    private ArrayList<Order> orderList = new ArrayList<Order>();

    public Receipt(Payment payment){
        this.payment=payment;
        for (Table t: payment.getTables()){
            this.orderList.add(t.getOrder());
        }
    }

    public void printReceipt(){
        String orderIdList = ""; // to get the list of order ids to be printed later
        String tableList = ""; // to get the list of tables to be printed later

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(); // to get current date and time
        
        // Print address?
        // System.out.println("Server: " +) STAFFID
        System.out.println(String.format("Date/Time: " + dateFormat.format(date)));
        System.out.println();
        System.out.println();

        System.out.print("Order ID: ");
        for (Table table: this.payment.getTables()) {
            orderIdList += table.getOrder().getOrderID() + " ";
            tableList += table.getTableNum() + " ";
        }
        System.out.println("Order ID: " + tableList);
        System.out.println("Order ID: " + orderIdList);

        System.out.printf("%50s\n", "=".repeat(50));

        for (Table table: this.payment.getTables()) {
            for (OrderItem o: table.getOrder().getOrderList()){
                System.out.printf("%2d %35s $%-10f\n", o.getQuantityOrdered(),o.getMenuItem().getItemName(),o.getQuantityOrdered()*o.getMenuItem().getPrice());
            }
        }

        System.out.printf("%50s\n", "=".repeat(50));
        System.out.printf("%-40s $%-8f", "Sub-total:", this.payment.getSubTotal());
        System.out.printf("%-40s $%-8f", "Taxes:", this.payment.getTax());
        System.out.printf("%50s\n", "-".repeat(50));
        System.out.printf("%-40s $%-8f", "TOTAL:", this.payment.getTax()+this.payment.getSubTotal());
        System.out.println();
        System.out.printf("%50s\n", "*".repeat(50));
        System.out.print("        * Thank you for dining with us! *        ");
        System.out.printf("%50s\n", "*".repeat(50));
    }
}
