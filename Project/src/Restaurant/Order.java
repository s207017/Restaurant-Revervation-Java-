package Restaurant;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Scanner;

public class Order {
    private int orderID;
    private int staffID;
    private int tableNum;
    private LocalDateTime date;
    private double total;
    private ArrayList<OrderItem> orderList;
    private static int orderNum = 1;

    public Order(int staffID, int tableNum) {
        this.orderID = orderNum++;
        this.staffID = staffID;
        this.tableNum = tableNum;
        this.date = LocalDateTime.now();
        this.orderList = new ArrayList<>();
    }
    public double getTotal(){
        return this.total;
    }
    public int checkItemExistence(int itemID,Menu menu){
        MenuItem temp;
        for(int i = 0;i<orderList.size();i++){
            if(orderList.get(i).getItem() == menu.getMenuItemFromID(itemID)){
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
            }else if(temp.getItemID() > 500){
                menu.printDrink();
                while(choice <=500 || choice > 600){
                    System.out.print("Enter of drink (ID more than 500): ");
                    choice = sc.nextInt();
                }
                ((SetPackage) temp).addDrink(menu.getMenuItemFromID(choice));
            }
            if(500<choice && choice<=500+ menu.getSetPackageItems().size()){
                MenuItem set = temp;
                SetPackage tempSetPackage = new SetPackage(set.getItemName(),set.getItemID(),set.getPrice(),set.getDescription());
                menu.printDrinkLTEPrice(tempSetPackage.getMaxDrinkPrice()); 
                System.out.print("Please select drink: ");
                int drinkID = sc.nextInt();
                while (300>=drinkID && drinkID>300+ menu.getDrinkItems().size()){
                    System.out.print("Invalid ID, please try again: ");
                    drinkID = sc.nextInt();
                }
                // add drink item to items array in setpackage
                tempSetPackage.addSide(menu.getMenuItemFromID(drinkID));
                temp = tempSetPackage; //upcasting
            }
            System.out.print("Enter quantity of items to be ordered: ");
            quantity = sc.nextInt();
            while(quantity<=0){
                System.out.print("Invalid quantity! Please enter valid entry: ");
                quantity = sc.nextInt();
            }
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
        printOrder();
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
            while(quantity <=0){
                System.out.print("Please enter a valid quantity: ");
                quantity = sc.nextInt();
            }
            while(quantity > orderList.get(index).getQuantityOrdered()){// Quantity to be removed too high
                System.out.printf("Only %d orders of %s exist.\n",
                        orderList.get(index).getQuantityOrdered(),
                        orderList.get(index).getItem().getItemName());
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
    public LocalDateTime getDate(){
        return date;
    }
    public ArrayList<OrderItem> getOrderItemList(){
        return this.orderList;
    }

    public void printOrder(){
        System.out.printf("Order: %d for Table Number: %d\n",this.orderID,this.tableNum);
        System.out.println("ID | Item Name | Quantity ");
        for(OrderItem o: this.orderList){
            System.out.printf("%d | %s | %d\n",o.getItem().getItemID(),o.getItem().getItemName(),o.getQuantityOrdered());
        }
    }
}
