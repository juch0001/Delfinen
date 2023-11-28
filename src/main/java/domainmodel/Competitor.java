package domainmodel;

import java.util.ArrayList;
import java.util.EnumMap;

public class Competitor extends Member {
    //TODO ARRAYLIST
    protected ArrayList<Discipline> disciplines;
    protected EnumMap<Discipline, Double> results; //https://www.geeksforgeeks.org/difference-between-enummap-and-hashmap/

    public Competitor(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team, ArrayList<Discipline> disciplines, EnumMap<Discipline, Double> results) {
        super(email,firstName, lastName, age, debt, status, team);
        this.disciplines = disciplines;
        this.results = results;
    }
    public void addResult(EnumMap<Discipline, Double> results, Discipline discipline, double time){
        EnumMap<Discipline, Double> tempResults = results;
        tempResults.put(discipline, time);
    }

    public EnumMap<Discipline, Double> getResults() {
        return results;
    }

    public ArrayList<Discipline> getDisciplines(){
        return disciplines;
    }


}
