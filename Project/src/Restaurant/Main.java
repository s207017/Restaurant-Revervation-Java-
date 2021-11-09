package Restaurant;

import java.io.IOException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;

public class Main{

    Menu menu = new Menu();
    MenuInterface menuInterface = new MenuInterface(menu);
    Staff staff = new Staff();
    public static GetInput gi = new GetInput();

    public Main() throws IOException {
    }

    // made public class not static bc there was static error
    public static void main(String[] arg) throws IOException {

        Menu menu = new Menu();
        Restaurant restaurant = new Restaurant();
        MenuInterface menuInterface = new MenuInterface(menu);
        Staff staff = new Staff();
        //public static GetInput gi = new GetInput();
        OrderInterfaceUI orderInterface = new OrderInterfaceUI(menu, restaurant);
        //WRITE function init data to load text file data to the programme

        //Clears the CMD prompt
        clearScreen();
        //getStaffID asks the user for StaffID input and store it so that once keyed in during initialisation, the
        //waiter does not need to key in again.
        staff.setStaffID();
        printAppOptions();
        int option = 1;
        int opt;
        while (option < 13 && option >= 1) { //TO BE UPDATED AS WHEN AND WHEN NEW SWITCH is added
            switch (option) {
                case 1: // Create/update/remove menu item
                    clearScreen();
                    opt = 1;
                    while (opt != -1 ){
                        menuInterface.printOptionsMenuItems();
                        opt = gi.getInt();
                        while (opt < 1 && opt > 3) {
                            opt = gi.getInt();
                            System.out.println("Input should be either 1, 2 or 3!");
                        }
                        switch (opt) { //the 3 functions below need some error handling
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
                                opt = -1;
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 2: // Create/update/remove set packages
                    clearScreen();
                    opt = 1;
                    menuInterface.printOptionsSetPackages();
                    while (opt >= 1 && opt <=3){
                        System.out.print("Enter your option: ");
                        opt = gi.getInt();
                        switch(opt){
                            case 1:
                                menuInterface.createSetPackageInterface();
                                break;
                            case 2:
                                menuInterface.updateSetPackageInterface();
                                break;
                            case 3:
                                menuInterface.removeSetPackageInterface();
                                break;
                            case 4:
                                opt = 4;
                                break;
                            default:
                                System.out.println("Invalid input!");
                                opt = 1;
                                continue;
                        }
                    }
                    break;

                case 3:
                    clearScreen();
                    orderInterface.createOrder();
                    break;

                case 4:
                    clearScreen();
                    orderInterface.viewOrder();
                    break;

                case 5:
                    clearScreen();
                    opt = 1;
                    System.out.println("1. Add item(s) to an existing order");
                    System.out.println("2. Remove item(s) from an existing order");
                    System.out.println("3. Return to the main menu");
                    while (opt == 1 || opt == 2) {
                        System.out.print("Input your option: ");
                        opt = gi.getInt();
                        switch (opt) {
                            case 1:
                                //orderInterface.addItemsToOrder();
                                break;
                            case 2:
                                //orderInterface.removeItemsFromOrder();
                                break;
                            case 3:
                                opt = 0;
                                break;
                            default:
                                System.out.println("Invalid input!");
                                opt = 1;
                                continue;
                        }
                    }
                    break;



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



    public static void printAppOptions(){
        System.out.println("1. Create/Update/Remove menu items from the menu");
        System.out.println("2. Create/Update/Remove set packages");
        System.out.println("3. Create new order");
        System.out.println("4. View existing order");
        System.out.println("5. Add or remove item(s) to/from an existing order");
    }
}
