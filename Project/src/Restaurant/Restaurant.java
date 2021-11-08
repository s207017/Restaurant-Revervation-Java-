package Restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Restaurant {
    private ArrayList<Table> tableList = new ArrayList<Table>();
    private ArrayList<TransHistDay> transactionHistory = new ArrayList<TransHistDay>();

    public Restaurant(){
        tableList.add(new Table(1,2));
        tableList.add(new Table(2,2));
        tableList.add(new Table(3,4));
        tableList.add(new Table(4,4));
        tableList.add(new Table(5,4));
        tableList.add(new Table(6,6));
        tableList.add(new Table(7,8));
        tableList.add(new Table(8,10));
    }

    public ArrayList<TransHistDay> getTransactionHistory(){
        return transactionHistory;
    }

    //setters

    //getters
    public ArrayList<Table> getTableList() {
        return tableList;
    }

    //when restaurant buys new table and wants to add them
    public void addTable(int tableCapacity){
        tableList.add(new Table(tableList.size()+1,tableCapacity));
    }


    /**
     * Returns the table id for date & time entered.
     * Returns -1 if no table is found.
     *
     * @param dateString the date and time of the request in the format "dd/MM/yyyy HH"
     * @param pax the number of guests. table capacity should be greater than or equal to this.
     * @param name the name of the customer making reservation
     * @param tel the phone number of the customer
     * @return returns first available table, doesnt iterate through all unless no table. -1 if no table is found.
     */
    public int reserveTable(String dateString, int pax, String name, String tel) {
        LocalDateTime arrivalDateTime = PeriodGetter.getDate();
        //returns date in dateTime format where the minutes are 00

        // get first available table
        for (Table table : tableList) {
            if (table.getCapacity() >= pax && table.isFree(arrivalDateTime)) {
                table.reserve(arrivalDateTime, pax, name, tel);
                return table.getTableNum();
            }
        }
        return -1;
    }

    /**
     *
     * @param pax checks tables against the pax of incomers
     * @param currentDateTime gets the current dateTime from the main()
     * @return returns an arraylist of Table objects that have status=FREE
     */
    //use arrayList.isEmpty() to find out if the array returned has any tables at all.
    public ArrayList<Table> getAvailableTables(int pax, LocalDateTime currentDateTime){
        ArrayList<Table> availableTables= new ArrayList<Table>();
        for (Table t: tableList){
            //removes overdue reservations
            t.updateReservationsHashMap(currentDateTime);
            //updates levels free & reserved
            t.updateLevel(currentDateTime);
            if (t.getTableStatus()== Table.Level.FREE && t.getCapacity()>=pax){
                availableTables.add(t);
            }
        }
        return availableTables;
    }


    /**
     * @return returns string that contains each table and its respective status and capacity
     * as well as the total number of free, occupied and reserved tables.
     */
    public String toString(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        int reserved=0, occupied=0, free=0;
        sb.append("\nRESTAURANT TABLES\n");
        for (Table t : tableList) {
            //removes overdue reservations
            t.updateReservationsHashMap(currentDateTime);
            //updates levels free & reserved
            t.updateLevel(currentDateTime);
            if (t.getTableStatus()== Table.Level.FREE){
                sb.append("Table: %d - Capacity: %d - Status: Available\n", t.getTableNum(), t.getCapacity());
                free++;
            }
            else if (t.getTableStatus()==Table.Level.OCCUPIED) {
                sb.append("Table: " + t.getTableNum() + " - Capacity: " + t.getCapacity() + " - Status: Occupied - People Seated: " + t.getPax() + "\n");
                occupied++;
            }
            else{
                sb.append(String.format("Table : %d - Capacity: %d - Status: Reserved\n"), t.getTableNum(), t.getCapacity());
                reserved++;
            }
        }
        sb.append(String.format("Tables Available: %d - Tables Occupied: %d - Tables Reserved: %d\n", free, occupied, reserved));
        return sb.toString();

    }

    public Table getTableFromTableNum(int tableNum){
        for (Table t: tableList){
            if (t.getTableNum()==tableNum){
                return t;
            }
        }
        return null;
    }
}

