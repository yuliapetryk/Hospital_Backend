package db;

import entities.Patient;
import entities.Staff;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class StaffDaoTest {
    private static Statement statement;

    private static StaffDao staffDao;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        GeneralDB db = new GeneralDB();
        statement = db.setConnection();
        staffDao = new StaffDao(statement.getConnection());
    }

    @Test
    void get() throws SQLException {
        Staff staff = new Staff(1326, "Petrenko","Petro", "Petrovych","doctor", "+380965432123", "hbygyg554d");
        assertEquals(staff,staffDao.get(1326));
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        Staff staff = new Staff(1329183, "Gorodna", "Inna", "Ivanivna", "nurse", "+380987212124","sonia032012");

        Staff existingStaff = staffDao.get(1329183);
        if (existingStaff != null) {
            staffDao.delete(1329183);
        }

        staffDao.add(staff);
        assertEquals(staff, staffDao.get(1329183));
        assertNotEquals(staff, staffDao.get(3213));

    }


    @Test
    void update() throws SQLException, ClassNotFoundException {
        Staff staff = new Staff(1326, "Petrenko", "Petro", "Petrovych", "doctor", "+380965432123", "hbygyg554d");
        Staff staffUpdated = new Staff(1326, "PetrenkoNew", "PetroNew", "Petrovych", "doctor", "+380965432123", "hbygyg554d");

        staffDao.update(1326, staffUpdated);
        assertEquals(staffUpdated, staffDao.get(1326));
        assertNotEquals(staff, staffDao.get(1326));

        Staff staffOriginal = new Staff(1326, "Petrenko", "Petro", "Petrovych", "doctor", "+380965432123", "hbygyg554d");
        staffDao.update(1326, staffOriginal);
        assertEquals(staffOriginal, staffDao.get(1326));
        assertNotEquals(staffUpdated, staffDao.get(1326));
    }

    @Test
    void delete() throws SQLException {
        Staff staff = new Staff(1329183, "Gorodna", "Inna", "Ivanivna", "nurse", "+380987212124","sonia032012");

        Staff existingStaff = staffDao.get(1329183);
        if (existingStaff == null) {
            staffDao.add(staff);
        }
        staffDao.delete(1329183);
        assertNull(staffDao.get(1329183));
    }

    @Test
    void getNameById() throws SQLException {
        Staff staff = new Staff(1326, "Petrenko", "Petro", "Petrovych", "doctor", "+380965432123", "hbygyg554d");

        Staff existingStaff = staffDao.get(1326);
        if (existingStaff != null) {
            staffDao.delete(1326);
        }

        staffDao.add(staff);
        String fullName = staffDao.getNameById(1326);

        assertEquals("Petrenko Petro Petrovych", fullName);

    }


}
