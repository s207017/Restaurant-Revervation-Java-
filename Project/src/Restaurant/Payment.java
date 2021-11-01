package Restaurant;

import java.util.ArrayList;

public class Payment {
    protected ArrayList<Table> tables;
    protected double total;
    public Payment(){
        this.tables = new ArrayList<>();
        this.total = 0;
    }
    public double getTotal(){
        int sum = 0;
        for(int i = 0; i < tables.size();i++){
            sum += tables.get(i).getOrder().
        }
    }
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

