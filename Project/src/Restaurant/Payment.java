package Restaurant;

import java.util.Scanner;
import java.util.ArrayList;


public class Payment {
    protected ArrayList<Table> tables;
    protected double subtotal;
    protected double tax;
    protected boolean paymentComplete;
    public Payment(){
        this.tables = new ArrayList<>();
        this.subtotal = 0;
        this.paymentComplete = false;
    }

    public void calculateSubTotal(){
        int sum = 0;
        for(int i = 0; i < tables.size();i++){
            sum += tables.get(i).getOrder().getTotal();
        }
        this.subtotal = sum;
    }

    public void calculateTax(){
        this.tax = this.subtotal * 1.177;
    }


    public double getSubTotal(){
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void applyDiscount(){
        this.subtotal = 0.9 * this.subtotal;
        this.tax = 0.9 * this.tax;
    }

    public void addTable(Table table){
        this.tables.add(table);
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public boolean checkPaymentComplete(){
        return this.paymentComplete;
    }

    public void pushItemsToHistory(ArrayList<TransHistDay> TransHist){
        if(this.tables.get(0).getOrder().getDate() != TransHist.get(TransHist.size()-1).getDate()){//Check if day exists
            TransHist.add(new TransHistDay(this.tables.get(0).getOrder().getDate()));
        }
        for(Table t: tables){
            for(OrderItem o: t.getOrder().getOrderItemList()){
                TransHistItem temp = TransHist.get(TransHist.size()).findTransHist(o.getItem().getItemName(),o.getItem().getPrice());
                if(temp == null){//If item does not exist yet, create a new slot for the item
                    TransHist.get(TransHist.size()-1).getTransList().add(new TransHistItem(o.getItem().getItemName(),o.getQuantityOrdered(),o.getItem().getPrice()));
                }else{//Item already exists, can just add to the existing slot
                    temp.setQuantity(o.getQuantityOrdered());
                }
            }
        }
    }
}

class CashPayment extends Payment {
    private double cashPaid = 0;
    Scanner sc = new Scanner(System.in);


    public CashPayment() {
        super();
    }

    public void setCashPaid(double cashPaid) {
        this.cashPaid = cashPaid;
    }

    public double getCashPaid() {
        return cashPaid;
    }

    public double getChange() {
        return this.cashPaid-this.subtotal-this.tax;
    }

}


