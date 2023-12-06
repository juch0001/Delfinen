package domainmodel.comparator;

import domainmodel.Discipline;
import domainmodel.SwimResult;

import java.util.Comparator;

public class DisciplinComparator implements Comparator<SwimResult> {

    @Override
    public int compare(SwimResult swimResult1, SwimResult swimResult2) {
        return Integer.compare(swimResult1.getDiscipline().ordinal() ,swimResult2.getDiscipline().ordinal());
    }


}
