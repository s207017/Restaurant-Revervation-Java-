package Restaurant;

import java.io.*;
import java.util.ArrayList;

/**
 *An instance of this class represents a single membership.
 */
public class Membership {
    /**
     * The list of members of the restaurant.
     */
    ArrayList<Member> membersList = new ArrayList<Member>();

    /**
     * The constructor for membership.
     * Reads the phone number of every member from the members.txt file and creates new members which are added to the list of members in this membership.
     * @throws IOException
     */
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

    /**
     * Getter for membersList of this membership.
     * @return
     */
    public ArrayList<Member> getMembersList() {
        return membersList;
    }

    /**
     * Adds member to the list of members in this membership.
     * @param member
     * @throws IOException
     */
    public void addMember(Member member) throws IOException {
        membersList.add(member);
    }

    /**
     * Checks if a person is a member in the list of members in this membership using phone number.
     * @param number The phone number of a person.
     * @return The index of the member in the list of members if the member exists. Else, 0.
     */
    public int checkMembership(int number){
        int count = 0;
        for (Member member: membersList){
            count++;
            if (member.getNumber() == number)
                return count;
        }
        return 0;
    }

    /**
     * Updates the members.txt file by overwriting the text file. Writes the phone number of every member in the list of members in this membership.
     * @throws IOException
     */
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
//