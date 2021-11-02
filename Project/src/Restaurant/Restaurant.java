<<<<<<< Updated upstream
package Restaurant;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Restaurant { 
    private ArrayList<Table> tableList = new ArrayList<Table>();
    private ArrayList<Payment> transactionHistory = new ArrayList<Payment>();


    public Restaurant(){
        tableList.add(new Table(1,2,0));
        tableList.add(new Table(2,2,0));
        tableList.add(new Table(3,4,0));
        tableList.add(new Table(4,4,0));
        tableList.add(new Table(5,4,0));
        tableList.add(new Table(6,6,0));
        tableList.add(new Table(7,8,0));
        tableList.add(new Table(8,10,0));
    }
    public void addEntry(Payment payment){
        transactionHistory.add(payment);
    }
    public ArrayList<Payment> getTransactionHistory(){
        return transactionHistory;
    }

    //setters
    public void setTable(ArrayList<Table> table) {
        this.tableList = table;
    }

    //getters
    public ArrayList<Table> getTable() {
        return tableList;
    }


    //when restaurant buys new table and wants to add them
    public void addTable(int tableCapacity){
        tableList.add(new Table(tableList.size()+1,tableCapacity,0));
    }

    //prints the available tables
    public void getAvailableTables(){
        for (Table table: tableList){
            if (table.getTableStatus()== Table.Level.FREE){
              //smth
            }
        }
    }
    public ArrayList<Integer> getAvailableTables(int pax){
        ArrayList<Integer> availableTables= new ArrayList<Integer>();
        for (Table t: tableList){
            if (t.getTableStatus()== Table.Level.FREE && tableList.t()>=pax){
                availableTables.add(t.getTableNum());
            }
        }
        return availableTables;
    }

    public ArrayList<Integer> getReservedTables(int pax) {
        ArrayList<Integer> reservedTables= new ArrayList<Integer>();
        for (Table t: tableList){
            if (t.getTableStatus()== Table.Level.RESERVED){
                reservedTables.add(t.getTableNum());
            }
        }
        return reservedTables;

    }

    public ArrayList<Integer> getOccupiedTables(int pax) {
        ArrayList<Integer> occupiedTables= new ArrayList<Integer>();
        for (Table t: tableList){
            if (t.getTableStatus()== Table.Level.OCCUPIED){
                occupiedTables.add(t.getTableNum());
            }
        }
        return occupiedTables;

    }
}

=======
//package Restaurant;
//
//import java.util.ArrayList;
//
//public class Restaurant {
//    private int numTable;
//    private ArrayList<Table> tables = new ArrayList<Table>();
//
//    public Restaurant() {
//        this.numTable = 0;
//        this.tables = null;
//    }
//
//    public void setNumTable(int numTable) {
//        this.numTable = numTable;
//    }
//
//    public void setTable(ArrayList<Table> table) {
//        this.tables = table;
//    }
//
//    public int getNumTable() {
//        return numTable;
//    }
//
//    public ArrayList<Table> getTable() {
//        return tables;
//    }
//
//    public void addTable(Table table){
//        this.tables.add(table);
//        this.numTable++;
//    }
//
//    public void getAvailableTables(int pax){
//        for (Table table: tables){
//            if (table.getTableStatus()== Table.Level.FREE && table.getTableCap()>=pax){
//                System.out.println(table.getTableNum() + ": " + table.getTableCap());
//            }
//        }
//    }
//
//    public void getReservations(){
//        for (Table table: tables) {
//            if (table.getReservations() != null){
//                System.out.println(table.getTableNum());
//                for (Reservation res: table.getReservations()){
//                    res.printReservationDetails();
//                }
//            }
//        }
//    }
//}
>>>>>>> Stashed changes
