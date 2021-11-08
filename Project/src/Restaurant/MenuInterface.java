package Restaurant;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class


public class MenuInterface {
    private Menu menu;

    public MenuInterface(Menu menu) {
        this.menu = menu;
    }

    public void createNewMenuItemInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What type of menu item is your new menu?");
        this.printMenuTypes();
        //menu.printMenuTypes();
        System.out.print("Your input: ");
        int menuTypeInt = sc.nextInt();
        System.out.print("Enter the name of the new menu item: ");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.print("Enter the price of the new menu item: ");
        double price = sc.nextDouble();
        System.out.println("Enter the description of the new menu item in one line: ");
        sc.nextLine();
        String desc = sc.nextLine();
        menu.createNewMenuItem(menuName, menuTypeInt, price, desc);
    }

    public void removeMenuItemInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        int ID;
        this.printMenuTypes();
        System.out.println("What type of menu item would you like to remove?");
        System.out.print("Your input: ");
        int menuType = sc.nextInt();
        switch(menuType){
            case 1:
                menu.printMainCourse();
                break;
            case 2:
                menu.printSide();
                break;
            case 3:
                menu.printDrink();
                break;
            case 4:
                menu.printDesert();
                break;
            case -1:
                break;
            default:
                System.out.println("Please enter values 1-4, enter -1 to exit.");
        }
        System.out.print("What is the menu item ID you would like to delete?");
        ID = sc.nextInt();
        menu.removeMenuItem(menuType, ID);
        System.out.println("Updated: ");
        switch(menuType){
            case 1:
                menu.printMainCourse();
                break;
            case 2:
                menu.printSide();
                break;
            case 3:
                menu.printDrink();
                break;
            case 4:
                menu.printDesert();
                break;
            case -1:
                break;
            default:
                System.out.println("Please enter values 1-4, enter -1 to exit.");
        }
        System.out.println("REMOVE MENU ITEM END");
    }

    public void updateMenuItemInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        int ID, changeOption;
        menu.printMenu();
        System.out.println("Enter the menu ID which you want to modify: ");
        ID = sc.nextInt();
        changeOption = 1;
        while (changeOption != 4) {
            System.out.println("What do you want to change?");
            this.printChangeTypes();
            System.out.println("Enter your option: ");
            changeOption = sc.nextInt();
            menu.updateMenuItem(ID, changeOption);
        }
        System.out.println("UPDATE MENU ITEM END");

    }

    public void printChangeTypes(){
        System.out.println("|        1. Price        |");
        System.out.println("|        2. Name         |");
        System.out.println("|     3. Description     |");
        System.out.println("|        4. Quit         |");
    }

    public void printMenuTypes(){
        System.out.println("|     1. Main Course      |");
        System.out.println("|        2. Sides         |");
        System.out.println("|        3. Drinks        |");
        System.out.println("|       4. Desserts       |");
    }



    public void createSetPackageInterface(){
        double initialPrice = 0;
        ArrayList<MenuItem> menuID = new ArrayList<MenuItem>();
        Scanner sc = new Scanner(System.in);
        System.out.println("You are now creating a set package");
        System.out.print("Enter the name of the set package: ");
        String name = sc.next();
        System.out.println("Enter the description of the set package:");
        String desc = sc.next();

        System.out.print("Enter the menu ID of the main course item.");
        for (int i = 0; i < menuID.size(); i++){
            (menuID.get(i)).getItemID();
        }
        System.out.println("");

    }

}
