package Restaurant;

/**
 * An instance of this entity class represents the collective amount of a certain item sold in a TransHistDay
 */
public class TransHistItem {
    /**
     * The name of the menu item in this TransHistItem
     */
    private String item;
    /**
     * The quantity of the menu item in this TransHistItem
     */
    private int quantity;
    /**
     * The price of the menu item in this TransHistItem
     */
    private double price;

    /**
     * Constructor takes item name, quantity, and price to create a new instance of TransHist Item
     * @param item Name of TransHistItem to be created
     * @param quantity Quantity of TransHistItem to be created
     * @param price Price of TransHistItem to be created
     */
    public TransHistItem(String item,int quantity,double price){
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Gets the name of this TransHistItem
     * @return Returns the name of the item as a String
     */
    public String getItem(){return this.item;}

    /**
     * Gets the quantity of this TransHistItem
     * @return Returns the quantity of this TransHistItem as an integer
     */
    public int getQuantity(){return this.quantity;}

    /**
     * Gets the price of this TransHisItem
     * @return Returns the price of this TransHistItem as a double
     */
    public double getPrice(){return this.price;}

    /**
     * Sets the name of this TransHistItem
     * @param item Name of this TransHistItem
     */
    public void setItem(String item){
        this.item = item;
    }

    /**
     * Sets the quantity of this TransHistItem by adding to it
     * @param quantity Quantity of item to be added to this TransHistItem
     */
    public void setQuantity(int quantity){
        this.quantity += quantity;
    }

    /**
     * Sets the price of this TransHist Item
     * @param price Price of this TransHistItem
     */
    public void setPrice(double price){
        this.price = price;
    }

}
