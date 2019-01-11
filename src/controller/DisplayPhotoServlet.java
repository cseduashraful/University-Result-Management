package controller;
import stermip.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class DisplayPhotoServlet
 */
@WebServlet("/displayphoto")


public class DisplayPhotoServlet extends HttpServlet {
	private static String DB_URL="jdbc:oracle:thin:@//127.0.0.1:1521/XE";
	//static final String DB_URL = "jdbc:oracle:thin:@http://127.0.0.1:8080/apex/f?p=4550:11:244036022536119::NO::::orcl";
	private static String USER = "result2"; 
	private static String PASS = "result2";
	private static Connection con = null; 
	private static Statement stmt = null; 
	private static String sql;
	private static void establishConnection(){
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Registered");
			con = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Established");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            establishConnection();
            PreparedStatement ps = con.prepareStatement("select pic from photos where serial = ? and reg_id = ?");
            String id = request.getParameter("id");
            System.out.println(id);
            users user = (users) request.getSession().getAttribute("user");
            String reg_id=user.getReg_id();
            System.out.println(reg_id);
            ps.setString(2,reg_id);
            ps.setInt(1,Integer.parseInt(id));
            int sr = Integer.parseInt(id);
            if(sr==0){
            	ps = con.prepareStatement("select pic from photos where serial = 0");
            }
            ResultSet rs = ps.executeQuery();
            rs.next();
            Blob  b = rs.getBlob("pic");
            response.setContentType("image/jpg");
            response.setContentLength( (int) b.length());
            InputStream is = b.getBinaryStream();
            OutputStream os = response.getOutputStream();
            byte buf[] = new byte[(int) b.length()];
            is.read(buf);
            os.write(buf);
            os.close();
        }
        catch(Exception ex) {
             System.out.println(ex.getMessage());
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
}