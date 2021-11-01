package Restaurant;

import java.util.Scanner;
import java.util.ArrayList;


public class Payment {
    protected ArrayList<Table> tables;
    protected double subtotal;
    protected double tax;
    protected Membership membership;
    public Payment(Membership membership){
        this.tables = new ArrayList<>();
        this.subtotal = 0;
        this.membership = membership;
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
}

class CashPayment extends Payment {
    private double cashPaid = 0;
    Scanner sc = new Scanner(System.in);

    public CashPayment(Membership membership) {
        super(membership);
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

    public void makePayment() {
         // System.out.print("Select table(s): "); should be done in the app class -> insert table to arraylist //
        System.out.print("Member? Y/N");
        String choice = sc.next();
        this.calculateSubTotal();
        this.calculateTax();
        switch (choice){
            case "Y":
            case "y":
                System.out.print("Enter customer's mobile number: ");
                boolean check = membership.checkMembership(sc.next());
                if (check){
                    this.applyDiscount();
                    System.out.println("Customer is a member, discount applied");
                    break;
                }else{
                    System.out.println("Customer is not a member, apply for membership? Y/N");
                    choice = sc.next();
                    switch (choice){
                        case "Y":
                        case "y":
                            System.out.print("Enter customer's mobile number");
                            membership.addMember(sc.next());
                            this.applyDiscount();
                            System.out.println("Customer is now a member, discount applied");
                            break;
                        case "N":
                        case "n":
                            break;
                    }
                }
            case "N":
            case "n":
                System.out.println("Customer is not a member, apply for membership? Y/N");
                choice = sc.next();
                switch (choice) {
                    case "Y":
                    case "y":
                        System.out.print("Enter customer's mobile number");
                        membership.addMember(sc.next());
                        this.applyDiscount();
                        System.out.println("Customer is now a member, discount applied");
                        break;
                    case "N":
                    case "n":
                        break;
                }
        }
        System.out.println("Payment by cash \nTotal: " + (this.subtotal+this.tax));
        System.out.print("Cash received: ");
        setCashPaid(sc.nextDouble());
        System.out.print("Payment completed \nChange: " + this.getChange());
    }
}

class CardPayment extends Payment{
    Scanner sc = new Scanner(System.in);

    public CardPayment(Membership membership) {
        super(membership);
    }

    public void makePayment() {
        // System.out.print("Select table(s): "); should be done in the app class -> insert table to arraylist //
        System.out.print("Member? Y/N");
        String choice = sc.next();
        this.calculateSubTotal();
        this.calculateTax();
        switch (choice){
            case "Y":
            case "y":
                System.out.print("Enter customer's mobile number: ");
                boolean check = membership.checkMembership(sc.next());
                if (check){
                    System.out.println("Customer is a member, discount applied");
                    this.applyDiscount();
                    break;
                }else{
                    System.out.println("Customer is not a member, apply for membership? Y/N");
                    choice = sc.next();
                    switch (choice){
                        case "Y":
                        case "y":
                            System.out.print("Enter customer's mobile number");
                            membership.addMember(sc.next());
                            this.applyDiscount();
                            System.out.println("Customer is now a member, discount applied");
                            break;
                        case "N":
                        case "n":
                            break;
                    }
                }
            case "N":
            case "n":
                System.out.println("Customer is not a member, apply for membership? Y/N");
                choice = sc.next();
                switch (choice) {
                    case "Y":
                    case "y":
                        System.out.print("Enter customer's mobile number");
                        membership.addMember(sc.next());
                        this.applyDiscount();
                        System.out.println("Customer is now a member, discount applied");
                        break;
                    case "N":
                    case "n":
                        break;
                }
        }
        System.out.println("Payment by card \nTotal: " + (this.subtotal+this.tax));
        System.out.print("Processing payment..... \nPayment completed");
    }
}

