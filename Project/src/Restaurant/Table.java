package Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

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
    private Map<LocalDateTime, Reservation> reservations;


    public Table(int tableNum, int capacity){
        this.tableNum = tableNum;
        this.capacity = capacity;
        this.pax = 0; //when initialising the table it should be 0 ppl sitting
        this.tableStatus = Level.FREE;
        this.reservations = new HashMap<>();
    }

    boolean isFree(LocalDateTime arrivalDateTime){
        return !reservations.containsKey(arrivalDateTime);
    }

    void reserve(LocalDateTime arrivalDateHour, int pax, String name, String tel){
        reservations.put(arrivalDateHour, new Reservation(name, pax, tel));
    }

    /**
     * remove reservations if reservation+15min grace period is still less than current time
     * this ensures that the reservations in Reservations are beyond current time - 15min grace period only
     * @param currentDateTime
     */
    void updateReservationsHashMap(LocalDateTime currentDateTime) {
        Iterator<Map.Entry<LocalDateTime, Reservation>>
                iterator = reservations.entrySet().iterator();

        //gets purely the current date without the time
        String string_current_date = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate current_date = LocalDate.parse(string_current_date, date_formatter);


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
     * updates level to either free or reserved, exits if occupied
     * @param currentDateTime gets currentDateTime from main()
     */
    void updateLevel(LocalDateTime currentDateTime){
        //if occupied, exit this
        if (this.tableStatus==Level.OCCUPIED){
            return;
        }

        //gets purely the current date without the time
        String string_current_date = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate current_date = LocalDate.parse(string_current_date, date_formatter);

        //for loop that goes through the reservations
        for(Map.Entry<LocalDateTime, Reservation> entry : reservations.entrySet()) {
            LocalDateTime dateTimeOfReservation = entry.getKey();
            String string_reserved_date = dateTimeOfReservation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate reserved_date = LocalDate.parse(string_reserved_date, date_formatter);

            if (reserved_date.isEqual(current_date)){
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
            else if (reserved_date.isAfter(current_date)){
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
        return this.order;
    }

    //setters
    //tableId doesn't change, capacity doesn't change. we don't need setters for those.
    public void setTableStatus(Level level){
        this.tableStatus=level;
    }
    public void setPax(int pax){this.pax=pax;}
    public void setOrder(Order order){
        this.order = order;
    }




    //occupy table
    /**
     * arrayList(i-1).assignTable(pax) called in main()
     * @param pax number of people to be seated. stored here to be retrieved during payment
     */
    public void assignTable(int pax){
        this.pax = pax;
        this.tableStatus = Level.OCCUPIED;
    }
    //free table
    public void freeTable(){
        this.pax = 0;
        this.tableStatus = Level.FREE;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table: %d - MAX: %d\n", id, capacity));
        reservations.forEach((k, v) -> sb.append(String.format("\tDate: %s/%s %s: %s\n",
                k.getDayOfMonth(), k.getMonth(),
                k.getHour(), v)));
        return sb.toString();
    }
}
