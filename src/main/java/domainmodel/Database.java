package domainmodel;

import java.util.ArrayList;

public class Database {
    private ArrayList<Member> membersList = new ArrayList<>();
    private ArrayList<Competitor> competitiveMembersList = new ArrayList<>();

    public void addMember(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team) {
        membersList.add(new Member(email,firstName,lastName,age,debt,status, team));
    }
    public void addCompetitor(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team, Discipline discipline){
        competitiveMembersList.add(new Competitor(email,firstName,lastName,age,debt,status, team, discipline));
    }
    public ArrayList<Member> getMembersList(){
       return membersList;
    }

    public ArrayList<Member> findMember(String email) {
        ArrayList<Member> searchMember = new ArrayList<>();

        for (Member member : membersList) {
            if (member.getEmail().toLowerCase().contains(email.toLowerCase())) {
                searchMember.add(member);
            }
        }
        return searchMember;
    }
}
