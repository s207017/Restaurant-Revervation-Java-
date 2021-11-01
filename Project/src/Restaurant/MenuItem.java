package Restaurant;
import java.util.ArrayList;

public class MenuItem {
    private String itemName;
    private int itemID;
    private double price;
    public MenuItem(String itemName,int itemID, double price){
        this.itemName = itemName;
        this.itemID = itemID;
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
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

}

class Drink extends MenuItem {
    public Drink(String itemName,int itemID, double price){
        super(itemName,itemID,price);
    }
}

class SetPackage extends MenuItem {
    ArrayList<MenuItem> setItems = new ArrayList<MenuItem>();
    int numMainCourse = 1;
    int numDrink = 1;
    int numSide = 1;
    public SetPackage(String itemName,int itemID, double price/*, int numMainCourse, int numDrink, int numSide*/){
        super(itemName,itemID,price);
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
    public void addMainCourse(MainCourse mainCourse){
        if (numMainCourse > 0) {
            setItems.add(mainCourse);
            numMainCourse--;
        }else{
            System.out.println("Error, reached maximum number of main course(s).");
        }
    }

    public void addDrink(Drink drink){
        if (numDrink > 0){
            setItems.add(drink);
            numDrink--;
        }else{
            System.out.println("Error, reached maximum number of drink(s).");
        }
    }

    public void addSide(Side side){
        if (numDrink >0) {
            setItems.add(side);
            numSide--;
        }else{
            System.out.println("Error, reached maximum number of side(s).");
        }
    }
}

class MainCourse extends MenuItem {
    public MainCourse(String itemName,int itemID, double price){
        super(itemName,itemID,price);
    }
}

class Side extends MenuItem {
    public Side(String itemName,int itemID, double price){
        super(itemName,itemID,price);
    }
}

class Desert extends MenuItem {
    public Desert(String itemName,int itemID, double price){
        super(itemName,itemID,price);
    }
}
// - Pizza          8.00
// - Set A:         15.00
//   - Green Tea    0.00