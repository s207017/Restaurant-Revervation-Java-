package Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

public class PaymentUI {
    private CashPayment payByCash = null;
    private Payment payment = null;
    private Restaurant r;
    private Membership m;
    private ArrayList<TransHistDay> transHistDayArrayList;
    private Staff s;

    /**
     *
     * @param r -> Restaurant which is inputted in main -> Gain access to table info
     * @param m -> Membership which is inputted in main -> Gain access to members list to check for membership
     * @param transHistDayArrayList -> transHistDayArrayList which is inputted in main -> to push all order items into the transaction history list
     */
    public PaymentUI(Restaurant r, Membership m, ArrayList<TransHistDay> transHistDayArrayList, Staff s) {
        this.r = r;
        this.m = m;
        this.transHistDayArrayList = transHistDayArrayList;
        this.s = s;
    }

    /**
     * 1. Ask for payment method.
     * 2. Ask for table(s) to pay for
     * 3. Show total price
     * 4. Ask if member
     *      - If yes and customer is indeed a member -> apply discount
     *      - If no or customer is not a member when checked -> Ask for sign up for membership
     *          - If yes -> create membership and apply discount
     *          - If no -> no discount
     * 5. Receive payment
     *      - If payment fails -> terminate method.
     *      - If payment succeeds -> show change (cash payment), print receipt and push orderitems into transaction history.
     */
    public boolean selectPaymentMethod() {
        int choice;
        System.out.print("Payment by? \n(1) Cash \n(2) Others \n*ENTER -1 TO EXIT\nEnter option: ");
        do {
            choice = GetInput.getInt();
            switch (choice) {
                case 1:
                    this.payByCash = new CashPayment();
                    this.payment = this.payByCash;
                    return true;
                case 2:
                    this.payment = new Payment();
                    return true;
                case -1:
                    System.out.println("Terminating payment...");
                    return false;
                default:
                    System.out.print("Please enter a valid option: ");
                    continue;
            }
        } while (true);
    }

    public boolean selectTable() {
        int choice;
        System.out.println(r);
        System.out.print("*ENTER -1 WHEN DONE\nSelect table number(s) for payment: ");
        choice = GetInput.getInt();
        while (choice != -1) {
            Table t = (this.r.getTableFromTableNum(choice));
            if (t == null) { //not a valid table input
                System.out.print("Invalid table number, please try again: ");
                choice = GetInput.getInt();
            } else if (t.getTableStatus() != Table.Level.OCCUPIED || t.getOrder() == null) { //not occupied or no order
                System.out.print("No order or table is unoccupied, please try again: ");
                choice = GetInput.getInt();
            } else {
                this.payment.addTable(this.r.getTableFromTableNum(choice));
                System.out.println("Table " + choice + " selected");
                System.out.print("*ENTER -1 WHEN DONE\nSelect another table number for payment: ");
                choice = GetInput.getInt();
            }
        }
        if (this.payment.getTables().isEmpty()) {
            System.out.println("Terminating payment...");
            return false;
        }
        return true;
    }

    public void showAmount() {
        this.payment.calculateSubTotal();
        this.payment.calculateTax();
        System.out.printf("Sub-total: %.2f \nTax: %.2f \nTotal: %.2f \n",this.payment.getSubTotal(),this.payment.getTax(),(this.payment.getSubTotal() + this.payment.getTax()));
    }

    public void checkMembership() throws IOException {
        int isMember = 0;
        int phoneNumber = -1;
        char selection;
        System.out.print("Is customer a member? [Y/N] ");
        selection = GetInput.getChar();
        while (selection != 'y' && selection != 'Y' && selection != 'n' && selection != 'N') {
            System.out.println("Please enter a valid option: ");
            selection = GetInput.getChar();

        }
        if (selection == 'y' || selection == 'Y') {
            System.out.print("Please enter phone number: ");
            phoneNumber = GetInput.getInt();
            while (phoneNumber < 80000000 || phoneNumber > 99999999) {
                System.out.print("*ENTER -1 TO END\nPlease enter a valid phone number: ");
                phoneNumber = GetInput.getInt();
                if (phoneNumber == -1) {
                    break;
                }
            }
            isMember = m.checkMembership(phoneNumber);
            if (isMember == 0) {
                System.out.println("Customer is not a member");
            } else {
                this.payment.applyDiscount();
                System.out.println("Customer is a member, discount applied!");
                System.out.printf("Sub-total: %.2f \nTax: %.2f \nDiscount: %.2f \nTotal: %.2f \n",this.payment.getSubTotal(),this.payment.getTax(),this.payment.getDiscountApplied(),(this.payment.getSubTotal() + this.payment.getTax() - this.payment.getDiscountApplied()));
            }
        }
        if (isMember == 0) {
            System.out.print("Apply for membership? [Y/N]: ");
            selection = GetInput.getChar();
            if (selection == 'y' || selection == 'Y') {
                System.out.print("Enter customer's mobile number: ");
                this.m.addMember(new Member(GetInput.getInt()));
                this.payment.applyDiscount();
                System.out.println("Customer is now a member, discount applied");
                System.out.printf("Sub-total: %.2f \nTax: %.2f \nDiscount: %.2f \nTotal: %.2f \n",this.payment.getSubTotal(),this.payment.getTax(),this.payment.getDiscountApplied(),(this.payment.getSubTotal() + this.payment.getTax() - this.payment.getDiscountApplied()));
            }
        }
    }

    public void makePayment() throws IOException {
        char selection;
        System.out.println();
        System.out.print("Payment received? [Y/N]: ");
        selection = GetInput.getChar();
        while (selection != 'y' && selection != 'Y') {
            System.out.print("Payment not yet received.\nPayment received? [Y/N]: ");
            selection = GetInput.getChar();
            if (selection == 'n' || selection == 'n') {
                System.out.println("PAYMENT FAILED");
                return;
            }
        }
        if (this.payment instanceof CashPayment) {
            System.out.print("Cash received: ");
            double cashReceived = GetInput.getDouble();
            while (cashReceived < this.payment.getSubTotal() + this.payment.getTax() - this.payment.getDiscountApplied()) {
                System.out.print("*ENTER -1 TO END\nInsufficient amount.\nCash received: ");
                cashReceived = GetInput.getDouble();
                if (cashReceived == -1) {
                    System.out.println("PAYMENT FAILED");
                    return;
                }
            }
            this.payByCash = (CashPayment) this.payment;
            this.payByCash.setCashPaid(cashReceived);
            System.out.printf("Change: %.2f\n",this.payByCash.getChange());
            this.payment = this.payByCash;
        }
        System.out.println("PAYMENT SUCCESS!");
        addToHistory();
    }

    public void generateReceipt(int staffID) {
        Receipt newReceipt = new Receipt(this.payment);
        System.out.println();
        System.out.println();
        newReceipt.printReceipt(staffID);
    }

    public void addToHistory() throws IOException {
        this.payment.pushItemsToHistory(transHistDayArrayList);
    }

    /**
     * if select payment method is successful, ie payment is not terminated (when -1 is entered)
     * this function will continue with the rest of the payment functions
     * else, return to main function
     */
    public void makePaymentUI() throws IOException {
        if(selectPaymentMethod() && selectTable()){
            showAmount();
            checkMembership();
            makePayment();
            generateReceipt(s.getStaffID());
            for(Table t: payment.getTables()){
                t.freeTable();
            }
        }
        else{
            return;
        }
    }
}
