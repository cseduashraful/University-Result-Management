package controller;
import stermip.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Functions.*;
/**
 * Servlet implementation class studentOperationServlet
 */
@WebServlet("/studentOperation")
public class studentOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("task").equalsIgnoreCase("admitToSemester")) admitToSemester(request,response);
		if(request.getParameter("task").equalsIgnoreCase("homepage")) response.sendRedirect("success.jsp");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("admitToSem")!=null) performAdmission(request,response);
	}
	
	private void performAdmission(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		students stu =(students) request.getSession().getAttribute("user");
		ArrayList<semester>sems = dbManager.fetchSemestersWhereStatusIs("AdmissionAllowed");
		int index = Integer.parseInt(request.getParameter("semester"));
		System.out.println("index:"+index+" here");
		semester sem = sems.get(index);
		System.out.println("Semname:"+sem.getSemName());
		if(sem.admitStudent(stu)){
			System.out.println("Admission Successfull");
			request.getSession().setAttribute("sem", sem);
			response.sendRedirect("Admissionsuccessful.jsp");
		}
		else response.sendRedirect("admitToSemester.jsp");
	}


	private void admitToSemester(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			response.sendRedirect("admitToSemester.jsp");
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("success.jsp");
		}
	}
	

}
