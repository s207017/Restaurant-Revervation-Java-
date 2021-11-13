package Restaurant;


import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * An instance of this class represents the transaction history for one day
 */
public class TransHistDay {
    /**
     * Date of this TransHistDay
     */
    private LocalDateTime date;
    /**
     * Arraylist to hold all records of transactions for the day in the form of TransHistItems
     */
    private ArrayList<TransHistItem> transList;

    /**
     * Constructor takes in date variable and instantiates an arraylist to hold the TransHistItems
     * @param date date of this TransHistDay
     */
    public TransHistDay(LocalDateTime date){
        this.date = date;
        this.transList  = new ArrayList<TransHistItem>();
    }

    /**
     * This method is used to get the date of this TransHistDay
     * @return Returns the date of this TransHistDay
     */
    public LocalDateTime getDate(){
        return this.date;
    }

    /**
     * This method is used to get the list of transactions
     * @return Returns the Arraylist of TransHistItems
     */
    public ArrayList<TransHistItem> getTransList(){
        return transList;
    }

    /**
     *This method is used to find a specific item by matching item name and price
     * The method returns the TransHistItem object if it finds it
     * If it does not, it returns null
     * @param name Name of item to be found in this TransHistDay
     * @param price Price of item to be found in this TransHistDay
     * @return Returns TransHistItem in TransHistDay with corresponding name and price
     */
    public TransHistItem findTransHist(String name, double price) {
        for (TransHistItem i : this.transList) {
            if (i.getItem() == name && i.getPrice() == price) {
                return i;
            }
        }
        return null;
    }

    /**
     * This method is used to add items to this TransHistDay
     * The method checks for the existence of the item, and adds to its quantity
     * If the item does not exist in the day yet, the method will create a new TransHistItem to hold it
     * @param name Name of this item to be added to this TransHistDay
     * @param quantity Quantity of items to be added to this TransHistDay
     * @param price Price of item to be added to this day of TransHistDay
     */
    public void addTransHistItem(String name, int quantity, double price){
        for(TransHistItem t: this.transList){//Checking if item is inside already
            if(t.getItem() == name && t.getPrice() == price) {//Found a matching item in list
                t.setQuantity(quantity);
                return;
            }
        }
        //For loop fully iterated, item doesn't exist inside
        transList.add(new TransHistItem(name, quantity, price));
    }
}
