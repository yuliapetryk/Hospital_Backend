package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Appointment;
import entities.Patient;
import entities.Staff;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import services.LoginService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    private static class UserRequest {
        public String mode;
        public String id;
        public String password;
    }

    final String MODE_DOCTOR = "0";
    final String MODE_PATIENT = "1";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            UserRequest userRequest= objectMapper.readValue(request.getReader(), UserRequest.class);
            LoginService loginService= new LoginService();
            Object user = null;
            System.out.println(userRequest.mode);
            if (Objects.equals(userRequest.mode, MODE_DOCTOR)) {
                user = loginService.loginStaff(userRequest.id, userRequest.password);
            } else if (Objects.equals(userRequest.mode, MODE_PATIENT)) {
                user = loginService.loginPatient(userRequest.id, userRequest.password);
            } else {
                throw new IllegalArgumentException("Invalid mode: " + userRequest.mode);
            }

            String json;
            if (user == null) {

                json = new ObjectMapper().writeValueAsString(0);
            } else {
                json = new ObjectMapper().writeValueAsString(user);
            }
            System.out.println(user);

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR, "Server error");
        }
    }

}
