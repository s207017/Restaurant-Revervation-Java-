package Restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Gets input from the user and throws the InputMismatchException when an input of the
 * a different type is entered, keeps asking the user for input until a valid input is entered.
 * Attributes and methods are declared as static such that they are global
 */
public class GetInput {
    /**
     * inputI is the user input of integer type
     */
    static int inputI;
    /**
     * inputS is the user input of String type
     */
    static String inputS;
    /**
     * inputD is the user input of double type
     */
    static double inputD;
    /**
     * inputC is the user input of char type
     */
    static char inputC;
    /**
     * New Scanner is created to read in input from the user in each method
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * Gets an integer input from the user
     * @return A valid integer input
     */
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

    /**
     * Gets an integer input from the user within a specified range (inclusive)
     * @param min is the minimum value of the range (inclusive)
     * @param max is the maximum value of the range (inclusive)
     * @return A valid integer input within the specified range (inclusive)
     */
    public static int getIntFromRange(int min, int max){
        boolean isValid = false;
        while(!isValid) {
            try {
                inputI = sc.nextInt();
                if(inputI >= min && inputI <= max) {
                    isValid = true;
                }else {
                    System.out.printf("Input should be between %d and %d (inclusive)\n", min, max);
                    System.out.print("Your input: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid integer input: ");
                sc.next();
            }
        }
        return inputI;
    }

    /**
     * Gets a String input from the user
     * @return A valid String input
     */
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

    /**
     * Gets a double input from the user
     * @return A valid double input
     */
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

    /**
     * Gets a double input from the user within a specified range (exclusive)
     * @param min is the minimum value of the range (exclusive)
     * @param max is the maximum value of the range (exclusive)
     * @return A valid double input within the specified range (exclusive)
     */
    public static double getDoubleFromRange(double min, double max){
        boolean isValid = false;
        while(!isValid) {
            try {
                inputD = sc.nextDouble();
                if(inputD > min && inputD < max) {
                    isValid = true;
                }else {
                    System.out.printf("Input should be between %.2f and %.2f (exclusive)\n", min, max);
                    System.out.print("Your input: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid double input: ");
                sc.next();
            }
        }
        return inputD;
    }

    /**
     * Gets a char (just one character, any more than one is invalid) input from the user
     * @return A valid char input
     */
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
