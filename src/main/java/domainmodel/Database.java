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
    //private ArrayList<Member> competitiveMembersList; //TODO DET HER KAN VÆRE EN FEJL HER MEN MÅSKE IK


    public Database() {
        //TODO competitor skal have egen filehandler/loaddata?
        this.membersList = fh.loadData(fileMember);
        //this.membersList = fh.loadCompData(fileCompetitor);
    }


    public void addMember(String email, String firstName, String lastName, int age, Boolean status, Team team) {
        membersList.add(new Member(email,firstName,lastName,age,status, team));
        fh.saveData(membersList, fileNameMember);
    }
    public void addCompetitor(String email, String firstName, String lastName, int age, Boolean status, Team team, EnumMap<Discipline, Double> results){
        membersList.add(new Competitor(email,firstName,lastName,age, status, team, results));
        fh.saveData(membersList, fileNameCompetitor);
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
    /*
    public ArrayList<Member> findCompetitor(String email){ //TODO lav den her
        ArrayList<Member> searchCompetitor = new ArrayList<>(); //TODO Behøver den at returne arraylist?
        for (Member member : membersList) {
            if (member.getEmail().toLowerCase().contains(email.toLowerCase())) {
                searchCompetitor.add(member);
            }
        }
        return searchCompetitor;
    }
    */

    public void saveData(ArrayList<Member> membersList, String fileName){
        fh.saveData(membersList, fileName);
    }


}