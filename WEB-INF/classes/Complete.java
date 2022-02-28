//https://www.tutorialspoint.com/jdbc/statement-object-example.htm
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/complete")
public class Complete extends HttpServlet {

	//static final String UPDATE_QUERY = "UPDATE merchandise set qty = 20 WHERE name = 'Dragon Rage'";
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set the MIME type for the response message
		response.setContentType("text/html");
		// Get a output writer to write the response message into the network socket
		PrintWriter out = response.getWriter();

		// Print an HTML page as the output of the query

		out.println(
				"<!DOCTYPE html> <html lang='en'>   <head>     <meta charset='UTF-8' />     <meta http-equiv='X-UA-Compatible' content='IE=edge' />     <meta name='viewport' content='width=device-width, initial-scale=1.0' />     <meta       name='google-signin-client_id'       content='403668592559-aa285rb700e0ik67s7jtb47smd81pkli.apps.googleusercontent.com.apps.googleusercontent.com'     />     <link       href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css'       rel='stylesheet'       integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3'       crossorigin='anonymous'     />     <link       rel='stylesheet'       href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css'     />     <link       href='https://api.mapbox.com/mapbox-gl-js/v2.7.0/mapbox-gl.css'       rel='stylesheet'     />     <title>Rocket Game Corner</title>     <link rel='stylesheet' href='style.css' />     <link       rel='icon'       href='https://s2.coinmarketcap.com/static/img/coins/64x64/8303.png'     />   </head>   <body>     <!-- Navbar -->     <nav class='navbar navbar-expand-lg bg-dark navbar-dark py-3 fixed-top'>       <div class='container'>         <a href='index.html' class='navbar-brand'>Rocket Game Corner</a>          <button           class='navbar-toggler'           type='button'           data-bs-toggle='collapse'           data-bs-target='#navmenu'         >           <span class='navbar-toggler-icon'></span>         </button>          <div class='collapse navbar-collapse' id='navmenu'>           <ul class='navbar-nav ms-auto'>             <li class='nav-item'>               <a href='index.html' class='nav-link'>Home</a>             </li>           </ul>         </div>       </div>     </nav>");

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/rocketgamecorner?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
				"root", "root"); Statement stmt = conn.createStatement();) {

			// Item Listing
			String name = String.valueOf(request.getParameter("name"));
			String qtyString = String.valueOf(request.getParameter("qty"));
			int qtyInt = Integer. valueOf(qtyString) - 1;
			qtyString = Integer.toString(qtyInt);

			//int rs;
			String query = "UPDATE merchandise set qty = " + qtyString + " WHERE name = '" + name + "'";

			Boolean ret = stmt.execute(query); // Send the query to the server

			out.println(
					"<!-- Complete Page -->     <section       class='bg-dark text-light p-5 p-lg-0 pt-lg-5 text-center text-sm-start'     >       <div class='container'>         <div class='row g-4'>           <div class='d-sm-flex align-items-center justify-content-between'>             <div>               <h1>Complete page</h1>                            </div>           </div>         </div>       </div>     </section>");

			out.print(
					"    <!-- Confirmation -->     <section id='about' class='p-5'>       <div class='container'><div class='row align-items-center justify-content-between'>           <div class='col-md text-center'>             <img               src='https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/7d555e33-5cbc-4c51-b6b8-1052f25e40b5/de51fpf-fb3943d0-291a-4b18-addc-55b6404b4528.jpg/v1/fill/w_800,h_800,q_75,strp/poke_mail_by_melonbreads_de51fpf-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzdkNTU1ZTMzLTVjYmMtNGM1MS1iNmI4LTEwNTJmMjVlNDBiNVwvZGU1MWZwZi1mYjM5NDNkMC0yOTFhLTRiMTgtYWRkYy01NWI2NDA0YjQ1MjguanBnIiwiaGVpZ2h0IjoiPD04MDAiLCJ3aWR0aCI6Ijw9ODAwIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLndhdGVybWFyayJdLCJ3bWsiOnsicGF0aCI6Ilwvd21cLzdkNTU1ZTMzLTVjYmMtNGM1MS1iNmI4LTEwNTJmMjVlNDBiNVwvbWVsb25icmVhZHMtNC5wbmciLCJvcGFjaXR5Ijo5NSwicHJvcG9ydGlvbnMiOjAuNDUsImdyYXZpdHkiOiJjZW50ZXIifX0._l-Bon71vOGmrjvo1mb8rWaauvgYqrV2F0Ak5iES2jo'               class='img-fluid w-50'               alt=''             />           </div>           <div class='col-md p-5'>             <h2>Your item is on the way</h2>             <p>               We've sent a purchase confirmation to your email <br />               Expect a delivery to arrive in 2 to 3 working days <br /> 			  Thank you for shopping with Rocket Game Corner!             </p><form method='post' action='shop'><button type='submit' class='btn btn-link'>Click here to continue shopping</button></form></div></div></div></section>");

			out.println(
					"    <!-- Footer -->     <footer class='p-5 bg-dark text-white text-center position-relative'>       <div class='container'>         <p class='lead'>Copyright &copy 2022 Rocket Game Corner</p>         <a href='#' class='position-absolute bottom-0 end-0 p-5'>           <i class='bi bi-arrow-up-circle h1'></i>         </a>       </div>     </footer>      <!-- Dropdown menu -->     <div class='dropdown-menu'>       <form class='px-4 py-3'>         <div class='mb-3'>           <label for='exampleDropdownFormEmail1' class='form-label'             >Email address</label           >           <input             type='email'             class='form-control'             id='exampleDropdownFormEmail1'             placeholder='email@example.com'           />         </div>         <div class='mb-3'>           <label for='exampleDropdownFormPassword1' class='form-label'             >Password</label           >           <input             type='password'             class='form-control'             id='exampleDropdownFormPassword1'             placeholder='Password'           />         </div>         <div class='mb-3'>           <div class='form-check'>             <input               type='checkbox'               class='form-check-input'               id='dropdownCheck'             />             <label class='form-check-label' for='dropdownCheck'>               Remember me             </label>           </div>         </div>         <button type='submit' class='btn btn-primary'>Sign in</button>       </form>       <div class='dropdown-divider'></div>       <a class='dropdown-item' href='#'>New around here? Sign up</a>       <a class='dropdown-item' href='#'>Forgot password?</a>     </div>      <script       src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'       integrity='sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p'       crossorigin='anonymous'     ></script>     <script src='https://apis.google.com/js/platform.js' async defer></script>     <script>       function onSignIn(googleUser) {         // Useful data for your client-side scripts:         var profile = googleUser.getBasicProfile();         console.log('ID: ' + profile.getId()); // Don't send this directly to your server!         console.log('Full Name: ' + profile.getName());         console.log('Given Name: ' + profile.getGivenName());         console.log('Family Name: ' + profile.getFamilyName());         console.log('Image URL: ' + profile.getImageUrl());         console.log('Email: ' + profile.getEmail());          // The ID token you need to pass to your backend:         var id_token = googleUser.getAuthResponse().id_token;         console.log('ID Token: ' + id_token);       }     </script>   </body> </html>");

		} catch (Exception ex) {
			out.println("<p>Error: " + ex.getMessage() + "</p>");
			out.println("<p>Check Tomcat console for details.</p>");
			ex.printStackTrace();
		}
		out.close();
	}
}