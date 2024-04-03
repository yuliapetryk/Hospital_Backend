package db;


import entities.Appointment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AppointmentDaoTest {
    private static Statement statement;
    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        GeneralDB db = new GeneralDB();
        statement = db.setConnection();
    }

    @Test
    void get() throws SQLException {
        AppointmentDao appointmentDao = new AppointmentDao(statement.getConnection());
        Appointment appointment = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication",null,"surgery" );
        assertEquals(appointment,appointmentDao.get(8));
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        Appointment appointment = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication",null,"surgery" );
        AppointmentDao appointmentDao = new AppointmentDao(statement.getConnection());
        appointmentDao.add(appointment);
        assertEquals(appointment,appointmentDao.get(8));
        //assertNotEquals(appointment,appointmentDao.get(7));
    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
        Appointment appointment = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication", null, "surgery");
        Appointment appointmentUpdated = new Appointment("2024-01-01", 3213, 1324, "diagnosisNew", "medicationNew", null, "surgeryNew");
        AppointmentDao appointmentDao = new AppointmentDao(statement.getConnection());

        appointmentDao.update(8, appointmentUpdated);
        assertEquals(appointmentUpdated, appointmentDao.get(8));
        assertNotEquals(appointment, appointmentDao.get(8));

        Appointment appointmentOriginal = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication", null, "surgery");
        appointmentDao.update(8, appointmentOriginal);
        assertEquals(appointmentOriginal, appointmentDao.get(8));
        assertNotEquals(appointmentUpdated, appointmentDao.get(8));
    }

}
