package db;

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
    public Optional<Patient> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Patient> getAll() {
        return null;
    }

    @Override
    public void add(Patient patient) throws SQLException {
        try {
            String sql = "INSERT INTO  public.\"Patients\" ( last_name, first_name, patronymic,sex, date_of_birth, address ) values (?, ?, ?,?, to_date(?, 'YYYY-MM-DD'), ? )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, patient.getLastName());
            ps.setString(2, patient.getFirstName());
            ps.setString(3, patient.getPatronymic());
            ps.setString(4, patient.getSex());
            ps.setString(5, patient.getDateOfBirth());
            ps.setString(6, patient.getAddress());
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
