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
    public void startPayment(){
        int choice;
        String selection;
        int phoneNumber = -1;
        boolean isMember = false;

        System.out.println("*ENTER -1 TO TERMINATE*\nPayment by\n1. Cash\n2. Others\nInput: ");
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
        System.out.println("*ENTER -1 WHEN DONE*\nSelect table number(s) for payment.");
        //print out all tables//
        while (choice != -1) {
            Table t = (this.r.getTableFromTableNum(choice));
            if (t == null) {
                System.out.println("Invalid table number, please try again.");
            }else {
                this.payment.addTable(this.r.getTableFromTableNum(choice));
            }
        }
        this.payment.calculateSubTotal();
        this.payment.calculateTax();
        System.out.println("Sub-total: " + this.payment.getSubTotal() + "\nTax: " + this.payment.getSubTotal() +"\nTotal: " + (this.payment.getSubTotal()+this.payment.getTax()));
        System.out.print("Is customer a member? Y/N");
        selection = GetInput.getString();
        while (selection != "y" || selection != "Y" || selection != "n" || selection != "N"){
            System.out.print("Please enter a valid option: ");
            selection = GetInput.getString();
        }
        if (selection == "y" || selection == "Y") {
            System.out.print("Please enter phone number: ");
            phoneNumber = GetInput.getInt();
            while (phoneNumber < 9000000 || phoneNumber > 99999999) {
                System.out.print("*ENTER -1 TO END*\nPlease enter a valid phone number: ");
                phoneNumber = GetInput.getInt();
                if (phoneNumber == -1) {
                    break;
                }
            }
            isMember = m.checkMembership(phoneNumber);
            if (isMember == false) {
                System.out.println("Customer is not a member");
            }else {
                this.payment.applyDiscount();
                System.out.println("Customer is a member, discount applied!");
                System.out.println("Sub-total: " + this.payment.getSubTotal() + "\nTax: " + this.payment.getSubTotal() +"\nTotal: " + (this.payment.getSubTotal()+this.payment.getTax()));
            }
        }
        if (isMember == false){
            System.out.println("Apply for membership? Y/N");
            selection = GetInput.getString();
            if (selection == "y" || selection == "Y") {
                System.out.print("Enter customer's mobile number");
                this.m.addMember(new Member(GetInput.getInt()));
                this.payment.applyDiscount();
                System.out.println("Customer is now a member, discount applied");
                System.out.println("Sub-total: " + this.payment.getSubTotal() + "\nTax: " + this.payment.getSubTotal() +"\nTotal: " + (this.payment.getSubTotal()+this.payment.getTax()));
            }
        }
        System.out.println("Payment received? Y/N ");
        selection = GetInput.getString();
        while (selection == "y" || selection == "Y"){
            System.out.println("Payment not yet received.\nPayment received? Y/N ");
            selection = GetInput.getString();
            if (selection == "n" || selection == "N"){
                System.out.println("PAYMENT FAILED");
                return;
            }
        }
        if (this.payment instanceof CashPayment) {
            System.out.print("Cash received: ");
            double cashReceived = GetInput.getDouble();
            while (cashReceived < this.payment.getSubTotal()+this.payment.getTax()) {
                System.out.print("*ENTER -1 TO END*Insufficient amount.\nCash received: ");
                cashReceived = GetInput.getDouble();
                if (cashReceived == -1){
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
        Receipt newReceipt = new Receipt(this.payment);
        newReceipt.printReceipt();
        this.payment.pushItemsToHistory(transHistDayArrayList);
        return;
    }
}
