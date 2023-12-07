package ui;

import controller.Controller;
import domainmodel.*;
import domainmodel.comparator.TimeComparator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;

public class UserInterface {
    public static final String BLUE_BOLD = "\033[1;34m"; //farvekode til blå
    public static final String BLUE_UNDERLINED = "\033[4;34m"; //blå linje under tekst
    public static final String BLACK_BOLD = "\033[1;30m"; //Markeret sort (hvis man har hvid skærm)
    public static final String RESET = "\033[0m";
    public static void printDolphin(){
        String dolphinArt =
                "     __\n" +
                        "                               _.-~  )\n" +
                        "                    _..--~~~~,'   ,-/     _\n" +
                        "                 .-'. . . .'   ,-','    ,' )\n" +
                        "               ,'. . . _   ,--~,-'__..-'  ,'\n" +
                        "             ,'. . .  (@)' ---~~~~      ,'\n" +
                        "            /. . . . '~~             ,-'\n" +
                        "           /. . . . .             ,-'\n" +
                        "          ; . . . .  - .        ,'\n" +
                        "         : . . . .       _     /\n" +
                        "        . . . . .          `-.:\n" +
                        "       . . . ./  - .          )\n" +
                        "      .  . . |  _____..---.._/ _____\n" +
                        "~---~~~~----~~~~             ~~";
        System.out.println(dolphinArt);

    }
    Controller controller = new Controller();
    private static Scanner keyboard = new Scanner(System.in);

