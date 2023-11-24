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
}
