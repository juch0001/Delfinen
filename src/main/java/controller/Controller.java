package controller;

import domainmodel.Database;
import domainmodel.Discipline;
import domainmodel.Member;

import java.util.ArrayList;
import domainmodel.Team;

public class Controller {
    Database database = new Database();

    public ArrayList<Member> getMemberlist(){
        return database.getMembersList();
    }
    public void addMember(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team){
        database.addMember(email,firstName,lastName,age,debt,status,team);
    }

    public void addCompetitiveMember(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team, ArrayList<Discipline> disciplines) {
        database.addCompetitor(email,firstName,lastName,age,debt,status, team, disciplines);
    }

    public ArrayList<Member> findMember(String email) {
        return database.findMember(email);
    }
}
