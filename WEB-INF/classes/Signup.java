
// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\HelloServlet.java"
import java.io.*;
import jakarta.servlet.*; // Tomcat 10
import jakarta.servlet.http.*; // Tomcat 10
import jakarta.servlet.annotation.*; // Tomcat 10
import java.sql.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;        // Tomcat 9
//import javax.servlet.annotation.*;  // Tomcat 9

@WebServlet("/signup") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class Signup extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {

      try (
            // Step 1: Allocate a database 'Connection' object
            Connection conn = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/useraccounts?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                  "root", "root"); // for MySQL only

            // Step 2: Allocate a 'Statement' object in the Connection
            Statement stmt = conn.createStatement();) {
         response.setContentType("text/html");
         PrintWriter pw = response.getWriter();

         String userName = request.getParameter("user-name");
         String password = request.getParameter("password");
         String email = request.getParameter("email");
         String sqlInsert =  "insert into user (username,password,email) values('"+userName+"','"+password+"','"+email+"')";

         System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging 
         stmt.executeUpdate(sqlInsert); 

         RequestDispatcher rd=request.getRequestDispatcher("confirmation.html");
         rd.include(request,response);
         pw.close();
      } catch (SQLException ex) {
         ex.printStackTrace();
      } // Step 5: Close conn and stmt - Done automatically by try-with-resources
   }
}
