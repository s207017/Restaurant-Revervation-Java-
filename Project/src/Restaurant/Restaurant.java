package Restaurant;

import java.util.ArrayList;

public class Restaurant {
    private int numTable;
    private ArrayList<Table> tables = new ArrayList<Table>();

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public void setTable(ArrayList<Table> table) {
        this.tables = table;
    }

    public int getNumTable() {
        return numTable;
    }

    public ArrayList<Table> getTable() {
        return tables;
    }

    public void addTable(Table table){
        this.tables.add(table);
        this.numTable++;
    }

    public void getAvailableTables(int pax){
        for (Table table: tables){
            if (table.getTableStatus()== Table.Level.FREE && table.getTableCap()>=pax){
                System.out.println(table.getTableNum());
            }
        }
    }

    public void getReservations(){
        for (Table table: tables) {
            if (table.getReservations() != null){
                System.out.println(table.getTableNum());
                for (Reservation res: table.getReservations()){
                    res.printReservationDetails();
                }
              
            }
        }
    }
}
