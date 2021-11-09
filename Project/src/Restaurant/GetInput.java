package Restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInput {
    static int inputI;
    static String inputS;
    static double inputD;
    static char inputC;
    static Scanner sc = new Scanner(System.in);

    public static int getInt(){
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

    public static int getInt(int min, int max){
        boolean isValid = false;
        inputI = -10;
        while(!isValid || inputI < min || inputI > max) {
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

    public static String getString(){
        boolean isValid = false;
        while(!isValid) {
            try {
                inputS = sc.next();
                inputS += sc.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid string input: ");
                sc.next();
            }
        }
        return inputS;
    }

    public static double getDouble(){
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

    public static char getChar(){
        boolean isValid = false;
        while(!isValid) {
            try {
                inputC = sc.next(".").charAt(0);
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid char input: ");
                sc.next();
            }
        }
        return inputC;
    }
}
