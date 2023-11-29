package controller;

import domainmodel.*;

import java.util.ArrayList;
import java.util.EnumMap;

public class Controller {
    Database database = new Database();
    Economy economy = new Economy();

    public ArrayList<Member> getMemberlist(){
        return database.getMembersList();
    }
    public void addMember(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team){
        database.addMember(email,firstName,lastName,age,debt,status,team);
    }

    public void addCompetitiveMember(String email, String firstName, String lastName, int age, double debt, Boolean status, Team team, ArrayList<Discipline> disciplines, EnumMap<Discipline, Double> results) {
        database.addCompetitor(email,firstName,lastName,age,debt,status, team, disciplines, results);
    }

    public ArrayList<Member> findMember(String email) {
        return database.findMember(email);
    }
    public void saveData(ArrayList<Member> memberList, String fileName){
        database.saveData(memberList, fileName);
    }

    public void addResult(EnumMap<Discipline, Double> results, Discipline discipline, double time){

    }

    //TODO SPØRG OM MAN MÅ DET HER
    public int totalIncome(){
        return economy.totalIncome(database.getMembersList());
    }
}