<<<<<<< Updated upstream
package Restaurant;

import com.sun.tools.javac.Main;

import java.util.ArrayList;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Set;

public class Menu {
    private ArrayList<Desert> dessertItems = new ArrayList<Desert>();
    private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
    private ArrayList<MainCourse> mainCourseItems = new ArrayList<MainCourse>();
    private ArrayList<SetPackage> setPackageItems = new ArrayList<SetPackage>();
    private ArrayList<Side> sideItems = new ArrayList<Side>();

    public Menu(){
        mainCourseItems.add(new MainCourse("Dry Truffle Ramen",101,6.90,"Freshly made ramen with house made sauce and truffle oil"));
        mainCourseItems.add(new MainCourse("Tonkotsu Shouyu Ramen",102,7.9,"Creamy Tonkotsu soup stock flavoured with a secret blend of Japanese shouyu and dried fishes"));
        mainCourseItems.add(new MainCourse("Original Tonkotsu Ramen",103,7.9,"Our best seller"));

        sideItems.add(new Side("Gyoza",201,5,"Dumplings"));
        sideItems.add(new Side("Ebi Fry",202,4.9,"Prawn"));

        drinkItems.add(new Drink("Green Tea",301,1.5,"Refreshing green tea"));
        drinkItems.add(new Drink("Mineral Water",302,1.5,"Plain ol bottled water"));
        drinkItems.add(new Drink("Can Drink",303,1.5,"Choose any canned drink from our fridge"));

        dessertItems.add(new Desert("Ice Cream",401,4.50,"A yummy ice cold refresher"));

        setPackageItems.add(new SetPackage("Set Meal A",501,13.40,"Tonkotsu Ramen + Ebi Fry + Drink"));
        setPackageItems.add(new SetPackage("Set Meal B",501,13.70,"Tonkotsu Ramen + Gyoza + Drink"));
    }

    public ArrayList<MainCourse> getMainCourseItems() {
        return mainCourseItems;
    }

    public ArrayList<Desert> getDessertItems() {
        return dessertItems;
    }

    public ArrayList<Drink> getDrinkItems() {
        return drinkItems;
    }

