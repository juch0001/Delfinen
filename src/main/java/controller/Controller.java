package controller;

import domainmodel.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;

public class Controller {
    Database database = new Database();
    Economy economy = new Economy();

    public ArrayList<Member> getMemberlist(){
        return database.getMembersList();
    }
    public void addMember(String email, String firstName, String lastName, LocalDate birthday, Boolean status, Team team){
        database.addMember(email,firstName,lastName,birthday,status,team);
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

    public int totalIncome(){
        return economy.totalIncome(database.getMembersList());
    }
}