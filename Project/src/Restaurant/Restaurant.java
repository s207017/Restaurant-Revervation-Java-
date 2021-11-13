package Restaurant;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Restaurant {
    private ArrayList<Table> tableList = new ArrayList<Table>();
    private ArrayList<TransHistDay> transactionHistory = new ArrayList<TransHistDay>();
    private ArrayList<Staff> staffList = new ArrayList<Staff>();
    private Menu menu = new Menu();

    public Restaurant() throws IOException {
        BufferedReader tablesText = new BufferedReader(
                new FileReader("./textfiles/tables.txt")
        );

        // for reading items from text file
        int tableNum = 0;
        int tableCap =0;

        int x = 0;
        String s;
        while ((s = tablesText.readLine()) != null) {
            if (x % 2 == 0) {
                tableNum = Integer.parseInt(s);
            } else if (x % 2 == 1) {
                tableCap = Integer.parseInt(s);
                Table newTable = new Table(tableNum,tableCap);
                tableList.add(newTable);
            }
            x++;
        }
        tablesText.close();

        BufferedReader staffText = new BufferedReader(
                new FileReader("./textfiles/staff.txt")
        );

        // for reading items from text file
        int staffID = 0;
        while ((s = staffText.readLine()) != null) {
            staffID = Integer.parseInt(s);
            Staff newStaff = new Staff(staffID);
            staffList.add(newStaff);
        }
        staffText.close();

        BufferedReader transHistDayText = new BufferedReader(
                new FileReader("./textfiles/transhistday.txt")
        );

        // for reading items from text file
        TransHistDay dailyRecord =null;
        LocalDateTime dateInput = null;
        String transHistItemName = null;
        int qty = 0;
        double price;
        x = 0;
        while ((s = transHistDayText.readLine()) != null) {
            if (s.equals("last-record")){
                transactionHistory.add(dailyRecord);
                break;
            }
            if (s.equals("new-record")){
                if (x!=0){
                    transactionHistory.add(dailyRecord);
                }
                dateInput = LocalDateTime.parse(transHistDayText.readLine());
                dailyRecord = new TransHistDay(dateInput);
                s = transHistDayText.readLine();
                x=0;
            }
            switch (x%3){
                case 0:
                    transHistItemName = s;
                    break;
                case 1:
                    qty = Integer.parseInt(s);
                    break;
                case 2:
                    price = Double.parseDouble(s);
                    dailyRecord.addTransHistItem(transHistItemName,qty,price);
                    break;
            }
            x++;
        }
        transHistDayText.close();

        BufferedReader reservationsText = new BufferedReader(
                new FileReader("./textfiles/reservationsrecords.txt")
        );

        // for reading items from text file
        String[] oneRowData;
        String nameInput = null;
        int telInput,paxInput,tableNumInput = 0;
        while ((s = reservationsText.readLine()) != null) {
            if (s.equals("TABLE")) {
                s = reservationsText.readLine();
                tableNumInput = Integer.parseInt(s);
            } else {
                oneRowData = s.split(";");
                dateInput = LocalDateTime.parse(oneRowData[0]);
                nameInput = oneRowData[1];
                paxInput = Integer.parseInt(oneRowData[2]);
                telInput = Integer.parseInt(oneRowData[3]);
                getTableFromTableNum(tableNumInput).reserve(dateInput, paxInput, nameInput, telInput);
            }
        }
        reservationsText.close();
    }

    public Menu getMenu(){return this.menu;}

    public ArrayList<Staff> getStaffList(){
        return staffList;
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
    public void addTable(int tableCapacity) throws IOException {
        tableList.add(new Table(tableList.size()+1,tableCapacity));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/tables.txt", false)
        );
        for (Table table: tableList){
            bw.write(table.getTableNum()+"\n"+table.getCapacity()+"\n");
        }
        bw.close();
        BufferedWriter bw2 = new BufferedWriter(
                new FileWriter("./textfiles/tables.txt", false)
        );
        bw2.write("TABLE\n"+(tableList.size()+1)+"\n");
        bw2.close();
    }

    public void writeReservationsToTextFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/reservationsrecords.txt", false)
        );
        for (Table t: tableList){
            bw.write("TABLE\n");
            bw.write(t.getTableNum()+"\n");
            for (Map.Entry<LocalDateTime,Reservation> entry : t.getReservations().entrySet()) {
                bw.write(entry.getKey() + ";" + entry.getValue().getName() + ";" + entry.getValue().getPax() + ";" + entry.getValue().getTel()+ "\n");
            }
        }
        bw.close();

    }


    /**
     * Returns the table id for date & time entered.
     * Returns -1 if no table is found.
     *
     * @param pax the number of guests. table capacity should be greater than or equal to this.
     * @param name the name of the customer making reservation
     * @param tel the phone number of the customer
     * @return returns first available table, doesnt iterate through all unless no table. -1 if no table is found.
     */
    public int reserveTable(LocalDateTime arrivalDateTime, int pax, String name, int tel) throws IOException {

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
    public ArrayList<Table> getAvailableTables(int pax, LocalDateTime currentDateTime) throws IOException {
        ArrayList<Table> availableTables= new ArrayList<Table>();
        for (Table t: tableList){
            //removes overdue reservations
            t.updateReservationsAccordingToCurrentTime();
            //updates levels free & reserved
            t.updateTableStatus();
            if (t.getTableStatus()== Table.Level.FREE && t.getCapacity()>=pax){
                availableTables.add(t);
            }
        }
        return availableTables;
    }


    public Table removeReservation(LocalDateTime removeDateTime, int tel){
        for (Table t : tableList) {
            Iterator<Map.Entry<LocalDateTime, Reservation>>
                    iterator = t.getReservations().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<LocalDateTime, Reservation> entry = iterator.next();

                if (removeDateTime.isEqual(entry.getKey()) &&
                        entry.getValue().getTel() == (tel)) {
                    iterator.remove();
                    return t;
                }
            }
        }
        //has iterated through all tables, no free tables
        return null;
    }

    public Table getTableFromReservationHashMap(LocalDateTime dateTimeToCheck, int tel){
        for (Table t : tableList){
            if (t.getReservations().containsKey(dateTimeToCheck) &&
                t.getReservations().get(dateTimeToCheck).getTel()==tel){
                return t;
            }
        }
        return null;
    }


    /**
     * @return returns string that contains each table and its respective status and capacity
     * as well as the total number of free, occupied and reserved tables.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int reserved=0, occupied=0, free=0;
        sb.append("\nRESTAURANT TABLES\n");
        for (Table t : tableList) {
            //removes overdue reservations
            try {
                t.updateReservationsAccordingToCurrentTime();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //updates levels free & reserved
            t.updateTableStatus();
            if (t.getTableStatus()== Table.Level.FREE){
                sb.append(String.format("Table: %d - Capacity: %d - Status: Available\n", t.getTableNum(), t.getCapacity()));
                free++;
            }
            else if (t.getTableStatus()==Table.Level.OCCUPIED) {
                sb.append(String.format("Table: %d - Capacity: %d - Status: Occupied - People Seated: %d\n", t.getTableNum(), t.getCapacity(), t.getPax()));
                occupied++;
            }
            else{
                sb.append(String.format("Table : %d - Capacity: %d - Status: Reserved\n", t.getTableNum(), t.getCapacity()));
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

    public void addStaff(int ID) throws IOException {
        this.staffList.add(new Staff(ID));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/staff.txt", false)
        );

        for (Staff staff: staffList){
            bw.write(staff.getStaffID()+"\n");
        }
        bw.close();
    }

    public Staff getStaffFromID(int ID){
        for(Staff s: staffList){
            if(ID == s.getStaffID()){
                return s;
            }
        }
        return null;
    }
}

