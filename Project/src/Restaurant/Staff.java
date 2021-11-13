package Restaurant;

import java.util.Scanner;

public class Staff{
    /**
     * Integer attribute which is the staff ID number of a staff represented by an instance of this class
     */
    private int staffID;

    /**
     * Constructor for staff class
     * @param ID
     */
    public Staff(int ID) {
        this.staffID = ID;
    }

    /**
     * gets staff ID
      * @return staffID of the instance of the class
     */
    public int getStaffID() {
        return staffID;
    }

    // setter
    public void setStaffID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your 4 digit staff ID: ");
        this.staffID = GetInput.getInt();
    }
}