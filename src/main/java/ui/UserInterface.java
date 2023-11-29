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
                    //TODO sout spørg bruger om email - Klaret
                    //TODO printe medlems info ud på medlem man søger på - klaret??
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
    //TODO email unik check - sørge for at der SKAL være @ i
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
                        System.out.println("Emailen eksisterer allerede. Indtast en ny mail: ");
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
        if (competitorInput.equals("ja".toLowerCase())) {
            System.out.println("Hvilke discipliner svømmer medlemmet i? (Rygcrawl/Crawl/Butterfly/Brystsvømning): ");
            String disciplineInput = keyboard.next().toLowerCase();
            ArrayList<Discipline> tempDisciplines = new ArrayList<>();

            switch (disciplineInput.toLowerCase()){
                case "rygcrawl", "ryg", "r":
                    tempDisciplines.add(BACK_CRAWL);
                    break;
                case "crawl", "cra", "c":
                    tempDisciplines.add(CRAWL);
                    break;
                case "butterfly", "but":
                    tempDisciplines.add(BUTTERFLY);
                    break;
                case "brystsvømning", "bry":
                    tempDisciplines.add(BREASTSTROKE);
                    break;
                default:
                    System.out.println("Disciplin findes ikke");
            }

            System.out.println( firstName + " " + lastName + " svømmer i disse discipliner: " +
                    disciplineInput);
            EnumMap<Discipline, Double> results = new EnumMap<>(Discipline.class); //TODO test om man kan have mere af samme discipline
            controller.addCompetitiveMember (email, firstName, lastName, age, debt, status, team, tempDisciplines, results);

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
}