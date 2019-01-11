package controller;
import stermip.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import Functions.*;

/**
 * Servlet implementation class fileUploadServlet
 */
@WebServlet("/fileUpload")
public class fileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("reached!!!");
		//if(request.getParameter("FileOk")!=null){
			System.out.println("reached!!! also");
			String filename = request.getParameter("SelectedFile");
			System.out.println(filename);
			
			
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	        if (!isMultipart) {
	            
	            System.out.println("nothing!!!");
	            return;
	        }
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(MAX_MEMORY_SIZE);
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	        String uploadFolder = getServletContext().getRealPath("")
            + File.separator + DATA_DIRECTORY;
	        System.out.println(uploadFolder+"nothing!!!");
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(MAX_REQUEST_SIZE);
	        try{
	        	 List items = upload.parseRequest(request);
	             Iterator iter = items.iterator();
	             while (iter.hasNext()){
	            	 FileItem item = (FileItem) iter.next();
	            	 if (!item.isFormField()){
	            		 String fileName = new File(item.getName()).getName();
	                     String filePath = uploadFolder + File.separator + fileName;
	                     File uploadedFile = new File(filePath);
	                     System.out.println(filePath);
	                     item.write(uploadedFile);
	                     
	                     // write code to transfer from xls to Oracle
	                     FileInputStream input_document = new FileInputStream(new File(filePath));
	                     xlsToOracle.convert(input_document);
	                     //within
	                     try{
	                    	 
	                 		File file = new File(filePath);
	              
	                 		if(file.delete()){
	                 			System.out.println(file.getName() + " is deleted!");
	                 		}else{
	                 			System.out.println("Delete operation is failed.");
	                 		}
	              
	                 	}catch(Exception e){
	              
	                 		e.printStackTrace();
	              
	                 	}
	            	 }
	             }
	             getServletContext().getRequestDispatcher("/success.jsp").forward(
	                     request, response);
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
		}
	//}

}
