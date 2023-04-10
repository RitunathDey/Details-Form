import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SuccessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // check if user is logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {

            // Get email from session
            String email = (String) session.getAttribute("email");

            // Get database connection
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ritunath", "root", "ritunath");

                // Retrieve user details from database
                String sql = "SELECT * FROM users WHERE email=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Display user details
                 out.println("<html>");
                      out.println("<head>");
                      out.println("<title>User Details</title>");
                      out.println("<style>");
                      out.println(".container { margin: auto; width: 80%; }");
                      out.println(".user-details { border: 1px solid #ccc; padding: 20px; }");
                      out.println(".user-details h2 { font-size: 24px; color: #333; margin-bottom: 10px; }");
                      out.println(".user-details p { font-size: 18px; color: #666; margin-bottom: 5px; }");
                      out.println("</style>");
                      out.println("</head>");
                      out.println("<body>");
                      out.println("<div class=\"container\">");
                      out.println("<header>");
                      out.println("<h1>User Details</h1>");
                      out.println("</header>");
                      out.println("<section class=\"user-details\">");
                      out.println("<h2>Name:</h2><p>" + rs.getString("name") + "</p>");
                      out.println("<h2>Email:</h2><p>" + rs.getString("email") + "</p>");
                      out.println("<h2>Mobile:</h2><p>" + rs.getString("mobile") + "</p>");
                      out.println("<h2>Address:</h2><p>" + rs.getString("address") + "</p>");
                      out.println("<h2>Department:</h2><p>" + rs.getString("department") + "</p>");
                      out.println("<h2>Location:</h2><p>" + rs.getString("location") + "</p>");
                      out.println("<h2>Date of Birth:</h2><p>" + rs.getString("dob") + "</p>");
                      out.println("<h2>Age:</h2><p>" + rs.getInt("age") + "</p>");
                    // Get the photo path and create a link to it
                    String photoPath = rs.getString("photo_path");
                    if (photoPath != null && !photoPath.isEmpty()) {
                        out.println("<p>Photo: <a href='" + photoPath + "'>" + "Image" + "</a></p>");
                    } else {
                        out.println("<p>No photo available</p>");
                    }
                    out.println("</div>");
                    out.println("</div>");
                } else {
                    out.println("<h2>User not found!</h2>");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                out.close();
            }

        } else {
            // user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
