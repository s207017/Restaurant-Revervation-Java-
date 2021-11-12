package Restaurant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainUI {
    public static void main(String[] arg) throws IOException, InterruptedException {
        Restaurant restaurant = new Restaurant();
        MenuController menuController = new MenuController(restaurant.getMenu());
        ReservationController reservationController = new ReservationController(restaurant);
        TableAvailabilityController tableAvailabilityController = new TableAvailabilityController(restaurant);
        SalesRevenueReportController salesRevenueReportController = new SalesRevenueReportController(restaurant);
        OrderController orderController = new OrderController(restaurant.getMenu(), restaurant);
        Membership membership = new Membership();
        MembershipController membershipController = new MembershipController(membership);

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

        PaymentController paymentController = new PaymentController(restaurant,membership, restaurant.getTransactionHistory(),thisStaff);

        System.out.println("Logging you in to OOPsie RRPSS...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Making bread...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Petting cats...");

        int option, choice;
        do {
            System.out.println("\nEntering main app...");
            option = getOption();
            switch (option) {
                case 1: //Create/update/remove menu item
                    do {
                        menuController.printOptionsMenuItems();
                        choice = GetInput.getIntFromRange(1, 4);
                        switch (choice) {
                            case 1:
                                menuController.createNewMenuItemUI();
                                break;
                            case 2:
                                menuController.updateMenuItemUI();
                                break;
                            case 3:
                                menuController.removeMenuItemUI();
                                break;
                            case 4:
                                break;
                            default:
                                break;
                        }
                    } while (choice != 4);
                    break;
                case 2: // Create/update/remove set packages
                    do {
                        menuController.printOptionsSetPackages();
                        choice = GetInput.getIntFromRange(1, 4);
                        switch (choice) {
                            case 1:
                                menuController.createSetPackageUI();
                                break;
                            case 2:
                                menuController.updateSetPackageUI();
                                break;
                            case 3:
                                menuController.removeSetPackageUI();
                                break;
                            case 4:
                                break;
                            default:
                                break;
                        }
                    } while (choice != 4);
                    break;
                case 3:
                    restaurant.getMenu().printMenu();
                    pressEnterToContinue();
                    break;
                case 4:
                    orderController.addItemsToOrder();
                    break;
                case 5:
                    orderController.checkTableOrder();
                    break;
                case 6:
                    orderController.printAddRemove();
                    System.out.print("Enter your option: ");
                    choice = GetInput.getIntFromRange(1, 3);
                    switch (choice) {
                        case 1:
                            orderController.addItemsToOrder(); //same as case 3
                            break;
                        case 2:
                            orderController.removeItemsFromOrder();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 7:
                    reservationController.createReservationBooking();
                    break;
                case 8:
                    reservationController.printCheckRemove();
                    System.out.print("Enter your choice: ");
                    choice = GetInput.getIntFromRange(1, 3);
                    switch (choice) {
                        case 1:
                            reservationController.checkReservationBooking();
                            break;
                        case 2:
                            reservationController.removeReservationBooking();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 9:
                    System.out.print("*ENTER ANY OTHER KEY TO RETURN\nDoes the customer have a reservation? [Y/N]: ");
                    char YN = GetInput.getChar();
                    if (YN=='y'||YN=='Y'){
                        tableAvailabilityController.assignTable(true);
                    }
                    else if (YN=='n'||YN=='N'){
                        tableAvailabilityController.assignTable(0);
                    }
                    else{
                        break;
                    }
                    break;
                case 10:
                    tableAvailabilityController.checkTableAvailability();
                    pressEnterToContinue();
                    break;
                case 11:
                    System.out.print("Membership functions:\n(1) Add member\n(2) Remove member\n");
                    while(true){
                        System.out.print("*ENTER 3 TO EXIT\nEnter option: ");
                        choice = GetInput.getIntFromRange(1,3);
                        if(choice==1){
                            membershipController.addMember();
                            break;
                        }else{
                            membershipController.removeMember();
                            break;
                        }
                    }
                    break;
                case 12:
                    paymentController.makePaymentController();
                    break;
                case 13:
                    salesRevenueReportController.printSalesRevenueReport();
                    pressEnterToContinue();
                    break;
                case 14:
                    restaurant.writeReservationsToTextFile();
                    System.out.println("Thank you for your hard work!\nApp terminating...");
                    return;
            }
        }while(option != -1);
    }

    public static void printAppOptions(){
        System.out.println("");
        System.out.println("                                                                              /\\_/\\");
        System.out.println("                                                                             / o o \\");
        System.out.println("+" + "-".repeat(164) + "+");
        System.out.printf("|%100s%65s\n","OOPsie Restaurant Reservation & Point of Sale App","|");
        System.out.println("+" + "-".repeat(164) + "+");
        System.out.printf("| %-60s %-60s %-40s %s\n", "(1) Create/Update/Remove menu item", "(6) Add or remove item(s) to/from an existing order", "(11) Add or remove a member","|");
        System.out.printf("| %-60s %-60s %-40s %s\n","(2) Create/Update/Remove set package item","(7) Create new reservation","(12) Make payment","|");
        System.out.printf("| %-60s %-60s %-40s %s\n","(3) View menu","(8) Check or remove an existing reservation","(13) View sales revenue report","|");
        System.out.printf("| %-60s %-60s %-40s %s\n","(4) Create new order","(9) Assign table","(14) Shut down app","|");
        System.out.printf("| %-60s %-60s %-40s %s\n","(5) View existing order","(10) Check table availability","","|");
        System.out.println("+" + "-".repeat(164) + "+");
    }

    public static int getOption() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        printAppOptions();
        System.out.print("Enter option: ");
        int option = GetInput.getIntFromRange(1,14);
        System.out.println("");
        return option;
    }

    public static void pressEnterToContinue()
    {
        System.out.print("*PRESS ENTER TO CONTINUE");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }


}
