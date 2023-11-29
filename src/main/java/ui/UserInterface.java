package ui;

import controller.Controller;
import domainmodel.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

import static domainmodel.Discipline.*;

public class UserInterface {
    Controller controller = new Controller();
    Scanner keyboard = new Scanner(System.in);

    private int scanIntWithRetry() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Not a number! Try again");
        }
        return scanner.nextInt();
    }

    public void startProgram() {
        boolean runProgram = true;
        int menuNumber;

        while (runProgram) {
            System.out.println("Menu: ");
            System.out.println("1. Opret et medlem");
            System.out.println("2. Se den komplette medlemsliste");
            System.out.println("3. Søg E-mail for et medlem");
            System.out.println("4. Se den totale indkomst");
            System.out.println("5. Se den totale gæld");
            System.out.println("6. Tilføj træningsresultat");
            System.out.println("7. Tilføj svømmeresultat");
            System.out.println("8. Se top 5 resultater i hver disciplin");
            System.out.println("9. Afslut program");


            menuNumber = scanIntWithRetry();

            switch (menuNumber) {
                case 1:
                    addMemberMenu();
                    break;
                case 2:
                    printMemberlist(controller.getMemberlist());
                    break;
                case 3:
                    searchMemberUI();
                    break;
                case 4:
                    System.out.println("Total årlig kontingent indkomst: " + controller.totalIncome() + " kr.");
                    break;
                case 8:
                    //
                    break;
                case 9:
                    System.out.println("Programmet afsluttes...");
                    runProgram = false;
                    break;
            }
        }
    }

    public void printMemberlist(ArrayList<Member> memberList) {
        for (Member member : memberList) {
            if (member instanceof Competitor) {
                System.out.println(member.getEmail() + " " +
                        member.getFirstName() + " " +
                        member.getLastName() + " " +
                        member.getAge() + " " +
                        member.getDebt() + " " +
                        member.statusToString(member.getStatus()) + " " +
                        member.getTeam() + " " +
                        ((Competitor) member).getDisciplines());
            } else {
                System.out.println(member.getEmail() + " " +
                        member.getFirstName() + " " +
                        member.getLastName() + " " +
                        member.getAge() + " " +
                        member.getDebt() + " " +
                        member.statusToString(member.getStatus()) + " " +
                        member.getTeam());
            }
        }
    }

    //TODO Teste tilføjelse af disciplin

    public void addMemberMenu() {
            String email;
            boolean validEmail;

            do {
                System.out.println("Indtast e-mail: ");
                email = keyboard.next();

                // Tjek om e-mail allerede eksisterer
                ArrayList<Member> existingMembers = controller.getMemberlist();
                validEmail = true;

                for (Member existingMember : existingMembers) {
                    if (existingMember.getEmail().equalsIgnoreCase(email)) {
                        System.out.println("Emailen eksisterer allerede. ");
                        validEmail = false;
                        break;
                    }
                }

                if (!email.contains("@")) {
                    System.out.println("Ikke gyldig email. Indtast en ny: ");
                    validEmail = false;
                }

            } while (!validEmail);

        System.out.println("Indtast første navn: "); //first name
        String firstName = keyboard.next();

        System.out.println("Indtast efternavn: "); //last name
        String lastName = keyboard.next();

        System.out.println("Indtast alder: "); //age
        int age = scanIntWithRetry();

        System.out.println("Indtast gæld: "); //debt
        double debt = scanIntWithRetry();

        System.out.println("Indtast status (passiv/aktiv) : "); //status
        boolean status = keyboard.next().equalsIgnoreCase("Aktiv");

        System.out.println("Indtast hold (Motionist / Junior / Senior): "); //teams
        Team team;
        String teamInput = keyboard.next().toLowerCase();
        if (teamInput.equals("motionist")) {
            team = Team.EXCERCISER;
        } else if (teamInput.equals("junior")) {
            team = Team.JUNIOR;
        } else {
            team = Team.SENIOR;
        }
        System.out.println("Du har tilføjet ét nyt medlem: \n" +
                email + "\n" + firstName + " " + lastName + "\n" +
                age + "\n" + debt + "\n" + (status? "aktiv" : "passiv") + "\n" + team);


        System.out.println("Er medlemmet konkurrence svømmer? (Ja/Nej): ");
        String competitorInput = keyboard.next().toLowerCase();
        if (competitorInput.equals("ja".toLowerCase())) { //TODO GØR DET HER TIL EN METODE????
            System.out.println("Hvilke discipliner svømmer medlemmet i? (Rygcrawl/Crawl/Butterfly/Brystsvømning): ");
            String disciplineInput = keyboard.next().toLowerCase();
            EnumMap<Discipline, Double> results = new EnumMap<>(Discipline.class); //
            switch (disciplineInput.toLowerCase()){
                case "rygcrawl", "ryg", "r":
                    results.put(BACK_CRAWL, 0.0);
                    break;
                case "crawl", "cra", "c":
                    results.put(CRAWL, 0.0);
                    break;
                case "butterfly", "but":
                    results.put(BUTTERFLY, 0.0);
                    break;
                case "brystsvømning", "bry":
                    results.put(BREASTSTROKE, 0.0);
                    break;
                default:
                    System.out.println("Disciplin findes ikke");
            }

            System.out.println( firstName + " " + lastName + " svømmer i disse discipliner: " +
                    disciplineInput);
            controller.addCompetitiveMember (email, firstName, lastName, age, debt, status, team, results);

        }else {
            controller.addMember(email, firstName, lastName, age, debt, status, team);
        }

    }
    // public void
    public void searchMemberUI(){
        System.out.println("Indtast e-mail for at søge efter medlem: ");
        String emailInput = keyboard.next();

        ArrayList<Member> searchResults = controller.findMember(emailInput);

        if (searchResults.isEmpty()){
            System.out.println("Ingen medlemmer fundet");
        }else {
            System.out.println("Medlemmer fundet: ");
            for (Member member : searchResults) {
                printMemberInfo(member);
            }
        }
    }

    private void printMemberInfo(Member member) {
        System.out.println("E-mail: " + member.getEmail());
        System.out.println("Fornavn: " + member.getFirstName());
        System.out.println("Efternavn: " + member.getLastName());
        System.out.println("Alder: " + member.getAge());
        System.out.println("Gæld: " + member.getDebt());
        System.out.println("Status: " + member.statusToString(member.getStatus()));
        System.out.println("Hold: " + member.getTeam());
    }
    public void addResultUI(){
        System.out.println("Skriv email på personen du vil tilføje et resultat til");
        String email = keyboard.next();
        //Competitor competitor = controller.findMember(email).get(0);  TODO lav metode til competitor
        //System.out.println("Du vil ændre " + competitor.getFirstName() + " " + competitor.getLastName());
        System.out.println("Hvilken disicpline vil du tilføje en tid til?(Skriv tallet): ");
        System.out.println("1. RygCrawl");
        System.out.println("2. Crawl");
        System.out.println("3. Butterfly");
        System.out.println("4. Bryst Svømning");

        String userChoiceDiscipline = keyboard.next();
        Discipline discipline = null;
        boolean userChoiceStatus = true;
        switch (userChoiceDiscipline){
            case "1"-> discipline = BACK_CRAWL;
            case "2" -> discipline = CRAWL;
            case "3" -> discipline = BUTTERFLY;
            case "4" -> discipline = BREASTSTROKE;
            default -> userChoiceStatus = false;
        }
        if (userChoiceStatus){
            System.out.println("Du valgte " + discipline);
            System.out.println("Skriv tiden: (1.23)");
            double userChoiceTime = keyboard.nextDouble();
            //controller.addResult(competitor.getResults());
        }


    }
}