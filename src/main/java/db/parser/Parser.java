package db.parser;


import entities.Appointment;
import entities.Patient;
import entities.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Parser {

    public Patient createPatientDB(ResultSet patientDB) throws SQLException {
        Patient patient = new Patient(
                patientDB.getInt(1),
                patientDB.getString(2),
                patientDB.getString(3),
                patientDB.getString(4),
                patientDB.getString(5),
                patientDB.getString(6),
                patientDB.getString(7));
        return patient;
    }

    public Appointment createAppointmentDB(ResultSet appointmentDB) throws SQLException {
        Appointment appointment = new Appointment(
                appointmentDB.getInt(1),
                appointmentDB.getString(2),
                appointmentDB.getInt(3),
                appointmentDB.getInt(4),
                appointmentDB.getString(5),
                appointmentDB.getString(6),
                appointmentDB.getString(7),
                appointmentDB.getString(8));
        return appointment;
    }

    public Staff createStaffDB(ResultSet staffDB) throws SQLException {
        Staff staff = new Staff(
                staffDB.getInt(1),
                staffDB.getString(2),
                staffDB.getString(3),
                staffDB.getString(4),
                staffDB.getString(6),
                staffDB.getString(5));
        return staff;
    }
}
