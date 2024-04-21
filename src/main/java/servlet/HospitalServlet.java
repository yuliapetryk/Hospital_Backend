package servlet;


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

public class HospitalServlet extends HttpServlet {

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

    private void updateStatus(int appointmentId) throws SQLException, ClassNotFoundException {
        appointmentDao.updateStatus(appointmentId);
    }

    private List<Appointment> getAllAppointments() throws SQLException, ClassNotFoundException {
        return appointmentDao.getAllByPatient(1324);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(getAllAppointments());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.print(json);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String idParameter = pathParts[pathParts.length - 1];

        if (idParameter != null && !idParameter.isEmpty()) {

            int appointmentId = Integer.parseInt(idParameter);

            try {
                updateStatus(appointmentId);

                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter out = response.getWriter();
                out.print("Appointment status updated successfully");
                out.flush();

            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException("Error updating appointment status", e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.print("Missing or invalid 'id' parameter");
            out.flush();
        }
    }


}

