package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneralDB {

    public Statement setConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","2503");
        Statement ps = conn.createStatement();
        return ps;

    }


}