    public ArrayList<Side> getSideItems() {
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
        for(MainCourse m : mainCourseItems){
            System.out.format("%4d.%-60s$%.2f%n", m.getItemID(), m.getItemName(), m.getPrice());
            System.out.printf("     %s\n", m.getDescription());
        }
        System.out.println("-".repeat(100));
        System.out.println("Sides:");
        for(Side s : sideItems){
            System.out.printf("%4d.%-60s$%.2f%n", s.getItemID(), s.getItemName(), s.getPrice());
            System.out.printf("     %s\n", s.getDescription());
        }
        System.out.println("-".repeat(100));
        System.out.println("Drinks:");
        for(Drink d : drinkItems){
            System.out.printf("%4d.%-60s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
            System.out.printf("     %s\n", d.getDescription());
        }
        System.out.println("-".repeat(100));
        System.out.println("Deserts:");
        for(Desert de : dessertItems){
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
        for(MainCourse m : mainCourseItems){
            System.out.format("%4d.%-60s$%.2f%n", m.getItemID(), m.getItemName(), m.getPrice());
            System.out.printf("     %s\n", m.getDescription());
        }
        System.out.println("-".repeat(100));
    }
    public void printSide(){
        System.out.println("-".repeat(100));
        System.out.println("Sides:");
        for(Side s : sideItems){
            System.out.printf("%4d.%-60s$%.2f%n", s.getItemID(), s.getItemName(), s.getPrice());
            System.out.printf("     %s\n", s.getDescription());
        }
        System.out.println("-".repeat(100));

    }
    public void printDrink(){
        System.out.println("-".repeat(100));
        System.out.println("Drinks:");
        for(Drink d : drinkItems){
            System.out.printf("%4d.%-60s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
            System.out.printf("     %s\n", d.getDescription());
        }
        System.out.println("-".repeat(100));
    }
    public void printDesert(){
        System.out.println("-".repeat(100));
        System.out.println("Deserts:");
        for(Desert de : dessertItems){
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
            for (MainCourse m: mainCourseItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (200<ID && ID<=300+sideItems.size()){
            for (Side m: sideItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (300<ID && ID<=400+ drinkItems.size()){
            for (Drink m: drinkItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (400<ID && ID<=500+ dessertItems.size()){
            for (Desert m: dessertItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of maincourse
                }
            }
        }
        else if (500<ID && ID<=600+ setPackageItems.size()){
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
                (200<ID && ID<=300+sideItems.size()) ||
                (300<ID && ID<=400+ drinkItems.size()) ||
                (400<ID && ID<=500+ dessertItems.size()) ||
                (500<ID && ID<=600+ setPackageItems.size())){
            return true;
        }
        else return false;
    }


    public void createNewMenuItem(String name, int menuItemType, double price, String description){
        int ID;
        switch (menuItemType){
            case 1: //maincourse
                for (MainCourse m: mainCourseItems){ //gets the last itemID in the arraylist
                    ID=m.getItemID()
                }
                MainCourse mainCourse = new MainCourse(name, ID+1, price, description); //ID+1 is to add the item at the next point
                break;
            case 2: //side
                for (Side m: sideItems){
                    ID=m.getItemID();
                }
                Side side = new Side(name, ID+1, price, description);
                break;
            case 3:
                for (Drink m: drinkItems){
                    ID=m.getItemID();
                }
                Drink drink = new Drink(name, ID+1, price, description);
                break;
            case 4:
                for (Desert m: dessertItems){
                    ID=m.getItemID();
                }
                Desert desert = new Desert(name, ID+1, price, description);
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
=======
//package Restaurant;
//
//import java.util.ArrayList;
//
//public class Menu {
//    private ArrayList<Desert> dessertItems = new ArrayList<Desert>();
//    private ArrayList<Drink> drinkItems = new ArrayList<Drink>();
//    private ArrayList<MainCourse> mainCourseItems = new ArrayList<MainCourse>();
//    private ArrayList<SetPackage> setPackageItems = new ArrayList<SetPackage>();
//    private ArrayList<Side> sideItems = new ArrayList<Side>();
//
//    public Menu(){
//        mainCourseItems.add(new MainCourse("Dry Truffle Ramen",101,6.90,"Freshly made ramen with house made sauce and truffle oil"));
//        mainCourseItems.add(new MainCourse("Tonkotsu Shouyu Ramen",102,7.9,"Creamy Tonkotsu soup stock flavoured with a secret blend of Japanese shouyu and dried fishes"));
//        mainCourseItems.add(new MainCourse("Original Tonkotsu Ramen",103,7.9,"Our best seller"));
//
//        sideItems.add(new Side("Gyoza",201,5,"Dumplings"));
//        sideItems.add(new Side("Ebi Fry",202,4.9,"Prawn"));
//
//        drinkItems.add(new Drink("Green Tea",301,1.5,"Refreshing green tea"));
//        drinkItems.add(new Drink("Mineral Water",302,1.5,"Plain ol bottled water"));
//        drinkItems.add(new Drink("Can Drink",303,1.5,"Choose any canned drink from our fridge"));
//
//        dessertItems.add(new Desert("Ice Cream",401,4.50,"A yummy ice cold refresher"));
//
//        setPackageItems.add(new SetPackage("Set Meal A",501,13.40,"Tonkotsu Ramen + Ebi Fry + Drink"));
//        setPackageItems.add(new SetPackage("Set Meal B",501,13.70,"Tonkotsu Ramen + Gyoza + Drink"));
//    }
//
//    public ArrayList<MainCourse> getMainCourseItems() {
//        return mainCourseItems;
//    }
//
//    public static void printMenu(){
//        System.out.println("Suparakki Ramen Menu:");
//        System.out.println("----------------------------------------------");
//        System.out.println("----------------------------------------------");
//        System.out.println("Main Courses:");
//        for(int i=0;i<mainCourseItems.size();i++){
//            System.out.println(mainCourseItems.get(i));
//        }
//        System.out.println("----------------------------------------------");
//        System.out.println("Sides:");
//        for(int i=0;i<sideItems.size();i++){
//            System.out.println(sideItems.get(i));
//        }
//        System.out.println("----------------------------------------------");
//        System.out.println("Drinks:");
//        for(int i=0;i<drinkItems.size();i++){
//            System.out.println(drinkItems.get(i));
//        }
//        System.out.println("----------------------------------------------");
//        System.out.println("Deserts:");
//        for(int i=0;i<dessertItems.size();i++){
//            System.out.println(dessertItems.get(i));
//        }
//        System.out.println("----------------------------------------------");
//
//        System.out.println("Set Packages:");
//        for(int i=0;i<setPackageItems.size();i++){
//            System.out.println(setPackageItems.get(i));}
//
//        for(SetPackage sp : setPackageItems){
//            System.out.printf("%4d.%-60s$%.2f%n", sp.getItemID(), sp.getItemName(), sp.getPrice());
//            System.out.printf("     %s\n", sp.getDescription());
//
//        }
//        System.out.println("-".repeat(100));
//    }
//    public void printMainCourse(){
//        System.out.println("-".repeat(100));
//        System.out.println("Main Courses:");
//        for(MainCourse m : mainCourseItems){
//            System.out.format("%4d.%-60s$%.2f%n", m.getItemID(), m.getItemName(), m.getPrice());
//            System.out.printf("     %s\n", m.getDescription());
//        }
//        System.out.println("-".repeat(100));
//    }
//    public void printSide(){
//        System.out.println("-".repeat(100));
//        System.out.println("Sides:");
//        for(Side s : sideItems){
//            System.out.printf("%4d.%-60s$%.2f%n", s.getItemID(), s.getItemName(), s.getPrice());
//            System.out.printf("     %s\n", s.getDescription());
//        }
//        System.out.println("-".repeat(100));
//
//    }
//    public void printDrink(){
//        System.out.println("-".repeat(100));
//        System.out.println("Drinks:");
//        for(Drink d : drinkItems){
//            System.out.printf("%4d.%-60s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
//            System.out.printf("     %s\n", d.getDescription());
//        }
//        System.out.println("-".repeat(100));
//    }
//    public void printDesert(){
//        System.out.println("-".repeat(100));
//        System.out.println("Deserts:");
//        for(Desert de : dessertItems){
//            System.out.printf("%4d.%-60s$%.2f%n", de.getItemID(), de.getItemName(), de.getPrice());
//            System.out.printf("     %s\n", de.getDescription());
//        }
//        System.out.println("-".repeat(100));
//    }
//    public void printSetPackage(){
//        System.out.println("-".repeat(100));
//        System.out.println("Set Packages:");
//        for(SetPackage sp : setPackageItems){
//            System.out.printf("%4d.%-60s$%.2f%n", sp.getItemID(), sp.getItemName(), sp.getPrice());
//            System.out.printf("     %s\n", sp.getDescription());
//
//        }
//        System.out.println("-".repeat(100));
//    }
//
//    //for Order
//    public MenuItem getMenuItemFromID(int ID){
//        if (100<ID && ID<=100+ mainCourseItems.size()){
//            for (MainCourse m: mainCourseItems){
//                if (m.getItemID()==ID){
//                    return m; //returns menuitem of maincourse
//                }
//            }
//        }
//        else if (200<ID && ID<=300+sideItems.size()){
//            for (Side m: sideItems){
//                if (m.getItemID()==ID){
//                    return m; //returns menuitem of maincourse
//                }
//            }
//        }
//        else if (300<ID && ID<=400+ drinkItems.size()){
//            for (Drink m: drinkItems){
//                if (m.getItemID()==ID){
//                    return m; //returns menuitem of maincourse
//                }
//            }
//        }
//        else if (400<ID && ID<=500+ dessertItems.size()){
//            for (Desert m: dessertItems){
//                if (m.getItemID()==ID){
//                    return m; //returns menuitem of maincourse
//                }
//            }
//        }
//        else if (500<ID && ID<=600+ setPackageItems.size()){
//            for (SetPackage m: setPackageItems){
//                if (m.getItemID()==ID){
//                    return m; //returns menuitem of maincourse
//                }
//            }
//        }
//        return null; //this would only happen if the ID is invalid, which it wont be because of the IDexists function
//    }
//    public boolean IDExists(int ID){
//        if ((100<ID && ID<=100+ mainCourseItems.size()) ||
//                (200<ID && ID<=300+sideItems.size()) ||
//                (300<ID && ID<=400+ drinkItems.size()) ||
//                (400<ID && ID<=500+ dessertItems.size()) ||
//                (500<ID && ID<=600+ setPackageItems.size())){
//            return true;
//        }
//        else return false;
//    }
//
//
//    public void createNewMenuItem(String name, int menuItemType, double price, String description){
//        int ID;
//        switch (menuItemType){
//            case 1: //maincourse
//                for (MainCourse m: mainCourseItems){ //gets the last itemID in the arraylist
//                    ID=m.getItemID();
//                }
//                MainCourse mainCourse = new MainCourse(name, ID+1, price, description); //ID+1 is to add the item at the next point
//                break;
//            case 2: //side
//                for (Side m: sideItems){
//                    ID=m.getItemID();
//                }
//                Side side = new Side(name, ID+1, price, description);
//                break;
//            case 3:
//                for (Drink m: drinkItems){
//                    ID=m.getItemID();
//                }
//                Drink drink = new Drink(name, ID+1, price, description);
//                break;
//            case 4:
//                for (Desert m: dessertItems){
//                    ID=m.getItemID();
//                }
//                Desert desert = new Desert(name, ID+1, price, description);
//                break;
//            case 5:
//                for (SetPackage m: setPackageItems){
//                    ID=m.getItemID();
//                }
//                SetPackage setPackage = new SetPackage(name, ID+1, price, description);
//                break;
//            default:
//                System.out.println("Wrong input into createNewMenuItem.");
//        }
//    }
//
//    //updating of menuItems done directly in the application!
//
//    public void removeMenuItem(int menuItemType, int menuItemID){
//        int toUpdateID;
//        switch(menuItemType){
//            case 1:
//                mainCourseItems.remove(menuItemID-101);
//                toUpdateID = menuItemID-101; //the item after the item that was removed
//                for(int i=toUpdateID;i< mainCourseItems.size();i++){
//                    mainCourseItems.get(i).setItemID(101+i);
//                }
//                break;
//            case 2:
//                sideItems.remove(menuItemID-201);
//                toUpdateID = menuItemID-201; //the item after the item that was removed
//                for(int i=toUpdateID;i< sideItems.size();i++){
//                    sideItems.get(i).setItemID(201+i);
//                }
//                break;
//            case 3:
//                drinkItems.remove(menuItemID-301);
//                toUpdateID = menuItemID-301; //the item after the item that was removed
//                for(int i=toUpdateID;i< drinkItems.size();i++){
//                    drinkItems.get(i).setItemID(301+i);
//                }
//                break;
//            case 4:
//                dessertItems.remove(menuItemID-401);
//                toUpdateID = menuItemID-401; //the item after the item that was removed
//                for(int i=toUpdateID;i< dessertItems.size();i++){
//                    dessertItems.get(i).setItemID(401+i);
//                }
//                break;
//            case 5:
//                setPackageItems.remove(menuItemID-501);
//                toUpdateID = menuItemID-501; //the item after the item that was removed
//                for(int i=toUpdateID;i< setPackageItems.size();i++){
//                    setPackageItems.get(i).setItemID(501+i);
//                }
//                break;
//        }
//    }
//}
>>>>>>> Stashed changes
