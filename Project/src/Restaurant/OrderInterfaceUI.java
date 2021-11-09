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
    public void createOrder(){
        System.out.print("Enter table number: ");
        int TableNum = GetInput.getInt();
        while(this.restaurant.getTableFromTableNum(TableNum).getTableStatus() != Table.Level.OCCUPIED){
            System.out.print("No one at the table! Enter again");
            TableNum = GetInput.getInt();
        }
        Order o = new Order(123,TableNum);
    }
    //View order
    public void viewOrder(){
        System.out.print("Enter table number: ");
        int TableNum = GetInput.getInt();
    }

    //Add items to order
//    public void addItemsToOrder(){
//        System.out.print("Enter table number: ");
//        int TableNum = GetInput.getInt();
//        while(this.restaurant.getTableFromTableNum(TableNum).getTableStatus() != Table.Level.OCCUPIED) {
//            System.out.print("No one at the table! Enter again");
//            TableNum = GetInput.getInt();
//        }
//        int choice = 0, quantity = 0;
//        while(choice != -1) {
//            System.out.print("Enter ID of intended item to be ordered (-1 to end): ");
//            choice = GetInput.getInt();
//            if(choice == -1) break;
//            //set temp to be the menuItem/setPackage item
//            MenuItem temp;
//            if(choice>500 || choice <=500+menu.getSetPackageItems().size()) {
//                temp = menu.getSetPackageItemFromID(choice);
//            }
//            else{
//                temp = menu.getMenuItemFromID(choice);
//            }
//            if(temp == null){
//                System.out.println("Item does not exist, please enter valid ID.");
//                continue;
//            }
//            if(500<choice && choice<=500+ menu.getSetPackageItems().size()){
//                SetPackage tempSetPackage = new SetPackage(temp.getItemName(),temp.getItemID(),temp.getPrice(),temp.getDescription());
//                menu.printDrinkLTEPrice(tempSetPackage.getMaxDrinkPrice());
//                System.out.print("Please select drink: ");
//                int drinkID = sc.nextInt();
//                while (300>=drinkID && drinkID>300+ menu.getDrinkItems().size()){
//                    System.out.print("Invalid ID, please try again: ");
//                    drinkID = sc.nextInt();
//                }
//                // add drink item to items array in setpackage
//                tempSetPackage.addSide(menu.getMenuItemFromID(drinkID));
//                temp = tempSetPackage; //upcasting
//            }
//            System.out.print("Enter quantity of items to be ordered: ");
//            quantity = sc.nextInt();
//            while(quantity<=0){
//                System.out.print("Invalid quantity! Please enter valid entry: ");
//                quantity = sc.nextInt();
//            }
//            int index = checkItemExistence(choice,menu);
//            if(index < 0) {//Item does not exist in order yet; create new orderitem
//                orderList.add(new OrderItem(temp, quantity));
//            }else{//Item already exists in order; update the quantity
//                orderList.get(index).addQuantityOrdered(quantity);
//            }
//            this.total += (temp.getPrice() * quantity);
//        }
//    }
//    //Remove items from order
// public void removeItemsFromOrder(){
//        System.out.print("Enter table number: ");
//        int TableNum = GetInput.getInt();
//        while(this.restaurant.getTableFromTableNum(TableNum).getTableStatus() != Table.Level.OCCUPIED){
//            System.out.print("No one at the table! Enter again");
//            TableNum = GetInput.getInt();
//        }
//
//    }

}
