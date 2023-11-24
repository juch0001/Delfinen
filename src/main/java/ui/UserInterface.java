package ui;

import controller.Controller;
import domainmodel.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();

    private int scanIntWithRetry() {
        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Not a number! Try again");
        }
        return scanner.nextInt();
    }
    public void startProgram() {
    Scanner keyboard = new Scanner(System.in);


        boolean runProgram = true;
        int menuNumber;

        while (runProgram) {
            System.out.println("Menu: ");
            System.out.println("1. Opret et medlem");
            System.out.println("2. Se den komplete medlemsliste");
            System.out.println("3. Søg Email for et medlem");
            System.out.println("4. Se den totale indkomst");
            System.out.println("5. Se den totale gæld");
            System.out.println("6. Tilføj træningsresultat");
            System.out.println("7. Tilføj svømmeresultat");
            System.out.println("8. Se alle resultater");


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
            }
        }
    }


    public void printMemberlist(ArrayList<Member> memberList){
        for (Member member:memberList) {
            if (member instanceof Competitor) {
                System.out.println(member.getEmail() + " " +
                        member.getFirstName() + " " +
                        member.getLastName() + " " +
                        member.getAge() + " " +
                        member.getDebt() + " " +
                        member.statusToString(member.getStatus()) + " " +
                        member.getTeam() + " " +
                        ((Competitor) member).getDiscipline());
            }else {
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
    Scanner keyboard = new Scanner(System.in);
    public void addMemberMenu(){
            System.out.println("Indtast e-mail: ");
            String email = keyboard.next();
            System.out.println("Indtast første navn: ");
            String firstName = keyboard.next();
            System.out.println("Indtast efternavn: ");
            String lastName = keyboard.next();
            System.out.println("Indtast alder: ");
            int age = keyboard.nextInt();
            System.out.println("Indtast gæld: ");
            double debt = keyboard.nextInt();
            System.out.println("Indtast status: ");
            String statusInput = keyboard.next();
            boolean status;
            if(statusInput.equals("passive")) {
                 status = false;
            } else {
                status = true;
            }
            System.out.println("Indtast hold: ");
            String teamInput = keyboard.next();
            Team team;
            if (teamInput.equals("Motionist")){
                team = Team.EXCERCISER;
            } else if (teamInput.equals("Junior")) {
                team = Team.JUNIOR;
            }else {
                team = Team.SENIOR;
            }
            System.out.println("Du har tilføjet ét nyt medlem: \n" +
                    email + "\n" + firstName + " " + lastName + "\n" +
                    age + "\n" + debt + "\n" + statusInput + "\n" + team);
            controller.addMember(email, firstName, lastName, age, debt, status, team);
           /* System.out.println("Er medlemmet konkurrence svømmer?");
            String competitorInput = keyboard.next().toLowerCase();
            if (competitorInput.equals("Yes".toLowerCase())) {
                System.out.println("Hvilke discipliner svømmer medlemmet i?");
                String disciplineInput = keyboard.next();

                System.out.println( firstName + " " + lastName + " svømmer i disse discipliner: " +
                        disciplineInput);
                //controller.addCompetitiveMember(disciplineInput);
            }else {

            }*/

    }
    public void searchMemberUI(){
        String emailInput = keyboard.next();
        ArrayList<Member> searchResults = controller.findMember(emailInput);
        if (searchResults.isEmpty()){
            System.out.println("Ingen medlemmer fundet");
        }else {
            System.out.println("Medlemmer fundet: ");
            for (Member name : searchResults) {
                System.out.println(" - " + name.getEmail());
            }
        }
    }
}
