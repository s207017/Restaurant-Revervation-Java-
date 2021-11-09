package Restaurant;

import java.util.Scanner;

public class Staff{
    private int staffID;

    public Staff() {
        staffID = 0;
    }

    // getter
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