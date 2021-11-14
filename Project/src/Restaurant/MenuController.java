package Restaurant;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Used to manage menu related operations such as creating, removing or updating a menu item in the menu
 */
public class MenuController {
    /**
     * Declaring menu reference
     */
    private Menu menu;

    /**
     * Constructor of the MenuController class
     * @param menu
     */
    public MenuController(Menu menu) {
        this.menu = menu;
    }

    /**
     * User Interface used when creating a new menu item in the menu.
     * Asks for the name of the menu item, price, description and an integer value for the menu type
     * Then it calls the createNewMenuItem method in the menu class
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
            printMenu(menuTypeInt, false);
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
            printMenu(menuTypeInt, true);
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


    /**
     * User interface for removing an existing menu item from the menu
     * Asks the user for the type of menu item he/she wants to remove, followed by the menu item's ID,
     * then it performs error checking just to make sure that the menu ID entered by the user exists in the menu
     * @throws IOException
     * @throws InterruptedException
     */
    public void removeMenuItemUI() throws IOException, InterruptedException {
        int ID, end, menuTypeInt;
        boolean invalidInput;
        do{
            printMenuTypes();
            System.out.println("What type of menu item would you like to remove?");
            System.out.print("Enter your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printMenu(menuTypeInt, false);
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
            printMenu(menuTypeInt, true);
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

    /**
     * User interface for updating an existing menu item in the menu. Asks the user for the type of menu item he/she wants
     * to update, followed by the menu ID of the menu item, performs error checking just to make sure that the menu ID entered
     * by the user exists in the menu. It then asks the user what attribute of the menu item (i.e. price, name, description)
     * needs to be updated.
     * @throws IOException
     * @throws InterruptedException
     */
    public void updateMenuItemUI() throws IOException, InterruptedException {
        int ID, end, menuTypeInt, changeOption;
        boolean invalidInput, changed;
        do{
            printMenuTypes();
            System.out.println("What type of menu item would you like to update?");
            System.out.print("Enter your input: ");
            menuTypeInt = GetInput.getIntFromRange(1,4);
            printMenu(menuTypeInt, false);
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
                changed = menu.updateMenuItem(ID, changeOption, true, false);

                if (changed){
                    System.out.println("Item updated!");
                }
            }while(changeOption != 4);

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

    /**
     * Method for printing out the options for the attributes of a menu item prior to getting a user's input
     */
    public void printChangeTypes(){
        System.out.println("(1) Price");
        System.out.println("(2) Name");
        System.out.println("(3) Description");
        System.out.println("(4) Return to the menu interface");
    }

    /**
     * Method for printing out the types of menu items prior to getting a user's input
     */
    public void printMenuTypes(){
        System.out.println("(1) Main Course");
        System.out.println("(2) Sides");
        System.out.println("(3) Drinks");
        System.out.println("(4) Desserts");
    }

    /**
     * Method for printing out the methods available related to menu items in
     * this user interface class prior to getting a user's input
     */
    public void printOptionsMenuItems(){
        System.out.println("What would you like to do?");
        System.out.println("(1) Create menu item");
        System.out.println("(2) Update menu item");
        System.out.println("(3) Remove menu item");
        System.out.println("(4) Return to the menu interface");
        System.out.print("Enter your option: ");
    }

    /**
     * Method for printing out the methods available related to set packages in
     * this user interface class prior to getting a user's input
     */
    public void printOptionsSetPackages(){
        System.out.println("What would you like to do?");
        System.out.println("(1) Create a set package");
        System.out.println("(2) Update an existing set package");
        System.out.println("(3) Remove an existing set package");
        System.out.println("(4) Return to the main menu");
        System.out.print("Enter your option: ");
    }

    /**
     * Method used for printing out the menu items in the menu.
     * @param menuTypeInt is used in the switch statement within the method. Different menu type is represented by
     *                    a different integer value. Hence, different types of menu items will be printed depending on
     *                    this integer value passed into the method
     * @param updated is used to determine whether to print "existing menu" or "updated menu" within the method
     */
    public void printMenu(int menuTypeInt, boolean updated){
        String a;
        if (updated){
            a = " updated ";
        } else {
            a = " existing ";
        }
        switch(menuTypeInt){
            case 1:
                System.out.println();
                System.out.println("These are the" + a + "MAIN COURSES");
                menu.printMainCourse();
                break;
            case 2:
                System.out.println();
                System.out.println("These are the" + a + "SIDES");
                menu.printSide();
                break;
            case 3:
                System.out.println();
                System.out.println("These are the" + a + "DRINKS");
                menu.printDrink();
                break;
            case 4:
                System.out.println();
                System.out.println("These are the" + a + "DESSERTS");
                menu.printDesert();
                break;
            default:
                break;
        }
    }

    /**
     * User interface for creating set package. Asks for the name of the new set package, its description and the maximum price
     * of the drink. Error check for name is performed to prevent duplicate names on the menu. It then asks for the menu ID for
     * a main course item and side menu item and performs error checking to ensure that the two menu IDs exist in the menu.
     * It then asks for the maximum price of the drink which can be chosen by the customer, followed by discount rate to be applied.
     * The waiter can repeatedly try out different discount rate until he is satisfactory with the final price.
     * @throws IOException
     */
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
                discountRate = GetInput.getDoubleFromRange(0,100);
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
            menu.createNewSetPackage(name, finalPrice, desc, setItems,maxPrice);
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

    /**
     * User interface for removing a set package. Asks for the set package ID, performs error checking to ensure that
     * it is an existing ID on the menu, then calls removeMenuItem in the menu class
     * @throws IOException
     */
    public void removeSetPackageUI() throws IOException {
        int menuItemID;
        boolean invalidInput;
        System.out.println("You are now removing a set package item from the menu");
        menu.printSetPackage();
        do {
            invalidInput = false;
            System.out.print("Enter the menu item ID of the set package item you would like to remove: ");
            menuItemID = GetInput.getInt();
            if (501 > menuItemID || menuItemID > 500 + menu.getSetPackageItems().size()){
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


    /**
     * User interface for updating set package item on the menu
     * @throws IOException
     */
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
                if (501 > menuItemID || menuItemID > 500 + menu.getSetPackageItems().size()) {
                    invalidInput = true;
                }
                if (invalidInput) {
                    System.out.println("The menu ID is out of range for SET PACKAGE!");
                } else {
                    System.out.println("Menu ID found... Loading..");
                }
            } while (invalidInput);
            //changeOption = 1;
            System.out.println("check");
            do {
                System.out.println("Item information: ");
                System.out.println("Name: " + menu.getSetPackageItems().get(menuItemID - 501).getItemName());
                System.out.println("Price: " + menu.getSetPackageItems().get(menuItemID - 501).getPrice());
                System.out.println("Description: " + menu.getSetPackageItems().get(menuItemID - 501).getDescription());
                System.out.println("-".repeat(40));
                System.out.println("What do you want to change?");
                this.printChangeTypes();
                System.out.print("Enter your option: ");
                changeOption = GetInput.getIntFromRange(1, 4);
                changed = menu.updateMenuItem(menuItemID, changeOption, true, true);

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
