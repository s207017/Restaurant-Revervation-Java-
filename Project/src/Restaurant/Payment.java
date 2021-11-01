package Restaurant;

import java.util.ArrayList;

public abstract class Payment {
    private ArrayList<Table> tables;
    private double subtotal;
    private double tax;
    public Payment(){
        this.tables = new ArrayList<>();
        this.subtotal = 0;
    }
    public double getSubTotal(){
        int sum = 0;
        for(int i = 0; i < tables.size();i++){
            sum += tables.get(i).getOrder().getTotal();
        }
        this.subtotal = sum;
        return sum;
    }
    public double calculateTax(){
        this.tax = this.subtotal * 1.177;
        return this.tax;
    }
    public abstract void makePayment();
}

public class CashPayment extends Payment{

}


