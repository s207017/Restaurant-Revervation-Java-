package Restaurant;

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
}

