package Restaurant;

import java.util.ArrayList;

public class Restaurant {
    private int numTable;
    private ArrayList<Table> tables = new ArrayList<Table>();

    public Restaurant(){
        tables.add(new Table(1,2,0));
        tables.add(new Table(2,2,0));
        tables.add(new Table(3,4,0));
        tables.add(new Table(4,4,0));
        tables.add(new Table(5,4,0));
        tables.add(new Table(6,6,0));
        tables.add(new Table(7,8,0));
        tables.add(new Table(7,10,0));
    }

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

    private ArrayList<Payment> transactionHistory = new ArrayList<Payment>();
    public void addEntry(Payment payment){
        transactionHistory.add(payment);
    }


}

