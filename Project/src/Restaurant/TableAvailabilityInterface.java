package Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TableAvailabilityInterface {
    private Restaurant r;
    Scanner sc = new Scanner(System.in);
    public TableAvailabilityInterface(Restaurant r){
        this.r = r;
    }
    public void printTableAvailability(){
        System.out.print(r.toString());
    }
    public int askForPax(){
        int pax;
        System.out.print("Enter the number of pax: "); //needs error handling
        pax = sc.nextInt();
        return pax;
    }

    //print statement asking them if they got reserve
    // if yes then call assignTable(true)

    public void assignTable(){
        LocalDateTime localDateTime = LocalDateTime.now();
        int pax = askForPax(), tableNum, newTableNum;
        boolean notIn = true;
        ArrayList<Integer> availableTableNumbers = new ArrayList<Integer>();
        System.out.println("You are now assigning a table to customer(s)");
        if (r.getAvailableTables(pax, localDateTime).size() == 0){
            System.out.println("All tables are occupied or reserved");
            return;
        } else {
            System.out.println("The available table numbers are: ");
            for (int i = 0; i < r.getAvailableTables(pax, localDateTime).size(); i++){
                tableNum = r.getAvailableTables(pax, localDateTime).get(i).getTableNum();
                availableTableNumbers.add(tableNum);
                System.out.print(tableNum + "||");
            }
        }
        while (notIn){
            System.out.print("Enter the table number to assign the customer: ");
            newTableNum = sc.nextInt(); //needs error handling
            if (availableTableNumbers.contains(newTableNum)){
                r.getTableFromTableNum(newTableNum).occupyTable(pax);
                System.out.println("Table assigned! Bring the customers to the table");
                notIn = false;
            } else {
                System.out.println("Table unavailable! Please enter a new table number");
            }
        }
    }
    public void assignReservedTable(){





    }
}
