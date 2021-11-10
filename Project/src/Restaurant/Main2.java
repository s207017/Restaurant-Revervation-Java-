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
            if (count==1){
                System.out.print("Enter your staff ID: ");
            }
            else {
                System.out.print("Invalid StaffID. Please enter a valid staff ID: ");
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
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    orderInterface.addItemsToOrder();
                    break;
                case 4:
                    System.out.print("Enter table number: ");
                    int tableNum = GetInput.getIntFromRange(1, 8);
//                    Table table = restaurant.getTableFromTableNum(tableNum);
//                    System.out.println("Table num is " + table.getTableNum());
//                    Order order = table.getOrder();
//                    System.out.printf("Order extracted with ID %d\n",order.getOrderID());
//                    order.printOrder();
                    restaurant.getTableFromTableNum(tableNum).getOrder().printOrder();
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
                    paymentInterface.selectPaymentMethod();
                    paymentInterface.selectTable();
                    paymentInterface.showAmount();
                    paymentInterface.checkMembership();
                    paymentInterface.makePayment();
                    paymentInterface.generateReceipt();
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
