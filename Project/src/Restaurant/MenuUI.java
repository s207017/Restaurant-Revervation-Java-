package Restaurant;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MenuUI {
    private Menu menu;

    public MenuUI(Menu menu) {
        this.menu = menu;
    }

    /**
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void createNewMenuItemUI() throws IOException, InterruptedException {
        int menuTypeInt, end;
        do{
            System.out.println("What is the type of the new menu item?");
            printMenuTypes();
            System.out.print("Enter your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printExistingMenu(menuTypeInt);
            System.out.print("Enter the name of the new menu item: ");
            String menuName = GetInput.getString();
            while (menu.checkIfNameExists(menuName)){
                System.out.println("The name already exists in the menu");
                System.out.print("Please enter a new name: ");
                menuName = GetInput.getString();
            }
            System.out.print("Enter the price of the new menu item: ");
            double price = GetInput.getDouble();
            System.out.print("Enter the description of the new menu item: ");
            String desc = GetInput.getString();
            menu.createNewMenuItem(menuName, menuTypeInt, price, desc);
            System.out.println("New item added to the menu!");
            printUpdatedMenu(menuTypeInt);
            TimeUnit.SECONDS.sleep(1);

            System.out.println();
            System.out.println("What would you like to do next?");
            System.out.println("(1) Create another menu");
            System.out.println("(2) Return to the menu interface");
            System.out.print("Enter your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2){
                break;
            }
        } while (end == 1);
    }

    public void removeMenuItemUI() throws IOException, InterruptedException {
        int ID, end, menuTypeInt;
        boolean invalidInput;
        do{
            printMenuTypes();
            System.out.println("What type of menu item would you like to remove?");
            System.out.print("Enter your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printExistingMenu(menuTypeInt);
            System.out.println("What is the menu item ID you would like to delete?");
            System.out.print("Enter your input: ");
            do {
                invalidInput = false;
                System.out.print("Enter your input: ");
                ID = GetInput.getInt();
                switch (menuTypeInt){
                    case 1:
                        if (101 > ID || ID > 100 + menu.getMainCourseItems().size()){
                            invalidInput = true;
                        }
                        break;
                    case 2:
                        if (201 > ID || ID > 200 + menu.getSideItems().size()){
                            invalidInput = true;
                        }
                        break;
                    case 3:
                        if (301 > ID || ID > 300 + menu.getDrinkItems().size()){
                            invalidInput = true;
                        }
                        break;
                    case 4:
                        if (401 > ID || ID > 400 + menu.getDessertItems().size()){
                            invalidInput = true;
                        }
                        break;
                    default:
                        break;
                }
                if (invalidInput) {
                    System.out.println("The menu item ID is out of range for the selected menu type!");
                } else {
                    System.out.println("Menu item ID found... LOADING...");
                }
            } while (invalidInput);
            menu.removeMenuItem(menuTypeInt, ID);
            System.out.println("Item removed!");
            printUpdatedMenu(menuTypeInt);
            TimeUnit.SECONDS.sleep(1);

            System.out.println();
            System.out.println("What would you like to do next?");
            System.out.println("(1) Remove another menu");
            System.out.println("(2) Return to the menu interface");
            System.out.print("Enter your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2) {
                break;
            }
        } while (end != 2);
    }

    public void updateMenuItemUI() throws IOException, InterruptedException {
        int ID, end, menuTypeInt, changeOption, changeCount;
        boolean invalidInput, changed;
        do{
            printMenuTypes();
            System.out.println("What type of menu item would you like to update?");
            System.out.print("Enter your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printExistingMenu(menuTypeInt);
            System.out.println("What is the menu item ID you would like to update?");
            do {
                invalidInput = false;
                System.out.print("Enter your input: ");
                ID = GetInput.getInt();
                switch (menuTypeInt){
                    case 1:
                        if (101 > ID || ID > 100 + menu.getMainCourseItems().size()){
                            invalidInput = true;
                        }
                        break;
                    case 2:
                        if (201 > ID || ID > 200 + menu.getSideItems().size()){
                            invalidInput = true;
                        }
                        break;
                    case 3:
                        if (301 > ID || ID > 300 + menu.getDrinkItems().size()){
                            invalidInput = true;
                        }
                        break;
                    case 4:
                        if (401 > ID || ID > 400 + menu.getDessertItems().size()){
                            invalidInput = true;
                        }
                        break;
                    default:
                        break;
                }
                if (invalidInput) {
                    System.out.println("The menu item ID is out of range for the selected menu type!");
                } else {
                    System.out.println("Menu item ID found... LOADING...");
                    System.out.println();
                }
            } while (invalidInput);
            do{
                System.out.println("Item information: ");
                System.out.println("Name: " + menu.getMenuItemFromID(ID).getItemName());
                System.out.println("Price: " + menu.getMenuItemFromID(ID).getPrice());
                System.out.println("Description: " + menu.getMenuItemFromID(ID).getDescription());
                System.out.println("-".repeat(40));
                System.out.println("What do you want to change?");
                this.printChangeTypes();
                System.out.print("Enter your option: ");
                changeOption = GetInput.getIntFromRange(1,4);
                changed = menu.updateMenuItembool(ID, changeOption, true);
                if (changed){
                    System.out.println("Item updated!");
                }
            }while(changeOption != 4);
//            if (changeCount != 0) {
//                System.out.println("Item updated!");
//                System.out.println("After update: ");
//                System.out.println("Name: " + menu.getMenuItemFromID(ID).getItemName());
//                System.out.println("Price: " + menu.getMenuItemFromID(ID).getPrice());
//                System.out.println("Description: " + menu.getMenuItemFromID(ID).getDescription());
//                System.out.println("-".repeat(40));
//                TimeUnit.SECONDS.sleep(2);
//            }

            System.out.println();
            System.out.println("(1) Update another menu");
            System.out.println("(2) Back to main app");
            System.out.print("Enter your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2) {
                break;
            }
        } while (end != 2);
    }

    public void printChangeTypes(){
        System.out.println("(1) Price");
        System.out.println("(2) Name");
        System.out.println("(3) Description");
        System.out.println("(4) Return to the menu interface");
    }

    public void printMenuTypes(){
        System.out.println("(1) Main Course");
        System.out.println("(2) Sides");
        System.out.println("(3) Drinks");
        System.out.println("(4) Desserts");
    }

    public void printOptionsMenuItems(){
        System.out.println("What would you like to do?");
        System.out.println("(1) Create menu item");
        System.out.println("(2) Update menu item");
        System.out.println("(3) Remove menu item");
        System.out.println("(4) Return to the menu interface");
        System.out.print("Enter your option: ");
    }

    public void printOptionsSetPackages(){
        System.out.println("What would you like to do?");
        System.out.println("(1) Create a set package");
        System.out.println("(2) Update an existing set package");
        System.out.println("(3) Remove an existing set package");
        System.out.println("(4) Return to the main menu");
        System.out.print("Enter your option: ");
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


    public void createSetPackageUI() throws IOException {
        double maxPrice, discountRate, finalPrice, end;
        int mainMenuID, sideMenuID;
        boolean invalidInput;
        do {
            ArrayList<MenuItem> setItems = new ArrayList<MenuItem>();
            System.out.println();
            System.out.println("You are now creating a set package");
            System.out.print("Enter the name of the set package: ");
            String name = GetInput.getString();
            while (menu.checkIfNameExists(name)){
                System.out.println("The name already exists in the menu");
                System.out.print("Please enter a new name: ");
                name = GetInput.getString();
            }
            System.out.println("Enter the description of the set package in one line:");
            String desc = GetInput.getString();
            System.out.println("You will now have to choose 1 main course item and 1 side menu item");
            menu.printMainCourse();
            do {
                invalidInput = false;
                System.out.print("Enter the menu item ID of the main course item: ");
                mainMenuID = GetInput.getInt();
                if (101 > mainMenuID || mainMenuID > 100 + menu.getMainCourseItems().size()){
                    invalidInput = true;
                }
                if (invalidInput) {
                    System.out.println("The menu item ID is out of range for the MAIN MENU!");
                } else {
                    System.out.println("Menu item ID found... Adding it to the set package...");
                }
            } while (invalidInput);
            setItems.add(menu.getMenuItemFromID(mainMenuID));
            menu.printSide();
            System.out.print("Enter the menu item ID of the side: ");
            do {
                invalidInput = false;
                System.out.print("Enter the menu item ID of the main course item: ");
                sideMenuID = GetInput.getInt();
                if (201 > sideMenuID || sideMenuID > 200 + menu.getSideItems().size()){
                    invalidInput = true;
                }
                if (invalidInput) {
                    System.out.println("The menu item ID is out of range for SIDE MENU!");
                } else {
                    System.out.println("Menu item ID found... Adding it to the set package...");
                }
            } while (invalidInput);
            setItems.add(menu.getMenuItemFromID(sideMenuID));
            System.out.print("Enter the maximum price of drink: ");
            maxPrice = GetInput.getDouble();
            double tempTotalPrice = menu.getMenuItemFromID(mainMenuID).getPrice() +
                    menu.getMenuItemFromID(sideMenuID).getPrice() + maxPrice;
            System.out.println("The total price of the current combination, with the maximum drink price is : " +
                    "$ " + String.format("%,.2f", tempTotalPrice));
            do {
                System.out.print("Enter the discount rate: ");
                discountRate = GetInput.getDouble();   //TO BE MODIFIED TO GETDOUBLE(0,100)
                finalPrice = tempTotalPrice * ((100 - discountRate) / 100);
                System.out.println("The new price is: " + String.format("%,.2f", finalPrice));
                System.out.println("Would you like to proceed with this pricing? Y/N ");
                System.out.print("Enter your input: ");
                char yesNo = GetInput.getChar();
                if (yesNo == 'Y' || yesNo == 'y') {
                    break;
                } else {
                    System.out.println("Enter the new discount rate below");
                }
            } while (true);
            menu.createNewSetPackage(name, finalPrice, desc, setItems);
            System.out.println();
            System.out.println("(1) Create another set package");
            System.out.println("(2) Return to the set package interface");
            System.out.print("Enter your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2) {
                break;
            }
        } while (end != 2);
    }

    public void removeSetPackageUI() throws IOException { //same as removing any other menu item so i don't think need this function
        int menuItemID;
        boolean invalidInput;
        System.out.println("You are now removing a set package item from the menu");
        menu.printSetPackage();
        do {
            invalidInput = false;
            System.out.print("Enter the menu item ID of the set package item you would like to remove: ");
            menuItemID = GetInput.getInt();
            if (501 > menuItemID || menuItemID > 500 + menu.getSideItems().size()){
                invalidInput = true;
            }
            if (invalidInput) {
                System.out.println("The menu item ID is out of range for SET PACKAGE!");
            } else {
                System.out.println("Menu item ID found... Removing from the menu...");
            }
        } while (invalidInput);
        menu.removeMenuItem(5, menuItemID);
    }

    public void updateSetPackageUI() throws IOException {
        int menuItemID, changeOption, end;
        boolean invalidInput, changed;
        do {
            System.out.println("You are now updating a set package item");
            menu.printSetPackage();
            do {
                invalidInput = false;
                System.out.print("Enter the menu ID of the set package item you would like to update: ");
                menuItemID = GetInput.getInt();
                if (501 > menuItemID || menuItemID > 500 + menu.getSideItems().size()) {
                    invalidInput = true;
                }
                if (invalidInput) {
                    System.out.println("The menu ID is out of range for SET PACKAGE!");
                } else {
                    System.out.println("Menu ID found... Loading..");
                }
            } while (invalidInput);
            //changeOption = 1;

            do {
                System.out.println("Item information: ");
                System.out.println("Name: " + menu.getMenuItemFromID(menuItemID).getItemName());
                System.out.println("Price: " + menu.getMenuItemFromID(menuItemID).getPrice());
                System.out.println("Description: " + menu.getMenuItemFromID(menuItemID).getDescription());
                System.out.println("-".repeat(40));
                System.out.println("What do you want to change?");
                this.printChangeTypes();
                System.out.print("Enter your option: ");
                changeOption = GetInput.getIntFromRange(1, 4);
                changed = menu.updateMenuItembool(menuItemID, changeOption, true);
                if (changed) {
                    System.out.println("Set package updated!");
                }
            } while (changeOption != 4);

            System.out.println();
            System.out.println("1. Update another set package");
            System.out.println("2. Return to the set package interface");
            System.out.print("Your input: ");
            end = GetInput.getIntFromRange(1,2);
            if (end == 2) {
                System.out.println("----------UPDATE SET PACKAGE END----------");
                System.out.println();
            }
        } while (end != 2);
    }
}
