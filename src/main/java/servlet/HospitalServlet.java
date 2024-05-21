package servlet;


import com.google.gson.Gson;
import entities.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.HospitalService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class HospitalServlet extends HttpServlet {
    private HospitalService hospitalService;

    @Override
    public void init() throws ServletException {
        try {
            hospitalService = new HospitalService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize HospitalService", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String json;

        try {
            List<Appointment> appointments = hospitalService.getAllAppointments();
            json = gson.toJson(appointments);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving appointments");
            return;
        }

        out.print(json);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo != null ? pathInfo.split("/") : new String[0];
        String idParameter = pathParts.length > 0 ? pathParts[pathParts.length - 1] : null;

        if (idParameter != null && !idParameter.isEmpty()) {
            int appointmentId = Integer.parseInt(idParameter);

            try {
                hospitalService.updateStatus(appointmentId);
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter out = response.getWriter();
                out.print("Appointment status updated successfully");
                out.flush();
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating appointment status");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.print("Missing or invalid 'id' parameter");
            out.flush();
        }
    }
}
