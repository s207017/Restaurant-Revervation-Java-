package Restaurant;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> mainCourseItems = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> sideItems = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> drinkItems = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> dessertItems = new ArrayList<MenuItem>();
    private ArrayList<SetPackage> setPackageItems = new ArrayList<SetPackage>();

    public Menu(){
        mainCourseItems.add(new MenuItem("Dry Truffle Ramen",101,6.90,"Freshly made ramen with house made sauce and truffle oil"));
        mainCourseItems.add(new MenuItem("Tonkotsu Shouyu Ramen",102,7.9,"Creamy Tonkotsu soup stock flavoured with a secret blend of Japanese shouyu and dried fishes"));
        mainCourseItems.add(new MenuItem("Original Tonkotsu Ramen",103,7.9,"Our best seller"));

        sideItems.add(new MenuItem("Gyoza",201,5,"Dumplings"));
        sideItems.add(new MenuItem("Ebi Fry",202,4.9,"Prawn"));

        drinkItems.add(new MenuItem("Green Tea",301,1.5,"Refreshing green tea"));
        drinkItems.add(new MenuItem("Mineral Water",302,1.5,"Plain ol bottled water"));
        drinkItems.add(new MenuItem("Can Drink",303,1.5,"Choose any canned drink from our fridge"));

        dessertItems.add(new MenuItem("Ice Cream",401,4.50,"A yummy ice cold refresher"));

        setPackageItems.add(new SetPackage("Set Meal A",501,13.40,"Tonkotsu Ramen + Ebi Fry + Drink"));
        setPackageItems.add(new SetPackage("Set Meal B",501,13.70,"Tonkotsu Ramen + Gyoza + Drink"));
    }

    public ArrayList<MenuItem> getMainCourseItems() {
        return mainCourseItems;
    }

    public ArrayList<MenuItem> getDessertItems() {
        return dessertItems;
    }

    public ArrayList<MenuItem> getDrinkItems() {
        return drinkItems;
    }

    public ArrayList<MenuItem> getSideItems() {
        return sideItems;
    }

    public ArrayList<SetPackage> getSetPackageItems() {
        return setPackageItems;
    }

    //print functions!
    public void printMenu(){
        System.out.println("Suparakki Ramen Menu:");
        System.out.println("-".repeat(100));
        System.out.println("-".repeat(100));
        System.out.println("Main Courses:");
        for(MenuItem m : mainCourseItems){
            System.out.format("%4d.%-60s$%.2f%n", m.getItemID(), m.getItemName(), m.getPrice());
            System.out.printf("     %s\n", m.getDescription());
        }
        System.out.println("-".repeat(100));
        System.out.println("Sides:");
        for(MenuItem s : sideItems){
            System.out.printf("%4d.%-60s$%.2f%n", s.getItemID(), s.getItemName(), s.getPrice());
            System.out.printf("     %s\n", s.getDescription());
        }
        System.out.println("-".repeat(100));
        System.out.println("Drinks:");
        for(MenuItem d : drinkItems){
            System.out.printf("%4d.%-60s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
            System.out.printf("     %s\n", d.getDescription());
        }
        System.out.println("-".repeat(100));
        System.out.println("Deserts:");
        for(MenuItem de : dessertItems){
            System.out.printf("%4d.%-60s$%.2f%n", de.getItemID(), de.getItemName(), de.getPrice());
            System.out.printf("     %s\n", de.getDescription());
        }
        System.out.println("-".repeat(100));
        System.out.println("Set Packages:");
        for(SetPackage sp : setPackageItems){
            System.out.printf("%4d.%-60s$%.2f%n", sp.getItemID(), sp.getItemName(), sp.getPrice());
            System.out.printf("     %s\n", sp.getDescription());

        }
        System.out.println("-".repeat(100));
    }
    public void printMainCourse(){
        System.out.println("-".repeat(100));
        System.out.println("Main Courses:");
        for(MenuItem m : mainCourseItems){
            System.out.format("%4d.%-60s$%.2f%n", m.getItemID(), m.getItemName(), m.getPrice());
            System.out.printf("     %s\n", m.getDescription());
        }
        System.out.println("-".repeat(100));
    }
    public void printSide(){
        System.out.println("-".repeat(100));
        System.out.println("Sides:");
        for(MenuItem s : sideItems){
            System.out.printf("%4d.%-60s$%.2f%n", s.getItemID(), s.getItemName(), s.getPrice());
            System.out.printf("     %s\n", s.getDescription());
        }
        System.out.println("-".repeat(100));

    }
    public void printDrink(){
        System.out.println("-".repeat(100));
        System.out.println("Drinks:");
        for(MenuItem d : drinkItems){
            System.out.printf("%4d.%-60s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
            System.out.printf("     %s\n", d.getDescription());
        }
        System.out.println("-".repeat(100));
    }
    public void printDesert(){
        System.out.println("-".repeat(100));
        System.out.println("Deserts:");
        for(MenuItem de : dessertItems){
            System.out.printf("%4d.%-60s$%.2f%n", de.getItemID(), de.getItemName(), de.getPrice());
            System.out.printf("     %s\n", de.getDescription());
        }
        System.out.println("-".repeat(100));
    }
    public void printSetPackage(){
        System.out.println("-".repeat(100));
        System.out.println("Set Packages:");
        for(SetPackage sp : setPackageItems){
            System.out.printf("%4d.%-60s$%.2f%n", sp.getItemID(), sp.getItemName(), sp.getPrice());
            System.out.printf("     %s\n", sp.getDescription());

        }
        System.out.println("-".repeat(100));
    }

    //for Order
    public MenuItem getMenuItemFromID(int ID){
        if (100<ID && ID<=100+ mainCourseItems.size()){
            for (MenuItem m: mainCourseItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (200<ID && ID<=200+sideItems.size()){
            for (MenuItem m: sideItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (300<ID && ID<=300+ drinkItems.size()){
            for (MenuItem m: drinkItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (400<ID && ID<=400+ dessertItems.size()){
            for (MenuItem m: dessertItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (500<ID && ID<=500+ setPackageItems.size()){
            for (SetPackage m: setPackageItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        return null; //this would only happen if the ID is invalid, which it wont be because of the IDexists function
    }
    public boolean IDExists(int ID){
        if ((100<ID && ID<=100+ mainCourseItems.size()) ||
                (200<ID && ID<=200+sideItems.size()) ||
                (300<ID && ID<=300+ drinkItems.size()) ||
                (400<ID && ID<=400+ dessertItems.size()) ||
                (500<ID && ID<=500+ setPackageItems.size())){
            return true;
        }
        else return false;
    }


    public void createNewMenuItem(String name, int menuItemType, double price, String description){
        int ID = 0;
        switch (menuItemType){
            case 1: //maincourse
                for (MenuItem m: mainCourseItems){ //gets the last itemID in the arraylist
                    ID=m.getItemID();
                }
                MenuItem mainCourse = new MenuItem(name, ID+1, price, description); //ID+1 is to add the item at the next point
                break;
            case 2: //side
                for (MenuItem m: sideItems){
                    ID=m.getItemID();
                }
                MenuItem side = new MenuItem(name, ID+1, price, description);
                break;
            case 3:
                for (MenuItem m: drinkItems){
                    ID=m.getItemID();
                }
                MenuItem drink = new MenuItem(name, ID+1, price, description);
                break;
            case 4:
                for (MenuItem m: dessertItems){
                    ID=m.getItemID();
                }
                MenuItem desert = new MenuItem(name, ID+1, price, description);
                break;
            case 5:
                for (SetPackage m: setPackageItems){
                    ID=m.getItemID();
                }
                SetPackage setPackage = new SetPackage(name, ID+1, price, description);
                break;
            default:
                System.out.println("Wrong input into createNewMenuItem.");
        }
    }

    //updating of menuItems done directly in the application!

    public void removeMenuItem(int menuItemType, int menuItemID){
        int toUpdateID;
        switch(menuItemType){
            case 1:
                mainCourseItems.remove(menuItemID-101);
                toUpdateID = menuItemID-101; //the item after the item that was removed
                for(int i=toUpdateID;i< mainCourseItems.size();i++){
                    mainCourseItems.get(i).setItemID(101+i);
                }
                break;
            case 2:
                sideItems.remove(menuItemID-201);
                toUpdateID = menuItemID-201; //the item after the item that was removed
                for(int i=toUpdateID;i< sideItems.size();i++){
                    sideItems.get(i).setItemID(201+i);
                }
                break;
            case 3:
                drinkItems.remove(menuItemID-301);
                toUpdateID = menuItemID-301; //the item after the item that was removed
                for(int i=toUpdateID;i< drinkItems.size();i++){
                    drinkItems.get(i).setItemID(301+i);
                }
                break;
            case 4:
                dessertItems.remove(menuItemID-401);
                toUpdateID = menuItemID-401; //the item after the item that was removed
                for(int i=toUpdateID;i< dessertItems.size();i++){
                    dessertItems.get(i).setItemID(401+i);
                }
                break;
            case 5:
                setPackageItems.remove(menuItemID-501);
                toUpdateID = menuItemID-501; //the item after the item that was removed
                for(int i=toUpdateID;i< setPackageItems.size();i++){
                    setPackageItems.get(i).setItemID(501+i);
                }     
                break;
        }
    }
}