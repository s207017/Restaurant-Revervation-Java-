package Restaurant;

import java.util.Scanner;

public class MembershipInterface {
    private Membership memberShip;

    public MembershipInterface(Membership memberShip) {
        this.memberShip = memberShip;
    }

    public void AddMember(){
        Scanner sc = new Scanner(System.in);
        int number;
        System.out.println("You are now adding a new member to the system");
        System.out.print("Enter the mobile number of the new member: ");
        number = sc.nextInt();
        if (memberShip.checkMembership(number)){
            System.out.println("Existing member. Cannot be added");
        } else {
            Member newMember = new Member(number);
            memberShip.addMember(newMember);
            System.out.println("Adding new member completed!");
        }
    }
}
