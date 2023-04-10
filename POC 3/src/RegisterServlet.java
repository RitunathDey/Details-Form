import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    
    // validate form data
    if(name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
        // display error message if any form parameter is missing
        request.setAttribute("errorMessage", "All fields are required");
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    } else {
        // insert user data into the database using JDBC
        try {
        	try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ritunath", "root", "ritunath");
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            conn.close();
        } catch(SQLException e) {
            // display error message if there is a problem with the database
            request.setAttribute("errorMessage", "Error connecting to database");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Registration successful, redirect to login page
        response.sendRedirect("reglog.jsp");
    }
}

}
