package Restaurant;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        SetPackage s=null;
        String orderIdList = ""; // to get the list of order ids to be printed later
        String tableList = ""; // to get the list of tables to be printed later
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now(); // to get current date and time

        // Print address?
        // System.out.println("Server: " +) STAFFID
        System.out.println("RECEIPT \n\n");
        System.out.println(String.format("Date/Time: " + dtf.format(date)));
        System.out.println();
        System.out.println();
        for (Table table: this.payment.getTables()) {
            orderIdList += table.getOrder().getOrderID() + " ";
            tableList += table.getTableNum() + " ";
        }
        System.out.println("Table(s): " + tableList);
        System.out.println("Order ID: " + orderIdList);

        System.out.printf("%50s\n", "=".repeat(50));

        for (Table table: this.payment.getTables()) {
            for (OrderItem o: table.getOrder().getOrderItemList()){
                if(o.getItem() instanceof SetPackage){
                    s = (SetPackage)o.getItem();
                    System.out.printf("%35s %-10f\n",s.getItemName(),s.getPrice());
//                    for(MenuItem m: s.getSetItems()){
//                        System.out.printf("\t- %35s\n",m.getItemName());
//                    }
                }else {
                    System.out.printf("%2d %35s $%-10f\n", o.getQuantityOrdered(), o.getItem().getItemName(), o.getQuantityOrdered() * o.getItem().getPrice());
                }
            }
        }

        System.out.printf("%50s\n", "=".repeat(50));
        System.out.printf("%-40s $%-8f\n", "Sub-total:", this.payment.getSubTotal());
        System.out.printf("%-40s $%-8f\n", "Taxes:", this.payment.getTax());
        System.out.printf("%-40s $%-8f\n", "Discount Applied:", this.payment.getDiscountApplied());
        System.out.printf("%50s\n", "-".repeat(50));
        System.out.printf("%-40s $%-8f", "TOTAL:", this.payment.getTax()+this.payment.getSubTotal()-this.payment.getDiscountApplied());
        System.out.println("");
        System.out.printf("%50s\n", "*".repeat(50));
        System.out.print("        * Thank you for dining with us! *        \n");
        System.out.printf("%50s\n", "*".repeat(50));
    }
}
