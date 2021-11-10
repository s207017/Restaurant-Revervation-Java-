package Restaurant;

import java.io.IOException;

public class Main{




    // made public class not static bc there was static error
    public static void main(String[] arg) throws IOException, InterruptedException {



        Restaurant restaurant = new Restaurant();
        MenuUI menuInterface = new MenuUI(restaurant.getMenu());
        ReservationUI reservationInterface = new ReservationUI(restaurant);
        TableAvailabilityUI tableAvailabilityInterface = new TableAvailabilityUI(restaurant);
        //Staff staff;
        //staff = restaurant.getStaffFromID(GetInput.getInt());
        SalesRevenueReportUI salesRevenueReportInterface = new SalesRevenueReportUI(restaurant);

        //public static GetInputGetInput = new GetInput();
        OrderUI orderInterface = new OrderUI(restaurant.getMenu(), restaurant);
        Membership membership = new Membership();
        MembershipUI membershipInterface = new MembershipUI(membership);

        //Clears the CMD prompt
        ////clearScreen();
        //getStaffID asks the user for StaffID input and store it so that once keyed in during initialisation, the
        //waiter does not need to key in again.
        //staff.setStaffID();
        printAppOptions();
        int option = 2;
        int opt;
        while (option < 13 && option >= 1) { //TO BE UPDATED AS WHEN AND WHEN NEW SWITCH is added
            switch (option) {
                case 1: // Create/update/remove menu item
                    //clearScreen();
                    opt = 1;
                    while (opt != -1 ){
                        menuInterface.printOptionsMenuItems();
                        opt =GetInput.getInt();
                        while (opt < 1 && opt > 3) {
                            opt =GetInput.getInt();
                            System.out.println("Input should be either 1, 2 or 3!");
                        }
                        switch (opt) {
                            case 1:
                                menuInterface.createNewMenuItemUI();
                                break;
                            case 2:
                                menuInterface.updateMenuItemUI();
                                break;
                            case 3:
                                menuInterface.removeMenuItemUI();
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
                    //clearScreen();
                    opt = 1;
                    while (opt != -1) {
                        menuInterface.printOptionsSetPackages();
                        while (opt < 1 || opt > 3) {
                            System.out.print("Enter your option: ");
                            opt =GetInput.getInt();
                            if (opt < 1 || opt > 3){
                                System.out.println("Invalid input! Try again");
                                continue;
                            } else {
                                break;
                            }
                        }
                        switch (opt) {
                            case 1:
                                menuInterface.createSetPackageUI();
                                break;
                            case 2:
                                menuInterface.updateSetPackageUI();
                                break;
                            case 3:
                                menuInterface.removeSetPackageUI();
                                break;
                            case 4:
                                System.out.println("Exiting..");
                                opt = -1;
                                break;
                            default:
                                break;
                        }
                    }
                    break;

                case 3:
                    //clearScreen();
                    orderInterface.addItemsToOrder();
                    break;

                case 4:
                    //clearScreen();
                    opt = 1;
                    while (opt != -1){
                        orderInterface.viewOrder();
                        System.out.print("Enter -1 to return to the main menu");
                        opt =GetInput.getInt();
                    }
                    break;

                case 5:
                    //clearScreen();
                    opt = 1;
                    while (opt != -1) {
                        orderInterface.printAddRemove();
                        while (opt < 1 || opt > 3) {
                            System.out.print("Enter your option: ");
                            opt =GetInput.getInt();
                            if (opt < 1 || opt > 3){
                                System.out.println("Invalid input! Try again");
                                continue;
                            } else {
                                break;
                            }
                        }
                        switch (opt) {
                            case 1:
                                orderInterface.addItemsToOrder();
                                break;
                            case 2:
                                orderInterface.removeItemsFromOrder();
                                break;
                            case 3:
                                System.out.println("Exiting..");
                                opt = -1;
                                break;
                            default:
                                break;

                        }
                    }
                    break;

                case 6:
                    //clearScreen();
                    opt = 1;
                    while (opt != -1){
                        System.out.println("1. Create new reservation");
                        System.out.println("2. Return to the main menu");
                        while (opt < 1 || opt > 2){
                            System.out.print("Enter your option: ");
                            opt =GetInput.getInt();
                            if (opt < 1 || opt > 2){
                                System.out.println("Invalid input! Try again");
                                continue;
                            } else {
                                break;
                            }
                        }
                        switch (opt){
                            case 1:
                                reservationInterface.checkReservationBooking();
                                break;
                            case 2:
                                System.out.println("Exiting");
                                opt = -1;
                                break;
                        }
                    }
                    break;

                case 7:
                    //clearScreen();
                    opt = 1;
                    while (opt != 1){
                        opt =GetInput.getInt();
                        reservationInterface.printCheckRemove();
                        while (opt < 1 || opt > 3){
                            System.out.print("Enter your option: ");
                            opt =GetInput.getInt();
                            if (opt < 1 || opt > 3){
                                System.out.println("Invalid input! Try again");
                                continue;
                            } else {
                                break;
                            }
                        }
                        switch (opt){
                            case 1:
                                reservationInterface.checkReservationBooking();
                                break;
                            case 2:
                                reservationInterface.removeReservationBooking();
                                break;
                            case 3:
                                System.out.println("Exiting..");
                                opt = -1;
                                break;
                        }
                    }
                    break;

                case 8:
                    //clearScreen();
                    tableAvailabilityInterface.assignTable();
                    break;

                case 9:
                    //clearScreen();
                    System.out.println(restaurant.toString());
                    break;

                case 10:
                    //clearScreen();
                    membershipInterface.AddMember();
                    break;


                case 11:
                    //clearScreen();
                    //PaymentInterface paymentInterface = new PaymentInterface(restaurant, membership)
                    //PaymentInterface.
                    break;


                case 12:
                    //clearScreen();
                    salesRevenueReportInterface.printSalesRevenueReport();
                    break;


                default:
                    break;
            }
        }
    }

//        public static void //clearScreen(){
//            try {
//                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
//            } catch (Exception E) {
//                System.out.println(E);
//            }
//    }



    public static void printAppOptions(){
        System.out.println("1. Create/Update/Remove menu items from the menu");
        System.out.println("2. Create/Update/Remove set packages");
        System.out.println("3. Create new order");
        System.out.println("4. View existing order");
        System.out.println("5. Add or remove item(s) to/from an existing order");
        System.out.println("6. Create new reservation");
        System.out.println("7. Check or remove an existing reservation");
        System.out.println("8. Assign table");
    }
}
