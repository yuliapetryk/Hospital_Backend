package db;

import info.Staff;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StaffDaoTest {
    private static Statement statement;
    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        GeneralDB db = new GeneralDB();
        statement = db.setConnection();
    }

    @Test
    void get() throws SQLException {
        StaffDao StaffDao = new StaffDao(statement.getConnection());
        Staff staff = new Staff(1326, "Petrenko","Petro", "Petrovych","doctor", "+380965432123");
        assertEquals(staff,StaffDao.get(1326));
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        Staff staff = new Staff(1326, "Petrenko","Petro", "Petrovych","doctor", "+380965432123");
        StaffDao staffDao = new StaffDao(statement.getConnection());
        //staffDao.add(staff);
        assertEquals(staff,staffDao.get(1326));
        assertNotEquals(staff,staffDao.get(3213));
    }
}