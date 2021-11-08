package Restaurant;


import java.util.ArrayList;
import java.time.LocalDateTime;

public class TransHistDay {
    private LocalDateTime date;
    private ArrayList<TransHistItem> transList;
    public TransHistDay(LocalDateTime date){
        this.date = date;
        this.transList  = new ArrayList<TransHistItem>();
    }
    public LocalDateTime getDate(){
        return this.date;
    }
    public ArrayList<TransHistItem> getTransList(){
        return transList;
    }
    public TransHistItem findTransHist(String name, double price) {
        for (TransHistItem i : this.transList) {
            if (i.getItem() == name && i.getPrice() == price) {
                return i;
            }
        }
        return null;
    }
}
