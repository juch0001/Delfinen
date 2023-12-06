package domainmodel;

import datasource.FileHandler;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    private final FileHandler fh = new FileHandler();
    private final File fileMember = new File("member_data.csv");
    private final File fileSwimResults = new File("swim_result.csv");
    private final String fileNameMember = fileMember.getName();
    private final String fileNameSwimResults = fileSwimResults.getName();


    private ArrayList<Member> membersList;
    private ArrayList<SwimResult> swimResults;

    public Database() {
        this.membersList = fh.loadData(fileMember);
        this.swimResults = fh.loadSwimResult(fileSwimResults);
    }

    public void addMember(String email, String firstName, String lastName, LocalDate birthday, boolean status, Team team, boolean isPaid) {
        Member newMember = new Member(email, firstName, lastName, birthday, status, team, isPaid);
        newMember.teamList();
        membersList.add(newMember);
        fh.saveData(membersList, fileNameMember);
    }

    public ArrayList<Member> getMembersList(){
        return membersList;
    }

    public ArrayList<Member> findMembers(String email) {
        ArrayList<Member> searchMembers = new ArrayList<>();
        for (Member member : membersList) {
            if (member.getEmail().toLowerCase().contains(email.toLowerCase())) {
                searchMembers.add(member);
            }
        }
        return searchMembers;
    }

   /* public int calculateAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }


    public Member findMember(String email) {
        for (Member member : membersList) {
            if (member.getEmail().toLowerCase().contains(email.toLowerCase())) {
                return member;
            }
        }
        return null;
    }*/

    public double individualMemberDebt(Member member) {
        return member.individualMemberDebt();
    }
    public int calculateIncome (Member member) {
        return member.calculateIncome();
    }
    public void loadData(){
        fh.loadData(fileMember);
    }
    public void saveData(){
        fh.saveData(membersList, fileNameMember);
    }
    public void saveResults(){
        fh.saveResults(swimResults, fileNameSwimResults);
    }
    public void loadResult(){
        fh.loadSwimResult(fileSwimResults);
    }
}