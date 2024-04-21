package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.AppointmentDao;
import db.GeneralDB;
import entities.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;


public class AppointmentsServlet extends HttpServlet {

    private GeneralDB db = new GeneralDB();
    private Statement statement;
    private AppointmentDao appointmentDao;
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            statement = db.setConnection();
            appointmentDao = new AppointmentDao(statement.getConnection());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        Appointment appointment = objectMapper.readValue(request.getReader(), Appointment.class);

        try {
            appointmentDao.add(appointment);

            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error: Can't add new appointment");
            e.printStackTrace();
        }
    }
}

