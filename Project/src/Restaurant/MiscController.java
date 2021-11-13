package Restaurant;

import java.io.IOException;

public class MiscController {
    private Restaurant r;

    public MiscController(Restaurant r) {
        this.r = r;
    }

    public void addTable() throws IOException {
        System.out.println("You are now adding a new table");
        System.out.println("Pax per table must be even numbers and, ");
        System.out.println("Minimum pax per table is 2 and the maximum pax per table is 10");
        System.out.println("What is the desired pax number for the new table? ");
        System.out.print("Your input: ");
        int pax = GetInput.getIntFromRange(2, 10);
        if (pax % 2 == 1){
            System.out.println("You entered an odd number");
            pax += 1;
            System.out.println("A table with pax size of " + pax + " will be created instead");
        }
        r.addTable(pax);
        System.out.println("Table added successfully!");
    }

    public void addStaff() throws IOException {
        int ID;
        boolean exists;
        do {
            exists = false;
            System.out.print("Enter the 4 digit staff ID: ");
            ID = GetInput.getIntFromRange(1000, 9999);
            for (int i = 0; i < r.getStaffList().size(); i++) {
                if (ID == r.getStaffList().get(i).getStaffID()) {
                    exists = true;
                    System.out.println("The staff ID already exists, input another staff ID");
                }
            }
        } while (exists == true);
        r.addStaff(ID);
        System.out.println("Staff successfully added!");
    }
}