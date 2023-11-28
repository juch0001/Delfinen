package domainmodel;
import java.util.ArrayList;
import java.util.EnumSet;

public class Economy {

public int totalIncome(ArrayList<Member> members) {
    int total = 0;
    for (Member member : members) {
        if (member.getStatus()) {
            switch (member.getTeam()) {
                case JUNIOR -> total += 1800;
                case SENIOR -> total += 1600;
                case EXCERCISER -> total += 2000;
            }
        } else {
            total += 500;
        }
    }
    return total;
}
}