package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hpsf.Section;

import Functions.dbManager;
import Functions.mdsFactory;

import stermip.csAssignment;
import stermip.students;
import stermip.teacher;

/**
 * Servlet implementation class teacherOperationservlet
 */
@WebServlet("/teacherOperation")
public class teacherOperationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("task").equalsIgnoreCase("myCourses")) myCourses(request,response);
		else if(request.getParameter("task").equalsIgnoreCase("insertMarks")) insertMarks(request,response);
		else if(request.getParameter("task").equalsIgnoreCase("viewMarks")) viewMarks(request,response);
	}

	private void insertMarks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		   int i = Integer.parseInt(request.getParameter("serial"));
		   teacher tch = (teacher) request.getSession().getAttribute("user");
		   ArrayList<csAssignment>css=tch.myCourses();
		   csAssignment cs = css.get(i);
		   request.getSession().setAttribute("cs", cs);
		   response.sendRedirect("insertMarks.jsp");
		
	}
	private void viewMarks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		   int i = Integer.parseInt(request.getParameter("serial"));
		   teacher tch = (teacher) request.getSession().getAttribute("user");
		   ArrayList<csAssignment>css=tch.myCourses();
		   csAssignment cs = css.get(i);
		   request.getSession().setAttribute("cs", cs);
		   response.sendRedirect("viewCourseResult.jsp");
		
	}
	private void myCourses(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("coursesOfTeacher.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("calculateMarks")!=null) calculateMarks(request,response);
	}

	private void calculateMarks(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		csAssignment cs= (csAssignment) request.getSession().getAttribute("cs");
		ArrayList<String>sectors = cs.getCrs().nameOfMarkingSectors();
		ArrayList<students>stus = cs.getSem().getStus();
		for(int i =0;i<stus.size();i++){
			double markArray[] = new double[20];
			for(int j=0;j<sectors.size();j++){
				String name =""+i+","+j;
				double marks = Double.parseDouble(request.getParameter(name));
				mdsFactory.setMarks(sectors.get(j), marks, markArray);
				
			}
			dbManager.insertMarks(stus.get(i).getReg_id(),cs.getCrs().getCourseId(),cs.getSem().getSemName(),cs.getSem().getSemSession(),markArray);
			
		}
		dbManager.updateCtAssignment(cs.getSem().getSemName(),cs.getSem().getSemSession(), cs.getCrs().getCourseId());
		response.sendRedirect("viewCourseResult.jsp");
		
		
	}

}
