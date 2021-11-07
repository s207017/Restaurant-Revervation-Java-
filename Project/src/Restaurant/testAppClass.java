package Restaurant;

public class testAppClass {
    public static void main(String[] arg){
        Menu testMenu = new Menu();
        Restaurant testicle = new Restaurant();
        System.out.println("Creating order");
        Order testorder = new Order(211,3);
        testorder.addOrderItems(testMenu);
        testorder.removeOrderItems(testMenu);
        testorder.printOrder();
    }
}
