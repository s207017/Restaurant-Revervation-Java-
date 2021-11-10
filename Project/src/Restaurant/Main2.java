package Restaurant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Logging you in to OOPsie RRPSS...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Building kitchen...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Configuring ant tasks...");

        int option, choice;
        do {
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
                    restaurant.getMenu().printMenu();
                    break;
                case 4:
                    orderInterface.addItemsToOrder();
                    break;
                case 5:
                    orderInterface.checkTableOrder();
                    break;
                case 6:
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
                case 7:
                    reservationInterface.createReservationBooking();
                    break;
                case 8:
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
                case 9:
                    System.out.println("Do you have a reservation? Enter [y/n]");
                    char YN = GetInput.getChar();
                    if (YN=='y'||YN=='Y'){
                        tableAvailabilityInterface.assignTable(true);
                    }
                    else if (YN=='n'||YN=='N'){
                        tableAvailabilityInterface.assignTable();
                    }
                    else {
                        break;
                    }
                    break;
                case 10:
                    tableAvailabilityInterface.checkTableAvailability();
                    break;
                case 11:
                    membershipInterface.AddMember();
                    break;
                case 12:
                    paymentInterface.makePaymentInterface();
                    break;
                case 13:
                    salesRevenueReportInterface.printSalesRevenueReport();
                    break;
                case 14:
                    System.out.println("App terminating...");
                    return;
            }
        }while(option != -1);
    }

    public static void printAppOptions(){
        System.out.println("");
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
        System.out.print("Enter your option: ");
        int option = GetInput.getIntFromRange(1,14);
        System.out.println("");
        return option;
    }


}
