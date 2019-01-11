package controller;
import stermip.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class AddPhotoServlet
 */
@WebServlet("/addphoto")
public class AddPhotoServlet extends HttpServlet {
	
	
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Apache Commons-Fileupload library classes
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu  = new ServletFileUpload(factory);

            if (! ServletFileUpload.isMultipartContent(request)) {
                System.out.println("sorry. No file uploaded");
                return;
            }

            // parse request
            List items = sfu.parseRequest(request);
            
            FileItem title = (FileItem) items.get(0);
            String   phototitle =  title.getString();

            // get uploaded file
            FileItem file = (FileItem) items.get(1);
                        
            establishConnection();
            users user = (users) request.getSession().getAttribute("user");
            
            String findmax = "select max(serial) as mx from photos where reg_id=?";
            PreparedStatement pst = con.prepareStatement(findmax);
            pst.setString(1, user.getReg_id());
            ResultSet rs = pst.executeQuery();
            int sr=0;
            while(rs.next()){
            	sr = rs.getInt("mx");
            	System.out.println(sr);
            }
        	sr++;
            
            
            String jdbc_insert_sql = "INSERT INTO photos"
	            + "(REG_ID, title,pic,serial,status) VALUES"
	            + "(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(jdbc_insert_sql);
            ps.setString(1,user.getReg_id());
            ps.setString(2, phototitle);
            // size must be converted to int otherwise it results in error
            ps.setBinaryStream(3, file.getInputStream(), (int) file.getSize());
            ps.setInt(4,sr);
            ps.setString(5, "past");
            ps.executeUpdate();
            request.getSession().setAttribute("photoString", "Photo Added Successfully");
            response.sendRedirect("success.jsp");
        }
        catch(Exception ex) {
        	ex.printStackTrace();
            out.println( "Error --> " + ex.getMessage());
        }
    } 
}
