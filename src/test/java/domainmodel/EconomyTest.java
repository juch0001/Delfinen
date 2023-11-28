package domainmodel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EconomyTest {
   private ArrayList testMember = new ArrayList<>();
    Member junior = new Member("an@gmail.com", "Andrea", "Ryholt", 24, 0, true, Team.JUNIOR);
    Member senior = new Member("ra@gmail.com", "Ras", "Hansen", 44, 0, true, Team.SENIOR);
    Member excerciser = new Member("sof@gmail.com", "Sofie", "Jensen", 27, 0, true, Team.EXCERCISER);
    @Test
    void totalIncome() {
    Economy economy = new Economy();
    testMember.add(junior);
    testMember.add(senior);
    testMember.add(excerciser);
    int expectedTotal = 5400;
    int actualTotal = economy.totalIncome(testMember);

    assertEquals(expectedTotal, actualTotal);
    }
}