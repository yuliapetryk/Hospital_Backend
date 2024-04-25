package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import db.AppointmentDao;
import db.GeneralDB;
import entities.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


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

    private List<Appointment> getAllAppointments(int id) throws SQLException, ClassNotFoundException {
        return appointmentDao.getAllByDoctor(id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String idParameter = pathParts[pathParts.length - 1];

        if (idParameter != null && !idParameter.isEmpty()) {

            int id = Integer.parseInt(idParameter);
            PrintWriter out = response.getWriter();

            Gson gson = new Gson();
            String json = null;
            try {
                json = gson.toJson(getAllAppointments(id));
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            out.print(json);
            out.flush();
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

