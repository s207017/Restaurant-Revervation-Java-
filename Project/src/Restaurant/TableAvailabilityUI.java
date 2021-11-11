package Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TableAvailabilityUI {
    private Restaurant r;


    /**
     * constructor
     * @param r instantiates restaurant
     */
    public TableAvailabilityUI(Restaurant r){
        this.r = r;
    }

    //print statement asking them if they got reserve
    // if yes then call assignTable(true)

<<<<<<< HEAD:Project/src/Restaurant/TableAvailabilityInterface.java
    public void assignTable(int pax){
=======
    public void assignTable(){
        //checks if getavailable tables returns any tables when 0 people want to sit
        //meanwhile, it also updates tables and reservations based on current time
        if (r.getAvailableTables(0, LocalDateTime.now()).size()==0){ //pax == 0 returns all tables
            System.out.println("All tables are occupied. Please ask customer to wait.");
            return;
        }

>>>>>>> main:Project/src/Restaurant/TableAvailabilityUI.java
        ArrayList<Table> availableTables;
        LocalDateTime localDateTime = LocalDateTime.now();

        if (pax==0){
            pax = askForPax();
        }
        System.out.println("Checking for an available table...");

        //determines if the arraylist of available tables is empty or not
        if (r.getAvailableTables(pax, localDateTime).size() == 0) {
            System.out.println("Currently no table available for " + pax + ". Please ask customer to wait.");
            return;
        } else {
            //prints available tables
            System.out.println("The available table numbers are: ");
            System.out.print(" || ");
            availableTables = r.getAvailableTables(pax, localDateTime);
            for (Table t: availableTables){
                System.out.print("Table " + t.getTableNum() + " || ");
            }
            System.out.println();

            //asks waiter to choose table from printed list of tables
            System.out.print("Enter the table number to assign the customer: ");
            int newTableNum = GetInput.getInt();
            int i;
            boolean valid = false;
            //checks if the table number inputted exists in the availabletables array
            while (!valid) {
                for (i = 0; i < availableTables.size(); i++) {
                    if (availableTables.get(i).getTableNum() == newTableNum) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    System.out.print("Please enter a valid available table number: ");
                    newTableNum = GetInput.getInt();
                }
            }

            r.getTableFromTableNum(newTableNum).occupyTable(pax);

            System.out.println("Table assigned! Bring the customers to table " + newTableNum + ".");
        }
    }

    public void checkTableAvailability(){
        System.out.println(r);
    }

    public void assignTable(boolean Reserved){
        //updates tables and reservations based on current time
        for (Table t: r.getTableList()){
            t.updateReservationsHashMap(LocalDateTime.now());
            t.updateLevel(LocalDateTime.now());

        }

        //gets hour of booking
        System.out.print("What time is your booking for? (24h format)");
        System.out.println("\t11 12 13 14 15 16 17 18 19 20 21 22");
        int bookingHour = GetInput.getIntFromRange(11,22);

        System.out.print("Please input telephone number used for booking: ");
        int tel = GetInput.getIntFromRange(80000000,99999999);

        //creates reservation key DateTime from the hour given
        LocalDate todayDate = LocalDate.now();
        String string_date = todayDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String reservationKeyString = string_date + "T" + bookingHour + ":00:00";
        LocalDateTime reservationKeyDateTime = LocalDateTime.parse(reservationKeyString);

        //checks if the bookingHour entered is before the current hour ||
        //checks if the bookingHour entered and current hour is the same, but its past 15min ||
        //checks if the bookingHour entered is greater than 1h+currentHour
        if (bookingHour<LocalDateTime.now().getHour() ||
                (bookingHour<LocalDateTime.now().getHour() &&
                LocalDateTime.now().getMinute()>15) ||
                        (bookingHour>LocalDateTime.now().getHour()+1)){

            System.out.print("Reservation expired/does not exist. Process table assignment as per normal.\n");
            r.removeReservation(reservationKeyDateTime, tel);
            assignTable(0);
        }
        else {
            Table t = r.getTableFromReservationHashMap(reservationKeyDateTime, tel);
            if (t==null){
<<<<<<< HEAD:Project/src/Restaurant/TableAvailabilityInterface.java
                System.out.print("Reservation does not exist. Process table assignment as per normal");
                assignTable(0);
=======
                System.out.print("Reservation does not exist. Process table assignment as per normal.\n");
                assignTable();
>>>>>>> main:Project/src/Restaurant/TableAvailabilityUI.java
                return;
            }
            else{
                //table with reservation exists.
                if (t.getTableStatus()==Table.Level.OCCUPIED){
                    int pax = t.getReservations().get(reservationKeyDateTime).getPax();
                    System.out.println("Customers' a bit too early! Checking tables as per normal now.");
                    assignTable(pax);
                    System.out.printf("Sorry! Please wait until time: %d for your table. Currently all tables are occupied.\n", bookingHour);
                    return;
                }
                else {
                    int pax = t.getReservations().get(reservationKeyDateTime).getPax();
                    t.occupyTable(pax);
                    t.deleteReservationFromHashMap(reservationKeyDateTime, tel);
                    System.out.println("Table assigned! Bring the customers to table " + t.getTableNum() + ".");
                }

            }
        }

    }

<<<<<<< HEAD:Project/src/Restaurant/TableAvailabilityInterface.java
=======
    public void assignTable(int pax){
        //updates tables and reservations based on current time
        for (Table t: r.getTableList()){
            t.updateReservationsHashMap(LocalDateTime.now());
            t.updateLevel(LocalDateTime.now());

        }

        ArrayList<Table> availableTables;

        LocalDateTime localDateTime = LocalDateTime.now();
        //int pax = askForPax(), tableNum, newTableNum;
        System.out.println("Checking for an available table...");

        //determines if the arraylist of available tables is empty or not
        if (r.getAvailableTables(pax, localDateTime).size() == 0) {
            System.out.println("Currently no table available for " + pax + ". Please ask customer to wait.");
            return;
        } else {
            //prints available tables
            System.out.println("The available table numbers are: ");
            System.out.print(" || ");
            availableTables = r.getAvailableTables(pax, localDateTime);
            for (Table t: availableTables){
                System.out.print("Table " + t.getTableNum() + " || ");
            }
            System.out.println();

            //asks waiter to choose table from printed list of tables
            System.out.print("Enter the table number to assign the customer: ");
            int newTableNum = GetInput.getInt();
            int i;
            boolean valid = false;
            //checks if the table number inputted exists in the avialabletables array
            while (!valid) {
                for (i = 0; i < availableTables.size(); i++) {
                    if (availableTables.get(i).getTableNum() == newTableNum) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    System.out.print("Please enter a valid available table number: ");
                    newTableNum = GetInput.getInt();
                }
            }

            r.getTableFromTableNum(newTableNum).occupyTable(pax);

            System.out.println("Table assigned! Bring the customers to table " + newTableNum + ".");
        }
    }

>>>>>>> main:Project/src/Restaurant/TableAvailabilityUI.java
    /**
     * asks for the number of guests
     * @return returns number of people to be seated
     */
    public int askForPax(){
        int pax;
        System.out.print("Enter the number of pax: ");
        pax = GetInput.getIntFromRange(1,10);
        return pax;
    }
}
