package db;

import db.parser.Parser;
import entities.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class AppointmentDao implements Dao<Appointment>{

    private final Connection connection;

    public AppointmentDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger logger = Logger.getLogger(PatientDao.class.getName());



    public int getDoctorId(int appointmentId) {
        try {
            String sql = "SELECT doctor_id FROM public.\"Appointments\" WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ResultSet result = ps.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean getStatus(int appointmentId) {
        try {
            String sql = "SELECT status FROM public.\"Appointments\" WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ResultSet result = ps.executeQuery();
            result.next();
            return result.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getSurgery(int appointmentId) {
        try {
            String sql = "SELECT surgery FROM public.\"Appointments\" WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ResultSet result = ps.executeQuery();
            result.next();
            return result.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateStatus(int appointmentId) throws SQLException {
        PreparedStatement ps;
            String sql = "UPDATE  public.\"Appointments\" SET status = true WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.execute();
    }

    public void fulfillPrescription(int appointmentId, int employeeId ) throws SQLException {
        if (!getStatus(appointmentId)){
            StaffDao employee = new StaffDao(connection);
            String position = employee.getPosition(employeeId);
            if (Objects.equals(position, "nurse")){
                if (getSurgery(appointmentId) == null){
                    updateStatus(appointmentId);
                } else
                    throw new UnsupportedOperationException("Nurse can`t do surgeries.");

            } else if (Objects.equals(position, "doctor")){
                updateStatus(appointmentId);
            }
        }
    }

    @Override
    public Appointment get(int id){
        try {
            String sql = "SELECT * FROM public.\"Appointments\" WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (!result.next()) {
                return null;
            }

            Parser parser = new Parser();
            return parser.createAppointmentDB(result);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Appointment> getAllByPatient(int id) {
        List<Appointment> appointments = new ArrayList<>();

        try {
            String sql = "SELECT * FROM public.\"Appointments\" WHERE patient_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            Parser parser = new Parser();
            while (result.next()) {
                Appointment appointment = parser.createAppointmentDB(result);
                appointments.add(appointment);
            }

            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Appointment> getAllByDoctor(int id) {
        List<Appointment> appointments = new ArrayList<>();

        try {
            String sql = "SELECT * FROM public.\"Appointments\" WHERE doctor_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            Parser parser = new Parser();
            while (result.next()) {
                Appointment appointment = parser.createAppointmentDB(result);
                appointments.add(appointment);
            }

            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Appointment> getAll() {
        return null;
    }

    @Override
    public void add(Appointment appointment) throws SQLException {
        try {
            String sql = "INSERT INTO  public.\"Appointments\" (date, doctor_id, patient_id, diagnosis, medication, procedure, surgery, status ) values ( to_date(?, 'YYYY-MM-DD'), ?,?,?,?,?,? , ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appointment.getDate());
            ps.setInt(2, appointment.getDoctorId());
            ps.setInt(3, appointment.getPatientId());
            ps.setString(4, appointment.getDiagnosis());
            ps.setString(5, appointment.getMedication());
            ps.setString(6, appointment.getProcedure());
            ps.setString(7, appointment.getSurgery());
            ps.setBoolean(8, appointment.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error: can`t add new appointment");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(int id, Appointment appointment) {
        try {
            String sql = "UPDATE public.\"Appointments\" SET date=to_date(?, 'YYYY-MM-DD'), doctor_id=?, patient_id=?, diagnosis=?, medication=?, procedure=?, surgery=?, status=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, appointment.getDate());
            ps.setInt(2, appointment.getDoctorId());
            ps.setInt(3, appointment.getPatientId());
            ps.setString(4, appointment.getDiagnosis());
            ps.setString(5, appointment.getMedication());
            ps.setString(6, appointment.getProcedure());
            ps.setString(7, appointment.getSurgery());
            ps.setBoolean(8, appointment.getStatus());
            ps.setInt(9, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("No rows affected while updating appointment with ID: " + id);
            }
        } catch (SQLException e) {
            logger.severe("Error: can't update appointment with ID: " + id);
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void delete(int id) {
        try{
            String sql = "DELETE FROM public.\"Appointments\" WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error: can't delete appointment with ID: " + id);
            throw new RuntimeException(e.getMessage());
        };
    }
}
