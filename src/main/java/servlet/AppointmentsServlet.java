package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import entities.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AppointmentService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class AppointmentsServlet extends HttpServlet {
    private AppointmentService appointmentService;

    @Override
    public void init() throws ServletException {
        try {
            appointmentService = new AppointmentService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize AppointmentService", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo != null ? pathInfo.split("/") : new String[0];
        String idParameter = pathParts.length > 0 ? pathParts[pathParts.length - 1] : null;

        if (idParameter != null && !idParameter.isEmpty()) {
            int doctorId = Integer.parseInt(idParameter);
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String json;

            try {
                List<Appointment> appointments = appointmentService.getAllAppointmentsByDoctor(doctorId);
                json = gson.toJson(appointments);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving appointments");
                return;
            }

            out.print(json);
            out.flush();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing or invalid 'id' parameter");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        Appointment appointment = objectMapper.readValue(request.getReader(), Appointment.class);

        try {
            appointmentService.addAppointment(appointment);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding new appointment");
        }
    }
}
