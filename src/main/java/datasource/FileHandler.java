package datasource;

import domainmodel.Discipline;
import domainmodel.Member;
import domainmodel.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

public class FileHandler {
    public void saveData(ArrayList<Member> memberList, String fileName) {
        File file = new File(fileName);
        if (!lastMemberCheck(getLastLine(file), memberList)) {
            try {
                PrintStream printStream = new PrintStream(fileName);
                for (Member member : memberList) {
                    printStream.print(toCsv(member));
                }
                printStream.close();
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("file ikke fundet");
            }
        }
    }

    public ArrayList<Member> loadData(File file) {

        ArrayList<Member> membersList = new ArrayList();
        Scanner sc;
        try {
            sc = new Scanner(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Member member;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");
            String email = attributes[0];
            String firstName = attributes[1];
            String lastName = attributes[2];
            int age = Integer.parseInt(attributes[3]);
            boolean status = Boolean.parseBoolean(attributes[4]);
            member = new Member(
                    email,
                    firstName,
                    lastName,
                    age,
                    status,
                    parseMemberTeam(attributes[5]));
            membersList.add(member);
        }

        sc.close();
        return membersList;
    }

    public ArrayList<Member> loadCompData(File file) {

        ArrayList<Member> membersList = new ArrayList();
        Scanner sc;
        try {
            sc = new Scanner(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Competitor competitor;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");
            String email = attributes[0];
            String firstName = attributes[1];
            String lastName = attributes[2];
            int age = Integer.parseInt(attributes[3]);
            boolean status = Boolean.parseBoolean(attributes[4]);
            competitor = new Competitor(
                    email,
                    firstName,
                    lastName,
                    age,
                    status,
                    parseMemberTeam(attributes[5]),
                    parseDisciplinResult(attributes[6]) //TODO DET HER VIRKER MÅSKE FEJLEN KAN VÆRE AT VI SKAL HAVE PLACEHOLDERS VED DISCIPLIN ATTRIBUTEN!!!!
                    );
            membersList.add(competitor);
        }

        sc.close();
        return membersList;
    }

    private Team parseMemberTeam(String attributes) {
        if (attributes.equalsIgnoreCase("junior")) {
            return Team.JUNIOR;
        } else if (attributes.equalsIgnoreCase("senior")) {
            return Team.SENIOR;
        } else if (attributes.equalsIgnoreCase("motionist")) {
            return Team.EXERCISER;
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
                member.getLastName() + ";" +
                member.getAge() + ";" +
                member.getStatus() + ";" +
                member.getTeam() + "\n";
    }
    public Discipline parseDiscipline(String discipline){
        if (discipline.equals("CRAWL")){
            return Discipline.CRAWL;
        }else if (discipline.equals("BREASTSTROKE")){
            return Discipline.BREASTSTROKE;
        } else if (discipline.equals("BUTTERFLY")) {
            return Discipline.BUTTERFLY;
        } else if (discipline.equals("BACK_CRAWL")) {
            return Discipline.BACK_CRAWL;
        }
        return null;
    }
    public EnumMap<Discipline, Double> parseDisciplinResult(String timeAttribute){
        String[] disciplinesplit = timeAttribute.split(",");

        EnumMap<Discipline,Double> times = new EnumMap<>(Discipline.class);
        for (String disciplineTime: disciplinesplit) {
            String[] timeSplit = disciplineTime.split(":");
            Discipline discipline = parseDiscipline(timeSplit[0]);
            Double time = Double.valueOf(timeSplit[1]);
            times.put(discipline,time);
        }
        return times;
    }

}


