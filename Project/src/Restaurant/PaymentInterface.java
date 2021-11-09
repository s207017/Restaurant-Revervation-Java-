package Restaurant;

import java.util.ArrayList;

public class PaymentInterface {
    private CashPayment payByCash = null;
    private Payment payment = null;
    private Restaurant r;
    private Membership m;
    private ArrayList<TransHistDay> transHistDayArrayList;

    /**
     *
     * @param r -> Restaurant which is inputted in main -> Gain access to table info
     * @param m -> Membership which is inputted in main -> Gain access to members list to check for membership
     * @param transHistDayArrayList -> transHistDayArrayList which is inputted in main -> to push all order items into the transaction history list
     */
    public PaymentInterface(Restaurant r, Membership m, ArrayList<TransHistDay> transHistDayArrayList) {
        this.r = r;
        this.m = m;
        this.transHistDayArrayList = transHistDayArrayList;
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
    public void selectPaymentMethod() {
        int choice;
        System.out.print("*ENTER -1 TO TERMINATE*\nPayment by\n1. Cash\n2. Others\nInput: ");
        choice = GetInput.getInt();
        switch (choice) {
            case 1:
                this.payByCash = new CashPayment();
                this.payment = this.payByCash;
                break;
            case 2:
                this.payment = new Payment();
                break;
            case -1:
                System.out.println("TERMINATING PAYMENT...");
                return;
            default:
                System.out.println("Please enter a valid selection\n\n*ENTER -1 TO TERMINATE*\\nPayment by\\n1. Cash\\n2. Others\\nInput: \"");
        }
    }

    public void selectTable() {
        int choice;
        System.out.println(r.toString());
        System.out.print("*ENTER -1 WHEN DONE*\nSelect table number(s) for payment: ");
        choice = GetInput.getInt();
        while (choice != -1) {
            Table t = (this.r.getTableFromTableNum(choice));
            if (t == null) {
                System.out.print("Invalid table number, please try again: ");
                choice = GetInput.getInt();
            } else if (t.getTableStatus() != Table.Level.OCCUPIED && t.getOrder() != null) {
                System.out.print("No order, please try again: ");
                choice = GetInput.getInt();
            } else {
                this.payment.addTable(this.r.getTableFromTableNum(choice));
                System.out.println("Table " + choice + " selected");
                System.out.print("*ENTER -1 WHEN DONE*\nSelect another table number for payment: ");
                choice = GetInput.getInt();
            }
        }
        if (this.payment.getTables().isEmpty()) {
            System.out.println("TERMINATING ADDING OF TABLE...");
            return;
        }
    }

    public void showAmount() {
        this.payment.calculateSubTotal();
        this.payment.calculateTax();
        System.out.println("Sub-total: " + this.payment.getSubTotal() + "\nTax: " + this.payment.getSubTotal() + "\nTotal: " + (this.payment.getSubTotal() + this.payment.getTax()));
    }

    public void checkMembership() {
        boolean isMember = false;
        int phoneNumber = -1;
        char selection;
        System.out.print("Is customer a member? Y/N ");
        selection = GetInput.getChar();
        while (selection != 'y' && selection != 'Y' && selection != 'n' && selection != 'N') {
            System.out.println("Please enter a valid option: ");
            selection = GetInput.getChar();

        }
        if (selection == 'y' || selection == 'Y') {
            System.out.print("Please enter phone number: ");
            phoneNumber = GetInput.getInt();
            while (phoneNumber < 80000000 || phoneNumber > 99999999) {
                System.out.print("*ENTER -1 TO END*\nPlease enter a valid phone number: ");
                phoneNumber = GetInput.getInt();
                if (phoneNumber == -1) {
                    break;
                }
            }
            isMember = m.checkMembership(phoneNumber);
            if (isMember == false) {
                System.out.println("Customer is not a member");
            } else {
                this.payment.applyDiscount();
                System.out.println("Customer is a member, discount applied!");
                System.out.println("Sub-total: " + this.payment.getSubTotal() + "\nTax: " + this.payment.getSubTotal() + "\nTotal: " + (this.payment.getSubTotal() + this.payment.getTax()));
            }
        }
        if (isMember == false) {
            System.out.print("Apply for membership? Y/N: ");
            selection = GetInput.getChar();
            if (selection == 'y' || selection == 'Y') {
                System.out.print("Enter customer's mobile number: ");
                this.m.addMember(new Member(GetInput.getInt()));
                this.payment.applyDiscount();
                System.out.println("Customer is now a member, discount applied");
                System.out.println("Sub-total: " + this.payment.getSubTotal() + "\nTax: " + this.payment.getSubTotal() + "\nTotal: " + (this.payment.getSubTotal() + this.payment.getTax()));
            }
        }
    }

    public void makePayment() {
        char selection;
        System.out.println();
        System.out.println("Payment received? Y/N ");
        selection = GetInput.getChar();
        while (selection != 'y' && selection != 'Y') {
            System.out.println("Payment not yet received.\nPayment received? Y/N ");
            selection = GetInput.getChar();
            if (selection != 'n' || selection != 'n') {
                System.out.println("PAYMENT FAILED");
                return;
            }
        }
        if (this.payment instanceof CashPayment) {
            System.out.print("Cash received: ");
            double cashReceived = GetInput.getDouble();
            while (cashReceived < this.payment.getSubTotal() + this.payment.getTax()) {
                System.out.print("*ENTER -1 TO END*\nInsufficient amount.\nCash received: ");
                cashReceived = GetInput.getDouble();
                if (cashReceived == -1) {
                    System.out.println("PAYMENT FAILED");
                    return;
                }
            }
            this.payByCash = (CashPayment) this.payment;
            this.payByCash.setCashPaid(cashReceived);
            System.out.println("Change :" + this.payByCash.getChange());
            this.payment = this.payByCash;
        }
        System.out.println("PAYMENT SUCCESS!");
    }

    public void generateReceipt() {
        Receipt newReceipt = new Receipt(this.payment);
        System.out.println();
        System.out.println();
        newReceipt.printReceipt();
    }

    public void addToHistory(){
        this.payment.pushItemsToHistory(transHistDayArrayList);
    }
}
