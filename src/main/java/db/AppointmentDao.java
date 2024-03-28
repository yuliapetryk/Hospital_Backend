package db;

import info.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class AppointmentDao implements Dao<Appointment>{

    private final Connection connection;

    public AppointmentDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = Logger.getLogger(PatientDao.class.getName());

    @Override
    public Optional<Appointment> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> getAll() {
        return null;
    }

    @Override
    public void add(Appointment appointment) throws SQLException {
        try {
            String sql = "INSERT INTO  public.\"Appointments\" (date, doctorId, patientId, diagnosis, medication, procedure, surgery ) values ( to_date(?, 'YYYY-MM-DD'), ?,?,?,?,?,? )";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appointment.getDate());
            ps.setInt(2, appointment.getDoctorId());
            ps.setInt(3, appointment.getPatientId());
            ps.setString(4, appointment.getDiagnosis());
            ps.setString(5, appointment.getMedication());
            ps.setString(6, appointment.getProcedure());
            ps.setString(7, appointment.getSurgery());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error: can`t add new appointment");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Appointment appointment, String[] params) {

    }

    @Override
    public void delete(Appointment appointment) {

    }
}
