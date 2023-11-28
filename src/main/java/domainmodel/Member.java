package domainmodel;

public class Member {
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private double debt;
    private boolean status;
    private Team team;

    public Member(String email, String firstName,String lastName, int age, double debt, Boolean status, Team team){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.debt = debt;
        this.status = status;
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge(){
        return age;
    }

    public double getDebt() {
        return debt;
    }

    public boolean getStatus(){
        return status;
    }

    public Team getTeam() {
        return team;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public void setAge (int age){
        this.age = age;
    }

    public void setDebt (double debt){
        this.debt = debt;
    }

    public void setStatus (boolean status){
       this.status = status;
    }

    public void setTeam (Team team){
        this.team = team;
    }

    public String statusToString(boolean status){
        if (status){
            return "Aktiv";
        }else return "Passiv";
    }

}
