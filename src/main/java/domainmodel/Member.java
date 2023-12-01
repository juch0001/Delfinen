package domainmodel;

public class Member {
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean status;
    private Team team;

    public Member(String email, String firstName,String lastName, LocalDate birthday, Boolean status, Team team){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
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

    public LocalDate getBirthday(){
        return birthday;
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

    public void setBirthYear (LocalDate birthday){
        this.birthday = birthday;
    }


    public void setStatus (boolean status){
        this.status = status;
    }

    public void setTeam (Team team){
        this.team = team;
    }

    public String statusToString(boolean status){
        if (status){
            return "Ja"; //aktiv
        }else return "Nej"; //passiv
    }

}