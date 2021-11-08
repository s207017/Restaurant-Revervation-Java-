package Restaurant;

import java.util.Scanner;

public class Staff{
    private String staffID;

    public Staff() {
        staffID = null;
    }

    // getter
    public String getStaffID() {
        return staffID;
    }

    // setter
    public void setStaffID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your 4 digit staff ID: ");
        this.staffID = scanner.next();
    }
}