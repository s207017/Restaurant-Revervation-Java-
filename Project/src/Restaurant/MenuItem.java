package Restaurant;
import java.util.ArrayList;


/**
 * This is an entity class.
 * An instance of this class represents a single menu item.
 */
public class MenuItem {
    /**
     * The item ID of this menu item.
     */
    protected int itemID;
    /**
     * The item name of this menu item.
     */
    protected String itemName;
    /**
     * The price of this menu item.
     */
    protected double price;
    /**
     * The description of this menu item.
     */
    protected String description;

    /**
     * The constructor for MenuItem.
     * @param itemName The item ID of this menu item.
     * @param itemID The item name of this menu item.
     * @param price The price of this menu item.
     * @param description The description of this menu item.
     */
    public MenuItem(String itemName,int itemID, double price,String description){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }

    /**
     * Gets the price of this menu item.
     * @return The price of this menu item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the name of this menu item.
     * @return The name of this menu item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the item ID of this menu item.
     * @return The item ID of this menu item.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Gets the description of this menu item.
     * @return The description of this menu item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the item ID of this menu item.
     * @param ID The item ID of this menu item.
     */
    public void setItemID(int ID){
        this.itemID = ID;
    }

    /**
     * Setter for the name of this menu item.
     * @param itemName The name of this menu item.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Setter for the price of this menu item.
     * @param price The item price of this menu item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Setter for the description of this menu item.
     * @param description The description of this menu item.
     */
    public void setDescription(String description) {
        this.description = description;
    }



}
