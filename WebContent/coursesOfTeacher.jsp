<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "stermip.*"
    import = "Functions.*"
    import = "java.util.ArrayList"
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
<%if(user.getRole().equalsIgnoreCase("teacher")) {
teacher tch = (teacher) user;
ArrayList<csAssignment>css = tch.myCourses();
%>
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">My Courses</h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
				<h3>All of my courses are:</h3>
				<%
				for(int i = 0;i<css.size();i++){
					csAssignment cs=css.get(i);
					%>
					
					<table  id="tab" >
					<tr><td>Course Name:</td><td><%=cs.getCrs().getCourseId() %></td></tr>
					<tr><td>Course Code:</td><td><%=cs.getCrs().getCourseId() %></td></tr>
					<tr><td>Semester:</td><td><%=cs.getSem().getSemName() %></td></tr>
					<tr><td>Session:</td><td><%=cs.getSem().getSemSession() %></td></tr>
					</table>
					<%
					if(!cs.isResultSubmitted()){
						%>
							<a href="teacherOperation?task=insertMarks&serial=<%=i %>">* * *Insert Marks</a>
							<br>
							<br>
						<%
					}else {
						%>
						<a href="teacherOperation?task=viewMarks&serial=<%=i %>">* * *View Marks</a>
						<br>
						<br>
					<%
				}
				}
				
				%>
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
					<li><a href="success.jsp">Back</a></li>
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