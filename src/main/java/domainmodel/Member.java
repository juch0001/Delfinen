package domainmodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Member {

    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean status;
    private Team team;
    boolean isPaid;

    public Member(String email, String firstName,String lastName, LocalDate birthday, boolean status, Team team, boolean isPaid){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.status = status;
        this.team = team;
        this.isPaid = isPaid;
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

    public boolean isPaid() {
        return isPaid;
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

    public void setBirthday (LocalDate birthday){
        this.birthday = birthday;
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
            return "Aktiv";
        }else return "Passiv";
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }

    public void teamList() {
        int age = calculateAge();
        if (!(team == Team.EXERCISER)) {
            if (age < 18) {
                setTeam(Team.JUNIOR);
            } else {
                setTeam(Team.SENIOR);
            }
        }
    }

    public int calculateIncome() {
        if (status) {
            switch (team) {
                case JUNIOR:
                    return 1000;
                case SENIOR:
                    return 1600;
                case PENSIONER:
                    return 1200;
                case EXERCISER:
                    if (calculateAge() < 17){
                        return 1600;
                    }
                    return 1000;
            }
        } else {
            return 500;
        }
        return 0;
    }

    public double individualMemberDebt() {
        if (isPaid) {
            return 0;
        }
        return calculateIncome();
    }
}