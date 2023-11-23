package domainmodel;

public class Member {
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private double debt;
    private boolean status;

    public Member(String email, String firstName,String lastName, int age, double debt, Boolean status){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.debt = debt;
        this.status = status;
    }

}
