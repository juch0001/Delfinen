package domainmodel;

public class Competitor extends Member {
    private String team;
    private Discipline discipline;

    public Competitor(String email, String firstName,String lastName, int age, double debt, Boolean status,String team , Discipline discipline) {
        super(email,firstName, lastName, age, debt, status);
        this.team = team;
        this.discipline = discipline;
    }

}
