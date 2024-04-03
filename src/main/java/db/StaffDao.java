package db;

import db.parser.Parser;
import entities.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public Staff get(int id) {
        try {
            String sql = "SELECT * FROM public.\"Staff\" WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            Parser parser = new Parser();
            return parser.createStaffDB(result);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Staff> getAll() {
        return null;
    }


    public String getPosition(int employeeId) {
        try {
            String sql = "SELECT position FROM public.\"Staff\" WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet result = ps.executeQuery();
            result.next();
            return result.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void add(Staff employee) throws SQLException {
        try {
            String sql = "INSERT INTO  public.\"Staff\" ( id, last_name, first_name, patronymic, position, phone) values (?, ?, ?, ?,?, ? )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getPatronymic());
            ps.setString(5, employee.getPosition());
            ps.setString(6, employee.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error: can`t add new employee");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(int id, Staff employee) {
        try {
            String sql = "UPDATE public.\"Staff\" SET last_name=?, first_name=?, patronymic=?, position=?, phone=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getPatronymic());
            ps.setString(4, employee.getPosition());
            ps.setString(5, employee.getPhone());
            ps.setInt(6, employee.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("No rows affected while updating employee with ID: " + employee.getId());
            }
        } catch (SQLException e) {
            logger.severe("Error: can't update employee with ID: " + employee.getId());
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void delete(Staff employee) {

    }

    public void fulfillPrescription(Connection conn, int appointmentId, int employeeId) throws SQLException {
        AppointmentDao appointment = new AppointmentDao(conn);
        appointment.fulfillPrescription(appointmentId, employeeId);

    };

}
