package Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

/**
 * Used to manage payment operations.
 */
public class PaymentController {
    /**
     * Instantiates CashPayment object as null.
     * Will be assigned a new CashPayment object when payment by cash is selected.
     */
    private CashPayment payByCash = null;
    /**
     * Instantiates Payment object as null.
     * Will be asssigned a new Payment object when payment type is selected.
     */
    private Payment payment = null;
    /**
     * Declaring a Restaurant reference.
     */
    private Restaurant r;
    /**
     * Declaring a Membership reference.
     */
    private Membership m;
    /**
     * Declaring a TransHistDay Arraylist reference.
     */
    private ArrayList<TransHistDay> transHistDayArrayList;
    /**
     * Declaring a Staff reference.
     */
    private Staff s;

    /**
     * The constructor for PaymentController. Gets Restaurant, Membership, TransHistDay Arraylist, Staff objects from UI
     * and assigns it to Restaurant, Membership, TransHistDay Arraylist, Staff reference in this class.
     * @param r -> Restaurant object -> Gain access to table info
     * @param m -> Membership object -> Gain access to members list to check for membership
     * @param transHistDayArrayList -> transHistDayArrayList object -> to push all order items into the transaction history list
     */
    public PaymentController(Restaurant r, Membership m, ArrayList<TransHistDay> transHistDayArrayList, Staff s) {
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

    /**
     * Asks for table number to select Table object for payment. If the Table object is occupied and has an existing order,
     * adds table to the list of tables for payment. This is done until user inputs -1.
     * @return true if the list of tables for payment is empty after selection. Else, false.
     */
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

    /**
     * Calculates and prints the sub-total and taxes for payment.
     */
    public void showAmount() {
        this.payment.calculateSubTotal();
        this.payment.calculateTax();
        System.out.printf("Sub-total: %.2f \nTax: %.2f \nTotal: %.2f \n",this.payment.getSubTotal(),this.payment.getTax(),(this.payment.getSubTotal() + this.payment.getTax()));
    }

    /**
     * Asks if customer is a member. If, checks if yes, checks if customer is really a member by inputting the customer's phone number.
     * If customer is a member, applies discount.
     * Else and if the customer says no (when asked if he/she is a member), asks if customer would like to be a member.
     * If yes, adds customer as a member using phone number and applies discount.
     * Else, there is no membership discount applied.
     * @throws IOException
     */
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

    /**
     * Asks if payment is received. If yes and payment is by cash, asks for cash received, print out the change and payment goes through.
     * If yes and payment is by others, then payment goes through.
     * If no, asks again and if no is received again, terminates payment.
     * If payment is successful, pushes the order details to the daily transaction history.`
     * @throws IOException
     */
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

    /**
     * Creates new receipt for the payment and prints the receipt.
     * @param staffID
     */
    public void generateReceipt(int staffID) {
        Receipt newReceipt = new Receipt(this.payment);
        System.out.println();
        System.out.println();
        newReceipt.printReceipt(staffID);
    }

    /**
     * Adds order details to the daily transaction history.
     * @throws IOException
     */
    public void addToHistory() throws IOException {
        this.payment.pushItemsToHistory(transHistDayArrayList);
    }

    /**
     * if select payment method is successful, ie payment is not terminated (when -1 is entered)
     * this function will continue with the rest of the payment functions
     * else, return to main function
     */
    public void makePaymentController() throws IOException {
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
