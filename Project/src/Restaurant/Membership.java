package Restaurant;

import java.util.ArrayList;

public class Membership {
    ArrayList<Member> membersList = new ArrayList<Member>();

    public Membership(){
        membersList.add(new Member("98357328"));
        membersList.add(new Member("84457564"));
        membersList.add(new Member("92925963"));
        membersList.add(new Member("92715388"));
        membersList.add(new Member("82943623"));
        membersList.add(new Member("87569116"));
        membersList.add(new Member("95431183"));
        membersList.add(new Member("96789030"));
        membersList.add(new Member("97420100"));
        membersList.add(new Member("98517739"));
    }

    public void addMember(Member member){
        membersList.add(member);
    }

    public boolean checkMembership(String number){
        for (Member member: membersList){
            if (member.getNumber()== number)
                return true;
        }
        return false;
    }
}
