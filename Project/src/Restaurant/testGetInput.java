package Restaurant;

public class testGetInput {
    private static int a;
    private static String s;
    private static double d;

    public static void main(String[] args){
        System.out.print("Enter integer input: ");
        a = GetInput.getIntFromRange(8,20);
        System.out.println(a);
    }
}