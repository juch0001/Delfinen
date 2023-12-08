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
    public static final String BLACK_BOLD = "\033[1;30m"; //Markeret sort (hvis man har hvid skærm)
    public static final String RED_BOLD = "\033[1;31m"; //rød tekst
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

    private String yesNo(){
        Scanner scanner = new Scanner(System.in);
        String answer;
        do {
            answer = scanner.next();
            if (!answer.equalsIgnoreCase("ja") && !answer.equalsIgnoreCase("nej")){
                System.out.println("Du skal skrive ja eller nej...");
            }
        }while (!answer.equalsIgnoreCase("ja") && !answer.equalsIgnoreCase("nej"));
        return answer;
    }

    public void startProgram() {
        boolean runProgram = true;
        int menuNumber;
        printDolphin();

        while (runProgram) {
            System.out.println("\u2501".repeat(80) + RESET);
            System.out.println(BLUE_BOLD + "Menu: " + RESET);
            System.out.println(BLUE_BOLD + "1. " + RESET + "Opret medlem");
            System.out.println(BLUE_BOLD + "2. " + RESET + "Se komplet medlemsliste");
            System.out.println(BLUE_BOLD + "3. " + RESET + "Søg E-mail for et medlem");
            System.out.println(BLUE_BOLD + "4. " + RESET + "Se indkomst");
            System.out.println(BLUE_BOLD + "5. " + RESET + "Se gæld");
            System.out.println(BLUE_BOLD + "6. " + RESET + "Tilføj resultat");
            System.out.println(BLUE_BOLD + "7. " + RESET + "Se top 5 resultater i hver disciplin");
            System.out.println(BLUE_BOLD + "8. " + RESET + "Afslut program");
            System.out.println("\u2501".repeat(80) + RESET);


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
        boolean status = yesNo().equalsIgnoreCase("ja"); //TODO TEST

        System.out.println("Er kontingentet betalt ved oprettelse? (ja/nej)");
        boolean subscriptionPaid = yesNo().equalsIgnoreCase("ja"); //TODO TEST

        System.out.println(BLACK_BOLD + "Du har tilføjet ét nyt medlem: \n" + RESET +
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
            System.out.println(BLACK_BOLD + "Medlemmer fundet: " + RESET);
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


    public void addResults() {
        boolean addMoreResults;

        do {
            System.out.println("Indtast e-mail for medlemmet: ");
            String emailInput = keyboard.next();
            ArrayList<Member> searchResults = controller.findMember(emailInput);

            if (searchResults.isEmpty()) {
                System.out.println("Ingen medlemmer fundet med den angivne e-mail.");
            } else {
                Member selectedMember = searchResults.get(0);
                System.out.println("Email fundet: " + searchResults.get(0).getEmail());
                do {
                    System.out.println("Hvilken disciplin vil du tilføje en tid til? (indtast tal): ");
                    System.out.println("1. RygCrawl");
                    System.out.println("2. Crawl");
                    System.out.println("3. Butterfly");
                    System.out.println("4. Bryst Svømning");

                    int userChoiceDiscipline = scanIntWithRetry();
                    Discipline discipline = null;
                    boolean userChoiceStatus = true;

                    switch (userChoiceDiscipline) { //TODO TEST
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
                            System.out.println("Skriv tiden (eks. 1.23):");
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
                        System.out.println("Skriv dato (yyyy-mm-dd): ");
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

                        System.out.println("Er dette et trænings resultat eller stævne resultat\n" +
                                "1. træning\n" +
                                "2. stævne");
                        int userChoice;
                        String tournament = "null";
                        do {
                            userChoice = scanIntWithRetry();
                            if (userChoice == 1){ //training
                                SwimResult swimResult = new SwimResult(selectedMember.getEmail(), date, discipline, userChoiceTime, tournament, 0);
                                controller.getSwimResults().add(swimResult);
                                System.out.println(BLACK_BOLD + "Resultat blev tilføjet:" + RESET);
                                System.out.println(swimResult.getEmail());
                            }else if(userChoice == 2) { //tournament
                                System.out.println("Navn på stævne: ");
                                tournament = keyboard.next();
                            }
                        }while (userChoice > 2);
                            System.out.println("Skriv placering fra stævnet: ");
                            int placement = scanIntWithRetry();


                            SwimResult swimResult = new SwimResult(selectedMember.getEmail(), date, discipline, userChoiceTime, tournament, placement);
                            controller.getSwimResults().add(swimResult);
                            System.out.println(BLACK_BOLD + "Resultat blev tilføjet:" + RESET);
                            System.out.println(swimResult);

                    } else {
                        System.out.println("Ugyldig disciplinvalg.");
                    }

                    // Spørg brugeren, om de ønsker at tilføje flere resultater
                    System.out.println("\nVil du tilføje flere resultater? (ja/nej)");
                    addMoreResults = yesNo().equalsIgnoreCase("ja");

                } while (addMoreResults);
            }

            // Spørg brugeren, om de ønsker at søge efter et nyt medlem
            System.out.println("Vil du søge efter et nyt medlem? (ja/nej)");
            addMoreResults = yesNo().equalsIgnoreCase("ja");

        } while (addMoreResults);
    }


    public void printTotalIncome() {
        for (Member member : controller.getMemberlist()) {
            System.out.println(member.getFirstName() + " " +
                    member.getLastName() + " (" +
                    member.getTeam() + "): " + controller.calculateIncome(member) + "kr.");
        }
        System.out.println(BLACK_BOLD + "\nTotal årlig kontingent indkomst: " + controller.totalIncome() + "kr.\n" + RESET);
    }


    public void printTotalDebt() {
        for (Member member : controller.getMemberlist()) {
            if (!member.isPaid()) {
                System.out.println(member.getFirstName() + " " +
                        member.getLastName() + ": til betaling " +
                        RED_BOLD + controller.individualMemberDebt(member) + "kr." + RESET);
            }
        }
        System.out.println(BLACK_BOLD + "\nTotal gæld blandt medlemmer i klubben: " + RESET + RED_BOLD + controller.totalDebt() + "kr.\n" + RESET);
    }


    public void topFiveSwimmers(ArrayList<SwimResult> swimResults) {
        Collections.sort(swimResults, Collections.reverseOrder(new TimeComparator()));

        System.out.printf("%sTop 5 svømmere:%s\n", BLUE_BOLD, RESET);

        System.out.printf("%s%-23s %-15s %-20s %-10s %-4s%s\n",
                BLUE_BOLD, "Disciplin", "Dato", "Email", "Tid", "Stævne", RESET); //Udskriver overkrifterne

        int count = Math.min(5, swimResults.size());
        for (int i = 0; i < count; i++) {
            SwimResult result = swimResults.get(i);
            System.out.printf("%s%d.%s %-20s %-15s %-20s %-10s %-10s%s\n",
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
            System.out.println("Vælg en disciplin (indtast tal):");
            System.out.println("1. Ryg crawl\n" +
                    "2. Bryst\n" +
                    "3. Butterfly\n" +
                    "4. Crawl");
            userChoice = scanIntWithRetry();
            try{
                switch (userChoice){
                    case 1 -> topFiveSwimmers(controller.disciplineResults(Discipline.BACK_CRAWL));
                    case 2 -> topFiveSwimmers(controller.disciplineResults(Discipline.BREASTSTROKE));
                    case 3 -> topFiveSwimmers(controller.disciplineResults(Discipline.BUTTERFLY));
                    case 4 -> topFiveSwimmers(controller.disciplineResults(Discipline.CRAWL));
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("Der er ikke nok resultater");
            }
        }while (userChoice > 4);
    }
}