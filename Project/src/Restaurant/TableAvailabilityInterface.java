package Restaurant;

import java.util.Scanner;

public class TableAvailabilityInterface {
    private Restaurant r;
    public TableAvailabilityInterface(Restaurant r){
        this.r = r;
    }
    public void printTableAvailability(){
        System.out.print(r.toString());
    }
    public int askForPax(){
        Scanner sc = new Scanner(System.in);
        int pax;
        System.out.print("Enter the number of pax: ");
        pax = sc.nextInt();
        return pax;
    }
}
