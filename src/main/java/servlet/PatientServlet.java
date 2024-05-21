package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import entities.Appointment;
import entities.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PatientService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class PatientServlet extends HttpServlet {
    private PatientService patientService;

    @Override
    public void init() throws ServletException {
        try {
            patientService = new PatientService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize PatientService", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String idParameter = pathParts[pathParts.length - 1];

        if (idParameter != null && !idParameter.isEmpty()) {
            int id = Integer.parseInt(idParameter);
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String json;

            try {
                List<Appointment> appointments = patientService.getAllAppointments(id);
                json = gson.toJson(appointments);
            } catch (SQLException | ClassNotFoundException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving appointments");
                return;
            }

            out.print(json);
            out.flush();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        Patient patient = objectMapper.readValue(request.getReader(), Patient.class);

        try {
            patientService.addPatient(patient);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding new patient");
        }
    }
}
