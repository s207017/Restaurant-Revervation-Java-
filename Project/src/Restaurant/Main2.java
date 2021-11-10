package Restaurant;

import java.io.IOException;

public class Main2{
    public static void main(String[] arg) throws IOException {
        Restaurant restaurant = new Restaurant();
        MenuInterface menuInterface = new MenuInterface(restaurant.getMenu());
        ReservationInterface reservationInterface = new ReservationInterface(restaurant);
        TableAvailabilityInterface tableAvailabilityInterface = new TableAvailabilityInterface(restaurant);
        SalesRevenueReportInterface salesRevenueReportInterface = new SalesRevenueReportInterface(restaurant);
        OrderInterfaceUI orderInterface = new OrderInterfaceUI(restaurant.getMenu(), restaurant);
        Membership membership = new Membership();
        MembershipInterface membershipInterface = new MembershipInterface(membership);
        PaymentInterface paymentInterface = new PaymentInterface(restaurant,membership, restaurant.getTransactionHistory());

        Staff thisStaff;
        do {
            System.out.print("Enter your staff ID: ");
            int staffID = GetInput.getInt();
            thisStaff = restaurant.getStaffFromID(staffID);
        }while(thisStaff!=null);

        int option, choice;
        do {
            printAppOptions();
            option = getOption();
            switch (option) {
                case 1: //Create/update/remove menu item
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
                            System.out.println("Exiting...");
                            break;
                        default:
                            break;
                    }
                    break;
                case 2: // Create/update/remove set packages
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
                            printAppOptions();

                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    orderInterface.addItemsToOrder();
                    printAppOptions();

                    break;
                case 4:
                    System.out.print("Enter table number: ");
                    int tableNum = GetInput.getIntFromRange(1, 8);
                    restaurant.getTableFromTableNum(tableNum).getOrder().printOrder();
                    printAppOptions();

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
                            printAppOptions();

                            break;
                    }
                    break;
                case 6:
                    System.out.println("(1) Create new reservation");
                    System.out.println("(2) Return to the main menu");
                    choice = GetInput.getIntFromRange(1, 2);
                    switch (choice) {
                        case 1:
                            reservationInterface.checkReservationBooking();
                            break;
                        case 2:
                            printAppOptions();

                            break;
                    }
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
                            printAppOptions();

                            break;
                    }
                    break;
                case 8:
                    tableAvailabilityInterface.assignTable();
                    printAppOptions();

                    break;
                case 9:
                    System.out.println(restaurant);
                    printAppOptions();

                    break;
                case 10:
                    membershipInterface.AddMember();
                    printAppOptions();

                    break;
                case 11:
                    paymentInterface.selectPaymentMethod();
                    paymentInterface.selectTable();
                    paymentInterface.showAmount();
                    paymentInterface.checkMembership();
                    paymentInterface.makePayment();
                    paymentInterface.generateReceipt();
                    printAppOptions();

                    break;
                case 12:
                    salesRevenueReportInterface.printSalesRevenueReport();
                    printAppOptions();

                    break;
                case -1:
                    System.out.println("App terminating..."); //never happens hehe
                    break;
            }
        }while(option != -1);
    }

    public static void printAppOptions(){
        System.out.println("(1) Create/Update/Remove menu items from the menu");
        System.out.println("(2) Create/Update/Remove set packages");
        System.out.println("(3) Create new order"); //redundant
        System.out.println("(4) View existing order");
        System.out.println("(5) Add or remove item(s) to/from an existing order");
        System.out.println("(6) Create new reservation");
        System.out.println("(7) Check or remove an existing reservation");
        System.out.println("(8) Assign table");
        System.out.println("(9) Check table availability");
        System.out.println("(10) Add or remove a member");
        System.out.println("(11) Make payment");
        System.out.println("(12) View sales revenue report");
    }

    public static int getOption(){
        System.out.print("Enter your option: ");
        int option = GetInput.getIntFromRange(1,12);
        return option;
    }
}
