package Restaurant;

import java.io.IOException;

public class MiscController {
    private Restaurant r;

    public MiscController(Restaurant r) {
        this.r = r;
    }

    /**
     * This method adds a new table in the restaurant.
     * Since a table can only have even number of pax, when the waiter inputs an odd number for maximum pax
     * of the table, it adds 1 then creates the table.
     * @throws IOException
     */
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


    /**
     * This method adds a new staff ID for a new staff at the restaurant.
     * When the user inputs a desired staff ID, it iterates through the arraylist of staff instances stored in
     * restaurant obtained from getStaffList getter in the restaurant class to make sure the input ID does not exist.
     * If there is a match, it asks for another input, until a staff ID that does not already exist has been entered into the system
     * @throws IOException
     */
    public void addStaff() throws IOException {
        int ID;
        boolean exists;
        do {
            exists = false;
            System.out.print("Enter the 4 digit staff ID: ");
            ID = GetInput.getIntFromRange(0000, 9999);
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