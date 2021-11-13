package Restaurant;


import org.w3c.dom.ls.LSOutput;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This is an entity class.
 * An instance of this class represents a single receipt.
 */
public class Receipt {
    /**
     * Declaring a payment reference.
     */
    private Payment payment;
    /**
     * Arraylist of orders that are displayed on this receipt.
     */
    private ArrayList<Order> orderList = new ArrayList<Order>();

    /**
     * The constructor for Receipt.
     * @param payment The payment which details are displayed on this receipt.
     */
    public Receipt(Payment payment){
        this.payment=payment;
        for (Table t: payment.getTables()){
            this.orderList.add(t.getOrder());
        }
    }

    /**
     * Prints the following details of the payment: Date and time, Name and address of restaurant, Table number(s), Order ID(s), Staff ID,
     * Details (quantity, name and price) of order items, Sub-total, Taxes, Discounts and Total price.
     * @param staffID The staff ID of the staff who processed the payment detailed in this receipt.
     */
    public void printReceipt(int staffID){
        SetPackage s;
        String orderIdList = ""; // to get the list of order ids to be printed later
        String tableList = ""; // to get the list of tables to be printed later
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now(); // to get current date and time

        // Print address?
        // System.out.println("Server: " +) STAFFID

        System.out.println("-".repeat(50));
        System.out.printf("OOPSIE RESTAURANT%33s\n","63 NANYANG DR");
        System.out.printf("**RECEIPT**%39s\n","SINGAPORE 636922\n");
        System.out.println(String.format("Date/Time: " + dtf.format(date)));
        System.out.println();
        for (Table table: this.payment.getTables()) {
            orderIdList += table.getOrder().getOrderID() + " || ";
            tableList += table.getTableNum() + " || ";
        }
        System.out.println("Table(s): " + tableList);
        System.out.println("Order ID: " + orderIdList);
        System.out.println("Staff ID: " + staffID);

        System.out.printf("%50s\n", "=".repeat(50));
        System.out.printf("%3s  %-35s%10s\n", "QTY", "ITEM(S)", "PRICE($)");
        for (Table table: this.payment.getTables()) {
            for (OrderItem o: table.getOrder().getOrderItemList()){
                if(o.getItem() instanceof SetPackage){
                    s = (SetPackage)o.getItem();
                    System.out.printf("%3d  %-34s %10.2f\n",o.getQuantityOrdered(),s.getItemName(),s.getPrice());
                    for(MenuItem m: s.getSetItems()){
                        System.out.printf("\t- %s\n",m.getItemName());
                    }
                }else {
                    System.out.printf("%3d  %-34s %10.2f\n", o.getQuantityOrdered(), o.getItem().getItemName(), o.getQuantityOrdered() * o.getItem().getPrice());
                }
            }
        }

        System.out.printf("%50s\n", "=".repeat(50));
        System.out.printf("%-40s %9.2f\n", "Sub-total:", this.payment.getSubTotal());
        System.out.printf("%-40s %9.2f\n", "Taxes:", this.payment.getTax());
        System.out.printf("%-40s %9.2f\n", "Discount Applied:", this.payment.getDiscountApplied());
        System.out.printf("%50s\n", "-".repeat(50));
        System.out.printf("%-40s %9.2f", "TOTAL:", this.payment.getTax()+this.payment.getSubTotal()-this.payment.getDiscountApplied());
        System.out.println("");
        System.out.printf("%50s\n", "*".repeat(50));
        System.out.print("        * Thank you for dining with us! *        \n");
        System.out.printf("%50s\n", "*".repeat(50));
        System.out.printf("%50s\n", "-".repeat(50));
    }
}
