package Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ReservationInterface {
    Restaurant r;

    public ReservationInterface(Restaurant r) {
        this.r = r;
    }

    public void printCheckRemove() {
        System.out.println("1. Check existing reservation");
        System.out.println("2. Remove reservation");
        System.out.println("3. Return to the main menu");

    }

    public LocalDateTime getPossibleReservationDateTimes() {
        LocalDate nowDate = LocalDate.now();
        LocalDate incrDate = nowDate;
        System.out.println("Enter your choice for reservation date: ");
        for (int i = 0; i < 7; i++) {
            incrDate = incrDate.plusDays(1);
            System.out.println((i + 1) + ". " + incrDate);
        }
        int dateOption = GetInput.getInt();
        while (dateOption <= 0 || dateOption > 7) {
            System.out.println("Reservation is only allowed for the upcoming week. Please input a valid choide: ");
            dateOption = GetInput.getInt();
        }
        for (int i = 0; i <= (dateOption) - 1; i++) {
            nowDate = nowDate.plusDays(1);
        }
        System.out.println("Chosen reservation date: " + nowDate);
        String string_date = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //chose time
        System.out.println("Our operating hours are from 11am to 10pm.");
        System.out.println("Enter one of the following timings for booking (24hr format):\n11 12 13 14 15 16 17 18 19 20 21 22\n");
        int hourInt = GetInput.getIntFromRange(10, 22);
        String temp = string_date + "T" + hourInt + ":00:00";
        return LocalDateTime.parse(temp);
    }

    public LocalDateTime getCheckingPossibleReservationDateTimes() {
        LocalDate nowDate = LocalDate.now();
        LocalDate incrDate = nowDate;
        System.out.println("Please choose the reservation date: ");
        for (int i = 0; i < 8; i++) {
            System.out.println((i + 1) + ". " + incrDate);
            incrDate = incrDate.plusDays(1);
        }
        int dateOption = GetInput.getInt();
        while (dateOption <= 0 || dateOption > 7) {
            System.out.println("Please select valid option: ");
            dateOption = GetInput.getInt();
        }
        for (int i = 0; i < (dateOption) - 1; i++) {
            nowDate = nowDate.plusDays(1);
        }
        System.out.println("Chosen reservation date: " + nowDate);
        String string_date = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //chose time
        System.out.println("Our operating hours are from 11am to 10pm.");
        System.out.println("Enter one of the following timings for booking (24hr format):\n11 12 13 14 15 16 17 18 19 20 21 22\n");
        int hourInt = GetInput.getIntFromRange(10, 22);
        String temp = string_date + "T" + hourInt + ":00:00";
        return LocalDateTime.parse(temp);
    }


    public void createReservationBooking() throws InterruptedException {
        String findReservation = "1";
        while (Objects.equals(findReservation, "1")) {
            LocalDateTime reservationDateTime = getPossibleReservationDateTimes();

            System.out.print("Please input name for the booking: ");
            String name = GetInput.getString();


            System.out.print("Please input telephone number for the booking: ");
            int tel = GetInput.getIntFromRange(80000000,99999999);


            System.out.print("Please input the number of guests who will be dining: ");
            int pax = GetInput.getInt();
            while (pax <= 0 || pax > 10) {
                System.out.println("Please input valid number of guests. Max is 10 guests.");
                pax = GetInput.getInt();
            }


            int tableNum = r.reserveTable(reservationDateTime, pax, name, tel);
            if (tableNum == -1) {
                System.out.println("All the tables are occupied.");
                System.out.println("Would you like to try for another timing, or quit?");
                System.out.print("[1] Try for another timing.\n[Any other key] Quit.\n");
                findReservation = GetInput.getString();
            }
            else {
                Table t = r.getTableFromTableNum(tableNum);
                System.out.println();
                System.out.println("-".repeat(100));
                System.out.println("Reservation success!");
                System.out.print(t.getReservations().get(reservationDateTime).toString());
                System.out.println("-".repeat(100));
                System.out.println();
                TimeUnit.SECONDS.sleep(3);
                break;
            }
        }
        return;
    }


    public void checkReservationBooking() {
        System.out.println("Would you like to:");
        System.out.println("1. Check all reservations of a specific table.");
        System.out.println("2. Check reservations at a particular time.");
        System.out.println("3. Check reservations for a particular day.");
        System.out.println("4. Check all reservations now.");
        System.out.println("Any other number: Quit");
        int choice = GetInput.getInt();
        switch (choice) {
            case 1: {
                System.out.println("Enter a table number from below list: ");
                for (Table t : r.getTableList()) {
                    System.out.printf("Table Number: %d\n", t.getTableNum());
                }
                int tableChoice = GetInput.getInt();
                while (tableChoice > r.getTableList().size()) {
                    System.out.println("Please input valid table number.");
                    tableChoice = GetInput.getInt();
                }
                //valid input here
                Table table = r.getTableFromTableNum(tableChoice);
                System.out.println(table.toString());
                break;
            }
            case 2: {
                LocalDateTime checkReservationDateTime = getCheckingPossibleReservationDateTimes();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatDateTime = checkReservationDateTime.format(formatter);
                System.out.printf("At %s: \n\n", formatDateTime);
                for (Table t : r.getTableList()) {
                    if (t.getReservations().containsKey(checkReservationDateTime)) {
                        System.out.println(t.getReservations().get(checkReservationDateTime));
                    }
                }
                break;
            }
            case 3:{
                LocalDate nowDate = LocalDate.now();
                LocalDate incrDate = nowDate;
                System.out.println("Please choose the reservation date: ");
                for (int i = 0; i < 8; i++) {
                    System.out.println((i + 1) + ". " + incrDate);
                    incrDate = incrDate.plusDays(1);
                }
                int dateOption = GetInput.getInt();
                while (dateOption <= 0 || dateOption > 7) {
                    System.out.println("Please select valid option: ");
                    dateOption = GetInput.getInt();
                }
                for (int i = 0; i < (dateOption) - 1; i++) {
                    nowDate = nowDate.plusDays(1);
                }
                System.out.println("Chosen reservation date: " + nowDate);
                String temp, string_date = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDateTime checkingDateTime;
                int count=0;
                int hourInt;
                for (hourInt=11;hourInt<=22;hourInt++){
                    temp = string_date + "T" + hourInt + ":00:00";
                    checkingDateTime = LocalDateTime.parse(temp);
                    System.out.printf("At %s: \n", checkingDateTime);
                    for (Table t: r.getTableList()){
                        if (t.getReservations().containsKey(checkingDateTime)){

                            System.out.println(t.getReservations().get(checkingDateTime).toString());
                        }
                        else {
                            count++;
                        }
                    }
                    if (count==12){
                        System.out.println("There are no reservations");
                    }
                }
                break;
            }
            case 4: {
                String string_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String string_hour = String.valueOf(LocalDateTime.now().getHour());
                String temp = string_date + "T" + string_hour + ":00:00";
                LocalDateTime currentTimeInHour = LocalDateTime.parse(temp);
                System.out.println("At this moment, the reservations are as follows: ");
                for (Table t : r.getTableList()) {
                    if (t.getReservations().containsKey(currentTimeInHour)) {
                        System.out.println(t.getReservations().get(currentTimeInHour));
                    }
                    else{
                        System.out.printf("No reservations for table %d\n", t.getTableNum());
                    }
                }
                break;
            }
            default: {
                System.out.println("Terminating interface...");
                break;
            }
        }

    }

    public void removeReservationBooking() {
        LocalDateTime checkReservationDateTime = getCheckingPossibleReservationDateTimes();
        System.out.println("Please enter the phone number of person who booked the table: ");
        int tel = GetInput.getIntFromRange(80000000,99999999);

        for (Table t : r.getTableList()) {
            Iterator<Map.Entry<LocalDateTime, Reservation>>
                    iterator = t.getReservations().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<LocalDateTime, Reservation> entry = iterator.next();

                if (checkReservationDateTime.isEqual(entry.getKey()) &&
                        entry.getValue().getTel()==(tel)) {
                    iterator.remove();
                    System.out.printf("Reservation was in Table %d and it was successfully removed.\n", t.getTableNum());
                    System.out.printf("Remaining reservations for Table %d are:\n", t.getTableNum());
                    System.out.println(t.toString());
                    return;
                }
            }

        }
    }
}
