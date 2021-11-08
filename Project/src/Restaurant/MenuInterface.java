package Restaurant;

import java.util.Scanner;

public class MenuInterface {
    private Menu menu;

    public MenuInterface(Menu menu) {
        this.menu = menu;
    }

    public void createNewMenuItemInterface(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What type of menu item is your new menu?");
        menu.printMenuTypes();
        System.out.print("Your input: ");
        int menuTypeInt = sc.nextInt();
        System.out.print("Enter the name of the new menu item: ");
        String menuName = sc.next();
        System.out.print("Enter the price of the new menu item: ");
        double price = sc.nextDouble();
        System.out.println("Enter the description of the new menu item in one line: ");
        String desc = sc.nextLine();
        menu.createNewMenuItem(menuName, menuTypeInt, price, desc);
    }

    public void removeMenuItemInterface(){
        Scanner sc = new Scanner(System.in);
        int ID;
        menu.printMenuTypes();
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
}
