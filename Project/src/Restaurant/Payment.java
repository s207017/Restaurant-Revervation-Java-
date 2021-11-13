package Restaurant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

/**
 * An instance of this class represents a payment for orders of table(s)
 */
public class Payment {
    /**
     * Reference to Arraylist of Tables declared to hold tables for payment
     */
    protected ArrayList<Table> tables;
    /**
     * Subtotal price of all orders for this payment
     */
    protected double subtotal;
    /**
     * Tax on the total price of the orders for this payment
     */
    protected double tax;
    /**
     * Indicator for whether the payment is complete
     */
    protected boolean paymentComplete;
    /**
     * Discount applied to total price for members
     */
    protected double discountApplied = 0 ;

    /**
     * Constructor instantiates a new ArrayList of tables, sets subtotal to zero and paymentComplete to false
     */
    public Payment(){
        this.tables = new ArrayList<>();
        this.subtotal = 0;
        this.paymentComplete = false;
    }

    /**
     * Gets subtotal of this payment
     * @return Returns subtotal of this payment as a double
     */
    public double getSubTotal(){
        return subtotal;
    }

    /**
     * Gets tables paid for in this payment
     * @return Returns tables that payment is paying for as an ArrayList
     */
    public ArrayList<Table> getTables() {
        return tables;
    }

    /**
     * Gets discount to be applied to payment for members
     * @return Returns discount to be applied as a double
     */
    public double getDiscountApplied() {
        return discountApplied;
    }

    /**
     * Gets tax to be applied to payment
     * @return Returns dollar tax amount to be applied as a double
     */
    public double getTax() {
        return tax;
    }

    /**
     * Gets the payment complete variable to check whether payment is complete
     * @return Returns a boolean variable which indicates if this payment is complete
     */
    public boolean checkPaymentComplete(){
        return this.paymentComplete;
    }

    /**
     * Calculates subtotal price of all the orders from the tables of this payment
     */
    public void calculateSubTotal(){
        int sum = 0;
        for(int i = 0; i < tables.size();i++){
            sum += tables.get(i).getOrder().getTotal();
        }
        this.subtotal = sum;
    }

    /**
     * Calculates dollar tax amount to be applied and sets it to this Payment
     */
    public void calculateTax(){
        this.tax = this.subtotal * 0.01177;
    }

    /**
     * Applies discount to this Payments total
     */
    public void applyDiscount(){
        this.discountApplied = 0.1 * this.subtotal + 0.1 * this.tax;
    }

    /**
     * Adds a table for this payment
     * @param table Table object to be added to this Payment
     */
    public void addTable(Table table){
        this.tables.add(table);
    }

    /**
     * Pushes all items paid for in this payment to transaction history
     * @param TransHist ArrayList of TransHistDay for this Payment transactions to be added to
     * @throws IOException Thrown when reading/writing to text file and IOException error occurs
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
