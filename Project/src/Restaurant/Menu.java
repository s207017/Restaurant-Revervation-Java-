package Restaurant;
import java.io.*;
import java.util.ArrayList;

/**
 * A Menu object that contains all menu items/set packages
 */
public class Menu {
    /**
     * ArrayList that stores every main course item
     */
    private ArrayList<MenuItem> mainCourseItems = new ArrayList<MenuItem>();
    /**
     * ArrayList that stores every side item
     */
    private ArrayList<MenuItem> sideItems = new ArrayList<MenuItem>();
    /**
     * ArrayList that stores every drink item
     */
    private ArrayList<MenuItem> drinkItems = new ArrayList<MenuItem>();
    /**
     * ArrayList that stores every dessert item
     */
    private ArrayList<MenuItem> dessertItems = new ArrayList<MenuItem>();
    /**
     * ArrayList that stores every set package item
     */
    private ArrayList<SetPackage> setPackageItems = new ArrayList<SetPackage>();

    /**
     * Menu constructor
     * Reads in the menu items/set package from text files
     * @throws IOException Thrown when reading/writing to text file, an I/O exception occurs
     */
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


        int menutype = 0;
        for (BufferedReader textfile : listOfTextFiles) {
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
                    switch (menutype) {
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
                            SetPackage newpackage = new SetPackage(newitem.getItemName(), newitem.getItemID(), newitem.getPrice(), newitem.getDescription());
                            newpackage.addMainCourse(getMenuItemFromID(Integer.parseInt(textfile.readLine())));
                            newpackage.addSide(getMenuItemFromID(Integer.parseInt(textfile.readLine())));
                            newpackage.setMaxDrinkPrice(Double.parseDouble(textfile.readLine()));
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

    /**
     * Getter for main course items
     * @return All main course items (MenuItem type)
     */
    public ArrayList<MenuItem> getMainCourseItems() {
        return mainCourseItems;
    }

    /**
     * Getter for side items
     * @return All side items (MenuItem type)
     */
    public ArrayList<MenuItem> getSideItems() {
        return sideItems;
    }

    /**
     * Getter for drink items
     * @return All drink items (MenuItem type)
     */
    public ArrayList<MenuItem> getDrinkItems() {
        return drinkItems;
    }

    /**
     * Getter for dessert items
     * @return All dessert items (MenuItem type)
     */
    public ArrayList<MenuItem> getDessertItems() {
        return dessertItems;
    }

    /**
     * Getter for set package items
     * @return All set package items (SetPackage type)
     */
    public ArrayList<SetPackage> getSetPackageItems() {
        return setPackageItems;
    }

    /**
     * Updates menu items that were created/removed/updated to the respective menu item type text file
     * @param menuItems Takes in the all menu items from the menu items array
     * @param menuType Takes in the type of menu item to update
     * @throws IOException Thrown when reading/writing to text file, an I/O exception occurs
     */
    public void updateMenuToFile(ArrayList<MenuItem> menuItems, String menuType) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/"+menuType+".txt", false)
        );

        for (MenuItem item: menuItems){
            bw.write(item.getItemName()+"\n" +String.valueOf(item.getItemID())+"\n"+String.valueOf(item.getPrice()) +"\n"+item.getDescription()+"\n");
        }
        bw.close();
    }

