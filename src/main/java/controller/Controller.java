package controller;

import domainmodel.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class Controller {
    Database database = new Database();
    Economy economy = new Economy();

    public ArrayList<Member> getMemberlist() {
        return database.getMembersList();
    }

    public ArrayList<SwimResult> getSwimResults() {
        return database.getSwimResults();
    }

    public void addMember(String email, String firstName, String lastName, LocalDate birthday, Boolean status, Team team, boolean isPaid) {
        database.addMember(email, firstName, lastName, birthday, status, team, isPaid);
    }

    public ArrayList<Member> findMember(String email) {
        return database.findMembers(email);
    }

    public int totalIncome(){
        return economy.totalIncome(database.getMembersList());
    }

    public int calculateIncome(Member member){
        return database.calculateIncome(member);
    }

    public double totalDebt(){
        return economy.totalDebt(database.getMembersList());
    }

    public double individualMemberDebt(Member member){
        return database.individualMemberDebt(member);
    }

    public void loadData(){
        database.loadData();
    }

    public ArrayList<SwimResult> disciplineResults(Discipline discipline){
        return database.disciplineResults(getSwimResults(),discipline);
    }

    public void saveData(){
        database.saveData();
    }

    public void savaResults(){
        database.saveResults();
    }

    public void loadResults(){
        database.loadResult();
    }
}