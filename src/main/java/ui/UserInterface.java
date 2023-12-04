package ui;

import controller.Controller;
import domainmodel.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            System.out.println("1. Opret medlem");
            System.out.println("2. Se komplet medlemsliste");
            System.out.println("3. Søg E-mail for et medlem");
            System.out.println("4. Se indkomst");
            System.out.println("5. Se gæld");
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
                    printTotalIncome();
                    break;
                case 5:
                    printTotalDebt();
                    break;
                case 6:
                    //
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
            System.out.println(member.getEmail() + " " +
                    member.getFirstName() + " " +
                    member.getLastName() + " " +
                    member.getBirthday() + " " +
                    member.statusToString(member.getStatus()) + " " +
                    member.getTeam());

        }
    }


    //TODO Teste tilføjelse af disciplin
    public void addMemberMenu() {
        String email;
        boolean validEmail;
        String birthdayString;
        LocalDate birthday = null;

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

        //Sørger for at returnere igen istedet for at lukke programmet hvis man indtaster forkert.
        DateTimeFormatter dateFormatter = null;
        do {
            System.out.println("Indtast din fødsesldag (dd-MM-yyyy) : "); //age
            birthdayString = keyboard.next();
            try {
                dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                birthday = LocalDate.parse(birthdayString, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Forkert indtastning. Prøv igen. ");
            }
        } while (birthday == null);


        //Tildeler hvilket hold man er på afhængigt af alderen.
        Team team;
        if (calculateAge(birthday) < 18) {
            team = Team.JUNIOR;
            System.out.println("Du er tilmeldt hold: Junior");
        } else {
            team = Team.SENIOR;
            System.out.println("Du er tilmeldt hold: Senior");
        }

        System.out.println("Er medlemmet aktiv svømmer (ja/nej) : "); //status
        boolean status = keyboard.next().equalsIgnoreCase("ja");

        System.out.println("Indtast dags dato (dd-MM-yyyy) : ");
        String membershipSignUpDateString = keyboard.next();
        LocalDate membershipSignUpDate = LocalDate.parse(membershipSignUpDateString, dateFormatter);

        System.out.println("Du har tilføjet ét nyt medlem: \n" +
                "Email: " + email + "\n" + "Fornavn: " + firstName + "\n" + "Efternavn: " + lastName + "\n" +
                "Hold: " + team + "\n" + "Aktiv svømmer: " + (status ? "ja" : "nej") + "\n");

        System.out.println("Er medlemmet motionist eller konkurrencesvømmer: ");
        String competitorInput = keyboard.next().toLowerCase();
        if (competitorInput.equals("ja".toLowerCase())) { //TODO GØR DET HER TIL EN METODE????
            System.out.println("Hvilke discipliner svømmer medlemmet i? (Rygcrawl/Crawl/Butterfly/Brystsvømning): ");
            String disciplineInput = keyboard.next().toLowerCase();
            EnumMap<Discipline, Double> results = new EnumMap<>(Discipline.class); //
            switch (disciplineInput.toLowerCase()) {
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


            //controller.addMember(email, firstName, lastName, birthday, status, team, membershipSignUpDateString);

            //TODO
            System.out.println("Er kontingentet betalt ved oprettelse? (ja/nej)");
            boolean subscriptionPaid = keyboard.next().equalsIgnoreCase("ja");

            System.out.println(firstName + " " + lastName + " svømmer i disse discipliner: " +
                    disciplineInput);

        }

    }

    private int calculateAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }


    public void searchMemberUI() {
        System.out.println("Indtast e-mail for at søge efter medlem: ");
        String emailInput = keyboard.next();

        ArrayList<Member> searchResults = controller.findMember(emailInput);

        if (searchResults.isEmpty()) {
            System.out.println("Ingen medlemmer fundet");
        } else {
            System.out.println("Medlemmer fundet: ");
            for (Member member : searchResults) {
                printMemberInfo(member);
            }
        }
    }


    public void printMemberInfo(Member member) {
        System.out.println("E-mail: " + member.getEmail());
        System.out.println("Fornavn: " + member.getFirstName());
        System.out.println("Efternavn: " + member.getLastName());
        System.out.println("Fødselsdag: " + member.getBirthday());
        System.out.println("Alder: " + member.calculateAge());
        System.out.println("Status: " + member.statusToString(member.getStatus()));
        System.out.println("Hold: " + member.getTeam());
        System.out.println("Resultater:");
        for (Discipline discipline : Discipline.values()) {
            System.out.println(discipline + ": ");
        }
        System.out.println("Konkurrencesvømmer: Nej");
        System.out.println("Udestående gæld: " + controller.individualMemberDebt(member));
    }

    public void addSwimmingResult() { //TODO TILPAS TIL VORES SWIMRESULT CLASS
        System.out.println("Indtast e-mail for medlemmet: ");
        String emailInput = keyboard.next();
        ArrayList<Member> searchResults = controller.findMember(emailInput);


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
            System.out.println("Skriv tiden: (1.23)"); //TODO man skal kunne skrive . imellem tallene(lige nu virker det kun med hele tal)
            double userChoiceTime = keyboard.nextDouble();


            for (Member member : searchResults) {
                SwimResult swimResult = new SwimResult(member.getEmail(), LocalDate.now(), discipline, userChoiceTime, " ");
                System.out.println(swimResult.getSwimmingDetails());
            }
        } else {
            System.out.println("Ugyldig disciplinvalg.");
        }

        if (searchResults.isEmpty()) {
            System.out.println("Ingen medlemmer fundet med den angivne e-mail eller medlemmet er ikke en konkurrencesvømmer.");
        }
    }


        public void printTotalIncome () {
            System.out.println("Total årlig kontingent indkomst: " + controller.totalIncome() + "kr.\n");
            for (Member member : controller.getMemberlist()) {
                System.out.println(member.getFirstName() + " " +
                        member.getLastName() + " (" +
                        member.getTeam() + "): " + controller.individualMemberIncome(member) + "kr.");
            }
        }

        public void printTotalDebt () {
            System.out.println("Total gæld blandt medlemmer i klubben: " + controller.totalDebt() + "kr.\n");
            for (Member member : controller.getMemberlist()) {
                System.out.println(member.getFirstName() + " " +
                        member.getLastName() + ": til betaling " +
                        controller.individualMemberDebt(member) + "kr.");
            }
        }
    }


