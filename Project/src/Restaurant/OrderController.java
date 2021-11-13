package Restaurant;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Used to manage order operations.
 */
public class OrderController {
    /**
     * Declaring a menu reference.
     */
    private Menu menu;
    /**
     * Declaring a restaurant reference/
     */
    private Restaurant restaurant;

    /**
     * The Constructor for OrderController. Gets restaurant and menu objects from the UI and assigns it to the Restaurant and Menu reference in this class.
     * @param menu The menu object.
     * @param restaurant The restaurant object.
     */
    public OrderController(Menu menu, Restaurant restaurant){
        this.menu = menu;
        this.restaurant = restaurant;
    }

    /**
     * Help
     */
    public void addItemsToOrder(){
        int TableNum;
        System.out.println(restaurant);
        System.out.printf("*ENTER 9 TO EXIT\nEnter table number: ",restaurant.getTableList().size()+1);
        do{
            TableNum = GetInput.getIntFromRange(1,restaurant.getTableList().size()+1);
            if(TableNum == restaurant.getTableList().size()+1){
                System.out.println("Exiting order function...");
                return;
            }
            if(this.restaurant.getTableFromTableNum(TableNum).getTableStatus() != Table.Level.OCCUPIED){
                System.out.printf("*ENTER 9 TO EXIT\nNo one at the table. Enter table number again: ",restaurant.getTableList().size(),restaurant.getTableList().size()+1);
            }
        }while(this.restaurant.getTableFromTableNum(TableNum).getTableStatus() != Table.Level.OCCUPIED);
        Order order= this.restaurant.getTableFromTableNum(TableNum).getOrder();
        if(order == null){
            order = new Order(1234,TableNum,menu);
            restaurant.getTableFromTableNum(TableNum).setOrder(order);
        }else {
            System.out.println("Adding to existing order. Current order:");
            order.printOrder();
        }
        int choice = 0, quantity = 0;
        while(choice != -1) {
            System.out.print("*ENTER -1 TO STOP ORDERING\nEnter menu item ID of intended item to be ordered: ");
            choice = GetInput.getInt();
            if(choice == -1) {
                System.out.println("Exiting ordering function...");
                break;
            }
            //set temp to be the menuItem/setPackage item
            MenuItem temp;
            if(choice>500 && choice <=500+menu.getSetPackageItems().size()) {
                temp = menu.getSetPackageItemFromID(choice);
            }
            else{
                temp = menu.getMenuItemFromID(choice);
            }
            if(temp == null){
                System.out.println("Item does not exist, please enter valid ID.");
                continue;
            }
            else if(500<choice && choice<=500+ menu.getSetPackageItems().size()){
                if (temp instanceof SetPackage){
                    SetPackage tempSetPackage = new SetPackage(temp.getItemName(),temp.getItemID(),temp.getPrice(),temp.getDescription());
                    tempSetPackage.addMainCourse(menu.getMenuItemFromID(((SetPackage) temp).getSetItems().get(0).getItemID()));
                    tempSetPackage.addSide(menu.getMenuItemFromID(((SetPackage) temp).getSetItems().get(1).getItemID()));
                    tempSetPackage.setMaxDrinkPrice(((SetPackage) temp).getMaxDrinkPrice());
                    menu.printDrinkLTEPrice(tempSetPackage.getMaxDrinkPrice());
                    System.out.print("Please select drink: ");
                    int drinkID = GetInput.getIntFromRange(301,300+menu.getDrinkItems().size());
                    while (menu.getMenuItemFromID(drinkID).getPrice()>tempSetPackage.getMaxDrinkPrice()) {
                        System.out.print("The selected drink is not applicable for set packages\nPlease select drink again: ");
                        drinkID = GetInput.getIntFromRange(301,300+menu.getDrinkItems().size());
                    }
                    // add drink item to items array in setpackage
                    tempSetPackage.addSide(menu.getMenuItemFromID(drinkID));
                    temp = tempSetPackage; //upcasting
                }

            }
            System.out.print("Enter quantity of items to be ordered: ");
            quantity = GetInput.getInt();
            while(quantity<=0){
                System.out.print("Invalid quantity! Please enter valid entry: ");
                quantity = GetInput.getInt();
            }
            order.addOrderItems(temp,quantity);
        }
    }
    //Remove items from order
    public void removeItemsFromOrder(){
        Order order = null;
        while(order == null) {
            System.out.print("Enter table number: ");
            int TableNum = GetInput.getInt();
            while (this.restaurant.getTableFromTableNum(TableNum).getTableStatus() != Table.Level.OCCUPIED) {
                System.out.println("No one at the table! Enter again");
                TableNum = GetInput.getInt();
            }
            order = restaurant.getTableFromTableNum(TableNum).getOrder();
            if (order == null) {
                System.out.println("Table has no order! Enter another table");
            }
        }
        order.printOrder();
        MenuItem temp;
        int choice = 0,quantity = 0;
        while(choice != -1){
            System.out.print("*ENTER -1 TO STOP ORDERING\nEnter menu item ID of intended item to be removed: ");
            choice = GetInput.getInt();
            if(choice == -1) break; // End of order removal
            temp = menu.getMenuItemFromID(choice);
            if(temp == null){ //Item ID is invalid
                System.out.println("Item does not exist, please enter valid ID.");
                continue;
            }
            int index = order.checkItemExistence(choice);
            if(index<0){ // Order does not contain item
                System.out.println("Item does not exist in order.");
                continue;
            }
            System.out.print("Enter quantity to be removed: ");
            quantity = GetInput.getInt();
            while(quantity > order.getOrderItemList().get(index).getQuantityOrdered() || quantity<=0){// Quantity to be removed too high
                if(quantity>order.getOrderItemList().get(index).getQuantityOrdered()) {
                    System.out.printf("Only %d orders of %s exist.\n",
                            order.getOrderItemList().get(index).getQuantityOrdered(),
                            order.getOrderItemList().get(index).getItem().getItemName());
                }else if(quantity<=0){
                    System.out.println("Quantity entered is invalid");
                }
                System.out.print("Enter quantity to be removed: ");
                quantity = GetInput.getInt();
            }
            order.removeOrderItems(index,quantity);
        }

    }

    /**
     * Prints the options to add items, remove items and return to main app.
     */
    public void printAddRemove(){
        System.out.println("(1) Add item(s) to an existing order");
        System.out.println("(2) Remove item(s) from an existing order");
        System.out.println("(3) Return to main app");
    }

    /**
     * Asks for table number and if the table has an existing order, prints out the order.
     * If there is no existing order, prints an error message.
     */
    public void checkTableOrder(){
        System.out.println(restaurant);
        System.out.print("Enter table number: ");
        int tableNum = GetInput.getIntFromRange(1, restaurant.getTableList().size());
        Order temp = restaurant.getTableFromTableNum(tableNum).getOrder();
        if(temp == null){
            System.out.printf("No orders at table %d\n",tableNum);
            System.out.println("Exiting view order function...");
            return;
        }
        temp.printOrder();
    }


}
