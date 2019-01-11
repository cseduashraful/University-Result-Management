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
semester sem = (semester) session.getAttribute("sem");
%>

<!-- start header -->
<div id="header">
	<h1>University of Dhaka</h1>
	<p> Department of Computer Science and Engineering</p>
</div>
<!-- end header -->
<!-- start page -->
<%if(user.getRole().equalsIgnoreCase("student")) {%>
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">Student Registration Form  for Semester </h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
				<h3>Hi <%=user.getName() %>!! You have successfully admitted to <%=sem.getSemName() %> of <%=sem.getSemSession() %></h3>					 
			</div>
			<br>
			<br>
			<br>
			<div class="entry">
				<%
					sem=dbManager.fetchSemester(sem.getSemName(),sem.getSemSession());
					session.setAttribute("sem",sem);
				%>
					Assigned Courses for this semester are:<br>
					<%
					ArrayList<ctAssignment>cts = sem.getCts();
					for(int i=0;i<cts.size();i++){
						ctAssignment ct = cts.get(i);
					%>
					<%=i+1 %>. <%=ct.getCrs().getCourseId() %> ::: Teacher <%=ct.getTch().getName() %><br>
					<%} %>		 
			</div>
			<br>
			<br>
			<br>
			<div class="entry">
					Students Registered in this Semester are<br>
					<%
					
					ArrayList<students>stus =sem.getStus();
					for(int i=0;i<stus.size();i++){
					 students stu = stus.get(i);
					%>
					<%=i+1 %>. <%=stu.getName() %>
					<br>
					<%} %>
					<br>Return to <a href="studentOperation?task=homepage">Profile</a>					 
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