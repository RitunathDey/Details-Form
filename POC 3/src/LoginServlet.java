import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    
    // validate data
    if (email == null || email.equals("") || password == null || password.equals("")) {
        out.println("Please fill all the fields");
    } else {
        try {
            // connect to the database using JDBC
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ritunath", "root", "ritunath");

            // check if the email and password exist in the database
            PreparedStatement ps = con.prepareStatement("select * from users where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // if the email and password match, set the session attribute and redirect to the details page
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("details.jsp");
            } else {
                // if the email and password do not match, display an error message
                out.println("Invalid email or password. Please try again.");
            }

        } catch (Exception e) {
            out.println(e);
        }

    }
  }

}
