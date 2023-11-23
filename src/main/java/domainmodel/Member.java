package domainmodel;

public class Member {
    private String firstName;
    private String lastName;
    private int age;
    private double debt;
    private String email;
    private boolean status;

    public Member(String firstName,String lastName, int age, double debt, String email, Boolean status){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.debt = debt;
        this.email = email;
        this.status = status;
    }

}
