package Restaurant;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 * An instance of this object represents one order
 */
public class Order {
    /**
     * Static variable to represent the order number of this order
     * To be used by OrderID
     */
    private static int orderNum = 0;
    /**
     * Order ID of this number
     */
    private int orderID;
    /**
     * Staff ID of the staff in charge of taking this order
     */
    private int staffID;
    /**
     * Table number that this order is assigned to
     */
    private int tableNum;
    /**
     * Date that this order is made
     */
    private LocalDateTime date;
    /**
     * Total price of this order
     */
    private double total;
    /**
     * Reference to ArrayList of order items
     */
    private ArrayList<OrderItem> orderList;
    /**
     * Static menu declared to be used in mnethos of this Order
     */
    private static Menu menu;

    /**
     * Increments orderNum to be assigned to orderID
     */
    {orderNum +=1;}

    /**
     * Constructor takes in staff ID, the table number, and the menu to create an Order
     * @param staffID Staff ID of staff in charge of taking order
     * @param tableNum Table number that this order is assigned to
     * @param menu Menu for reference for this Order
     */
    public Order(int staffID, int tableNum,Menu menu) {
        this.orderID = orderNum;
        this.staffID = staffID;
        this.tableNum = tableNum;
        this.date = LocalDateTime.now();
        this.orderList = new ArrayList<>();
        this.menu = menu;
    }

    /**
     * Gets Order ID of this order
     * @return Returns order ID of this order as an integer
     */
    public int getOrderID(){
        return this.orderID;
    }

    /**
     * Gets Staff ID of staff in charge of this order
     * @return Returns order ID of staff in charge of this order
     */
    public int getStaffID(){
        return this.staffID;
    }

    /**
     * Gets Table number that this order is assigned to
     * @return Returns table number that this order is assigned to as an integer
     */
    public int getTableNum(){
        return this.tableNum;
    }

    /**
     * Gets date of this order
     * @return Returns date of this order as a LocalDateTime object
     */
    public LocalDateTime getDate(){
        return date;
    }

    /**
     * Gets list of order items in this order
     * @return Returns list of order items in this order as an ArrayList
     */
    public ArrayList<OrderItem> getOrderItemList(){
        return this.orderList;
    }

    /**
     * Gets total price of this order
     * @return Returns price of this order as a double
     */
    public double getTotal(){
        return this.total;
    }

    /**
     * Adds to total price of this order
     * @param amount Price to be added to this order as a double
     */
    public void addTotal(double amount){
        this.total += amount;
    }

    /**
     * Checks this order's order item list for the existence of a specific item
     * @param itemID ID of the menuItem to be checked
     * @return Returns the index of the requested item in the orderItem array
     */
    public int checkItemExistence(int itemID){
        MenuItem temp;
        for(int i = 0;i<orderList.size();i++){
            if(orderList.get(i).getItem() == menu.getMenuItemFromID(itemID)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds items to this order
      * @param item Item to be added to this order
     * @param quantity Quantity of item to be added to this order
     */
    public void addOrderItems(MenuItem item, int quantity){
        int index = checkItemExistence(item.getItemID());
        if(index < 0) {//Item does not exist in order yet; create new orderitem
            this.orderList.add(new OrderItem(item, quantity));
        }else{//Item already exists in order; update the quantity
            this.orderList.get(index).addQuantityOrdered(quantity);
        }
        this.total += (item.getPrice() * quantity);
    }

    /**
     * Removes items from order
     * @param index Index of menuItem in the orders orderItem arraylist
     * @param quantity Quantity of item to be deleted
     */
    public void removeOrderItems(int index, int quantity){
        this.orderList.get(index).subtractQuantityOrdered(quantity);
    }

    /**
     * Checks if order is completed
     * @return Boolean that indicates if order is completed
     */
    public boolean isOrderCompleted(){
        for(int i = 0;i < orderList.size();i++){
            if(orderList.get(i).getQuantityCompleted() < orderList.get(i).getQuantityOrdered()){
                return false;
            }
        }
        return true;
    }

    /**
     * Prints order by iterating through its order list getting each item's name and quantity ordered
     */
    public void printOrder(){
        System.out.printf("Order: %d for Table Number: %d\n",this.orderID,this.tableNum);
        System.out.printf("%4s%2s %-38s| %-4s\n","ID","|","ITEM NAME","QTY");
        System.out.println("-".repeat(50));
        for(OrderItem o: this.orderList){
            System.out.printf("%4d%2s %-38s| %-4d\n",o.getItem().getItemID(),"|",o.getItem().getItemName(),o.getQuantityOrdered());
        }
    }
}
