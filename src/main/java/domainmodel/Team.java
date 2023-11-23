package domainmodel;

import domainmodel.Member;

public class Team extends Member {
private String team;

public Team(String firstName,String lastName, int age, double debt, String email, Boolean status, String team) {
super(firstName, lastName, age, debt, email, status);
this.team = team;
}

//TODO Method for set domainmodel.Team to junior or senior (motionister?)
}
