package Restaurant;

public class testGetInput {
    private static GetInput i = new GetInput();
    private static int a;
    private static String s;
    private static double d;

    public static void main(String[] args){
        System.out.print("Enter integer input: ");
        a = i.getInt();
        System.out.println(a);

        System.out.print("Enter string input: ");
        s = i.getString();
        System.out.println(s);

        System.out.print("Enter double input: ");
        d = i.getDouble();
        System.out.println(d);
    }
}
