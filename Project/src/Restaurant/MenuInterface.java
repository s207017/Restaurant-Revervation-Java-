package Restaurant;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MenuInterface {
    private Menu menu;

    public MenuInterface(Menu menu) {
        this.menu = menu;
    }

    public void createNewMenuItemInterface() throws IOException, InterruptedException {
        int menuTypeInt, end;
        do{
            System.out.println("What is the type of the new menu item?");
            printMenuTypes();
            System.out.print("Your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printExistingMenu(menuTypeInt);
            System.out.print("Enter the name of the new menu item: ");
            String menuName = GetInput.getString();
            System.out.print("Enter the price of the new menu item: ");
            double price = GetInput.getDouble();
            System.out.println("Enter the description of the new menu item in one line: ");
            String desc = GetInput.getString();
            menu.createNewMenuItem(menuName, menuTypeInt, price, desc);
            System.out.println("New item added to the menu!");
            printUpdatedMenu(menuTypeInt);
            TimeUnit.SECONDS.sleep(3);


            System.out.println();
            System.out.println("What would you like to do next?");
            System.out.println("1. Create another menu");
            System.out.println("2. Return to the menu interface");
            System.out.print("Your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2){
                System.out.println("----------CREATE MENU ITEM END----------");
                System.out.println("\n");
            }
        } while (end == 1);
    }

    public void removeMenuItemInterface() throws IOException, InterruptedException {
        int ID, end, menuTypeInt;
        do{
            printMenuTypes();
            System.out.println("What type of menu item would you like to remove?");
            System.out.print("Your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printExistingMenu(menuTypeInt);
            System.out.println("What is the menu item ID you would like to delete?");
            System.out.print("Your input: ");
            do {
                ID = GetInput.getInt();
                if(!menu.IDExists(ID)){
                    System.out.println("Menu ID does not exist. Please enter a valid menu ID!");
                } else {
                    System.out.println("Menu ID found... removing..");
                }
            } while(!menu.IDExists(ID));
            menu.removeMenuItem(menuTypeInt, ID);
            System.out.println("Item removed!");
            printUpdatedMenu(menuTypeInt);
            TimeUnit.SECONDS.sleep(3);

            System.out.println();
            System.out.println("What would you like to do next?");
            System.out.println("1. Remove another menu");
            System.out.println("2. Return to the menu interface");
            System.out.print("Your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2) {
                System.out.println("----------REMOVE MENU ITEM END----------");
            }
        } while (end != 2);
    }

    public void updateMenuItemInterface() throws IOException, InterruptedException {
        int ID, end, menuTypeInt, changeOption, changeCount = 0;
        do{
            printMenuTypes();
            System.out.println("What type of menu item would you like to update?");
            System.out.print("Your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printExistingMenu(menuTypeInt);
            System.out.println("What is the menu item ID you would like to update?");
            System.out.print("Your input: ");
            do {
                ID = GetInput.getInt();
                if(!menu.IDExists(ID)){
                    System.out.println("Menu ID does not exist. Please enter a valid menu ID!");
                } else {
                    System.out.println("Menu ID found... LOADING..");
                }
            } while(!menu.IDExists(ID));
            do{
                System.out.println("What do you want to change?");
                this.printChangeTypes();
                System.out.print("Enter your option: ");
                changeOption = GetInput.getIntFromRange(1,4);
                if (menu.updateMenuItem(ID, changeOption, true)){
                    changeCount++;
                }
            }while(changeOption != 4);
            if (changeCount != 0) {
                System.out.println("Item updated!");
                printUpdatedMenu(menuTypeInt);
                TimeUnit.SECONDS.sleep(3);
            }

            System.out.println();
            System.out.println("1. Update another menu");
            System.out.println("2. Return to the menu interface");
            System.out.print("Your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2) {
                System.out.println("----------UPDATE MENU ITEM END----------");
            }
        } while (end != 2);
//        int ID, changeOption;
//        menu.printMenu();
//        System.out.print("Enter the menu ID which you want to modify: ");
//        ID = GetInput.getInt();
//        while (!menu.IDExists(ID)){
//            System.out.print("Menu ID does not exist, please enter a valid ID: ");
//            ID = GetInput.getInt();
//        }
//        do{
//            System.out.println("What do you want to change?");
//            this.printChangeTypes();
//            System.out.print("Enter your option: ");
//            changeOption = GetInput.getIntFromRange(1,4);
//            menu.updateMenuItem(ID, changeOption);
//        }while(changeOption != 4);
//        System.out.println("UPDATE MENU ITEM END");
    }

    public void printChangeTypes(){
        System.out.println("1. Price ");
        System.out.println("2. Name         ");
        System.out.println("3. Description   ");
        System.out.println("4. Return ");
    }

    public void printMenuTypes(){
        System.out.println("|      1. Main Course      |");
        System.out.println("|      2. Sides            |");
        System.out.println("|      3. Drinks           |");
        System.out.println("|      4. Desserts         |");
    }

    public void printOptionsMenuItems(){
        System.out.println("What would you like to do? ");
        System.out.println("|        1. Create menu item           |");
        System.out.println("|        2. Update menu item           |");
        System.out.println("|        3. Remove menu item           |");
        System.out.println("|        4. Return to the main menu    |");
        System.out.print("Your option: ");
    }

    public void printOptionsSetPackages(){
        System.out.println("Would you like to ");
        System.out.println("1. Create");
        System.out.println("2. Update");
        System.out.println("3. Remove a set package");
        System.out.println("4. Return to the main menu");
    }

    public void printExistingMenu(int menuTypeInt){
        switch(menuTypeInt){
            case 1:
                System.out.println();
                System.out.println("These are the existing MAIN COURSES");
                menu.printMainCourse();
                break;
            case 2:
                System.out.println();
                System.out.println("These are the existing SIDES");
                menu.printSide();
                break;
            case 3:
                System.out.println();
                System.out.println("These are the existing DRINKS");
                menu.printDrink();
                break;
            case 4:
                System.out.println();
                System.out.println("These are the existing DESSERTS");
                menu.printDesert();
                break;
            default:
                break;
        }
    }

    public void printUpdatedMenu(int menuTypeInt){
        switch(menuTypeInt){
            case 1:
                System.out.println();
                System.out.println("These are the updated MAIN COURSES");
                menu.printMainCourse();
                break;
            case 2:
                System.out.println();
                System.out.println("These are the updated SIDES");
                menu.printSide();
                break;
            case 3:
                System.out.println();
                System.out.println("These are the updated DRINKS");
                menu.printDrink();
                break;
            case 4:
                System.out.println();
                System.out.println("These are the updated DESSERTS");
                menu.printDesert();
                break;
            default:
                break;
        }
    }


    public void createSetPackageInterface(){
        double maxPrice, discountRate, finalPrice;
        int mainMenuID, sideMenuID;
        ArrayList<MenuItem> setItems = new ArrayList<MenuItem>();
        System.out.println("You are now creating a set package");
        System.out.print("Enter the name of the set package: ");
        String name = GetInput.getString();
        System.out.println("Enter the description of the set package in one line:");
        String desc = GetInput.getString();
        System.out.print("Enter the menu ID of the main course item: ");
        mainMenuID = GetInput.getInt(); //error checking needed
        setItems.add(menu.getMenuItemFromID(mainMenuID));
        System.out.print("Enter the menu ID of the side: ");
        sideMenuID = GetInput.getInt(); //error checking needed
        setItems.add(menu.getMenuItemFromID(sideMenuID));
        System.out.print("Enter the maximum price of drink: ");
        maxPrice = GetInput.getDouble();
        double tempTotalPrice = menu.getMenuItemFromID(mainMenuID).getPrice() +
                menu.getMenuItemFromID(sideMenuID).getPrice() + maxPrice;
        System.out.println("The total price of the current combination, with the maximum drink price is : " +
                "$ " + tempTotalPrice);
        do{
            do{
                System.out.print("Enter the percentage discount rate: ");
                discountRate = GetInput.getDouble();
                if (discountRate <= 0 || discountRate >= 100){
                    System.out.println("Invalid input! Please try again");
                } else {
                    break;
                }
            }while(true);
            finalPrice = tempTotalPrice * ((100 - discountRate)/100);
            System.out.print("The new price is: " + finalPrice);
            System.out.print("Would you like to proceed with this pricing? Y/N ");
            char yesNo = GetInput.getChar();
            if (yesNo == 'Y' || yesNo == 'y'){
                break;
            }
        }while(true);

        menu.createNewSetPackage(name, finalPrice, desc, setItems);
    }

    public void removeSetPackageInterface() throws IOException { //same as removing any other menu item so i don't think need this function
        int menuItemID;
        System.out.println("You are now removing a set package item from the menu");
        menu.printSetPackage();
        System.out.print("Enter the menu ID of the set package you would like to remove");
        menuItemID = GetInput.getInt(); //needs error checking
        menu.removeMenuItem(5, menuItemID);
    }

    public void updateSetPackageInterface() throws IOException {
        int menuItemID, changeOption;
        System.out.println("You are now updating a set package item");
        menu.printSetPackage();
        System.out.print("Enter the menu ID of the set package you would like to update");
        menuItemID = GetInput.getInt();
        changeOption = 1;
        while (changeOption != 4) {
            System.out.println("What do you want to change?");
            this.printChangeTypes();
            System.out.println("Enter your option: ");
            changeOption = GetInput.getInt();
            menu.updateMenuItem(menuItemID, changeOption);
        }
        System.out.println("UPDATE SET PACKAGE END");
    }
}
