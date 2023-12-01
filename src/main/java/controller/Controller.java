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
    public void addMember(String email, String firstName, String lastName, int age, Boolean status, Team team){
        database.addMember(email,firstName,lastName,age,status,team);
    }

    public void addCompetitiveMember(String email, String firstName, String lastName, int age, Boolean status, Team team, EnumMap<Discipline, Double> results) {
        database.addCompetitor(email,firstName,lastName,age ,status, team, results);
    }

    public ArrayList<Member> findMember(String email) {
        return database.findMember(email);
    }

    public void saveData(ArrayList<Member> memberList, String fileName){ //TODO begge lister
        database.saveData(memberList, fileName);
    }

    public void addResult(EnumMap<Discipline, Double> results, Discipline discipline, double time){

    }
   /* public double totalDebt(){
        return economy.totalDebt(database.getMembersList());
    }*/

    //TODO SPØRG OM MAN MÅ DET HER
    public int totalIncome(){
        return economy.totalIncome(database.getMembersList());
    }
}