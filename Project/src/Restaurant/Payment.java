package Restaurant;

import java.util.Scanner;
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
}

class CashPayment extends Payment {
    private double cashPaid = 0;
    Scanner sc = new Scanner(System.in);

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
        System.out.println("Payment by cash \nTotal: " + this.total);
        while (cashPaid < this.total){
            System.out.println("Cash received: ");
            cashPaid += sc.nextDouble();
            if (cashPaid < this.total){
                System.out.println("")
            }
        }



    }
}

