package Restaurant;

import java.io.IOException;
import java.util.Scanner;

public class MembershipUI {
    private Membership memberShip;

    public MembershipUI(Membership memberShip) {
        this.memberShip = memberShip;
    }

    public void addMember() throws IOException {
        int number;
        System.out.println("You are now adding a new member to the system");
        System.out.print("Enter the mobile number of the new member: ");
        number = GetInput.getIntFromRange(80000000,99999999);
        if (memberShip.checkMembership(number)!=0){
            System.out.println("Member already exists.`");
        } else {
            Member newMember = new Member(number);
            memberShip.addMember(newMember);
            System.out.println("Adding new member completed!");
        }
    }

    public void removeMember(){
        int number;
        Member member;
        System.out.println("You are now removing a member from the system");
        System.out.print("Enter the mobile number of member to remove: ");
        number = GetInput.getIntFromRange(80000000,99999999);
        if (memberShip.checkMembership(number)!=0){
            memberShip.getMembersList().remove(memberShip.checkMembership(number)-1); //checkMembership returns the index the member was found at
            System.out.println("Member removed!");
        } else {
            System.out.println("No member record found. Cannot be added");
        }
    }
}
