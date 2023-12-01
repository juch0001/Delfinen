package ui;

import controller.Controller;
import domainmodel.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

import static domainmodel.Discipline.*;

public class UserInterface {
    Controller controller = new Controller();
    private static Scanner keyboard = new Scanner(System.in);

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
                case 7:
                    addSwimmingResult();
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
                        member.getBirthday() + " " +
                        member.statusToString(member.getStatus()) + " " +
                        member.getTeam() + " " +
                        ((Competitor) member).getDisciplines());
            } else {
                System.out.println(member.getEmail() + " " +
                        member.getFirstName() + " " +
                        member.getLastName() + " " +
                        member.getBirthday() + " " +
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

            //Tjek om e-mail allerede eksisterer
            ArrayList<Member> existingMembers = controller.getMemberlist();
            validEmail = true;

            for (Member existingMember : existingMembers) {
                if (existingMember.getEmail().equalsIgnoreCase(email)) {
                    System.out.println("Emailen eksisterer allerede. ");
                    validEmail = false;
                    break;
                }
            }

            //Tjekker om der er @ og . i gyldig mail
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Ikke gyldig email. Indtast en ny: ");
                validEmail = false;
            }

        } while (!validEmail);

        System.out.println("Indtast første navn: "); //first name
        String firstName = keyboard.next();

        System.out.println("Indtast efternavn: "); //last name
        String lastName = keyboard.next();

        // TODO automatisere team på alder
        System.out.println("Indtast din fødsesldag (dd.mm.yyyy) : "); //age
        int age = scanIntWithRetry();

        System.out.println("Er medlemmet aktiv svømmer (ja/nej) : "); //status
        boolean status = keyboard.next().equalsIgnoreCase("ja");

        System.out.println("Du har tilføjet ét nyt medlem: \n" +
                email + "\n" + firstName + " " + lastName + "\n" +
                age + "\n" + (status? "ja" : "nej") + "\n" + team);

        System.out.println("Er medlemmet motionist eller konkurrencesvømmer: ");
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
            controller.addCompetitiveMember (email, firstName, lastName, age, status, team, results);

        }else {
            controller.addMember(email, firstName, lastName, age, status, team);
        }

    }



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



    public static void printMemberInfo(Member member) {
        System.out.println("E-mail: " + member.getEmail());
        System.out.println("Fornavn: " + member.getFirstName());
        System.out.println("Efternavn: " + member.getLastName());
        System.out.println("Fødselsdag: " + member.getBirthday());
        System.out.println("Status: " + member.statusToString(member.getStatus()));
        System.out.println("Hold: " + member.getTeam());

        // Tjekker om medlemmet er en konkurrencesvømmer
        if (member instanceof Competitor) {
            Competitor competitor = (Competitor) member;
            EnumMap<Discipline, Double> results = competitor.getResults();

            System.out.println("Konkurrencesvømmer: Ja");

            // Udskriver resultater for hver disciplin
            System.out.println("Resultater:");
            for (Discipline discipline : Discipline.values()) {
                Double time = results.getOrDefault(discipline, 0.0);
                System.out.println(discipline + ": " + time);
            }
        } else {
            System.out.println("Konkurrencesvømmer: Nej");
        }
    }

    public void addSwimmingResult() { //TODO TILPAS TIL VORES SWIMRESULT CLASS
        System.out.println("Indtast e-mail for medlemmet: ");
        String emailInput = keyboard.next();
        ArrayList<Member> searchResults = controller.findMember(emailInput);

        if (!searchResults.isEmpty() && searchResults.get(0) instanceof Competitor) {
            Competitor competitor = (Competitor) searchResults.get(0);

            System.out.println("Hvilken disciplin vil du tilføje en tid til? (Skriv tallet): ");
            System.out.println("1. RygCrawl");
            System.out.println("2. Crawl");
            System.out.println("3. Butterfly");
            System.out.println("4. Bryst Svømning");

            int userChoiceDiscipline = scanIntWithRetry();
            Discipline discipline = null;
            boolean userChoiceStatus = true;

            switch (userChoiceDiscipline) {
                case 1 -> discipline = Discipline.BACK_CRAWL;
                case 2 -> discipline = Discipline.CRAWL;
                case 3 -> discipline = Discipline.BUTTERFLY;
                case 4 -> discipline = Discipline.BREASTSTROKE;
                default -> userChoiceStatus = false;
            }

            if (userChoiceStatus) {
                System.out.println("Du valgte " + discipline);
                System.out.println("Skriv tiden: (1.23)");
                double userChoiceTime = keyboard.nextDouble();

                EnumMap<Discipline, Double> results = competitor.getResults();
                results.put(discipline, userChoiceTime);

                System.out.println("Svømmeresultat tilføjet for " + competitor.getFirstName() + " " + competitor.getLastName());
            } else {
                System.out.println("Ugyldigt valg af disciplin.");
            }
        } else {
            System.out.println("Ingen medlemmer fundet med den angivne e-mail eller medlemmet er ikke en konkurrencesvømmer.");
        }
    }
    /* hvorfor er den her
    public static Scanner getKeyboard() {
        return keyboard;
    }
*/
}