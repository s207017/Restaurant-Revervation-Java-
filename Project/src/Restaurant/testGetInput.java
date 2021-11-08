package Restaurant;

public class testGetInput {
    private static int a;
    private static String s;
    private static double d;

    public static void main(String[] args){
        System.out.print("Enter integer input: ");
        a = GetInput.getInt();
        System.out.println(a);

        System.out.print("Enter string input: ");
        s = GetInput.getString();
        System.out.println(s);

        System.out.print("Enter double input: ");
        d = GetInput.getDouble();
        System.out.println(d);
    }
}