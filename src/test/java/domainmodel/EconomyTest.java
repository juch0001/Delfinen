package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EconomyTest {
 Economy economy = new Economy();
 ArrayList<Member> members = new ArrayList<>();
 Member junior = new Member("an@gmail.com", "Andrea", "Ryholt", LocalDate.parse("2005-02-20"), true, Team.JUNIOR, false);
 Member senior = new Member("ra@gmail.com", "Ras", "Hansen", LocalDate.parse("1994-03-20"), true, Team.SENIOR, true);
 Member persioner = new Member("sof@gmail.com", "Sofie", "Jensen",LocalDate.parse("1729-06-02"), true, Team.PENSIONER, false);
 Member motionist = new Member("gam@gmail.com", "gammel", "dame",LocalDate.parse("1900-06-03"), true, Team.EXERCISER, true);


 @BeforeEach
 void setup() {
  members.add(junior);
  members.add(senior);
  members.add(persioner);
  members.add(motionist);

 }

 @Test
 void totalIncome() {
  int expectedTotal = 4800;
  int actualTotal = economy.totalIncome(members);

  assertEquals(expectedTotal, actualTotal);
 }

 @Test
 void totalDebt() {
  double exceptedTotal = 3400;
  double actualTotal = economy.totalDebt(members);

  assertEquals(exceptedTotal, actualTotal);
 }
}

