package db;

import db.parser.Parser;
import info.Patient;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PatientDao implements Dao<Patient> {

    private final Connection connection;

    public PatientDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = Logger.getLogger(PatientDao.class.getName());

    @Override
    public Patient get(int id) {
        try {
            String sql = "SELECT * FROM public.\"Patients\" WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            Parser parser = new Parser();
            return parser.createPatientDB(result);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Patient> getAll() {
        return null;
    }

    @Override
    public void add(Patient patient) throws SQLException {
        try {
            String sql = "INSERT INTO  public.\"Patients\" ( id, last_name, first_name, patronymic,sex, date_of_birth, address ) values (?, ?, ?, ?,?, to_date(?, 'YYYY-MM-DD'), ? )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, patient.getId());
            ps.setString(2, patient.getLastName());
            ps.setString(3, patient.getFirstName());
            ps.setString(4, patient.getPatronymic());
            ps.setString(5, patient.getSex());
            ps.setString(6, patient.getDateOfBirth());
            ps.setString(7, patient.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error: can`t add new patient");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Patient patient, String[] params) {

    }

    @Override
    public void delete(Patient patient) {

    }


}
