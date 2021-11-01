package Restaurant;

import java.util.ArrayList;

public class Payment {
    private ArrayList<Table> tables;
    private double total;
    public Payment(){
        this.tables = new ArrayList<>();
        this.total = 0;
    }
    public double getTotal(){
        int sum = 0;
        for(int i = 0; i < tables.size();i++){
            sum += tables.get(i).getOrder().
        }
    }
}


