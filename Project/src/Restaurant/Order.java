<<<<<<< Updated upstream
package Restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Scanner;

public class Order {
    private int orderID;
    private int staffID;
    private int tableNum;
    private Timestamp timestamp;
    private Date date;
    private double total;
    private ArrayList<OrderItem> orderList;

    public Order(int orderID, int staffID, int tableNum) {
        this.orderID = orderID;
        this.staffID = staffID;
        this.tableNum = tableNum;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.date = new Timestamp(date.getTime());
        this.orderList = new ArrayList<>();
    }
    public double getTotal(){
        return this.total;
    }
    public int checkItemExistence(int itemID,Menu menu){
        MenuItem temp;
        for(int i = 0;i<orderList.size();i++){
            if(orderList.get(i).getMenuItem() == menu.getMenuItemFromID(itemID)){
                return i;
            }
        }
        return -1;
    }
    public void addOrderItems(Menu menu){
        menu.printMenu();
        Scanner sc = new Scanner(System.in);
        MenuItem temp;
        int choice = 0, quantity = 0;
        while(choice != -1) {
            System.out.print("Enter ID of intended item to be ordered (-1 to end): ");
            choice = sc.nextInt();
            if(choice == -1) break;
            temp = menu.getMenuItemFromID(choice);
            if(temp == null){
                System.out.println("Item does not exist, please enter valid ID.");
                continue;
            }
            System.out.print("Enter quantity of items to be ordered: ");
            quantity = sc.nextInt();
            int index = checkItemExistence(choice,menu);
            if(index < 0) {//Item does not exist in order yet; create new orderitem
                orderList.add(new OrderItem(temp, quantity));
            }else{//Item already exists in order; update the quantity
                orderList.get(index).addQuantityOrdered(quantity);
            }
            this.total += (temp.getPrice() * quantity);
        }
    }
    public void removeOrderItems(Menu menu){
        menu.printMenu();
        MenuItem temp;
        Scanner sc = new Scanner(System.in);
        int choice = 0,quantity = 0;
        while(choice != -1){
            System.out.print("Enter ID of intended item to be removed: ");
            choice = sc.nextInt();
            if(choice == -1) break; // End of order removal
            temp = menu.getMenuItemFromID(choice);
            if(temp == null){ //Item ID is invalid
                System.out.println("Item does not exist, please enter valid ID.");
                continue;
            }
            int index = checkItemExistence(choice,menu);
            if(index<0){ // Order does not contain item
                System.out.println("Item does not exist in order");
                continue;
            }
            System.out.print("Enter quantity to be removed: ");
            quantity = sc.nextInt();
            while(quantity > orderList.get(index).getQuantityOrdered()){// Quantity to be removed too high
                System.out.printf("Only %d orders of %s exist.\n",
                        orderList.get(index).getQuantityOrdered(),
                        orderList.get(index).getMenuItem().getItemName());
                System.out.print("Enter quantity to be removed: ");
                quantity = sc.nextInt();
            }
            orderList.get(index).subtractQuantityOrdered(quantity);
        }
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
    public Date getDate(){
        return date;
    }
    public ArrayList<OrderItem> getOrderItemList(){
        return this.orderList;
    }
}
=======
//package Restaurant;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.text.SimpleDateFormat;
//
//public class Order {
//    private int orderID;
//    private int staffID;
//    private int tableNum;
//    //orderItems
//    private Timestamp timestamp;
//    private double total;
//
//    public Order(int orderID, int staffID, int tableNum) {
//        this.orderID = orderID;
//        this.staffID = staffID;
//        this.tableNum = tableNum;
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//    }
//    public double getTotal(){
//        return this.total;
//    }
//
//
//    public int checkItemExistence(int itemID,Menu menu){
//        MenuItem temp;
//        for(int i = 0;i<orderList.size();i++){
//            if(orderList.get(i).getMenuItem() == menu.getMenuItemFromID(itemID)){
//                return i;
//            }
//        }
//        return -1;
//    }
//    public void addOrderItems(Menu menu){
//        menu.printMenu();
//        Scanner sc = new Scanner(System.in);
//        MenuItem temp;
//        int choice = 0, quantity = 0;
//        while(choice != -1) {
//            System.out.print("Enter ID of intended item to be ordered (-1 to end): ");
//            choice = sc.nextInt();
//            if(choice == -1) break;
//            temp = menu.getMenuItemFromID(choice);
//            if(temp == null){
//                System.out.println("Item does not exist, please enter valid ID.");
//                continue;
//            }
//            System.out.print("Enter quantity of items to be ordered: ");
//            quantity = sc.nextInt();
//            int index = checkItemExistence(choice,menu);
//            if(index < 0) {//Item does not exist in order yet; create new orderitem
//                orderList.add(new OrderItem(temp, quantity));
//            }else{//Item already exists in order; update the quantity
//                orderList.get(index).addQuantityOrdered(quantity);
//            }
//            this.total += (temp.getPrice() * quantity);
//        }
//    }
//    public void removeOrderItems(Menu menu){
//        menu.printMenu();
//        MenuItem temp;
//        Scanner sc = new Scanner(System.in);
//        int choice = 0,quantity = 0;
//        while(choice != -1){
//            System.out.print("Enter ID of intended item to be removed: ");
//            choice = sc.nextInt();
//            if(choice == -1) break; // End of order removal
//            temp = menu.getMenuItemFromID(choice);
//            if(temp == null){ //Item ID is invalid
//                System.out.println("Item does not exist, please enter valid ID.");
//                continue;
//            }
//            int index = checkItemExistence(choice,menu);
//            if(index<0){ // Order does not contain item
//                System.out.println("Item does not exist in order");
//                continue;
//            }
//            System.out.print("Enter quantity to be removed: ");
//            quantity = sc.nextInt();
//            while(quantity > orderList.get(index).getQuantityOrdered()){// Quantity to be removed too high
//                System.out.printf("Only %d orders of %s exist.\n",
//                        orderList.get(index).getQuantityOrdered(),
//                        orderList.get(index).getMenuItem().getItemName());
//                System.out.print("Enter quantity to be removed: ");
//                quantity = sc.nextInt();
//            }
//            orderList.get(index).subtractQuantityOrdered(quantity);
//        }
//    }
//    public boolean isOrderCompleted(){
//        for(int i = 0;i < orderList.size();i++){
//            if(orderList.get(i).getQuantityCompleted() < orderList.get(i).getQuantityOrdered()){
//                return false;
//            }
//        }
//        return true;
//    }
//    public int getOrderID(){
//        return this.orderID;
//    }
//    public int getStaffID(){
//        return this.staffID;
//    }
//    public int getTableNum(){
//        return this.tableNum;
//    }
//
//    public ArrayList<OrderItem> getOrderList() {
//        return orderList;
//    }
//}
>>>>>>> Stashed changes
