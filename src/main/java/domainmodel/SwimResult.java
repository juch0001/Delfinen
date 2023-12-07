package domainmodel;

import java.time.LocalDate;

public class SwimResult {
    private LocalDate date;
    private Discipline discipline;
    private String email;
    private double time;
    private String tournament;
    private int tournamentPlacement;

    public SwimResult(String email, LocalDate date, Discipline discipline, double time, String tournament, int tournamentPlacement){
        this.email = email;
        this.date = date;
        this.discipline = discipline;
        this.time = time;
        this.tournament = tournament;
        this.tournamentPlacement = tournamentPlacement;
    }
    @Override
    public String toString() {
        return "Email: " + email +
                "\nDato: " + date +
                "\nDisciplin: " + discipline +
                "\nTid: " + time +
                "\nStævne: " + tournament;
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

