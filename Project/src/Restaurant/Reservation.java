package Restaurant;

import java.time.*;
import java.util.Date;
import java.time.*;
import java.util.Scanner;

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

    //setter for customerName is not included because there won't be an instance where the customer will change his name.

    public String getCustomerName() {
        return customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
