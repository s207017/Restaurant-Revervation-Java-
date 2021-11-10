package Restaurant;

import java.util.ArrayList;

public class Membership {
    ArrayList<Member> membersList = new ArrayList<Member>();

    public Membership(){
        membersList.add(new Member(91169149));
        membersList.add(new Member(81812335));
        membersList.add(new Member(87426732));
    }

    public void addMember(Member member){
        membersList.add(member);
    }

    public boolean checkMembership(int number){
        for (Member member: membersList){
            if (member.getNumber()== number)
                return true;
        }
        return false;
    }
}
