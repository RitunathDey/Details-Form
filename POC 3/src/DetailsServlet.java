import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


public class DetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // check if user is logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            
            // Get form parameters
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
            String department = request.getParameter("department");
            String location = request.getParameter("location");
            Part photo = request.getPart("photo");
            String dob = request.getParameter("dob");
            
       
            String submittedFileName = photo.getHeader("content-disposition")
                            .split(";")[2]
                            .split("=")[1]
                            .replaceAll("\"", "");

            // generate unique file name using UUID
            String uniqueFileName = UUID.randomUUID().toString() + "_" + submittedFileName;

            // specify upload directory
            String uploadPath = "C:/Users/Ritunath Dey/Downloads/eclipse-jee-2020-06-R-win32-x86_64/images/";

            // create upload directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // create file path for uploaded photo
            String filePath = uploadPath + uniqueFileName;

            // write photo to file
            try (OutputStream out1 = new FileOutputStream(filePath);
                 InputStream in = photo.getInputStream()) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out1.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            
            // Calculate age from date of birth
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date dobDate = null;
            try {
                dobDate = format.parse(dob);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date now = new Date();
            long ageLong = now.getTime() - dobDate.getTime();
            int age = (int) (ageLong / (1000L * 60L * 60L * 24L * 365L));
            
            // Get email from session
            String email = (String) session.getAttribute("email");
            
         // Construct unique photo column name using email
            String photoColumn = "photo_" + email.replace("@", "_").replace(".", "_");
            
            // Get database connection
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ritunath", "root", "ritunath");
                conn.setAutoCommit(false);
                
                // Update employee details in database
                String sql = "UPDATE users SET mobile=?, address=?, department=?, location=?, dob=?, age=?,photo_path=? WHERE email=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, mobile);
                ps.setString(2, address);
                ps.setString(3, department);
                ps.setString(4, location);
                ps.setString(5, dob);
                ps.setInt(6, age);
                ps.setString(7, filePath);
                ps.setString(8, email);
                int rows = ps.executeUpdate();
                
                if (rows > 0) {
                	session.setAttribute("mobile", mobile);
                    session.setAttribute("address", address);
                    session.setAttribute("department", department);
                    session.setAttribute("location", location);
                    session.setAttribute("dob", dob);
                    session.setAttribute("age", age);
                    session.setAttribute("photo_path", filePath);
                    conn.commit();
                    response.sendRedirect("success.jsp");
                } else {
                    conn.rollback();
                    out.println("<h2>Failed to Update Details!</h2>");
                }
                
            } catch (Exception e) {
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            
        } else {
            // user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
