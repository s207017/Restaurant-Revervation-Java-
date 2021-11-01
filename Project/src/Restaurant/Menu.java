package Restaurant;

import java.util.ArrayList;

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
        drinkItems.add(new Drink("Mineral Water",302,1.5,"Plain ol bottled water");
        drinkItems.add(new Drink("Can Drink",303,1.5,"Choose any canned drink from our fridge"));

        dessertItems.add(new Desert("Ice Cream",401,4.50,"A yummy ice cold refresher"));

        setPackageItems.add(new SetPackage("Set Meal A",501,13.40,"Tonkotsu Ramen + Ebi Fry + Drink"));
        setPackageItems.add(new SetPackage("Set Meal B",501,13.70,"Tonkotsu Ramen + Gyoza + Drink"));
    }

    public ArrayList<MainCourse> getMainCourseItems() {
        return mainCourseItems;
    }

    public static void printMenu(){
        System.out.println("Suparakki Ramen Menu:");
        System.out.println("----------------------------------------------");
        System.out.println("----------------------------------------------");
        System.out.println("Main Courses:");
        for(int i=0;i<mainCourseItems.size();i++){
            System.out.println(mainCourseItems.get(i));
        }
        System.out.println("----------------------------------------------");
        System.out.println("Sides:");
        for(int i=0;i<sideItems.size();i++){
            System.out.println(sideItems.get(i));
        }
        System.out.println("----------------------------------------------");
        System.out.println("Drinks:");
        for(int i=0;i<drinkItems.size();i++){
            System.out.println(drinkItems.get(i));
        }
        System.out.println("----------------------------------------------");
        System.out.println("Deserts:");
        for(int i=0;i<dessertItems.size();i++){
            System.out.println(dessertItems.get(i));
        }
        System.out.println("----------------------------------------------");
        System.out.println("Set Packages:");
        for(int i=0;i<setPackageItems.size();i++){
            System.out.println(setPackageItems.get(i));
        }
    }
}
