package Restaurant;

public class Member {
    /**
     * The phone number of this member.
     */
    private int Number;

    /**
     * The constructor for member.
     * @param number The phone number of this member.
     */
    public  Member(int number) {
        Number = number;
    }

    /**
     * Getter for phone number of this member.
     * @return The phone number of this member.
     */
    public int getNumber() {
        return Number;
    }
}
