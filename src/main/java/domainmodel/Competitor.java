package domainmodel;

import java.util.ArrayList;
import java.util.EnumMap;

public class Competitor extends Member {
    protected ArrayList<Discipline> disciplines;
    protected EnumMap<Discipline, Double> results; //https://www.geeksforgeeks.org/difference-between-enummap-and-hashmap/

    public Competitor(String email, String firstName, String lastName, int age, Boolean status, Team team, EnumMap<Discipline, Double> results) {
        super(email,firstName, lastName, age, status, team);
        this.disciplines = disciplines;
        this.results = results;
    }

    public Boolean addResult(EnumMap<Discipline, Double> results, Discipline discipline, double time){
        if (time < results.get(discipline).doubleValue() || results.get(discipline).doubleValue() == 0) { //chekcer om tiden man tilføjer er hurtigere end den som allerede er tilføjet
            results.put(discipline, time);
            return true; //den blev added
        }else{
            return false; //den blev ikke tilføjet
        }
    }

    public EnumMap<Discipline, Double> getResults() {
        return results;
    }

    public ArrayList<Discipline> getDisciplines(){
        return disciplines;
    }


}