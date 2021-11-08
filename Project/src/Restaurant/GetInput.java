package Restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInput {
    int inputI;
    String inputS;
    double inputD;
    Scanner sc = new Scanner(System.in);

    public int getInt(){
        boolean isValid = false;
        while(!isValid) {
            try {
                inputI = sc.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid integer input: ");
                sc.next();
            }
        }
        return inputI;
    }

    public String getString(){
        boolean isValid = false;
        while(!isValid) {
            try {
                inputS = sc.next();
                inputS += sc.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid string input: ");
                sc.nextLine();
            }
        }
        return inputS;
    }

    public double getDouble(){
        boolean isValid = false;
        while(!isValid) {
            try {
                inputD = sc.nextDouble();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid double input: ");
                sc.next();
            }
        }
        return inputD;
    }


}
