package Restaurant;

import java.util.ArrayList;

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
