package Restaurant;

import java.util.Scanner;

public class Main{

    Menu menu = new Menu();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] arg){
        //WRITE function init data to load text file data to the programme

        //Clears the CMD prompt
        clearScreen();
        //getStaffID asks the user for StaffID input and store it so that once keyed in during initialisation, the
        //waiter does not need to key in again.
        getStaffID();
        int option = 1;
        while (option < 13){ //TO BE UPDATED AS WHEN AND WHEN NEW SWITCH is added
            switch(option){
                case 1: // Create/update/remove menu item
                    clearScreen();
                    createUpdateRemoveMenuItem();
                    break;

                case 2: // Create/update/remove set packages
                    clearScreen();

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

    public static String getStaffID(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your 4 digit staff ID: ");
        //if employee class added, pls update this part
        String staffId = scanner.next();
        return staffId;

    }

    public static void createUpdateRemoveMenuItem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do? ");
        System.out.println("|     1. Create menu item     |");
        System.out.println("|     2. Update menu item     |");
        System.out.println("|     3. Remove menu item     |");
        System.out.print("Your option: ");
        int option = sc.nextInt();
        while (option < 1 && option > 3){
            option = sc.nextInt();
            System.out.println("Input should be either 1, 2 or 3!");
        }
        switch(option){
            case 1:
                System.out.println("What type of menu item is your new menu?");
                printMenuTypes();
                System.out.print("Your input: ");
                int menuTypeInt = sc.nextInt();
                System.out.print("Enter the name of the new menu item: ");
                String menuName = sc.next();
                System.out.print("Enter the price of the new menu item: ");
                double price = sc.nextDouble();
                System.out.println("Enter the description of the new menu item in one line: ");
                String desc = sc.nextLine();
                menu.createNewMenuItem(menuName, menuTypeInt, price, desc);

                break;

            case 2:
                System.out.println("What type of menu item would you like to update?");
                printMenuTypes();
                int option1 = sc.nextInt();
                switch(option1) {
                    case 1:
                        menu.printMainCourse();
                        break;
                    case 2:
                        menu.printSides();
                        break;
                    case 3:
                        menu.printDrinks();
                        break;
                    case 4:
                        menu.printDesserts();
                        break;
                    default:
                        break;
                }
                System.out.println("Enter the menu ID which you want to modify: ");
                int ID = sc.nextInt();
                int constant = 0;
                switch(option1){
                    case 1:
                        constant = 101;
                        break;
                    case 2:
                        constant = 201;
                        break;
                    case 3:
                        constant = 301;
                        break;
                    case 4:
                        constant = 401;
                        break;
                    default:
                        break;
                }
                int option2 = 1;
                while (option2 != 4){
                    System.out.println("What do you want to change?");
                    printChangeTypes();
                        System.out.println("Enter your option: ");
                        option2 = sc.nextInt();
                        switch(option2){
                            case 1:
                                System.out.println("What is the new price?");
                                double newPrice = sc.nextDouble();
                                (menu.mainCourseItems.get(ID-101)).setprice(newPrice);
                                break;
                            case 2:
                                System.out.println("What is the new name?");
                                String newName = sc.nextLine();
                                (menu.mainCourseItems.get(ID-101)).setItemName(newName);
                                break;
                            case 3:
                                System.out.println("What is the new description?");
                                String newDesc = sc.nextLine();
                                (menu.mainCourseItems.get(ID-101)).setDescription(newDesc);
                                break;
                            case 4:
                                option2 = 4;
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                    case 2: //201

                        break;
                    case 3: //301
                        menu.printDrinks();
                    case 4: //401
                        menu.printDesserts();




            case 3:

            default:
        }

    }

    public static void printChangeTypes(){
        System.out.println("|        1. Price        |");
        System.out.println("|        2. Name         |");
        System.out.println("|     3. Description     |");
        System.out.println("|        4. Quit         |");
    }

    public static void printMenuTypes(){
        System.out.println("|     1. Main Course      |");
        System.out.println("|        2. Sides         |");
        System.out.println("|        3. Drinks        |");
        System.out.println("|       4. Desserts       |");
    }

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
}
