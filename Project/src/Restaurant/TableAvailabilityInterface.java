package Restaurant;

public class TableAvailabilityInterface {
    private Restaurant r;
    public TableAvailabilityInterface(Restaurant r){
        this.r = r;
    }

    public void printTableAvailability(){
        System.out.print(r.toString());
    }



}
