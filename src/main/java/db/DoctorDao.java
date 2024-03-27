package db;

import info.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DoctorDao implements Dao<Doctor>{

    private final Connection connection;

    public DoctorDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = Logger.getLogger(PatientDao.class.getName());


    @Override
    public Optional<Doctor> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Doctor> getAll() {
        return null;
    }

    @Override
    public void add(Doctor doctor) {
        try {
            String sql = "INSERT INTO  public.\"Doctors\" ( last_name, first_name, patronymic, position, phone,specialty)) values (?, ?, ?,?, ?, ? )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, doctor.getLastName());
            ps.setString(2, doctor.getFirstName());
            ps.setString(3, doctor.getPatronymic());
            ps.setString(4, doctor.getPosition());
            ps.setString(5, doctor.getPhone());
            ps.setString(6, doctor.getSpecialty());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error: can`t add new doctor");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Doctor doctor, String[] params) {

    }

    @Override
    public void delete(Doctor doctor) {

    }
}
