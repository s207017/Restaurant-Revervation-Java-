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

/**
 * This is an entity class.
 * An instance of this class represents a single set package.
 */
class SetPackage extends MenuItem {
    /**
     * Arraylist of menu items that are part of the set package.
     */
    private ArrayList<MenuItem> setItems = new ArrayList<MenuItem>();
    /**
     * The maximum price of the drinks offered for this set package.
     */
    private double maxDrinkPrice;
    /**
     * The number of main courses offered for this set package.
     */
    private int numMainCourse = 1;
    /**
     * The number of drinks offered for this set package.
     */
    private int numDrink = 1;
    /**
     * The number of sides offered for this set package.
     */
    private int numSide = 1;

    /**
     * The constructor for SetPackage.
     * @param itemName The name of this set package.
     * @param itemID The item ID of this set package.
     * @param price The price of this set package.
     * @param description The description of this set package.
     */
    public SetPackage(String itemName, int itemID, double price, String description){
        super(itemName,itemID,price, description);
    }

    /**
     * Gets the list of menu items that are part of this set package.
     * @return Arraylist of menu items that are part of this set package.
     */
    public ArrayList<MenuItem> getSetItems(){
        return this.setItems;
    }

    /**
     * Gets the maximum price of the drinks offered for this set package.
     * @return The maximum price of the drinks offered for this set package.
     */
    public double getMaxDrinkPrice() {
        return maxDrinkPrice;
    }

    /**
     * Setter for the maximum price of the drinks offered for this set package.
     * @param maxDrinkPrice The maximum price of the drinks offered for this set package.
     */
    public void setMaxDrinkPrice(double maxDrinkPrice) {
        this.maxDrinkPrice = maxDrinkPrice;
    }

    /**
     * Adds a main course item to the list of menu items in this set package.
     * @param mainCourse The main course menu item to add to this set package.
     */
    public void addMainCourse(MenuItem mainCourse){
        if (numMainCourse > 0) {
            setItems.add(mainCourse);
            numMainCourse--;
        }else{
            System.out.println("Error, reached maximum number of main course(s).");
        }
    }

    /**
     * Adds a drink item to the list of menu items in this set package.
     * @param drink The drink menu item to add to this set package.
     */
    public void addDrink(MenuItem drink){
        if (numDrink > 0){
            setItems.add(drink);
            numDrink--;
        }else{
            System.out.println("Error, reached maximum number of drink(s).");
        }
    }

    /**
     * Adds a side item to the list of menu items in this set package.
     * @param side The side menu item to add to this set package.
     */
    public void addSide(MenuItem side) {
        if (numDrink > 0) {
            setItems.add(side);
            numSide--;
        } else {
            System.out.println("Error, reached maximum number of side(s).");
        }
    }
}