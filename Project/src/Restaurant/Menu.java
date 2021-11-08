package Restaurant;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuItem> mainCourseItems = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> sideItems = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> drinkItems = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> dessertItems = new ArrayList<MenuItem>();
    private ArrayList<SetPackage> setPackageItems = new ArrayList<SetPackage>();

    public Menu() throws IOException {
        ArrayList<BufferedReader> listOfTextFiles = new ArrayList<BufferedReader>();
        BufferedReader maincourse_text = new BufferedReader(
                new FileReader("./textfiles/maincourseitems.txt")
        );
        listOfTextFiles.add(maincourse_text);

        BufferedReader side_text = new BufferedReader(
                new FileReader("./textfiles/sideitems.txt")
        );
        listOfTextFiles.add(side_text);
        BufferedReader drink_text = new BufferedReader(
                new FileReader("./textfiles/drinkitems.txt")
        );
        listOfTextFiles.add(drink_text);
        BufferedReader dessert_text = new BufferedReader(
                new FileReader("./textfiles/dessertitems.txt")
        );
        listOfTextFiles.add(dessert_text);
        BufferedReader setpackages_text = new BufferedReader(
                new FileReader("./textfiles/setpackageitems.txt")
        );
        listOfTextFiles.add(setpackages_text);


        // for reading items from text file
        String itemname = null;
        int itemid = 0;
        Double itemprice = 0.0;
        String description = null;


        int menutype =0;
        for (BufferedReader textfile: listOfTextFiles) {
            int x = 0;
            String s;
            while ((s = textfile.readLine()) != null) {
                if (x % 4 == 0) {
                    itemname = s;
                } else if (x % 4 == 1) {
                    itemid = Integer.parseInt(s);
                } else if (x % 4 == 2) {
                    itemprice = Double.parseDouble(s);
                } else if (x % 4 == 3) {
                    description = s;
                    MenuItem newitem = new MenuItem(itemname, itemid, itemprice, description);
                    switch(menutype){
                        case 0:
                            mainCourseItems.add(newitem);
                            break;
                        case 1:
                            sideItems.add(newitem);
                            break;
                        case 2:
                            drinkItems.add(newitem);
                            break;
                        case 3:
                            dessertItems.add(newitem);
                            break;
                        case 4:
                            SetPackage newpackage = new SetPackage(newitem.getItemName(),newitem.getItemID(),newitem.getPrice(), newitem.getDescription());
                            newpackage.addMainCourse(getMenuItemFromID(Integer.parseInt(textfile.readLine())));
                            newpackage.addMainCourse(getMenuItemFromID(Integer.parseInt(textfile.readLine())));
                            setPackageItems.add(newpackage);
                            break;
                        default:
                            System.out.println("ERROR");
                            break;
                    }

                }
                x++;
            }
            menutype++;
            textfile.close();
        }
    }


    public void updateMenuToFile(ArrayList<MenuItem> menuItems, String menuType) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/"+menuType+".txt", false)
        );

        for (MenuItem item: menuItems){
            bw.write(item.getItemName()+"\n" +String.valueOf(item.getItemID())+"\n"+String.valueOf(item.getPrice()) +"\n"+item.getDescription()+"\n");
        }
        bw.close();
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
    public void printDrinkLTEPrice(double price){
        System.out.println("-".repeat(100));
        System.out.println("Drinks:");
        for(MenuItem d : drinkItems){
            if(d.getPrice() <= price) {
                System.out.printf("%4d.%-60s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
                System.out.printf("     %s\n", d.getDescription());
            }
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


    public void createNewMenuItem(String name, int menuItemType, double price, String description) throws IOException {
        int ID = 0;
        switch (menuItemType){
            case 1: //maincourse
                for (MenuItem m: mainCourseItems){ //gets the last itemID in the arraylist
                    ID=m.getItemID();
                }
                MenuItem mainCourse = new MenuItem(name, ID+1, price, description); //ID+1 is to add the item at the next point
                mainCourseItems.add(mainCourse);
                updateMenuToFile(mainCourseItems,"maincourseitems");
                break;
            case 2: //side
                for (MenuItem m: sideItems){
                    ID=m.getItemID();
                }
                MenuItem side = new MenuItem(name, ID+1, price, description);
                sideItems.add(side);
                updateMenuToFile(sideItems,"sideitems");
                break;
            case 3:
                for (MenuItem m: drinkItems){
                    ID=m.getItemID();
                }
                MenuItem drink = new MenuItem(name, ID+1, price, description);
                drinkItems.add(drink);
                updateMenuToFile(drinkItems,"drinkitems");
                break;
            case 4:
                for (MenuItem m: dessertItems){
                    ID=m.getItemID();
                }
                MenuItem dessert = new MenuItem(name, ID+1, price, description);
                dessertItems.add(dessert); // need?
                updateMenuToFile(dessertItems,"dessertitems");
                break;
            case 5:

            default:
                System.out.println("Wrong input into createNewMenuItem.");
        }
    }

    public void createNewSetPackage(String name,double price, String description, ArrayList<MenuItem> menuItems, SetPackage set) {
        int ID=0;
        for (SetPackage m: setPackageItems){
            ID=m.getItemID();
        }
        SetPackage newPackage = new SetPackage(name, ID+1, price,description);
        for (MenuItem item: menuItems) {
            if (100<item.getItemID() && item.getItemID()<=100+ mainCourseItems.size()) {
                newPackage.addMainCourse(item);
            }else if( 200<item.getItemID() && item.getItemID()<=200+sideItems.size()) {
                newPackage.addSide(item);
            }
        }
        setPackageItems.add(newPackage);
    }
    //updating of menuItems done directly in the application!
    public void removeMenuItem(int menuItemType, int menuItemID) throws IOException {
        int toUpdateID;
        switch(menuItemType){
            case 1:
                mainCourseItems.remove(menuItemID-101);
                toUpdateID = menuItemID-101; //the item after the item that was removed
                for(int i=toUpdateID;i< mainCourseItems.size();i++){
                    mainCourseItems.get(i).setItemID(101+i);
                }
                updateMenuToFile(mainCourseItems,"maincourseitems");
                break;
            case 2:
                sideItems.remove(menuItemID-201);
                toUpdateID = menuItemID-201; //the item after the item that was removed
                for(int i=toUpdateID;i< sideItems.size();i++){
                    sideItems.get(i).setItemID(201+i);
                }
                updateMenuToFile(sideItems,"sideitems");
                break;
            case 3:
                drinkItems.remove(menuItemID-301);
                toUpdateID = menuItemID-301; //the item after the item that was removed
                for(int i=toUpdateID;i< drinkItems.size();i++){
                    drinkItems.get(i).setItemID(301+i);
                }
                updateMenuToFile(drinkItems,"drinkitems");
                break;
            case 4:
                dessertItems.remove(menuItemID-401);
                toUpdateID = menuItemID-401; //the item after the item that was removed
                for(int i=toUpdateID;i< dessertItems.size();i++){
                    dessertItems.get(i).setItemID(401+i);
                }
                updateMenuToFile(dessertItems,"dessertitems");
                break;
            case 5:
                setPackageItems.remove(menuItemID-501);
                toUpdateID = menuItemID-501; //the item after the item that was removed
                for(int i=toUpdateID;i< setPackageItems.size();i++){
                    setPackageItems.get(i).setItemID(501+i);
                }
                //updateMenuToFile(dessertItems,"dessertitems");
                break;
        }
    }



    public void updateMenuItem(int ID, int changeOption) throws IOException {
        Scanner sc = new Scanner(System.in);
        changeOption = sc.nextInt();
        switch (changeOption) {
            case 1:
                System.out.println("What is the new price?");
                double newPrice = sc.nextDouble();
                getMenuItemFromID(ID).setPrice(newPrice);
                break;
            case 2:
                System.out.println("What is the new name?");
                String newName = sc.nextLine();
                getMenuItemFromID(ID).setItemName(newName);
                break;
            case 3:
                System.out.println("What is the new description?");
                String newDesc = sc.nextLine();
                getMenuItemFromID(ID).setDescription(newDesc);
                break;
            case 4:
                System.out.println("Exiting update of menu...");
                break;
            default:
                break;
        }
        updateMenuToFile(mainCourseItems,"maincourseitems");
        updateMenuToFile(sideItems,"sideitems");
        updateMenuToFile(drinkItems,"drinkitems");
        updateMenuToFile(dessertItems,"dessertitems");
    }
}
