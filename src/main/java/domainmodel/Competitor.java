package domainmodel;

import java.util.ArrayList;

public class Competitor extends Member {
    //private String team;
    private Discipline discipline;

    public Competitor(String email, String firstName,String lastName, int age, double debt, Boolean status, Team team, Discipline discipline) {
        super(email,firstName, lastName, age, debt, status, team);
        this.discipline = discipline;
    }
    public Discipline getDiscipline(){
        return discipline;
    }

}
