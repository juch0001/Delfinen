package domainmodel.comparator;

import domainmodel.SwimResult;

import java.util.Comparator;

public class TimeComparator implements Comparator<SwimResult> {

    @Override
    public int compare(SwimResult swimResult, SwimResult otherSwimResult) {
        return Double.compare(swimResult.getTime() ,otherSwimResult.getTime());
    }
}
