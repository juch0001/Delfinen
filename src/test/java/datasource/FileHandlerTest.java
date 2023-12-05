package datasource;

import domainmodel.Member;
import domainmodel.Team;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    FileHandler fileHandler = new FileHandler();
    Member member = new Member("an@gmail.com", "Andrea", "Ryholt", LocalDate.parse("1995-03-06"), true, Team.SENIOR, true);
    Member member2 = new Member("bas@gmail.com", "asdasd", "thomsen",LocalDate.parse("2002-08-02"), true, Team.EXERCISER, true);
    Member member3 = new Member("kar@gmail.com", "Andrbavevevea", "sadasd", LocalDate.parse("2009-02-23"),true, Team.JUNIOR, true);
    File file = new File("member_test_data.csv");
    @BeforeEach
    void setup(){
    }
    @org.junit.jupiter.api.Test
    void saveData() {
        ArrayList<Member> memberArrayList = new ArrayList<>();
        memberArrayList.add(member);
        memberArrayList.add(member2);
        memberArrayList.add(member3);

        fileHandler.saveData(memberArrayList, file.getName());
    }

    @org.junit.jupiter.api.Test
    void loadData() {
        ArrayList<Member> members = fileHandler.loadData(file);
        int expectedLength = 3;
        int actualLength = members.size();
        assertEquals(expectedLength,actualLength);
    }

    @org.junit.jupiter.api.Test
    void lastMemberCheck() {
    }

    @org.junit.jupiter.api.Test
    void getLastLine() {
    }
}