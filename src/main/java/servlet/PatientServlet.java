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

public class PatientServlet extends HttpServlet {

    private  GeneralDB db = new GeneralDB();
    private  Statement statement;
    private AppointmentDao appointmentDao;
    private void setUp() throws SQLException, ClassNotFoundException {
        statement = db.setConnection();
        appointmentDao = new AppointmentDao(statement.getConnection());

    }
    private List<Appointment> getAllAppointments() throws SQLException, ClassNotFoundException {
        setUp();
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


}
