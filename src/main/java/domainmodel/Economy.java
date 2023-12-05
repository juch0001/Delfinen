package domainmodel;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Economy {

    public int totalIncome(ArrayList<Member> members) { //TODO GØR SÅ DEN TAGER I MOD BEGGE LISTER
        int total = 0;
        for (Member member : members) {
            total += member.calculateIncome();
        }
        return total;
    }

    public double totalDebt(ArrayList<Member> members){
        double totalDebt = 0;
        for (Member member : members) {
            totalDebt += member.individualMemberDebt();
        }
        return totalDebt;
    }
}