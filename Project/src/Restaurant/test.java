package Restaurant;
public class test {
    public static void main(String[] args) {
        MainCourse pizza = new MainCourse("pizza",1,10);
        Drink coke = new Drink("coke",1,2);
        Side fries = new Side("fries", 1,3);
        SetPackage student_meal = new SetPackage("student meal", 10, 10);
        SetPackage set1 = new SetPackage(student_meal.getItemName(), student_meal.getItemID(),student_meal.getPrice());
        set1.addMainCourse(pizza);
        set1.addMainCourse(pizza);
    }
}