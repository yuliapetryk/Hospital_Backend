package services;

import db.AppointmentDao;
import db.GeneralDB;
import db.PatientDao;
import entities.Appointment;
import entities.Patient;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PatientService {
    private GeneralDB db = new GeneralDB();
    private Statement statement;
    private AppointmentDao appointmentDao;
    private PatientDao patientDao;

    public PatientService() throws SQLException, ClassNotFoundException {
        setUp();
    }

    private void setUp() throws SQLException, ClassNotFoundException {
        statement = db.setConnection();
        patientDao = new PatientDao(statement.getConnection());
        appointmentDao = new AppointmentDao(statement.getConnection());
    }

    public List<Appointment> getAllAppointments(int patientId) throws SQLException, ClassNotFoundException {
        return appointmentDao.getAllByPatient(patientId);
    }

    public void addPatient(Patient patient) throws SQLException {
        patientDao.add(patient);
    }
}
