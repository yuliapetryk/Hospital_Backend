package services;

import db.AppointmentDao;
import db.GeneralDB;
import entities.Appointment;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AppointmentService {
    private GeneralDB db = new GeneralDB();
    private Statement statement;
    private AppointmentDao appointmentDao;

    public AppointmentService() throws SQLException, ClassNotFoundException {
        setUp();
    }

    private void setUp() throws SQLException, ClassNotFoundException {
        statement = db.setConnection();
        appointmentDao = new AppointmentDao(statement.getConnection());
    }

    public List<Appointment> getAllAppointmentsByDoctor(int doctorId) throws SQLException {
        return appointmentDao.getAllByDoctor(doctorId);
    }

    public void addAppointment(Appointment appointment) throws SQLException {
        appointmentDao.add(appointment);
    }
}
