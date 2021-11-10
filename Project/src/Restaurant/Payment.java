package Restaurant;

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

    public void calculateSubTotal(){
        int sum = 0;
        for(int i = 0; i < tables.size();i++){
            sum += tables.get(i).getOrder().getTotal();
        }
        this.subtotal = sum;
    }

    public void calculateTax(){
        this.tax = this.subtotal * 0.01177;
    }


    public double getSubTotal(){
        return subtotal;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public double getTax() {
        return tax;
    }

    public void applyDiscount(){
        this.discountApplied = 0.1 * this.subtotal + 0.1 * this.tax;
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
        System.out.println("Pushing items to history");
        System.out.println("Current orders date: " + this.tables.get(0).getOrder().getDate().truncatedTo(ChronoUnit.DAYS));
        System.out.println("Date of last transaction day entry:  " + TransHist.get(TransHist.size()-1).getDate().truncatedTo(ChronoUnit.DAYS));
        if(TransHist.size()==0){//checking if list is empty
            System.out.println("List is empty, first entry ever");
            System.out.printf("Date of entry is %s\n",this.tables.get(0).getOrder().getDate());
            TransHist.add(new TransHistDay(this.tables.get(0).getOrder().getDate()));
            System.out.println("Trans history added");
        }
        else if(!this.tables.get(0).getOrder().getDate().truncatedTo(ChronoUnit.DAYS).isEqual(TransHist.get(TransHist.size()-1).getDate().truncatedTo(ChronoUnit.DAYS))){//Check if day exists
            System.out.println("First entry of the day in non empty list");
            TransHist.add(new TransHistDay(this.tables.get(0).getOrder().getDate()));
        }
        //TransHistDay is available for putting in of values
        for(Table t: tables){
            System.out.printf("Checking table %d\n",t.getTableNum());
            for(OrderItem o: t.getOrder().getOrderItemList()){
                System.out.printf("Checking order %s\n",o.getItem().getItemName());
                //temp is set to the latest TransHistDays transhistitemm that matches the current order items name
                TransHistItem temp = TransHist.get(TransHist.size()-1).findTransHist(o.getItem().getItemName(),o.getItem().getPrice());
                if(temp == null){//If item does not exist yet, create a new slot for the item
                    System.out.println("Curry rice doesnt exist");
                    TransHist.get(TransHist.size()-1).getTransList().add(new TransHistItem(o.getItem().getItemName(),o.getQuantityOrdered(),o.getItem().getPrice()));
                    System.out.println("Latest entry in list is " );
                    for(TransHistDay x: TransHist){
                        System.out.println(x.getDate());
                        for(TransHistItem y: x.getTransList()){
                            System.out.println(y.getItem());
                        }
                    }
                }else{//Item already exists, can just add to the existing slot
                    System.out.println("Curry rice exists, adding quantity");
                    temp.setQuantity(o.getQuantityOrdered());
                }
            }
        }
    }
}

class CashPayment extends Payment {
    private double cashPaid = 0;

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
        return this.cashPaid-(this.subtotal+this.tax-this.discountApplied);
    }

}


