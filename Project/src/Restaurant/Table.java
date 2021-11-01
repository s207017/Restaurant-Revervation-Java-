package Restaurant;

import java.util.ArrayList;

public class Table {
    private int tableNum;
    private int tableCap;
    private int pax;
    private Order order;
    enum Level {
        FREE,
        RESERVED,
        OCCUPIED
    }
    private Level tableStatus;
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    public Table(int tableNum, int tableCap, int pax){
        this.tableNum = tableNum;
        this.tableCap = tableCap;
        this.pax = pax;
        this.tableStatus = Level.FREE;
    }

    //getters
    public int getTableNum(){
        return tableNum;
    }
    public int getTableCap(){
        return tableCap;
    }
    public Level getTableStatus(){
        return tableStatus;
    }

    //reserve table
    public void reserveTable(int pax){
        if(pax<=tableCap){
            this.pax = pax;
            this.tableStatus = Level.RESERVED;
        }
    }

    //occupy table
    public void occupyTable(){
        if(pax<=tableCap){
            this.pax = pax;
            this.tableStatus = Level.OCCUPIED;
        }
    }
    public void freeTable(){
        this.pax = 0;
        this.tableStatus = Level.FREE;
    }
    public void assignOrder(Order order){
        this.order = order;
    }
    public Order getOrder(){
        return this.order;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }
}
