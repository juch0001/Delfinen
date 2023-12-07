package datasource;

import domainmodel.Discipline;
import domainmodel.Member;
import domainmodel.SwimResult;
import domainmodel.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public void saveData(ArrayList<Member> memberList, String fileName) {
        File file = new File(fileName);
        if (!lastMemberCheck(getLastLine(file), memberList)) {
            try {
                PrintStream printStream = new PrintStream(fileName);
                for (Member member : memberList) {
                    printStream.print(memberToCsv(member));
                }
                printStream.close();
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("file ikke fundet");
            }
        }
    }


    public void saveResults(ArrayList<SwimResult> swimResults, String filename){
        File file = new File(filename);
        if (!lastResultCheck(getLastLine(file), swimResults)){
            try {
                PrintStream printStream = new PrintStream(file);
                for (SwimResult swimResult:swimResults) {
                    printStream.print(resultToCsv(swimResult));
                }
                printStream.close();
            }catch (FileNotFoundException fileNotFoundException){
                System.out.println("fil ikke fundet");
            }
        }
    }
    public boolean lastResultCheck(String lastLine ,ArrayList<SwimResult> swimResults){
        String[] lastResult = lastLine.split(";");

        String lastResultEmail = swimResults.get(swimResults.size() - 1).getEmail();
        double lastResultTime = swimResults.get(swimResults.size() - 1).getTime();
        String lastResultEmailCsv = lastResult[0];

        return lastResultEmail.equals(lastResultEmailCsv);
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
        //DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");

            String email = attributes[0];
            String firstName = attributes[1];
            String lastName = attributes[2];

            try {
                LocalDate birthday = LocalDate.parse(attributes[3]);
                boolean status = Boolean.parseBoolean(attributes[4]);
                boolean isPaid = Boolean.parseBoolean(attributes[6]);

                member = new Member(
                        email,
                        firstName,
                        lastName,
                        birthday,
                        status,
                        parseMemberTeam(attributes[5]),
                        isPaid);
                membersList.add(member);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + attributes[3]);
            }
        }

        sc.close();
        return membersList;
    }
    public ArrayList<SwimResult> loadSwimResult(File file){
        ArrayList<SwimResult> swimResultList = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        SwimResult swimResult;
        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] attributes = line.split(";");

            try {
                String email = attributes[0];
                LocalDate date = LocalDate.parse(attributes[1]);
                Discipline discipline = parseDiscipline(attributes[2]);
                double time = Double.parseDouble(attributes[3]);
                String tournament = attributes[4];
                int tournamentPlacement = Integer.parseInt(attributes[5]);
                swimResult = new SwimResult(email,date,discipline,time,tournament,tournamentPlacement);
                swimResultList.add(swimResult);
            }catch (DateTimeParseException e){
                System.err.println("Error parsing date: " + attributes[1]);
            }


        }
        sc.close();
        return swimResultList;
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

    public String memberToCsv(Member member) {
        return member.getEmail() + ";" +
                member.getFirstName() + ";" +
                member.getLastName() + ";" +
                member.getBirthday() + ";" +
                member.getStatus() + ";" +
                member.getTeam() + ";" +
                member.isPaid() + "\n";
    }
    public String resultToCsv(SwimResult swimResult){
        return swimResult.getEmail() + ";" +
                swimResult.getDate() + ";" +
                swimResult.getDiscipline() + ";" +
                swimResult.getTime() + ";" +
                swimResult.getTournament() + ";" +
                swimResult.getTournamentPlacement() + ";" + "\n";
    }

    public Discipline parseDiscipline(String discipline){
        if (discipline.equalsIgnoreCase("Crawl")){
            return Discipline.CRAWL;
        }else if (discipline.equalsIgnoreCase("Brystsvømning")){
            return Discipline.BREASTSTROKE;
        } else if (discipline.equalsIgnoreCase("Butterfly")) {
            return Discipline.BUTTERFLY;
        } else if (discipline.equalsIgnoreCase("Ryg crawl")) {
            return Discipline.BACK_CRAWL;
        }
        return null;
    }

}
