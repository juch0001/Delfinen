package datasource;

import domainmodel.Member;
import domainmodel.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {
    private final File file = new File("member_data.csv");
    public void saveData(ArrayList<Member> memberList){

    }
    public ArrayList<Member> loadData(File file){

        ArrayList<Member> membersList = new ArrayList();
        Scanner sc = null;
        try {
            sc = new Scanner(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Member members;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");

            members = new Member(
                    attributes[0],
                    attributes[1],
                    attributes[2],
                    Integer.parseInt(attributes[3]),
                    Double.parseDouble(attributes[4]),
                    Boolean.parseBoolean(attributes[5]),
                    parseMemberTeam(attributes));

            membersList.add(members);
        }

        sc.close();
        return membersList;
    }

    private Team parseMemberTeam(String[] attributes){
        if (attributes[6].toLowerCase().equals("junior")){
            return Team.JUNIOR;
        }else if(attributes[6].toLowerCase().equals("senior")){
            return Team.SENIOR;
        }else if (attributes[6].toLowerCase().equals("motionist")){
            return Team.EXCERCISER;
        }
        return null;
    }

    //checks for changes from the user
    public Boolean lastMemberCheck(String lastLine, ArrayList<Member> memberList) {
        String[] lastMemberCsv = lastLine.split(";");

        String lastMemberCsvEmail = lastMemberCsv[0];
        String lastMemberListEmail = memberList.get(memberList.size() - 1).getEmail();

        return lastMemberCsvEmail.equals(lastMemberListEmail);
    }

    //get the last line of the csv file
    public String getLastLine(File file) {
        Scanner scanner;
        String lastLine;
        try {
            scanner = new Scanner(file);
            lastLine = null;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                lastLine = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lastLine;
    }

    public String toCsv(Member member) {
        return member.getEmail() + ";" +
                member.getFirstName() + ";" +
                member.getLastName();
    }

    public File getFile() {
        return file;
    }

   /* public Boolean lastMemberCheck(String lastLine, ArrayList<Member> memberList) {
        String[] lastMemberCsv = lastLine.split(";");

        String lastMemberCsvEmail = lastMemberCsv[0];
        String lastMemberListEmail = memberList.get(memberList.size() - 1).getEmail();

        return Objects.equals(lastMemberCsvEmail, lastMemberListEmail);
    }*/

}


