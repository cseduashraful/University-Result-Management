<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "stermip.*"
    import = "Functions.*"
    import ="java.util.ArrayList"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><%= ((users)session.getAttribute("user")).getName() %></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<%
users user = (users)session.getAttribute("user");
String photoString = (String) session.getAttribute("photoString");
%>

<!-- start header -->
<div id="header">
	<h1>University of Dhaka</h1>
	<p> Department of Computer Science and Engineering</p>
</div>
<!-- end header -->
<!-- start page -->
<%if(user.getRole().equalsIgnoreCase("chairman")) {%>
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">Give information about this</h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
			<%
				semester sem = (semester) session.getAttribute("sem");
				ArrayList<teacher>teacherArrayList = dbManager.fetchDeptTeacherList();
				ArrayList<course>courseArrayList = dbManager.fetchCourseList();
				if(user.getRole().equalsIgnoreCase("chairman") && sem.getSemStatus().equalsIgnoreCase("AdmissionNotStarted")){
				%>
				<h1 >Add course to <%=sem.getSemName()%> of <%=sem.getSemSession()%></h1>
				
				
				<form action="signUp" method="post">
				<h3>Available Courses</h3>
				<select name = "course">
				<%for(int i =0;i<courseArrayList.size();i++){ 
				course crs = courseArrayList.get(i);
				String courseId = crs.getCourseId();
				%>
				<option value="<%=courseId %>" >Course Id:<%=crs.getCourseId()%>,Name:<%=crs.getCourseName() %>  </option>
				<%} %>
				</select>
				<br>
				<br>
				<h3>Assign this course to:</h3>
				<select name = "teacher">
				<%for(int i =0;i<teacherArrayList.size();i++){ 
				teacher tch = teacherArrayList.get(i);
				String regId = tch.getReg_id();
				%>
				<option value="<%=regId %>" >Teacher Id:<%=tch.getReg_id() %>,Name:<%=tch.getName() %>  </option>
				<%} %>
				</select>
				<p style="padding-top: 20px; padding-left: 140px" >
				<input  type="submit" name="crsAssignOk" value="Add" />
				</p>
				
				</form>
				<%
				if(sem.getCts().size()==0){
				%>
				<h3>No Course Assigned Yet</h3>
				<%}else{ %>
				<form action="signUp" method="post">
				<br>
				<h3>Assigned Courses</h3>
				<%for(int i=0;i<sem.getCts().size();i++){ 
					ctAssignment ct=sem.getCtAssignmentAt(i);
				%>
				<p style="color: silver; background-image: url(images/blue.jpg);">
				<%=i+1 %>.
				<br>
				Course Code:<%=ct.getCrs().getCourseId() %>
				<br> Course Name:<%=ct.getCrs().getCourseName() %>
				<br>Teacher Name:<%=ct.getTch().getName() %>
				<br>Teacher Id:<%=ct.getTch().getReg_id() %>
				<a href="signUp?task=<%="removeCtAssignment" %>&course=<%=ct.getCrs().getCourseId()  %>">Remove</a>
				</p>
				<%} %>
				<input  type="submit" name="finalizeSem" value="Finalize" />
				</form>
				<%} %>
				
				<%} else{%>
				<h1>Not A Valid Link For You</h1>
				<%} %>	
			</div>
		</div>
	</div>
<%}else{ %>
<h1>This not a valid link for you</h1>
<%} %>
	<!-- end content -->
	<!-- start sidebar -->
	<div id="sidebar">
		<ul>
			<li>
				<h2>Operations for <%=user.getRole() %></h2>
				<ul>
					
					<%if(user.getStatus().equals("activated")){%>
					
					<%} %>
					<li><a href="success.jsp">My Profile</a></li>
					<li><a href="listphotos">My Photos </a></li>
					<li><a href="signUp?task=logOut">Log Out</a></li>
				</ul>
			</li>
			<li>
				<h2>Links</h2>
				<ul>
					<li><a href="http://www.du.ac.bd/">Dhaka University Homepage</a></li>
					<li><a href="http://www.cse.univdhaka.edu/">Dept. of CSE</a></li>
					<li><a href="#">About the developers</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<!-- end sidebar -->
</div>
<!-- end page -->
<div id="footer">
	<p>Design by Fatema Tuj Jahura Jamy</p>
</div>
<div align=center>Oracle Support: Md. Salman Ahmed.....Java Support: Md. Ashraful Islam</div>
</body>
</html>