
import java.sql.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","2503");
        conn.setAutoCommit(false);
        if(conn != null){

            String sql1 = "DELETE  FROM \"Patients\" WHERE last_name = 'PetrenkoP'";
            String sql2 = "INSERT INTO  \"Patients\"( last_name, first_name, patronymic,sex, date_of_birth, address ) values ('PetrenkoO','Petro', 'Petrovych','M', '1992-11-11', 'Kyiv, Khreshchatyk, 123')";

            Statement ps = conn.createStatement();

            try{
                ps.execute(sql1);
                ps.execute(sql2);
                conn.commit();
            } catch (Exception e){
                conn.rollback();
                throw new RuntimeException(e);
            }

           /* ResultSet rs =ps.executeQuery();
            while (rs.next()){
                System.out.println(String.format("id = %s, last name = %s, first name = %s",
                        rs.getString(1), rs.getString(2), rs.getString(3) ));

            }*/
            ps.close();

        }
        conn.close();
    }
}