package controller;
import stermip.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Functions.*;
/**
 * Servlet implementation class signUpServlet
 */
@WebServlet("/signUp")
public class signUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("task").equalsIgnoreCase("uploadStudentInfo")) response.sendRedirect("uploadStudentInfo.jsp");
		else if(request.getParameter("task").equalsIgnoreCase("logOut")) {
			request.getSession().invalidate();
			request.getSession().setAttribute("photoString", "You are logged out");
			response.sendRedirect("login.jsp");
		}
		else if(request.getParameter("task").equalsIgnoreCase("removeProfilePhoto")){
			users user = (users) request.getSession().getAttribute("user");
			user.removeProfilePhoto();
			response.sendRedirect("success.jsp");
		}
		
		else if(request.getParameter("task").equalsIgnoreCase("changeProPic")){
			try{
				int sr = Integer.parseInt(request.getParameter("serial"));
				users user = (users) request.getSession().getAttribute("user");
				user.setProfilePhoto(sr);
				response.sendRedirect("success.jsp");
				
			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("success.jsp");
			}
		}
		else if(request.getParameter("task").equalsIgnoreCase("deletePic")){
			try{
				int sr = Integer.parseInt(request.getParameter("serial"));
				users user = (users) request.getSession().getAttribute("user");
				user.deletePhoto(sr);
				response.sendRedirect("success.jsp");
				
			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("success.jsp");
			}
		}
		else if(request.getParameter("task").equalsIgnoreCase("defaultPic")){
			try{
				int sr = Integer.parseInt(request.getParameter("serial"));
				users user = (users) request.getSession().getAttribute("user");
				if(user.getRole().equalsIgnoreCase("chairman")){
					admin ch = (admin)user;
					ch.defaultPhoto(sr);
					response.sendRedirect("success.jsp");
				}
				response.sendRedirect("success.jsp");
				
			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("success.jsp");
			}
		}
		else if(request.getParameter("task").equalsIgnoreCase("registerNewCourse")){
			course crs = new course();
			crs.setType(request.getParameter("type"));
			request.getSession().setAttribute("crs", crs);
			response.sendRedirect("RegisterNewCourse.jsp"); 
			
		}
		else if(request.getParameter("task").equalsIgnoreCase("registerSingleUser")){
			users newUser = null;
			request.getSession().setAttribute("newUser", newUser);
			response.sendRedirect("registerSingleUser.jsp");
		}
		else if(request.getParameter("task").equalsIgnoreCase("configureNewSemester")){
			response.sendRedirect("configureNewSemester.jsp");
		}
		else if(request.getParameter("task").equalsIgnoreCase("addCourseToSemester")){
			response.sendRedirect("chooseSemToAddCrs.jsp");
		}
		else if(request.getParameter("task").equalsIgnoreCase("removeCtAssignment")){
			String courseId=request.getParameter("course");
			semester  sem = (semester)request.getSession().getAttribute("sem");
			if(sem.removeCourse(courseId)){
				request.getSession().setAttribute("photoString",""+courseId+" has been removed");
				response.sendRedirect("addCourseToSemester.jsp");
			}else{
				request.getSession().setAttribute("photoString",""+courseId+" wasn't removed. Try again");
				response.sendRedirect("addCourseToSemester.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("signUp")!=null) 
		{
			String reg_id = request.getParameter("reg_id");
			activateMe(request,response,reg_id);
		}
		else if(request.getParameter("submitActivation")!=null){
			String reg_id = request.getParameter("reg_id");
			String activation = request.getParameter("activation");
			Random rn = new Random();
			int pass = rn.nextInt();
			if(pass<0) pass=pass*(-1);
			boolean bl = dbManager.activate(reg_id, activation, pass);
			if(bl){
				
				String email = dbManager.emai(reg_id);
				SendPassword.perform(email, pass);
				String status = dbManager.status(reg_id);
				String str = "";
				if(status.equalsIgnoreCase("emailConfirmed")){
					str="Your email: "+email+" has been confirmed. Wait for confirmation email from admin.";
					str = str+"\nBy this time you can log in. Password has been sent to your email address";
				}
				else if(status.equalsIgnoreCase("activated")){
					str="Your account has been activated.";
					str = str+"\nPassword has been sent to your email address: "+email;
				}
				request.getSession().setAttribute("photoString", str);
				response.sendRedirect("login.jsp");
			}
			else {
				request.getSession().setAttribute("photoString", "Email confirmation failed");
				response.sendRedirect("login.jsp");
			}
			
			
		}
		else if(request.getParameter("ok")!=null) response.sendRedirect("login.jsp");
		else if(request.getParameter("login")!=null)  forLogin(request, response);
		else if(request.getParameter("courseOk")!=null) registerCourse(request,response);
		else if(request.getParameter("courseOk2")!=null) completeCourseRegistration(request,response);
		else if(request.getParameter("roleOk")!=null) createUser(request,response);
		else if(request.getParameter("infoOk")!=null) uploadUser(request,response);
		else if(request.getParameter("infoBack")!=null) backToRoleSelection(request,response);
		else if(request.getParameter("infoCancel")!=null)backToHomepage(request,response);
		else if(request.getParameter("infoOkNew")!=null) registerMe(request,response);
		else if(request.getParameter("semOk")!=null) configureNewSemester(request, response);
		else if(request.getParameter("crsAssignOk")!=null) assignCourseToSemester(request,response);
		else if(request.getParameter("finalizeSem")!=null) finalizeSemCourse(request,response);
		else if(request.getParameter("selectSem")!=null) semSelected(request,response);
		
	}
	private void semSelected(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<semester>sems = dbManager.fetchSemestersWhereStatusIs("AdmissionNotStarted");
		int index = Integer.parseInt(request.getParameter("semester"));
		System.out.println("index:"+index+" here");
		semester sem = sems.get(index);
		System.out.println("Semname:"+sem.getSemName());
		request.getSession().setAttribute("sem", sem);
		System.out.println("semester stored in database");
		request.getSession().setAttribute("photoString",""+sem.getSemName()+" of "+sem.getSemSession()+" session has been registered");
		response.sendRedirect("addCourseToSemester.jsp");
		
		
	}

	private void activateMe(HttpServletRequest request,
			HttpServletResponse response, String reg_id) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("printed from servlet.\nrequired reg_id:"+reg_id);
		String email = dbManager.emai(reg_id);
		if(email!=null){
			System.out.println(email);
			Random rn = new Random();
			int code = rn.nextInt();
			if(code<0) code=code*(-1);
			System.out.println(code);
			SendEmail.perform(email, code);
			boolean bl=dbManager.updateCode(reg_id, code);
			if(bl){
				response.sendRedirect("enterCode.jsp");
			}
			else response.sendRedirect("login.jsp");
		}else response.sendRedirect("login.jsp");
		
	}

	private void registerMe(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try {
			String regId = request.getParameter("regId");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mob = request.getParameter("mob");
			int yearOfRegistration = Integer.parseInt(request.getParameter("yearOfRegistration"));
			int date = Integer.parseInt(request.getParameter("date"));
			int month = Integer.parseInt(request.getParameter("month"));
			int year = Integer.parseInt(request.getParameter("year"))-1900;
			Date dob = new Date(year, month-1,date);
			users newUser =(users) request.getSession().getAttribute("newUser");
			if(newUser.getRole().equalsIgnoreCase("student")) ((students)newUser).setSerial(Integer.parseInt(request.getParameter("serial")));
			newUser.setReg_id(regId);
			newUser.setName(name);
			newUser.setMob(mob);
			newUser.setEmail(email);
			newUser.setDob(dob);
			newUser.setYearOfRegistration(yearOfRegistration);
			if(!checkUserNameValidity.isValid(newUser.getReg_id())) {
				System.out.println("reached here!!!!");
				request.getSession().setAttribute("photoString","Invalid User Name!!!");
				response.sendRedirect("registerSingleUser.jsp");
				return;
			}
			if(dbManager.insertUser(newUser,"pending")){
				
				activateMe(request, response, newUser.getReg_id());
			}else{
				response.sendRedirect("registerSingleUser.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("registerSingleUser.jsp");
		}

	}

	private void backToHomepage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.getSession().setAttribute("newUser", null);
		response.sendRedirect("MainPage.jsp");
	}

	private void backToRoleSelection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("newUser", null);
		response.sendRedirect("registerSingleUser.jsp");
		
	}

	private void finalizeSemCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		semester sem =(semester) request.getSession().getAttribute("sem");
		sem.setSemStatus("AdmissionAllowed");
		if(dbManager.updateSemStatus(sem)){
			response.sendRedirect("success.jsp");
		}else{
			response.sendRedirect("addCourseToSemester.jsp");
		}
	}

	private void assignCourseToSemester(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try{
			String reg_id = request.getParameter("teacher");
			System.out.println(reg_id);
			String course_id = request.getParameter("course");
			System.out.println(course_id);
			teacher tch=dbManager.fetchDeptTeacher(reg_id);
			course crs = dbManager.fetchCourse(course_id);
			ctAssignment ct = new ctAssignment(crs, tch, false);
			semester sem =(semester) request.getSession().getAttribute("sem");
			if(dbManager.insertCtAssignment(sem, ct)){
				sem.getCts().add(ct);
				request.getSession().setAttribute("sem", sem);
				request.getSession().setAttribute("photoString", ""+ct.getCrs().getCourseId()+" has been added");
				response.sendRedirect("addCourseToSemester.jsp");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			request.getSession().setAttribute("photoString","Error occured. Try again");
			response.sendRedirect("addCourseToSemester.jsp");
		}
		
	}

	private void configureNewSemester(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		try{
			String semName = request.getParameter("semName");
			int semSession = Integer.parseInt(request.getParameter("semSession"));
			semester sem = new semester(semName, semSession, "AdmissionNotStarted");
			if(dbManager.insertSemester(sem)){
				request.getSession().setAttribute("sem", sem);
				System.out.println("semester stored in database");
				request.getSession().setAttribute("photoString",""+sem.getSemName()+" of "+sem.getSemSession()+" session has been registered");
				response.sendRedirect("addCourseToSemester.jsp");
			}else{
				request.getSession().setAttribute("photoString","Error occured. Try again");
				response.sendRedirect("configureNewSemester.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
			request.getSession().setAttribute("photoString","Error occured. Try again");
			response.sendRedirect("configureNewSemester.jsp");
		}
	}

	private void forLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String reg_id, password;
		reg_id = request.getParameter("reg_id");
		System.out.println("printed from servlet.\nrequired reg_id:"+reg_id);
		password= request.getParameter("password");
		users user = dbManager.login(reg_id, password);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			response.sendRedirect("success.jsp");
			return;
		}else{
			response.sendRedirect("login.jsp");
			return;
		}
	}
	boolean isEmpty(String str){
		if(str==null || str.equalsIgnoreCase("")) return true;
		return false;
	}
	
	private void registerCourse(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String courseName=null, courseArea=null, courseId=null, markingSector[]={};
		double courseCredit=0.0;
		//System.out.println("Called from RegisterNewCourse.jsp");
		try {
			courseName = request.getParameter("courseName");
			courseId = request.getParameter("courseId");
			courseArea = request.getParameter("courseArea");
			courseCredit = Double.parseDouble(request.getParameter("courseCredit"));
			markingSector = request.getParameterValues("markingSector");
			for(int i=0;i<markingSector.length;i++){
				System.out.println(markingSector[i]+",");
			}
			System.out.println(courseArea+","+courseCredit+","+courseId+","+courseName);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("RegisterNewCourse.jsp");
		}
		if(markingSector.length==0||isEmpty(courseName)||isEmpty(courseArea)|| isEmpty(courseId)|| courseCredit==0.0) response.sendRedirect("RegisterNewCourse.jsp");
		else {
			System.out.println("get your jobs done:"+courseName+","+courseId+","+courseCredit);
			course crs = (course)request.getSession().getAttribute("crs");
			crs.setCourseArea(courseArea);
			crs.setCourseId(courseId);
			crs.setCourseName(courseName);
			crs.setCredit(courseCredit);
			markDistributionSystem mds = new concreteMarkDistributionSystem();
			for(int i=0;i<markingSector.length;i++){
				mds = mdsFactory.mdsWrapper(mds, markingSector[i]);
			}
			mds.printTotalMarks();
			crs.setMds(mds);
			request.getSession().setAttribute("crs", crs);
			request.getSession().setAttribute("markingSector", markingSector);
			response.sendRedirect("SetPercentage.jsp");
		}
		
	}
	
	
	
	private void completeCourseRegistration(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		double percentage[]=new double[10];
		String markingSector[] = (String[])request.getSession().getAttribute("markingSector");
		course crs = (course) request.getSession().getAttribute("crs");
		double tot =0;
		for(int i = 0;i<markingSector.length;i++){
			try {
				double value = Double.parseDouble(request.getParameter(markingSector[i]));
				tot = tot+value;
				System.out.println(value+",");
				mdsFactory.setPercentage(markingSector, i, value, percentage);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.getSession().setAttribute("photoString","You Entered invalid number");
				response.sendRedirect("SetPercentage.jsp");
			}
		}
		if(tot==100){
		crs.getMds().setPercentage(percentage);
		boolean bl = dbManager.insertCourse(crs);
		if(bl){
			request.getSession().setAttribute("photoString","Course has been registered successfully");
			response.sendRedirect("success.jsp");
		}
		else {
			request.getSession().setAttribute("photoString","Course registration failed. Check the data");
			response.sendRedirect("RegisterNewCourse.jsp");
		}}else{
			request.getSession().setAttribute("photoString","Summation must be 100. yours "+ tot);
			response.sendRedirect("SetPercentage.jsp");
		}
		///now just insert to database
	}
	private void createUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String role = request.getParameter("role");
		System.out.println(role);
		users newUser = userFactory.getInstanceOfUserFactory().createUser(role);
		request.getSession().setAttribute("newUser", newUser);
		response.sendRedirect("registerSingleUser.jsp");
		
	}
	private void uploadUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try {
			System.out.println("From Upload User");
			String regId = request.getParameter("regId");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mob = request.getParameter("mob");
			int yearOfRegistration = Integer.parseInt(request.getParameter("yearOfRegistration"));
			int date = Integer.parseInt(request.getParameter("date"));
			int month = Integer.parseInt(request.getParameter("month"));
			int year = Integer.parseInt(request.getParameter("year"));
			Date dob = new Date(year, month-1,date);
			users newUser =(users) request.getSession().getAttribute("newUser");
			if(newUser.getRole().equalsIgnoreCase("student")) ((students)newUser).setSerial(Integer.parseInt(request.getParameter("serial")));
			newUser.setReg_id(regId);
			newUser.setName(name);
			newUser.setMob(mob);
			newUser.setEmail(email);
			newUser.setDob(dob);
			newUser.setYearOfRegistration(yearOfRegistration);
			if(!checkUserNameValidity.isValid(newUser.getReg_id())) {
				System.out.println("reached here!!!!");
				request.getSession().setAttribute("photoString","Invalid User Name!!!");
				response.sendRedirect("registerSingleUser.jsp");
				return;
			}
			if(dbManager.insertUser(newUser)){
				response.sendRedirect("success.jsp");
			}else{
				response.sendRedirect("registerSingleUser.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("registerSingleUser.jsp");
		}
		
		
	}
	

}
