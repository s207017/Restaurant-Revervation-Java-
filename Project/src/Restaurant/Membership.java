package Restaurant;

import java.util.ArrayList;

public class Membership {
    ArrayList<Member> membersList = new ArrayList<Member>();

    public Membership(){
        membersList.add(new Member(91169149));
        membersList.add(new Member(81812335));
    }

    public ArrayList<Member> getMembersList() {
        return membersList;
    }

    public void addMember(Member member){
        membersList.add(member);
    }

    public int checkMembership(int number){
        int count = 0;
        for (Member member: membersList){
            count++;
            if (member.getNumber() == number)
                return count;
        }
        return count;
    }
}
