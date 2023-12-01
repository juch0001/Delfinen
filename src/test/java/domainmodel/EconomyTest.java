package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;

class EconomyTest {
   Economy economy = new Economy();
   ArrayList<Member> members = new ArrayList<>();
   EnumMap<Discipline, Double> results = new EnumMap<>(Discipline.class);
   LocalDate andrea = LocalDate.ofEpochDay(2-2-2000);
    Member junior = new Member("an@gmail.com", "Andrea", "Ryholt", andrea, true, Team.JUNIOR);
    Member senior = new Member("ra@gmail.com", "Ras", "Hansen", 44, true, Team.SENIOR);
    Member excerciser = new Member("sof@gmail.com", "Sofie", "Jensen", 27, true, Team.EXERCISER);



    @BeforeEach
    void setup(){
     members.add(junior);
     members.add(senior);
     members.add(excerciser);

    }

    @Test
    void totalIncome() {
    int expectedTotal = 10800;
    int actualTotal = economy.totalIncome(members);

    assertEquals(expectedTotal, actualTotal);
    }

    /*@Test
    void totalDebt(){
    double exceptedTotal = 3400;
    double actualTotal = economy.totalDebt(members);

     assertEquals(exceptedTotal, actualTotal);
    }*/
}