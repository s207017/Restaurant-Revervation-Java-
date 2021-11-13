package Restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class Payment {
    protected ArrayList<Table> tables;
    protected double subtotal;
    protected double tax;
    protected boolean paymentComplete;
    protected double discountApplied = 0 ;
    public Payment(){
        this.tables = new ArrayList<>();
        this.subtotal = 0;
        this.paymentComplete = false;
    }

    //getters
    public double getSubTotal(){
        return subtotal;
    }
    public double getDiscountApplied() {
        return discountApplied;
    }
    public double getTax() {
        return tax;
    }
    public ArrayList<Table> getTables() {
        return tables;
    }

    /**
     * calculates sub-total by iterating though the list of tables selected for payment and adding up the total price
     * of each table's order and assigns it to the subtotal attribute.
      */
    public void calculateSubTotal(){
        int sum = 0;
        for(int i = 0; i < tables.size();i++){
            sum += tables.get(i).getOrder().getTotal();
        }
        this.subtotal = sum;
    }

    /**
     * calculates GST and service charge (1.177%) of sub-total
     */
    public void calculateTax(){
        this.tax = this.subtotal * 0.01177;
    }

    /**
     * calculates the membership discount (10% of sub-total and tax)
     */
    public void applyDiscount(){
        this.discountApplied = 0.1 * this.subtotal + 0.1 * this.tax;
    }

    /**
     * adds table to the list of tables for payment.
     * @param table which customer wishes to pay for (as some customers may want to pay for multiple tables at one go)
     */
    public void addTable(Table table){
        this.tables.add(table);
    }

    // DO WE NEED THIS?
    public boolean checkPaymentComplete(){
        return this.paymentComplete;
    }

    /**
     *
     * @param TransHist the list of daily transactions which is updated when payment is completed.
     * @throws IOException
     */
    public void pushItemsToHistory(ArrayList<TransHistDay> TransHist) throws IOException {
        if(TransHist.size()==0){//checking if list is empty
            TransHist.add(new TransHistDay(this.tables.get(0).getOrder().getDate()));
        }
        else if(!this.tables.get(0).getOrder().getDate().truncatedTo(ChronoUnit.DAYS).isEqual(TransHist.get(TransHist.size()-1).getDate().truncatedTo(ChronoUnit.DAYS))){//Check if day exists
            TransHist.add(new TransHistDay(this.tables.get(0).getOrder().getDate()));
        }
        //TransHistDay is available for putting in of values
        for(Table t: tables){
            for(OrderItem o: t.getOrder().getOrderItemList()){
                //temp is set to the latest TransHistDays transhistitemm that matches the current order items name
                TransHistItem temp = TransHist.get(TransHist.size()-1).findTransHist(o.getItem().getItemName(),o.getItem().getPrice());
                if(temp == null){//If item does not exist yet, create a new slot for the item
                    TransHist.get(TransHist.size()-1).getTransList().add(new TransHistItem(o.getItem().getItemName(),o.getQuantityOrdered(),o.getItem().getPrice()));
                }else{//Item already exists, can just add to the existing slot
                    temp.setQuantity(o.getQuantityOrdered());
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/transhistday.txt", false)
        );

        for (TransHistDay transHistDay: TransHist){
            bw.write("new-record\n"+transHistDay.getDate()+"\n");
            for (TransHistItem transHistItem: transHistDay.getTransList()){
                bw.write(transHistItem.getItem()+"\n"+transHistItem.getQuantity()+"\n"+transHistItem.getPrice()+"\n");
            }
        }
        bw.write("last-record\n");
        bw.close();
    }
}

class CashPayment extends Payment {
    private double cashPaid = 0;

    public CashPayment() {
        super();
    }
    //setter
    public void setCashPaid(double cashPaid) {
        this.cashPaid = cashPaid;
    }

    //getter
    public double getCashPaid() {
        return cashPaid;
    }

    /**
     * calculates the change to return to customers who opt for cash payment.
     * @return change to be returned to customer.
     */
    public double getChange() {
        return this.cashPaid-(this.subtotal+this.tax-this.discountApplied);
    }

}


