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


    public Database() {
        //TODO competitor skal have egen filehandler/loaddata?
        this.membersList = fh.loadData(fileMember);
    }


    public void addMember(String email, String firstName, String lastName, LocalDate birthday, Boolean status, Team team) {
        membersList.add(new Member(email,firstName,lastName,birthday,status, Team.SENIOR));
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
    public int calculateAge(Member member){
        LocalDate currentDate = LocalDate.now();
        LocalDate memberBirthday = member.getBirthday();
        return Period.between(memberBirthday,currentDate).getYears();
    }

    public void updateAge(ArrayList<Member> members){ //TODO SKAL DEN RETURNE TEAMET ELLER HVAD
        for (Member member:members) {
            int age = calculateAge(member);
            if (!(member.getTeam() == Team.EXERCISER)){
                if (age < 18){
                    member.setTeam(Team.JUNIOR);
                }else{
                    member.setTeam(Team.SENIOR);
                }
            }
        }
    }

    public Member findCompetitor(String email){ //
        for (Member competitor : membersList) {
            if (competitor.getEmail().toLowerCase().contains(email.toLowerCase())) {
                return competitor;
            }
        }
        return searchCompetitor;
    }
    */

    public void saveData(ArrayList<Member> membersList, String fileName){
        fh.saveData(membersList, fileName);
    }


}