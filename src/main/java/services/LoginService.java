package services;

import db.Dao;
import db.GeneralDB;
import db.PatientDao;
import db.StaffDao;
import entities.Patient;
import entities.Staff;


import java.sql.SQLException;
import java.sql.Statement;

public class LoginService {
    private GeneralDB db = new GeneralDB();
    private Statement statement;
    private StaffDao staffDao;
    private PatientDao patientDao;

    public LoginService(){}

        public Staff loginStaff( String id, String password)  {
            try {
                statement = db.setConnection();
                staffDao = new StaffDao(statement.getConnection());
                Staff staff = staffDao.get(Integer.parseInt(id));
                String staffPassword = password.replaceAll("\"", "");
                if (staff != null && (staff.getPassword().equals(staffPassword))) {
                    return staff;
                } else {
                    return null;
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public Patient loginPatient( String id, String password)  {
        try {
            statement = db.setConnection();
            patientDao = new PatientDao(statement.getConnection());
           Patient patient = patientDao.get(Integer.parseInt(id));
            if (patient != null && patient.getPassword().equals(password)) {
                return patient;
            } else {
                return null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
