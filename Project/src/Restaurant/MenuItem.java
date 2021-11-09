package Restaurant;
import java.util.ArrayList;

public class MenuItem {
    protected int itemID;
    protected String itemName;
    protected double price;
    protected String description;
    public MenuItem(String itemName,int itemID, double price,String description){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }
    public void setItemID(int ID){
        this.itemID = ID;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public String getDescription() {
        return description;
    }

}

class  SetPackage extends MenuItem {
    ArrayList<MenuItem> setItems = new ArrayList<MenuItem>();
    double maxDrinkPrice;
    int numMainCourse = 1;
    int numDrink = 1;
    int numSide = 1;

    public void setMaxDrinkPrice(double maxDrinkPrice) {
        this.maxDrinkPrice = maxDrinkPrice;
    }

    public double getMaxDrinkPrice() {
        return maxDrinkPrice;
    }

    public SetPackage(String itemName, int itemID, double price, String description/*, int numMainCourse, int numDrink, int numSide*/){
        super(itemName,itemID,price, description);
//        this.numMainCourse = numMainCourse;
//        this.numDrink = numDrink;
//        this.numSide = numSide;
        // ^ if wanna have different combinations
    }

    // when customer selects set package from the menu, create another instance of set package to be put into orderitems.
    // SetPackage student_meal = new SetPackage("student meal", 10, 10);
    // ^ student_meal will be shown on the menu but when the customer orders this, create another object (shown below)
    // SetPackage set1 = new SetPackage(student_meal.getItemName(), student_meal.getItemID(),student_meal.getPrice());
    // selection of menuitems done by the customers.
    public void addMainCourse(MenuItem mainCourse){
        if (numMainCourse > 0) {
            setItems.add(mainCourse);
            numMainCourse--;
        }else{
            System.out.println("Error, reached maximum number of main course(s).");
        }
    }

    public void addDrink(MenuItem drink){
        if (numDrink > 0){
            setItems.add(drink);
            numDrink--;
        }else{
            System.out.println("Error, reached maximum number of drink(s).");
        }
    }

    public void addSide(MenuItem side) {
        if (numDrink > 0) {
            setItems.add(side);
            numSide--;
        } else {
            System.out.println("Error, reached maximum number of side(s).");
        }
    }
    public ArrayList<MenuItem> getSetItems(){
        return this.setItems;
    }
}