
import db.AppointmentDao;
import db.GeneralDB;
import db.PatientDao;
import db.StaffDao;
import info.Appointment;
import info.Patient;
import info.Staff;

import java.sql.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        GeneralDB db = new GeneralDB();
        Statement statement = db.setConnection();
        Patient patient = new Patient(1324, "Petrenko","Petro", "Petrovych","M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        PatientDao patientDao = new PatientDao(statement.getConnection());
       // patientDao.add(patient);
        Staff staff = new Staff(3283, "Ivanova","Ivanna", "Ivanivna", "nurse", "+380972653421");
        StaffDao staffDao = new StaffDao(statement.getConnection());
        //staffDao.add(staff);
        Appointment appointment = new Appointment("2024-01-01", 3213, 1324, "diagnosis", "medication",null,"surgery" );
        AppointmentDao appointmentDao = new AppointmentDao(statement.getConnection());
        //appointmentDao.add(appointment);
        //staffDao.fulfillPrescription(statement.getConnection(), 8, 3283);

    }
}