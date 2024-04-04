package db;


import entities.Patient;
import entities.Staff;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class PatientDaoTest {

    private static Statement statement;
    private static PatientDao patientDao;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        GeneralDB db = new GeneralDB();
        statement = db.setConnection();
        patientDao = new PatientDao(statement.getConnection());

    }

    @Test
    void get() throws SQLException {
        Patient patient = new Patient(1324, "Petrenko","Petro", "Petrovych","M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        assertEquals(patient,patientDao.get(1324));
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient(1325, "Petrenko","Petro", "Petrovych","M", "1992-11-11", "Kyiv, Khreshchatyk, 123");

        Patient existingPatient = patientDao.get(1325);
        if (existingPatient != null) {
            patientDao.delete(1325);
        }

        patientDao.add(patient);
        assertEquals(patient,patientDao.get(1325));
        assertNotEquals(patient,patientDao.get(5));
    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient(1325, "Petrenko", "Petro", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        Patient patientUpdated = new Patient(1325, "PetrenkoNew", "PetroNew", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");

        patientDao.update(1325, patientUpdated);
        assertEquals(patientUpdated, patientDao.get(1325));
        assertNotEquals(patient, patientDao.get(1325));

        Patient patientOriginal = new Patient(1325, "Petrenko", "Petro", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        patientDao.update(1325, patientOriginal);
        assertEquals(patientOriginal, patientDao.get(1325));
        assertNotEquals(patientUpdated, patientDao.get(1325));
    }

    @Test
    void delete() throws SQLException {
        Patient patient = new Patient(1328, "Petrenko", "Petro", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");

        Patient existingPatient = patientDao.get(1328);
        if (existingPatient == null) {
            patientDao.add(patient);
        }
        patientDao.delete(1328);
        assertNull(patientDao.get(1328));
    }

    @Test
    void getNameById() throws SQLException {
        Patient patient = new Patient(1325, "Petrenko", "Petro", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");

        Patient existingPatient = patientDao.get(1325);
        if (existingPatient != null) {
            patientDao.delete(1325);
        }

        patientDao.add(patient);
        String fullName = patientDao.getNameById(1325);

        assertEquals("Petrenko Petro Petrovych", fullName);

    }

}