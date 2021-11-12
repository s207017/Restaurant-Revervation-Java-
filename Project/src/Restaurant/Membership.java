package Restaurant;

import java.io.*;
import java.util.ArrayList;

public class Membership {
    ArrayList<Member> membersList = new ArrayList<Member>();

    public Membership() throws IOException {
        BufferedReader membersText = new BufferedReader(
                new FileReader("./textfiles/members.txt")
        );

        // for reading items from text file
        int phoneNum = 0;
        String s;
        while ((s = membersText.readLine()) != null) {
            phoneNum = Integer.parseInt(s);
            Member newMember = new Member(phoneNum);
            membersList.add(newMember);
        }
        membersText.close();
    }

    public ArrayList<Member> getMembersList() {
        return membersList;
    }

    public void addMember(Member member) throws IOException {
        membersList.add(member);
    }

    public int checkMembership(int number){
        int count = 0;
        for (Member member: membersList){
            count++;
            if (member.getNumber() == number)
                return count;
        }
        return 0;
    }

    public void updateMembershipText() throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("./textfiles/members.txt", false)
        );

        for (Member member_ : membersList) {
            bw.write(member_.getNumber() + "\n");
        }
        bw.close();
    }
}
