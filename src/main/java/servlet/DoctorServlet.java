package servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class DoctorServlet  extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // String json = new ObjectMapper().writeValueAsString(object);
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String json = gson.toJson(new String[]{"It's working"});
            out.print(json);
            out.flush();
        }

    }
