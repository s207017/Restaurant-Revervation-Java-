package Restaurant;

import java.util.Scanner;

import javax.crypto.BadPaddingException;

public class Main{
    public static void main(String[] arg){
        Menu menu = new Menu();
        MenuInterface menuInterface = new MenuInterface(menu);
        Staff staff = new Staff();
        Scanner scanner = new Scanner(System.in);
        public static GetInput gi = new GetInput();
        MenuInterface menu = new MenuInterface();
        // made public class not static bc there was static error
        public void main(String[] arg){
            //WRITE function init data to load text file data to the programme

            //Clears the CMD prompt
            clearScreen();
            //getStaffID asks the user for StaffID input and store it so that once keyed in during initialisation, the
            //waiter does not need to key in again.
            staff.setStaffID();
            printAppOptions();
            int option = 1;
            while (option < 13){ //TO BE UPDATED AS WHEN AND WHEN NEW SWITCH is added
                switch(option){
                    case 1: // Create/update/remove menu item
                        clearScreen();
                        Scanner sc = new Scanner(System.in);
                        System.out.println("What would you like to do? ");
                        System.out.println("|     1. Create menu item     |");
                        System.out.println("|     2. Update menu item     |");
                        System.out.println("|     3. Remove menu item     |");
                        System.out.print("Your option: ");
                        int opt = sc.nextInt();
                        while (opt < 1 && opt > 3) {
                            opt = sc.nextInt();
                            System.out.println("Input should be either 1, 2 or 3!");
                        }
                        switch (opt){ //the 3 functions below need some error handling
                            case 1:
                                menuInterface.createNewMenuItemInterface();
                                break;
                            case 2:
                                menuInterface.updateMenuItemInterface();
                                break;
                            case 3:
                                menuInterface.removeMenuItemInterface();
                                break;
                            case 4:
                                System.out.println("Exiting...");
                                break;
                            default:
                                break;
                        }
                        break;


                    case 2: // Create/update/remove set packages
                        clearScreen();
                        Scanner sc = new Scanner(System.in);
                        GetInput gi = new GetInput();
                        System.out.println("Would you like to ");
                        System.out.println("1. Create");
                        System.out.println("2. Update");
                        System.out.println("3. Remove a set package?");
                        int input = 0;
                        while (input < 1 && input > 3){
                            input = gi.getInput();
                        }
                        while (1){
                            switch(input){
                                case 1:
                                    menu.create

                            }
                        }



                    case 3:
                        clearScreen();

                    case 4:
                        clearScreen();


                    case 5:
                        clearScreen();


                    case 6:
                        clearScreen();


                    case 7:
                        clearScreen();


                    case 8:
                        clearScreen();


                    case 9:
                        clearScreen();


                    case 10:
                        clearScreen();


                    case 11:
                        clearScreen();


                    case 12:
                        clearScreen();


                    default:
                }

            }


        }

        public static void clearScreen(){
            try {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } catch (Exception E) {
                System.out.println(E);
            }
    }

    // added the staff class! not sure if it works
//    public static String getStaffID(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your 4 digit staff ID: ");
//        //if employee class added, pls update this part
//        String staffId = scanner.next();
//        return staffId;
//    }

//    public static void createMenuItem() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("What type of menu item is your new menu?");
//        printMenuTypes();
//        System.out.print("Your input: ");
//        int menuTypeInt = sc.nextInt();
//        System.out.print("Enter the name of the new menu item: ");
//        String menuName = sc.next();
//        System.out.print("Enter the price of the new menu item: ");
//        double price = sc.nextDouble();
//        System.out.println("Enter the description of the new menu item in one line: ");
//        String desc = sc.nextLine();
//        menu.createNewMenuItem(menuName, menuTypeInt, price, desc);
//    }
//
//    public static void updateMenuItem(){
//        Scanner sc = new Scanner(System.in);
//        int constant, ID, changeOption, opt;
//        System.out.println("What type of menu item would you like to update?");
//        printMenuTypes();
//        int menuType = sc.nextInt();
//        switch(menuType){
//            case 1:
//                constant = 101;
//                menu.printMainCourse();
//                System.out.println("Enter the menu ID which you want to modify: ");
//                ID = sc.nextInt();
//                opt = 1;
//                while (opt != 4) {
//                    System.out.println("What do you want to change?");
//                    printChangeTypes();
//                    System.out.println("Enter your option: ");
//                    changeOption = sc.nextInt();
//                    switch (changeOption) {
//                        case 1:
//                            System.out.println("What is the new price?");
//                            double newPrice = sc.nextDouble();
//                            (menu.mainCourseItems.get(ID - constant)).setprice(newPrice);
//                            break;
//                        case 2:
//                            System.out.println("What is the new name?");
//                            String newName = sc.nextLine();
//                            (menu.mainCourseItems.get(ID - constant)).setItemName(newName);
//                            break;
//                        case 3:
//                            System.out.println("What is the new description?");
//                            String newDesc = sc.nextLine();
//                            (menu.mainCourseItems.get(ID - constant)).setDescription(newDesc);
//                            break;
//                        case 4:
//                            opt = 4;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//
//                break;
//
//            case 2:
//                constant = 201;
//                menu.printSides();
//                System.out.println("Enter the menu ID which you want to modify: ");
//                ID = sc.nextInt();
//                opt = 1;
//                while (opt != 4) {
//                    System.out.println("What do you want to change?");
//                    printChangeTypes();
//                    System.out.println("Enter your option: ");
//                    changeOption = sc.nextInt();
//                    switch (changeOption) {
//                        case 1:
//                            System.out.println("What is the new price?");
//                            double newPrice = sc.nextDouble();
//                            (menu.sideItems.get(ID - constant)).setprice(newPrice);
//                            break;
//                        case 2:
//                            System.out.println("What is the new name?");
//                            String newName = sc.nextLine();
//                            (menu.sideItems.get(ID - constant)).setItemName(newName);
//                            break;
//                        case 3:
//                            System.out.println("What is the new description?");
//                            String newDesc = sc.nextLine();
//                            (menu.sideItems.get(ID - constant)).setDescription(newDesc);
//                            break;
//                        case 4:
//                            opt = 4;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//                break;
//
//
//            case 3:
//                constant = 301;
//                menu.printDrinks();
//                System.out.println("Enter the menu ID which you want to modify: ");
//                ID = sc.nextInt();
//                opt = 1;
//                while (opt != 4) {
//                    System.out.println("What do you want to change?");
//                    printChangeTypes();
//                    System.out.println("Enter your option: ");
//                    changeOption = sc.nextInt();
//                    switch (changeOption) {
//                        case 1:
//                            System.out.println("What is the new price?");
//                            double newPrice = sc.nextDouble();
//                            (menu.drinkItems.get(ID - constant)).setprice(newPrice);
//                            break;
//                        case 2:
//                            System.out.println("What is the new name?");
//                            String newName = sc.nextLine();
//                            (menu.drinkItems.get(ID - constant)).setItemName(newName);
//                            break;
//                        case 3:
//                            System.out.println("What is the new description?");
//                            String newDesc = sc.nextLine();
//                            (menu.drinkItems.get(ID - constant)).setDescription(newDesc);
//                            break;
//                        case 4:
//                            opt = 4;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//
//                break;
//            case 4:
//                constant = 401;
//                menu.printDesserts();
//                System.out.println("Enter the menu ID which you want to modify: ");
//                ID = sc.nextInt();
//                opt = 1;
//                while (opt != 4) {
//                    System.out.println("What do you want to change?");
//                    printChangeTypes();
//                    System.out.println("Enter your option: ");
//                    changeOption = sc.nextInt();
//                    switch (changeOption) {
//                        case 1:
//                            System.out.println("What is the new price?");
//                            double newPrice = sc.nextDouble();
//                            (menu.dessertItems.get(ID - constant)).setprice(newPrice);
//                            break;
//                        case 2:
//                            System.out.println("What is the new name?");
//                            String newName = sc.nextLine();
//                            (menu.dessertItems.get(ID - constant)).setItemName(newName);
//                            break;
//                        case 3:
//                            System.out.println("What is the new description?");
//                            String newDesc = sc.nextLine();
//                            (menu.dessertItems.get(ID - constant)).setDescription(newDesc);
//                            break;
//                        case 4:
//                            opt = 4;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//
//                break;
//            default:
//                break;
//        }
//    }
//
//    public static void removeMenuItem(){
//        Scanner sc = new Scanner(System.in);
//        int ID;
//        System.out.println("What type of menu item would you like to remove?");
//        printMenuTypes();
//        System.out.print("Your input: ");
//        int menuType = sc.nextInt();
//        switch(menuType){
//            case 1:
//                menu.printMainCourse();
//                break;
//            case 2:
//                menu.printSide();
//                break;
//            case 3:
//                menu.printDrink();
//                break;
//            case 4:
//                menu.printDesert();
//                break;
//            default:
//                ID = 0; //this possible error should be handled better.
//                break;
//        }
//        System.out.print("What is the menu item ID you would like to delete?");
//        ID = sc.nextInt();
//        menu.removeMenuItem(menuType, ID);
//    }

    public static void createSetPackages(){

    }

//    public static void printChangeTypes(){
//        System.out.println("|        1. Price        |");
//        System.out.println("|        2. Name         |");
//        System.out.println("|     3. Description     |");
//        System.out.println("|        4. Quit         |");
//    }
//
//    public static void printMenuTypes(){
//        System.out.println("|     1. Main Course      |");
//        System.out.println("|        2. Sides         |");
//        System.out.println("|        3. Drinks        |");
//        System.out.println("|       4. Desserts       |");
//    }


    public static int getIntegerInput(){
        //this function is to return int after getting a user input using scanner
        //MUST DO exception handling
    }
    public static int getStringInput(){
        //this function is to return String after getting a user input using scanner
        //MUST DO exception handling
    }
    public static int getDoubleInput(){
        //this function is to return double after getting a user input using scanner
        //MUST DO exception handling
    }

    public static void printAppOptions(){
        System.out.println("1. Create/Update/Remove menu items from the menu");
        System.out.println("2. Create/Update/Remove set packages");
    }
}
