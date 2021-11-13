package Restaurant;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * used to manage table availability related operations
 */
public class TableAvailabilityController {
    /**
     * declaring a Restaurant reference
     */
    private Restaurant r;


    /**
     * gets the Restaurant object from the UI and assigns it to the Restaurant reference in this class
     * @param r Restaurant object
     */
    public TableAvailabilityController(Restaurant r){
        this.r = r;
    }

    /**
     * prints out the toString() method in Restaurant Class
     * prints out all our tables and their current statuses with relevant information
     */
    public void checkTableAvailability(){
        System.out.println(r);
    }



    /**
     * input pax = 0, means there has not been a reservation
     * while pax=any other number means there has been a prior reservation but due to circumstances the reserved table cannot be occupied
     * checks through all tables to see if there's a table where tableStatus==FREE at the current date and time
     * if table found, asks waiter to assign guests to the table, else restaurant is full print returned.
     * @param pax number of quests to be seated
     * @throws IOException
     */
    public void assignTable(int pax) throws IOException {
        ArrayList<Table> availableTables;
        LocalDateTime localDateTime = LocalDateTime.now();

        if (pax==0){
            System.out.print("Enter the number of pax: ");
            pax = GetInput.getIntFromRange(1,10);
        }
        
        //updates tables and reservations based on current time
        for (Table t: r.getTableList()){
            t.updateReservationsAccordingToCurrentTime();
            t.updateTableStatus();

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

    /**
     * overrides previous assignTable(int)
     * this is called when there has been a reservation
     * checks through reservations to see if reservation is valid/not expired
     * if reservation does not exist/has expired/guests are too early then assignTable(int) is called
     * @param Reserved boolean value from UI that is not used
     * @throws IOException
     */
    public void assignTable(boolean Reserved) throws IOException {
        //updates tables and reservations based on current time
        for (Table t: r.getTableList()){
            t.updateReservationsAccordingToCurrentTime();
            t.updateTableStatus();

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
                System.out.print("Reservation does not exist. Process table assignment as per normal");
                assignTable(0);
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
}
