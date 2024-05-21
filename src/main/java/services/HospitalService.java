package services;

import db.AppointmentDao;
import db.GeneralDB;
import entities.Appointment;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HospitalService {
    private GeneralDB db = new GeneralDB();
    private Statement statement;
    private AppointmentDao appointmentDao;

    public HospitalService() throws SQLException, ClassNotFoundException {
        setUp();
    }

    private void setUp() throws SQLException, ClassNotFoundException {
        statement = db.setConnection();
        appointmentDao = new AppointmentDao(statement.getConnection());
    }

    public void updateStatus(int appointmentId) throws SQLException {
        appointmentDao.updateStatus(appointmentId);
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentDao.getAllByPatient(1324); // Зверніть увагу на статичне значення ID пацієнта, можливо, варто зробити його параметром методу
    }
}
