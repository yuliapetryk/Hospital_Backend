package db;


import entities.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class PatientDaoTest {

    private static Statement statement;
    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        GeneralDB db = new GeneralDB();
        statement = db.setConnection();
    }

    @Test
    void get() throws SQLException {
        PatientDao patientDao = new PatientDao(statement.getConnection());
        Patient patient = new Patient(1324, "Petrenko","Petro", "Petrovych","M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        assertEquals(patient,patientDao.get(1324));
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient(1325, "Petrenko","Petro", "Petrovych","M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        PatientDao patientDao = new PatientDao(statement.getConnection());
        //patientDao.add(patient);
        assertEquals(patient,patientDao.get(1325));
        assertNotEquals(patient,patientDao.get(5));
    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient(1325, "Petrenko", "Petro", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        Patient patientUpdated = new Patient(1325, "PetrenkoNew", "PetroNew", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        PatientDao patientDao = new PatientDao(statement.getConnection());

        patientDao.update(1325, patientUpdated);
        assertEquals(patientUpdated, patientDao.get(1325));
        assertNotEquals(patient, patientDao.get(1325));

        Patient patientOriginal = new Patient(1325, "Petrenko", "Petro", "Petrovych", "M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        patientDao.update(1325, patientOriginal);
        assertEquals(patientOriginal, patientDao.get(1325));
        assertNotEquals(patientUpdated, patientDao.get(1325));
    }

}