    private int scanIntWithRetry() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Ikke et tal! Prøv igen");
        }
        return scanner.nextInt();
    }


    public void startProgram() {
        boolean runProgram = true;
        int menuNumber;

        while (runProgram) {
            printDolphin();
            System.out.println("Menu: ");
            System.out.println(BLUE_BOLD + "1. Opret medlem" + RESET);
            System.out.println("2. Se komplet medlemsliste");
            System.out.println("3. Søg E-mail for et medlem");
            System.out.println("4. Se indkomst");
            System.out.println("5. Se gæld");
            System.out.println("6. Tilføj resultat");
            System.out.println("7. Se top 5 resultater i hver disciplin");
            System.out.println("8. Afslut program");


            menuNumber = scanIntWithRetry();

            switch (menuNumber) {
                case 1:
                    addMemberMenu();
                    controller.saveData();
                    break;
                case 2:
                    controller.loadData();
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
                    addResults();
                    controller.savaResults();
                    break;
                case 7:
                    controller.loadResults();
                    topFiveMenu();
                    break;
                case 8:
                    System.out.println("Programmet afsluttes...");
                    runProgram = false;
                    break;
            }
        }
    }

    public void printMemberlist(ArrayList<Member> memberList) {
        System.out.printf(BLUE_BOLD + "%-20s %-20s %-20s %-15s %-15s %-15s\n",
                "Email", "Fornavn", "Efternavn", "Fødselsdag", "Status", "Hold" + RESET);


        for (Member member : memberList) {
            System.out.printf("%-20s %-20s %-20s %-15s %-15s %-15s\n",
                    member.getEmail(),
                    member.getFirstName(),
                    member.getLastName(),
                    member.getBirthday(),
                    member.statusToString(member.getStatus()),
                    member.getTeam());
        }
    }


    public void addMemberMenu() {
        String email;
        boolean validEmail;
        String birthdayString;
        LocalDate birthday = null;

        do {
            System.out.println("Indtast e-mail: ");
            email = keyboard.next();

            //Tjekker om e-mail allerede eksisterer
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
        }
        while (!validEmail);
        System.out.println("Indtast første navn: "); //first name
        String firstName = keyboard.next();

        System.out.println("Indtast efternavn: "); //last name
        String lastName = keyboard.next();

        //Sørger for at returnere igen istedet for at lukke programmet hvis man indtaster forkert.
        do {
            System.out.println("Indtast din fødsesldag (yyyy-MM-dd) : "); //age
            birthdayString = keyboard.next();
            try {
                birthday = LocalDate.parse(birthdayString);
            } catch (DateTimeParseException e) {
                System.out.println("Forkert indtastning. Prøv igen. ");
            }
        } while (birthday == null);

        //Tildeler hvilket hold man er på afhængigt af alderen.
        Team team;
        if (calculateAge(birthday) < 18) {
            team = Team.JUNIOR;
            System.out.println("Du er tilmeldt hold: Junior");
        }
        if (calculateAge(birthday) > 17 && calculateAge(birthday) < 60) {
            team = Team.SENIOR;
            System.out.println("Du er tilmeldt hold: Senior");
        } else {
            team = Team.PENSIONER;
            System.out.println("Du er tilmeldt hold: Pensionist");
        }

        System.out.println("Er medlemmet aktiv svømmer (ja/nej) : "); //status
        boolean status = keyboard.next().equalsIgnoreCase("ja");

        System.out.println("Er kontingentet betalt ved oprettelse? (ja/nej)");
        boolean subscriptionPaid = keyboard.next().equalsIgnoreCase("ja");

        System.out.println("Du har tilføjet ét nyt medlem: \n" +
                "Email: " + email + "\n" + "Fornavn: " + firstName + "\n" + "Efternavn: " + lastName + "\n" +
                "Hold: " + team + "\n" + "Aktiv svømmer: " + (status ? "ja" : "nej") + "\n");
        controller.addMember(email, firstName, lastName, birthday, status, team, subscriptionPaid);
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


    public void addResults() {//TODO test det her
        boolean addMoreResults;

        do {
            System.out.println("Indtast e-mail for medlemmet: ");
            String emailInput = keyboard.next();
            ArrayList<Member> searchResults = controller.findMember(emailInput);


            if (searchResults.isEmpty()) {
                System.out.println("Ingen medlemmer fundet med den angivne e-mail.");
            } else {
                Member selectedMember = searchResults.get(0);
                System.out.println("Email fundet: " + searchResults.get(0));
                do {
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
                        double userChoiceTime;

                        do {
                            System.out.println("Skriv tiden: (1.23)");
                            String userInput = keyboard.next();

                            try {
                                // Erstat komma med punktum og håndter også indtastning med punktum
                                userChoiceTime = Double.parseDouble(userInput.replace(',', '.'));
                                if (userChoiceTime < 0) {
                                    throw new NumberFormatException(); //hvis a er negativt.
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Ugyldigt input. Indtast venligst et gyldigt tal.");
                                userChoiceTime = -1;
                            }
                        } while (userChoiceTime < 0);
                        System.out.println("skriv dato (yyyy-mm-dd): ");
                        String dateString;
                        LocalDate date = null;
                        do {
                            dateString = keyboard.next();
                            try {
                                date = LocalDate.parse(dateString);
                            } catch (DateTimeParseException e) {
                                System.out.println("Forkert indtastning. Prøv igen. ");
                            }
                        } while (date == null);

                        System.out.println("Er dette et trænings resultat eller stævne resultat (1. træning, 2. stævne)");
                        int userChoice = scanIntWithRetry();
                        if (userChoice == 1){ //training
                            SwimResult swimResult = new SwimResult(selectedMember.getEmail(), date, discipline, userChoiceTime, "null", 0);
                            controller.getSwimResults().add(swimResult);
                            System.out.println(swimResult);
                            System.out.println("Blev tilføjet...");
                        }else if(userChoice == 2) { //tournament
                            System.out.println("navn på turnering: ");
                            String tournament = keyboard.next();

                            System.out.println("Skriv placering i turneringen: ");
                            int placement = scanIntWithRetry();


                            SwimResult swimResult = new SwimResult(selectedMember.getEmail(), date, discipline, userChoiceTime, tournament, placement);
                            controller.getSwimResults().add(swimResult);
                            System.out.println(swimResult);
                            System.out.println("Blev tilføjet");
                        }
                    } else {
                        System.out.println("Ugyldig disciplinvalg.");
                    }

                    // Spørg brugeren, om de ønsker at tilføje flere resultater
                    System.out.println("Vil du tilføje flere resultater? (ja/nej)");
                    addMoreResults = keyboard.next().equalsIgnoreCase("ja");

                } while (addMoreResults);
            }

            // Spørg brugeren, om de ønsker at søge efter et nyt medlem
            System.out.println("Vil du søge efter et nyt medlem? (ja/nej)");
            addMoreResults = keyboard.next().equalsIgnoreCase("ja");

        } while (addMoreResults);
    }


    public void printTotalIncome() {
        for (Member member : controller.getMemberlist()) {
            System.out.println(member.getFirstName() + " " +
                    member.getLastName() + " (" +
                    member.getTeam() + "): " + controller.calculateIncome(member) + "kr.");
        }
        System.out.println("\nTotal årlig kontingent indkomst: " + controller.totalIncome() + "kr.\n");
    }


    public void printTotalDebt() {
        for (Member member : controller.getMemberlist()) {
            if (!member.isPaid()) {
                System.out.println(member.getFirstName() + " " +
                        member.getLastName() + ": til betaling " +
                        controller.individualMemberDebt(member) + "kr.");
            }
        }
        System.out.println("\nTotal gæld blandt medlemmer i klubben: " + controller.totalDebt() + "kr.\n");
    }

    public void topFiveSwimmers(ArrayList<SwimResult> swimResults) {
        Collections.sort(swimResults, Collections.reverseOrder(new TimeComparator()));

        System.out.printf("%s%-20s %-20s %-20s %-10s %-10s%s\n",
                BLUE_BOLD, "Disciplin", "Dato", "Svømmer Email", "Tid", "Turnering", RESET); //Udskriver overkrifterne

        System.out.printf("%sTop 5 svømmere:%s\n", BLUE_BOLD, RESET);

        int count = Math.min(5, swimResults.size());
        for (int i = 0; i < count; i++) {
            SwimResult result = swimResults.get(i);
            System.out.printf("%s%d.%s %-20s %-20s %-20s %-10s %-10s%s\n",
                    BLUE_BOLD, i + 1, RESET,
                    result.getDiscipline(),
                    result.getDate(),
                    result.getEmail(),
                    result.getTime(),
                    result.getTournament(),
                    RESET);
        }
    }


    public void topFiveMenu(){
        int userChoice;
        do{
            System.out.println("1. ryg crawl 2. bryst 3. butterfly 4. crawl");
            userChoice = scanIntWithRetry();
            try{
                switch (userChoice){
                    case 1 -> topFiveSwimmers(controller.disciplineResults(Discipline.BACK_CRAWL));
                    case 2 -> topFiveSwimmers(controller.disciplineResults(Discipline.BREASTSTROKE));
                    case 3 -> topFiveSwimmers(controller.disciplineResults(Discipline.BUTTERFLY));
                    case 4 -> topFiveSwimmers(controller.disciplineResults(Discipline.CRAWL));
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("Der ikke nok resultater");
            }
        }while (userChoice > 4);


    }
}



