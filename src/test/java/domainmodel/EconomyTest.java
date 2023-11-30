package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;

class EconomyTest {
   Economy economy = new Economy();
   ArrayList<Member> members = new ArrayList<>();
   EnumMap<Discipline, Double> results = new EnumMap<>(Discipline.class);
    Member junior = new Member("an@gmail.com", "Andrea", "Ryholt", 24, 1600, true, Team.JUNIOR);
    Member senior = new Member("ra@gmail.com", "Ras", "Hansen", 44, 100, true, Team.SENIOR);
    Member excerciser = new Member("sof@gmail.com", "Sofie", "Jensen", 27, 0, true, Team.EXERCISER);

    Competitor juniorComp = new Competitor("an@gmail.com", "Andrea", "Ryholt", 24, 1600, true, Team.JUNIOR, results);
    Competitor seniorComp = new Competitor("basd@gmail.com", "ba", "sd", 44, 100, true, Team.SENIOR, results);
    Competitor excerciserComp = new Competitor("asdasd@gmail.com", "asdae", "dasd", 27, 0, true, Team.EXERCISER, results);

    @BeforeEach
    void setup(){
     members.add(junior);
     members.add(senior);
     members.add(excerciser);
     members.add(juniorComp);
     members.add(seniorComp);
     members.add(excerciserComp);
    }

    @Test
    void totalIncome() {
    int expectedTotal = 5400;
    int actualTotal = economy.totalIncome(members);

    assertEquals(expectedTotal, actualTotal);
    }

    @Test
    void totalDebt(){
    double exceptedTotal = 3400;
    double actualTotal = economy.totalDebt(members);

     assertEquals(exceptedTotal, actualTotal);
    }
}