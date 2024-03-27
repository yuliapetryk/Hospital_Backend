
import db.GeneralDB;
import db.PatientDao;
import info.Patient;

import java.sql.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        GeneralDB db = new GeneralDB();
        Statement statement = db.setConnection();
        Patient patient = new Patient("Petrenko","Petro", "Petrovych","M", "1992-11-11", "Kyiv, Khreshchatyk, 123");
        PatientDao patientDao = new PatientDao(statement.getConnection());
        patientDao.add(patient);
    }
}