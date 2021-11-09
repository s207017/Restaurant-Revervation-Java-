package Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

public class ReservationInterface {
    Restaurant r;
    public ReservationInterface(Restaurant r){
        this.r=r;
    }
    /*
    6. Create reservation booking
    7. Check/Remove reservation booking
    */
    public LocalDateTime getPossibleReservationDateTimes(){
        LocalDate nowDate = LocalDate.now();
        LocalDate incrDate = nowDate;
        System.out.println("Enter your choice for reservation date: ");
        for (int i = 0; i < 7; i++) {
            incrDate = incrDate.plusDays(1);
            System.out.println((i + 1) + ". " + incrDate);
        }
        int dateOption = GetInput.getInt();
        while (dateOption<=0 || dateOption>7){
            System.out.println("Reservation is only allowed for the upcoming week. Please input a valid choide: ");
            dateOption = GetInput.getInt();
        }
        for (int i = 0; i < (dateOption)-1; i++) {
            nowDate = nowDate.plusDays(1);
        }
        System.out.println("Chosen reservation date: "+nowDate);
        String string_date = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //chose time
        System.out.print("Enter hour (24H format within opening hours): ");
        int hourInt = GetInput.getInt();
        while (10>hourInt ||hourInt>22) {
            System.out.print("Please enter an hour within opening hours: ");
            hourInt = GetInput.getInt();
        }
        String temp = string_date + "T0" + hourInt + ":00:00";
        return LocalDateTime.parse(temp);
    }

    public LocalDateTime getCheckingPossibleReservationDateTimes(){
        LocalDate nowDate = LocalDate.now();
        LocalDate incrDate = nowDate;
        System.out.println("Please choose the reservation date: ");
        for (int i = 0; i < 8; i++) {
            System.out.println((i + 1) + ". " + incrDate);
            incrDate = incrDate.plusDays(1);
        }
        int dateOption = GetInput.getInt();
        while (dateOption<=0 || dateOption>7){
            System.out.println("Please select valid option: ");
            dateOption = GetInput.getInt();
        }
        for (int i = 0; i < (dateOption)-1; i++) {
            nowDate = nowDate.plusDays(1);
        }
        System.out.println("Chosen reservation date: "+nowDate);
        String string_date = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //chose time
        System.out.print("Enter hour (24H format within opening hours): ");
        int hourInt = GetInput.getInt();
        if (nowDate==LocalDate.now()){
            while (hourInt<LocalDateTime.now().getHour()){
                System.out.print("Please enter current or future timings within today: ");
            }
        }
        else {
            while (10 > hourInt || hourInt > 22) {
                System.out.print("Please enter an hour within opening hours: ");
                hourInt = GetInput.getInt();
            }
        }
        String temp = string_date + "T0" + hourInt + ":00:00";
        return LocalDateTime.parse(temp);
    }



    public void createReservationBooking(){
        String findReservation = "1";
        while (findReservation=="1") {
            LocalDateTime reservationDateTime = getPossibleReservationDateTimes();

            System.out.print("Please input your name for the booking: ");
            String name = GetInput.getString();


            System.out.print("Please input your telephone number for the booking: ");
            String tel = GetInput.getString();
            while (tel.length() != 8) {
                System.out.println("Please input valid length of phone number.");
                tel = GetInput.getString();
            }

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
        }
    }

    public void checkReservationBooking(){
        System.out.println("Would you like to:");
        System.out.println("1. Check all reservations of a specific table.");
        System.out.println("2. Check reservations at a particular time.");
        System.out.println("3. Check all reservations now.");
        System.out.println("Any other number: Quit");
        int choice = GetInput.getInt();
        switch (choice){
            case 1:{
                System.out.println("Enter a table number from below list: ");
                for (Table t: r.getTableList()){
                    System.out.printf("Table Number: %d\n", t.getTableNum());
                }
                int tableChoice = GetInput.getInt();
                while (tableChoice>r.getTableList().size()){
                    System.out.println("Please input valid table number.");
                    tableChoice = GetInput.getInt();
                }
                //valid input here
                Table table = r.getTableFromTableNum(tableChoice);
                System.out.println(table.toString());
                break;
            }
            case 2:{
                LocalDateTime checkReservationDateTime = getCheckingPossibleReservationDateTimes();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatDateTime = checkReservationDateTime.format(formatter);
                System.out.printf("At %s: \n", formatDateTime);
                for (Table t : r.getTableList()){
                    if (t.getReservations().containsKey(checkReservationDateTime)){
                        System.out.println(t.getReservations().get(checkReservationDateTime));
                    }
                }
                break;
            }
            case 3: {
                String string_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String string_hour = String.valueOf(LocalDateTime.now().getHour());
                String temp = string_date + "T0" + string_hour + ":00:00";
                LocalDateTime currentTimeInHour=LocalDateTime.parse(temp);
                System.out.println("At this moment, the reservations are as follows: ");
                for (Table t : r.getTableList()){
                    if (t.getReservations().containsKey(currentTimeInHour)){
                        System.out.println(t.getReservations().get(currentTimeInHour));
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

    public void removeReservationBooking(){
        LocalDateTime checkReservationDateTime = getCheckingPossibleReservationDateTimes();
        System.out.println("Please enter the phone number of person who booked the table: ");
        String tel = GetInput.getString();
        while (tel.length() != 8) {
            System.out.println("Please input valid length of phone number.");
            tel = GetInput.getString();
        }
        for (Table t: r.getTableList()){
            if (t.getReservations().containsKey(checkReservationDateTime) &&
                    t.getReservations().get(checkReservationDateTime).getTel().equals(tel)){

            }
            Iterator<Map.Entry<LocalDateTime, Reservation>>
                    iterator = t.getReservations().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<LocalDateTime, Reservation> entry = iterator.next();

                if (checkReservationDateTime == entry.getKey() &&
                entry.getValue().getTel().equals(tel)){
                    iterator.remove();
                    System.out.printf("Reservation was in Table %d and it was successfully removed.\n", t.getTableNum());
                    System.out.printf("Remaining reservations for Table %d are:\n", t.getTableNum());
                    System.out.println(t);
                    return;
                }
            }

        }

    }
}