    /**
     * Updates set package items that were created/removed/updated to he set package text file
     * @throws IOException Thrown when reading/writing to text file, an I/O exception occurs
     */
    public void updateSetPackageFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/setpackageitems.txt", false)
        );
        for (SetPackage item: setPackageItems){
            bw.write(item.getItemName()+"\n" +String.valueOf(item.getItemID())+"\n"+String.valueOf(item.getPrice()) +"\n"+item.getDescription()+"\n"+item.getSetItems().get(0).getItemID()+"\n"+item.getSetItems().get(1).getItemID()+"\n"+item.getMaxDrinkPrice()+"\n");
        }
        bw.close();
    }

    /**
     * Prints the entire restaurant's menu
     */
    public void printMenu(){
        System.out.println("OOPsie Menu");
        System.out.println("=".repeat(132));
        System.out.println("Main Courses:");
        for(MenuItem m : mainCourseItems){
            System.out.format("%4d. %-120s$%.2f%n", m.getItemID(), m.getItemName(), m.getPrice());
            System.out.printf("       %s\n", m.getDescription());
        }
        System.out.println("-".repeat(132));
        System.out.println("Sides:");
        for(MenuItem s : sideItems){
            System.out.printf("%4d. %-120s$%.2f%n", s.getItemID(), s.getItemName(), s.getPrice());
            System.out.printf("       %s\n", s.getDescription());
        }
        System.out.println("-".repeat(132));
        System.out.println("Drinks:");
        for(MenuItem d : drinkItems){
            System.out.printf("%4d. %-120s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
            System.out.printf("       %s\n", d.getDescription());
        }
        System.out.println("-".repeat(132));
        System.out.println("Deserts:");
        for(MenuItem de : dessertItems){
            System.out.printf("%4d. %-120s$%.2f%n", de.getItemID(), de.getItemName(), de.getPrice());
            System.out.printf("       %s\n", de.getDescription());
        }
        System.out.println("-".repeat(132));
        System.out.println("Set Packages:");
        for(SetPackage sp : setPackageItems){
            System.out.printf("%4d. %-120s$%.2f%n", sp.getItemID(), sp.getItemName(), sp.getPrice());
            System.out.printf("       %s\n", sp.getDescription());

        }
        System.out.println("-".repeat(132));
    }

    /**
     * Prints only items from the main course items arraylist
     */
    public void printMainCourse(){
        System.out.println("-".repeat(132));
        System.out.println("Main Courses:");
        for(MenuItem m : mainCourseItems){
            System.out.format("%4d. %-120s$%.2f%n", m.getItemID(), m.getItemName(), m.getPrice());
            System.out.printf("       %s\n", m.getDescription());
        }
        System.out.println("-".repeat(132));
    }

    /**
     * Prints only items from the side items arraylist
     */
    public void printSide(){
        System.out.println("-".repeat(132));
        System.out.println("Sides:");
        for(MenuItem s : sideItems){
            System.out.printf("%4d. %-120s$%.2f%n", s.getItemID(), s.getItemName(), s.getPrice());
            System.out.printf("       %s\n", s.getDescription());
        }
        System.out.println("-".repeat(132));

    }

    /**
     * Prints only items from the drink items arraylist
     */
    public void printDrink(){
        System.out.println("-".repeat(132));
        System.out.println("Drinks:");
        for(MenuItem d : drinkItems){
            System.out.printf("%4d. %-120s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
            System.out.printf("       %s\n", d.getDescription());
        }
        System.out.println("-".repeat(132));
    }

    /**
     * Prints only drinks less than or equal to the inputted price
     * Called when customer orders a set package, and they are selecting the drink they want
     * Each set package has a maximum drink price attribute that specifies which drinks can be ordered for that particular set package
     * @param price The maximum drink price specified of each set package
     */
    public void printDrinkLTEPrice(double price){
        System.out.println("-".repeat(132));
        System.out.println("Drinks:");
        for(MenuItem d : drinkItems){
            if(d.getPrice() <= price) {
                System.out.printf("%4d. %-120s$%.2f%n", d.getItemID(), d.getItemName(), d.getPrice());
                System.out.printf("       %s\n", d.getDescription());
            }
        }
        System.out.println("-".repeat(132));
    }

    /**
     * Prints only items from the dessert items arraylist
     */
    public void printDesert(){
        System.out.println("-".repeat(132));
        System.out.println("Deserts:");
        for(MenuItem de : dessertItems){
            System.out.printf("%4d. %-120s$%.2f%n", de.getItemID(), de.getItemName(), de.getPrice());
            System.out.printf("       %s\n", de.getDescription());
        }
        System.out.println("-".repeat(132));
    }

    /**
     * Prints only items from the set package items arraylist
     */
    public void printSetPackage(){
        System.out.println("-".repeat(132));
        System.out.println("Set Packages:");
        for(SetPackage sp : setPackageItems){
            System.out.printf("%4d. %-120s$%.2f%n", sp.getItemID(), sp.getItemName(), sp.getPrice());
            System.out.printf("       %s\n", sp.getDescription());

        }
        System.out.println("-".repeat(132));
    }


    /**
     * Method to return the specific menu item (of MenuItem type) based on the inputted ID
     * Index starting with 1 represents main course
     * Index starting with 2 represents sides
     * Index starting with 3 represents drinks
     * Index starting with 4 represents dessert
     * @param ID Identifies the menu item inputted
     * @return The menu item (of MenuItem type) found from the particular inputted valid ID
     */
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
                    return m; //returns menuitem of side dish
                }
            }
        }
        else if (300<ID && ID<=300+ drinkItems.size()){
            for (MenuItem m: drinkItems){
                if (m.getItemID()==ID){
                    return m; //returns menuitem of drink
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
        return null;
    }

    /**
     * Method to return the specific set package item (of SetPackage type) based on the inputted ID
     * Index starting with 5 represents set package
     * @param ID Identifies the set package item inputted
     * @return The set package item (of SetPackage type) found from the particular inputted valid ID
     */
    public SetPackage getSetPackageItemFromID(int ID){
        if (500<ID && ID<=500 + setPackageItems.size()){
            for (SetPackage m: setPackageItems){
                if (m.getItemID()==ID){
                    return m;
                }
            }
        }
        return null; //not found
    }

    /**
     * Creates a new menu item, and adds on to the respective menu item type arraylist
     * Updates the respective text files accordingly
     * @param name Name of the new menu item
     * @param menuItemType Menu item type of the new menu item
     * @param price Price of the new menu item
     * @param description Description of the new menu item
     * @throws IOException Thrown when reading/writing to text file, an I/O exception occurs
     */
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

    /**
     * Creates a new set package item, and adds on to the set package items arraylist
     * Updates the set package text files accordingly
     * @param name Name of the new set package item
     * @param price Price of the new set package item
     * @param description Description of the new set package item
     * @param menuItems The menu items that the new set package consists of
     * @param maxDrinkPrice Specifies the maximum drink price that is allowed to be ordered when ordering this new set package
     * @throws IOException Thrown when reading/writing to text file, an I/O exception occurs
     */
    public void createNewSetPackage(String name,double price, String description, ArrayList<MenuItem> menuItems, double maxDrinkPrice) throws IOException {
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
        newPackage.setMaxDrinkPrice(maxDrinkPrice);
        setPackageItems.add(newPackage);
        updateSetPackageFile();
    }

    /**
     * Removes the relevant menu item based on the ID
     * Updates the relevant arraylist and text file accordingly
     * @param menuItemType Specifies the menu item type of the menu item to be removed
     * @param menuItemID Specifies the ID of the menu item to be removed
     * @throws IOException Thrown when reading/writing to text file, an I/O exception occurs
     */
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
                updateSetPackageFile();
                break;
        }
    }

    public boolean updateMenuItem(int ID, int changeOption, boolean counts, boolean set) throws IOException {
        boolean updated = false;
        switch (changeOption) {
            case 1:
                System.out.print("What is the new price?: ");
                double newPrice = GetInput.getDouble();
                if (set){
                    getSetPackageItemFromID(ID).setPrice(newPrice);
                } else {
                    getMenuItemFromID(ID).setPrice(newPrice);
                }
                updated = true;
                break;
            case 2:
                System.out.print ("What is the new name?: ");
                String newName = GetInput.getString();
                while (this.checkIfNameExists(newName)){
                    System.out.println("The name already exists in the menu");
                    System.out.print("Please enter a new name: ");
                    newName = GetInput.getString();
                }
                if (set){
                    getSetPackageItemFromID(ID).setItemName(newName);
                } else {
                    getMenuItemFromID(ID).setItemName(newName);
                }
                updated = true;
                break;
            case 3:
                System.out.println("What is the new description?:");
                String newDesc = GetInput.getString();
                if (set){
                    getSetPackageItemFromID(ID).setDescription(newDesc);
                } else {
                    getMenuItemFromID(ID).setDescription(newDesc);
                }
                updated = true;
                break;
            case 4:
                break;
            default:
                break;
        }
        updateMenuToFile(mainCourseItems,"maincourseitems");
        updateMenuToFile(sideItems,"sideitems");
        updateMenuToFile(drinkItems,"drinkitems");
        updateMenuToFile(dessertItems,"dessertitems");
        updateSetPackageFile();
        return updated;
    }

    /**
     * Checks that the name of a new menu/set package items exists already in the menu
     * @param newName The user inputted name of the new menu item
     * @return True if the name exists and false if not
     */
    public boolean checkIfNameExists(String newName){
        newName = newName.toLowerCase();
        for (int i = 0; i < this.getMainCourseItems().size(); i++){
            if (newName.equals(this.getMainCourseItems().get(i).getItemName().toLowerCase())){
                return true;
            }
        }

        for (int i = 0; i < this.getSideItems().size(); i++){
            if (newName.equals(this.getSideItems().get(i).getItemName().toLowerCase())){
                return true;
            }
        }

        for (int i = 0; i < this.getDrinkItems().size(); i++){
            if (newName.equals(this.getDrinkItems().get(i).getItemName().toLowerCase())){
                return true;
            }
        }

        for (int i = 0; i < this.getDessertItems().size(); i++){
            if (newName.equals(this.getDessertItems().get(i).getItemName().toLowerCase())){
                return true;
            }
        }

        for (int i = 0; i < this.getSetPackageItems().size(); i++){
            if (newName.equals(this.getSetPackageItems().get(i).getItemName().toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
