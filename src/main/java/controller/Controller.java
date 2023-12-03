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

    public void addMember(String email, String firstName, String lastName, LocalDate birthday, Boolean status, Team team, Boolean subscriptionPaid){
        database.addMember(email,firstName,lastName,birthday,status,team, subscriptionPaid);
    }


    public ArrayList<Member> findMember(String email) {
        return database.findMembers(email);
    }

    public int totalIncome(){
        return economy.totalIncome(database.getMembersList());
    }

    public int individualMemberIncome(Member member){
        return economy.individualMemberIncome(member);
    }

    public double totalDebt(){
        return economy.totalDebt(database.getMembersList());
    }

    public double individualMemberDebt(Member member){
        return economy.individualMemberDebt(member);
    }
}