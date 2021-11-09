package Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public void printCheckRemove(){
        System.out.println("1. Check existing reservation");
        System.out.println("2. Remove reservation");
        System.out.println("3. Return to the main menu");
    }
}
