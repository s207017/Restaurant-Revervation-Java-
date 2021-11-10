package Restaurant;

import java.io.IOException;

public class Main2{
    public static void main(String[] arg) throws IOException, InterruptedException {
        Restaurant restaurant = new Restaurant();
        MenuInterface menuInterface = new MenuInterface(restaurant.getMenu());
        ReservationInterface reservationInterface = new ReservationInterface(restaurant);
        TableAvailabilityInterface tableAvailabilityInterface = new TableAvailabilityInterface(restaurant);
        SalesRevenueReportInterface salesRevenueReportInterface = new SalesRevenueReportInterface(restaurant);
        OrderInterfaceUI orderInterface = new OrderInterfaceUI(restaurant.getMenu(), restaurant);
        Membership membership = new Membership();
        MembershipInterface membershipInterface = new MembershipInterface(membership);
        PaymentInterface paymentInterface = new PaymentInterface(restaurant,membership, restaurant.getTransactionHistory());

        //gets staffID from the staff using the UI
        Staff thisStaff;
        int staffID;
        int count=1;
        do {
            if (count==1){ //first time asking for staff id, invalid input message not printed
                System.out.print("Enter your staff ID: ");
            }
            else {
                System.out.print("Invalid staff ID. Please enter a valid staff ID: ");
            }
            staffID = GetInput.getInt();
            thisStaff = restaurant.getStaffFromID(staffID);
            count++;
        }while(thisStaff==null);

        int option, choice;
        do {
            printAppOptions();
            option = getOption();
            switch (option) {
                case 1: //Create/update/remove menu item
                    do {
                        menuInterface.printOptionsMenuItems();
                        choice = GetInput.getIntFromRange(1, 4);
                        switch (choice) {
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
                                System.out.println("Returning to the main menu...\n");
                                break;
                            default:
                                break;
                        }
                    } while (choice != 4);
                    break;
                case 2: // Create/update/remove set packages
                    do {
                        menuInterface.printOptionsSetPackages();
                        choice = GetInput.getIntFromRange(1, 4);
                        switch (choice) {
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
                                break;
                            default:
                                break;
                        }
                    } while (choice != 4);
                    break;
                case 3:
                    orderInterface.addItemsToOrder();
                    break;
                case 4:
                    orderInterface.checkTableOrder();
                    break;
                case 5:
                    orderInterface.printAddRemove();
                    System.out.print("Enter your option: ");
                    choice = GetInput.getIntFromRange(1, 3);
                    switch (choice) {
                        case 1:
                            orderInterface.addItemsToOrder(); //same as case 3
                            break;
                        case 2:
                            orderInterface.removeItemsFromOrder();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 6:
                    reservationInterface.createReservationBooking();
                    break;
                case 7:
                    reservationInterface.printCheckRemove();
                    choice = GetInput.getIntFromRange(1, 3);
                    switch (choice) {
                        case 1:
                            reservationInterface.checkReservationBooking();
                            break;
                        case 2:
                            reservationInterface.removeReservationBooking();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 8:
                    tableAvailabilityInterface.assignTable();
                    break;
                case 9:
                    tableAvailabilityInterface.checkTableAvailability();
                    break;
                case 10:
                    membershipInterface.AddMember();
                    break;
                case 11:
                    paymentInterface.makePaymentInterface();
                    break;
                case 12:
                    salesRevenueReportInterface.printSalesRevenueReport();
                    break;
                case -1:
                    System.out.println("App terminating..."); //never happens hehe
                    break;
            }
        }while(option != -1);
    }

    public static void printAppOptions(){
        System.out.println("");
        System.out.println("+" + "-".repeat(164) + "+");
        System.out.printf("|%100s%65s\n","OOPsie Restaurant Reservation & Point of Sale App","|");
        System.out.println("+" + "-".repeat(164) + "+");
        System.out.printf("| %-60s %-60s %-40s %s\n", "(1) Create/Update/Remove menu items from the menu", "(2) Create/Update/Remove set", "(3) Create new order","|");
        System.out.printf("| %-60s %-60s %-40s %s\n","(4) View existing order","(5) Add or remove item(s) to/from an existing order","(6) Create new reservation","|");
        System.out.printf("| %-60s %-60s %-40s %s\n","(7) Check or remove an existing reservation","(8) Assign table","(9) Check table availability","|");
        System.out.printf("| %-60s %-60s %-40s %s\n","(10) Add or remove a member","(11) Make payment","(12) View sales revenue report","|");
        System.out.println("+" + "-".repeat(164) + "+");
    }

    public static int getOption(){
        System.out.print("Enter your option: ");
        int option = GetInput.getIntFromRange(1,12);
        System.out.println("");
        return option;
    }


}
