package Restaurant;

import java.util.ArrayList;

public class Membership {
    ArrayList<Member> Members = new ArrayList<Member>();

    public void addMember(Member member){
        Members.add(member);
    }

    public boolean checkMembership(String number){
        for (Member member: Members){
            if (member.getNumber()== number)
                return true;
        }
        return false;
    }
}
