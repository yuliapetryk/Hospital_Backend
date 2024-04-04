package db;


import entities.Appointment;
import entities.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentDaoTest {
    private static Statement statement;
    private static  AppointmentDao appointmentDao;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        GeneralDB db = new GeneralDB();
        statement = db.setConnection();
        appointmentDao = new AppointmentDao(statement.getConnection());

    }

    @Test
    void get() throws SQLException {
        Appointment appointment = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication",null,"surgery" );
        assertEquals(appointment,appointmentDao.get(9));
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        Appointment appointment = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication",null,"surgery" );

        appointmentDao.add(appointment);
        assertEquals(appointment,appointmentDao.get(9));
        assertNotEquals(appointment,appointmentDao.get(7));
    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
        Appointment appointment = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication", null, "surgery");
        Appointment appointmentUpdated = new Appointment("2024-01-01", 3213, 1324, "diagnosisNew", "medicationNew", null, "surgeryNew");

        appointmentDao.update(9, appointmentUpdated);
        assertEquals(appointmentUpdated, appointmentDao.get(9));
        assertNotEquals(appointment, appointmentDao.get(9));

        Appointment appointmentOriginal = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication", null, "surgery");
        appointmentDao.update(9, appointmentOriginal);
        assertEquals(appointmentOriginal, appointmentDao.get(9));
        assertNotEquals(appointmentUpdated, appointmentDao.get(9));
    }


}
