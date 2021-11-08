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
            }
        }
        return inputI;
    }

    public String getString(){
        try{
            inputS = sc.next();
        }
        catch(InputMismatchException e){
            System.out.print("Please enter a valid string input: ");
            inputS = sc.next();
        }
        return inputS;
    }

    public double getDouble(){
        try{
            inputD = sc.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.print("Please enter a valid double input: ");
            inputD = sc.nextDouble();
        }
        return inputD;
    }


}
