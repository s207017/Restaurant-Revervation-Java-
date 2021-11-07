package Restaurant;

import java.util.ArrayList;
import java.util.Date;

public class TransHistDay {
    private Date date;
    private ArrayList<TransHistItem> transList;
    public TransHistDay(Date date){
        this.date = date;
        this.transList  = new ArrayList<TransHistItem>();
    }
    public Date getDate(){
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
