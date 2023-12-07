package domainmodel;
import java.util.ArrayList;

public class Economy {

    public int totalIncome(ArrayList<Member> members) {
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