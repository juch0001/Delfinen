package domainmodel;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Economy {
    //LocalDateTime dueDateForFee = LocalDateTime.now().withDayOfMonth(1).plusMonths(1); //duedate er d. 1. i måneden

    public int totalIncome(ArrayList<Member> members) { //TODO GØR SÅ DEN TAGER I MOD BEGGE LISTER
        int total = 0;
        for (Member member : members) {
            total += individualMemberIncome(member);
        }
        return total;
    }

    public int individualMemberIncome(Member member) {
        if (member.getStatus()) {
            switch (member.getTeam()) {
                case JUNIOR:
                    return 1000;
                case SENIOR:
                    if (member.calculateAge() > 59){
                        return 1200;
                    }
                    else{
                        return 1600;
                    }
                case EXERCISER:
                    if (member.calculateAge() < 17){
                        return 1600;
                    }
                    return 1000;
            }
        } else {
            return 500;
        }
        return 0;
    }

    public double totalDebt(ArrayList<Member> members){
        double totalDebt = 0;
        for (Member member : members) {
            totalDebt += individualMemberDebt(member);
        }
        return totalDebt;
    }

  /*  public boolean outstandingDebt(Member member) {
        return LocalDateTime.now().getDayOfMonth() && individualMemberIncome(member) > 0 && LocalDateTime.now().isAfter(member.membershipSignUpDate());
    }*/

    //TODO skal der måske laves boolean på betalt kontigent ved oprettelse? så kan betaling indtastes manuelt. Kan man automatisere det?
    public double individualMemberDebt(Member member){
        double individualDebt = 0;
        /*if (member.membershipSignUpDate()){
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(s<gd<shg)){
                individualDebt += individualMemberIncome(member) * 0.1; //10% månedlig fee
            }
        }*/
        return individualDebt;
    }

}