package Restaurant;

/**
 * This is an entity class.
 * An instance of this class represents a single memeber.
 */
public class Member {
    /**
     * This is an entity class.
     * The phone number of this member.
     */
    private int Number;

    /**
     * The constructor for Member.
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
