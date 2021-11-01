package Restaurant;

import java.util.ArrayList;


public class Payment {
    protected ArrayList<Table> tables;
    protected double total;
    protected double tax;
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

class CashPayment extends Payment {
    private double cashPaid;

    public void setCashPaid(double cashPaid) {
        this.cashPaid = cashPaid;
    }

    public double getCashPaid() {
        return cashPaid;
    }

    public double getchange() {
        return this.cashPaid-this.total;
    }

    public void makePayment() {
        System.out.println("Payment by cash \nTotal:")
    }
}

