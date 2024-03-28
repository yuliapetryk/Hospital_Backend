package db;

import info.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class StaffDao implements Dao<Staff>{

    private final Connection connection;

    public StaffDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = Logger.getLogger(PatientDao.class.getName());

    @Override
    public Optional<Staff> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Staff> getAll() {
        return null;
    }



    @Override
    public void add(Staff employee) throws SQLException {
        try {
            String sql = "INSERT INTO  public.\"Staff\" ( last_name, first_name, patronymic, position, phone,specialty)) values (?, ?, ?,?, ?, ? )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getPatronymic());
            ps.setString(4, employee.getPosition());
            ps.setString(5, employee.getPhone());
            ps.executeUpdate();
        } catch (
                SQLException e) {
            logger.severe("Error: can`t add new nurse");
        }
    }

    @Override
    public void update(Staff employee, String[] params) {

    }

    @Override
    public void delete(Staff employee) {

    }
}
