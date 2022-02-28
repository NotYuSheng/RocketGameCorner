import java.sql.*; 



public class LoginDao {  
   public static boolean validate(String email,String password){  
      boolean status=false;  
         try{  
            //Class.forName("oracle.jdbc.driver.OracleDriver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccounts?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                  "root", "root");  
      
            PreparedStatement ps=con.prepareStatement("select * from user where email=? and password=?");  
            ps.setString(1,email);  
            ps.setString(2,password);  
      
            ResultSet rs=ps.executeQuery(); 
            //System.out.println("this is ps: " + ps);             
            status=rs.next();  
            //System.out.println("this is status: " + status);

         }catch(Exception e){System.out.println(e);}  
      return status;  
   }  
}