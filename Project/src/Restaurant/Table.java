package Restaurant;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Table {
    private int tableNum;
    private int capacity;
    private int pax; //once occupied, update this
    private Order order;
    enum Level {
        FREE,
        RESERVED,
        OCCUPIED
    }
    private Level tableStatus;
    private Map<LocalDateTime, Reservation> reservations= new HashMap<>();


    public Table(int tableNum, int capacity){
        this.tableNum = tableNum;
        this.capacity = capacity;
        this.pax = 0; //when initialising the table it should be 0 ppl sitting
        this.tableStatus = Level.FREE;
    }

    /**
     * checks if the table has not been reserved for a particular timing
     * @param arrivalDateTime the timing that will be checked against
     * @return returns true/false
     */
    boolean isFree(LocalDateTime arrivalDateTime){
        return !reservations.containsKey(arrivalDateTime);
    }

    /**
     * reserves the table for a particular date and time. creates Reservation object as value
     * @param arrivalDateHour particular date and time
     * @param pax number of guests
     * @param name name of the person doing the reservation
     * @param tel telephone number of the person doing the reservation
     */
    void reserve(LocalDateTime arrivalDateHour, int pax, String name, int tel) throws IOException {
        reservations.put(arrivalDateHour, new Reservation(name, pax, tel));
    }

    /**
     * remove reservations if reservation+15min grace period is still less than current time
     * this ensures that the reservations in Reservations are beyond current time - 15min grace period only
     */
    void updateReservationsAccordingToCurrentTime() throws IOException {

        Iterator<Map.Entry<LocalDateTime, Reservation>>
                iterator = reservations.entrySet().iterator();

        //gets purely the current datetime
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Iterate over the HashMap
        while (iterator.hasNext()) {
            Map.Entry<LocalDateTime, Reservation> entry = iterator.next();

            LocalDateTime dateTimeOfReservation = entry.getKey();
            if (dateTimeOfReservation.plusMinutes(15).isBefore(currentDateTime)){
                iterator.remove();
            }
        }
    }

    /**
     * deleted reservations when the dateTime and telephone number is inserted
     * @param dateTime gets the dateTime input
     * @param tel gets the telephone number used to make the reservation
     */
    public void deleteReservationFromHashMap(LocalDateTime dateTime, int tel) throws IOException {
        Iterator<Map.Entry<LocalDateTime, Reservation>>
                iterator = reservations.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<LocalDateTime, Reservation> entry = iterator.next();

            if (entry.getKey().isEqual(dateTime) &&
            entry.getValue().getTel()==tel){
                iterator.remove();
            }
        }
    }

    /**
     * updates level to either free or reserved, exits if occupied
     */
    void updateTableStatus(){
        //if occupied, exit this
        if (this.tableStatus==Level.OCCUPIED){
            return;
        }

        //gets purely the current date without the time
        LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        //for loop that goes through the reservations
        for(Map.Entry<LocalDateTime, Reservation> entry : reservations.entrySet()) {

            //gets just the date from the stored dateTime
            LocalDateTime dateTimeOfReservation = entry.getKey();
            String stringReservedDate = dateTimeOfReservation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate reservedDate = LocalDate.parse(stringReservedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (reservedDate.isEqual(currentDate)){
                //same date, same hour
                if (currentDateTime.getHour()==dateTimeOfReservation.getHour()){
                    //same hour, within the 15min grace period for reservations -->reserved
                    if (currentDateTime.getMinute()<=15){
                        setTableStatus(Level.RESERVED);
                        return;
                    }
                    //beyond the 15min grace period-->set free
                    else {
                        setTableStatus(Level.FREE);
                        return;
                    }
                }
                //same date, current date is 1hr before the reserved hr --> reserved
                else if (currentDateTime.getHour()==dateTimeOfReservation.getHour()-1){
                    setTableStatus(Level.RESERVED);
                    return;
                }
            }
            /*
             * reservations current day
             * either no reservations on current day, or all reservations are more than 1h
             * after the current time
             * set table status to free
             */
            else if (reservedDate.isAfter(currentDate)){
                setTableStatus(Level.FREE);
                return;
            }
        }

    }

    //getters
    public int getTableNum(){ return tableNum;}
    public int getCapacity() {
        return capacity;
    }
    public int getPax() {
        return pax;
    }
    public Level getTableStatus(){return tableStatus;}
    public Order getOrder(){
        if(this.order == null){
            return null;
        }
        return this.order;
    }
    public Map<LocalDateTime, Reservation> getReservations(){return this.reservations;}

    //setters
    //tableId doesn't change, capacity doesn't change. we don't need setters for those.
    public void setTableStatus(Level level){
        this.tableStatus=level;
    }
    public void setPax(int pax){this.pax=pax;}
    public void setOrder(Order order){
        this.order = order;
    }


    /**
     * assigns table by changing to OCCUPIED enum and assigning pax
     */
    /**
     * arrayList(i-1).assignTable(pax) called in main()
     * @param pax number of people to be seated. stored here to be retrieved during payment
     */
    public void occupyTable(int pax){
        this.pax = pax;
        this.tableStatus = Level.OCCUPIED;
    }

    /**
     * frees a table by changing to FREE enum and making pax 0
     */
    public void freeTable(){
        this.pax = 0;
        this.tableStatus = Level.FREE;
        this.order = null;
    }

    /**
     * formats all the reservations of a table neatly
     * @return returns a string to be printed
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\nTable: %d - Capacity: %d\n", tableNum, capacity));
        reservations.forEach((k, v) -> sb.append(String.format("\tDate: %s %s at %s:00\n\t%s",
                k.getDayOfMonth(), k.getMonth(),
                k.getHour(), v)));
        if (reservations.isEmpty()){
            System.out.println("No reservations.");
        }
        return sb.toString();
    }
}
