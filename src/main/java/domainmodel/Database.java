package domainmodel;

import datasource.FileHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;

public class Database {
    FileHandler fh = new FileHandler();
    private final String fileNameMember = "member_data.csv";
    private final File fileMember = new File("member_data.csv");
    private final String fileNameCompetitor = "competitor_data.csv";
    private final File fileCompetitor = new File("competitor_data.csv");

    private ArrayList<Member> membersList;
    private ArrayList<Competitor> competitiveMembersList = new ArrayList<>();


    public Database() {
        //TODO FÅ DET FIXET
        this.membersList = fh.loadData(fileMember);
        this.competitiveMembersList = fh.loadData(fileCompetitor);
    }

    //TODO
    public void addMember(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team) {
        membersList.add(new Member(email,firstName,lastName,age,debt,status, team));
        fh.saveData(membersList, fileNameMember);
    }
    public void addCompetitor(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team, EnumMap<Discipline, Double> results){
        competitiveMembersList.add(new Competitor(email,firstName,lastName,age,debt,status, team, results));
        fh.saveData(competitiveMembersList, fileNameCompetitor);
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
    public void saveData(ArrayList<Member> membersList, String fileName){
        fh.saveData(membersList, fileName);
    }


}