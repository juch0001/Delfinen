package domainmodel;
import java.util.ArrayList;

public class Economy {

public int totalIncome(ArrayList<Member> members) {
    int total = 0;
    for (Member member : members) {
        if (member.getStatus()) {
            switch (member.getTeam()) {
                case JUNIOR -> total += 1800;
                case SENIOR -> total += 1600;
                case EXERCISER -> total += 2000;
            }
        } else {
            total += 500;
        }
    }
    return total;
}
*/
    public int totalIncome(ArrayList<Member> members) { //TODO GØR SÅ DEN TAGER I MOD BEGGE LISTER
        int total = 0;
        for (Member member : members) {
            total += individualMemberDebt(member);
        }
        return total;
    }

    public int individualMemberDebt(Member member) {
        if (member.getStatus()) {
            switch (member.getTeam()) {
                case JUNIOR: return 1800;
                case SENIOR: return 1600;
                case EXERCISER: return 2000;
            }
        } else {
            return 500;
        }
        return 0;
    }
    public double totalDebt(ArrayList<Member> members){
        double totalDebt = 0;
        totalDebt += totalMemberDebt(members);
        return totalDebt;
    }
    public double totalMemberDebt(ArrayList<Member> members){
        double totalMemberDebt = 0;
        for (Member member:members) {
            totalMemberDebt += member.getDebt();
        }
        return totalMemberDebt;
    }
    /*
    public double totalCompetitorDebt(ArrayList<Member> competitors){
        double totalCompetitorDebt = 0;
        for (Member competitor:competitors){
            totalCompetitorDebt += competitor.getDebt();
        }
        return totalCompetitorDebt;
    }
*/
}