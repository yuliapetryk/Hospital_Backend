package db;

import info.Nurse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class NurseDao implements Dao<Nurse>{

    private final Connection connection;

    public NurseDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = Logger.getLogger(PatientDao.class.getName());

    @Override
    public Optional<Nurse> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Nurse> getAll() {
        return null;
    }

    @Override
    public void add(Nurse nurse) throws SQLException {
        try {
            String sql = "INSERT INTO  public.\"Nurses\" ( last_name, first_name, patronymic, position, phone,specialty)) values (?, ?, ?,?, ?, ? )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nurse.getLastName());
            ps.setString(2, nurse.getFirstName());
            ps.setString(3, nurse.getPatronymic());
            ps.setString(4, nurse.getPosition());
            ps.setString(5, nurse.getPhone());
            ps.executeUpdate();
        } catch (
                SQLException e) {
            logger.severe("Error: can`t add new nurse");
        }
    }

    @Override
    public void update(Nurse nurse, String[] params) {

    }

    @Override
    public void delete(Nurse nurse) {

    }
}
