package Restaurant;

import java.util.Scanner;

public class OrderInterfaceUI {
    private Menu menu;
    private Restaurant restaurant;
    public OrderInterfaceUI(Menu menu,Restaurant restaurant){
        this.menu = menu;
        this.restaurant = restaurant;
    }
    //Create order
    public void addOrCreateOrder(){
        System.out.print("Enter table number: ");
        int TableNum = GetInput.getInt();
        while(this.restaurant.getTableList().get(TableNum-1).getTableStatus() != Table.Level.OCCUPIED){
            System.out.print("No one at the table! Enter again");
            TableNum = GetInput.getInt();
        }
        


    }
    //View order
    public void viewOrder(){
        System.out.print("Enter table number: ");
        int TableNum = GetInput.getInt();
    }

    //Add items to order
    public void addItemsToOrder(){
        System.out.print("Enter table number: ");
        int TableNum = GetInput.getInt();
    }
    //Remove items from order
    public void removeItemsFromOrder(){
        System.out.print("Enter table number: ");
        int TableNum = GetInput.getInt();
    }

}
