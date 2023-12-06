package domainmodel;

import java.time.LocalDate;

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
        this.tournament = tournament; //SÆT TIL NULL HVIS NU DET ER TRÆNING TODO INDE I UI
        this.tournamentPlacement = tournamentPlacement;
    }
    @Override
    public String toString() {
        return "Email: " + email +
                "\nDato: " + date +
                "\nDisciplin: " + discipline +
                "\nTid: " + time +
                "\nS" + tournament;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate() {
        return date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public double getTime() {
        return time;
    }

    public String getTournament() {
        return tournament;
    }

    public int getTournamentPlacement() {
        return tournamentPlacement;
    }


}

