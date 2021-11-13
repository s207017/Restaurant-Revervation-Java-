package Restaurant;

import java.io.IOException;
import java.util.Scanner;

/**
 * Used to manage membership operations.
 */
public class MembershipController {
    /**
     * Declaring a membership reference.
     */
    private Membership memberShip;

    /**
     * The constructor for MembershipController.
     * @param memberShip
     */
    public MembershipController(Membership memberShip) {
        this.memberShip = memberShip;
    }

    /**
     * Asks for the phone number of customer and checks if customer is an existing member.
     * If customer is not an existing member, adds member to the list of members and updates members.txt file.
     * @throws IOException
     */
    public void addMember() throws IOException {
        int number;
        System.out.println("You are now adding a new member to the system");
        System.out.print("Enter the mobile number of the new member: ");
        number = GetInput.getIntFromRange(80000000,99999999);
        if (memberShip.checkMembership(number)!=0){
            System.out.println("Member already exists.");
        } else {
            Member newMember = new Member(number);
            memberShip.addMember(newMember);
            memberShip.updateMembershipText();
            System.out.println("Adding new member completed!");
        }
    }

    /**
     * Asks for the phone number of customer and checks if customer is an existing member.
     * If customer is an existing member, removes member from the list of members and updates the member.txt file.
     * @throws IOException
     */
    public void removeMember() throws IOException {
        int number;
        Member member;
        System.out.println("You are now removing a member from the system");
        System.out.print("Enter the mobile number of member to remove: ");
        number = GetInput.getIntFromRange(80000000,99999999);
        if (memberShip.checkMembership(number)!=0){
            memberShip.getMembersList().remove(memberShip.checkMembership(number)-1); //checkMembership returns the index the member was found at
            memberShip.updateMembershipText();
            System.out.println("Member removed!");
        } else {
            System.out.println("No member record found. Cannot be added");
        }
    }
}
