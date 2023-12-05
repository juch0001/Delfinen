package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SwimResult {
    // resultat, tid, dato, disciplin, stævne/træning - hvis stævne, hvad er stævnenavn/sted
    // tjekke via mail - alle tider med mail findes og evt udskrives
    // nemmere med arrayliste til resultater da der er mange komponenter
    private LocalDate date;
    private Discipline discipline;
    private String email;
    private double time;
    private String tournament;

    public SwimResult(String email, LocalDate date, Discipline discipline, double time, String tournament){
        this.email = email;
        this.date = date;
        this.discipline = discipline;
        this.time = time;

        System.out.println("Indtast stævne: ");
        Scanner scanner = new Scanner(System.in);
        this.tournament = scanner.nextLine();
    }

    public String getSwimmingDetails() {
        return "Email: " + email +
                "\nDato: " + date +
                "\nDisciplin: " + discipline +
                "\nTid: " + time +
                "\nS" + tournament;
    }

}

