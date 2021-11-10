package Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        int pax;
        System.out.print("Enter the number of pax: ");
        pax = GetInput.getIntFromRange(1,10);
        return pax;
    }

    //print statement asking them if they got reserve
    // if yes then call assignTable(true)

    public void assignTable(){
        int exit = 0;
        char YN;
        while (exit != 1) {
            LocalDateTime localDateTime = LocalDateTime.now();
            int pax = askForPax(), tableNum, newTableNum;
            boolean notIn = true;
            ArrayList<Integer> availableTableNumbers = new ArrayList<Integer>();
            System.out.println("Checking for available seat(s)...");
            if (r.getAvailableTables(pax, localDateTime).size() == 0) {
                System.out.println("Currently no table available for " + pax + ". Please ask customer to wait.");
                return;
            } else {
                System.out.println("The available table numbers are: ");
                System.out.print("||");
                for (int i = 0; i < r.getAvailableTables(pax, localDateTime).size(); i++) {
                    tableNum = r.getAvailableTables(pax, localDateTime).get(i).getTableNum();
                    availableTableNumbers.add(tableNum);
                    System.out.print("Table " + tableNum + "||");
                }
                System.out.println();
            }
            if (availableTableNumbers.isEmpty()) {
                System.out.print("Currently no table available for " + pax + ". Please ask customer to wait.");
                return;
            }
            while (notIn) {
                System.out.print("Enter the table number to assign the customer: ");
                newTableNum = GetInput.getIntFromRange(1, r.getTableList().size());
                if (availableTableNumbers.contains(newTableNum)) {
                    r.getTableFromTableNum(newTableNum).occupyTable(pax);
                    System.out.println("Table assigned! Bring the customers to the table " + newTableNum + ".");
                    notIn = false;
                } else {
                    System.out.println("Please enter a valid table number");
                }
            }
            System.out.println("Return to Main Menu? Y/N: ");
            YN = GetInput.getChar();
            if (YN == 'y' || YN =='Y')
                exit =1;
        }
    }

    public void checkTableAvailability(){
        int exit = 0;
        char YN;
        while (exit != 1) {
            System.out.println(r);
            System.out.println("Return to Main Menu? Y/N: ");
            YN = GetInput.getChar();
            if (YN == 'y' || YN =='Y')
                exit =1;
        }
    }

    public void assignReservedTable(){

    }
}
