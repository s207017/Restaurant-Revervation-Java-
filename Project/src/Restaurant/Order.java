package Restaurant;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Scanner;

public class Order {
    private static int orderNum = 0;
    private int orderID;
    private int staffID;
    private int tableNum;
    private LocalDateTime date;
    private double total;
    private ArrayList<OrderItem> orderList;
    private static Menu menu;

    {orderNum +=1;}
    public Order(int staffID, int tableNum,Menu menu) {
        this.orderID = orderNum;
        this.staffID = staffID;
        this.tableNum = tableNum;
        this.date = LocalDateTime.now();
        this.orderList = new ArrayList<>();
        this.menu = menu;
    }
    public double getTotal(){
        return this.total;
    }

    /**
     *
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
     *
      * @param item Item to be added to the order
     * @param quantity Quantity of item to be added to the order
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
     *
     * @param index Index of menuItem in the orders orderItem arraylist
     * @param quantity Quantity of item to be deleted
     */
    public void removeOrderItems(int index, int quantity){
        this.orderList.get(index).subtractQuantityOrdered(quantity);
    }
    public boolean isOrderCompleted(){
        for(int i = 0;i < orderList.size();i++){
            if(orderList.get(i).getQuantityCompleted() < orderList.get(i).getQuantityOrdered()){
                return false;
            }
        }
        return true;
    }
    public int getOrderID(){
        return this.orderID;
    }
    public int getStaffID(){
        return this.staffID;
    }
    public int getTableNum(){
        return this.tableNum;
    }
    public LocalDateTime getDate(){
        return date;
    }
    public ArrayList<OrderItem> getOrderItemList(){
        return this.orderList;
    }
    public void addTotal(double amount){
        this.total += amount;
    }

    public void printOrder(){
        System.out.printf("Order: %d for Table Number: %d\n",this.orderID,this.tableNum);
        System.out.println("ID | Item Name | Quantity ");
        for(OrderItem o: this.orderList){
            System.out.printf("%d | %s | %d\n",o.getItem().getItemID(),o.getItem().getItemName(),o.getQuantityOrdered());
        }
    }
}
