package domainmodel;

import java.util.ArrayList;

import java.util.ArrayList;

public class Competitor extends Member {
    //TODO ARRAYLIST
    protected ArrayList<Discipline> disciplines;

    public Competitor(String email, String firstName,String lastName, int age, double debt, Boolean status, Team team, ArrayList<Discipline> disciplines) {
        super(email,firstName, lastName, age, debt, status, team);
        this.discipline = discipline;
    }
    public Discipline getDiscipline(){
        return discipline;
    }
}
