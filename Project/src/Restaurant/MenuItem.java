package Restaurant;
<<<<<<< Updated upstream
=======
import java.util.ArrayList;
>>>>>>> Stashed changes

public class MenuItem {
    private String itemName;
    private int itemID;
    private double price;
    public MenuItem(String itemName,int itemID, double price){
        this.itemName = itemName;
        this.itemID = itemID;
        this.price = price;
    }

}

class Drink extends MenuItem {
    public Drink(String itemName,int itemID, double price){
        super(itemName,itemID,price);
    }
}

class SetPackage extends MenuItem {
    public SetPackage(String itemName,int itemID, double price){
        super(itemName,itemID,price);
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