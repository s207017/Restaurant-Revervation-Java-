package Restaurant;

import java.time.*;
import java.util.Date;
import java.time.*;

public class Reservation {
    private Date date;
    private LocalTime time;
    private String customerName;
    private int pax;
    private String contactNumber;

    public Reservation(Date date, LocalTime time, String customerName, int pax, String contactNumber) {
        this.date = date;
        this.time = time;
        this.customerName = customerName;
        this.pax = pax;
        this.contactNumber = contactNumber;
    }

    //The following overloaded methods for changes in reservation details
    //#######################################################################
    public void changeReservation(Date date, LocalTime time){
        this.date = date;
        this.time = time;
    }

    public void changeReservation(String contactNumber){
        this.contactNumber = contactNumber;
    }

}
