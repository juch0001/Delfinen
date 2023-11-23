package domainmodel;

import domainmodel.Member;

public class Competitor extends Member {
    private Discipline discipline;

    public Competitor(String firstName,String lastName, int age, double debt, String email, Boolean status, Discipline discipline) {
        super(firstName, lastName, age, debt, email, status);
        this.discipline = discipline;
    }

}
