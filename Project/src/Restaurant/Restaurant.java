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

    public void getAvailableTables(){
        for (Table table: tables){
            if (table.getTableStatus()== Table.Level.FREE){
                System.out.println(table.getTableNum());
            }
        }
    }
}
