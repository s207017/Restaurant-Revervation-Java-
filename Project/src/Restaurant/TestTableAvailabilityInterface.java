package Restaurant;

public interface TestTableAvailabilityInterface {
    public static void main(String[] arg) {
        Menu menu = new Menu();
        Restaurant r = new Restaurant();
        TableAvailabilityInterface tableAvailabilityInterface= new TableAvailabilityInterface(r);
    }
}
