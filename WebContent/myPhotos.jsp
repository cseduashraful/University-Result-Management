<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "stermip.*"
    import = "Functions.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Photos of <%= ((users)session.getAttribute("user")).getName() %></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<%!
students ust;
teacher usf;
admin uc;
String photoString=null;
%>
<%
users user = (users)session.getAttribute("user");
ResultSet rs = (ResultSet) session.getAttribute("rs");
%>

<!-- start header -->
<div id="header">
	<h1>University of Dhaka</h1>
	<p> Department of Computer Science and Engineering</p>
</div>
<!-- end header -->
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">Photos of<br> <%=user.getName() %></h1>
			<br>
			<br>
			<div class="entry">
			
			<%
			out.println("<h1>Photos</h1>");
            while ( rs.next()) {
            	int serial = rs.getInt("serial");
                  out.println("<h4>" + rs.getString("title") + "</h4>");
                  String str = "<img height='160' src=displayphoto?id=" +  rs.getInt("serial") + "></img> <p/>";
                  System.out.println(str);
                  %>
                  <a href="displayphoto?id=<%=serial %>">
                  <%
                  out.println(str);
                  %>
                  </a>
                 <a href="signUp?task=changeProPic&serial=<%=serial %>">Make It Profile Photo</a>
                 <br><a href="signUp?task=deletePic&serial=<%=serial %>">Delete This Photo</a>
                 
                 
                 
                 <%
            }

			
			%>
			
			</div>
		</div>
	</div>
	<!-- end content -->
	<!-- start sidebar -->
	<div id="sidebar">
		<ul>
			<li>
				<h2>Operations for <%=user.getRole() %></h2>
				<ul>
					
					<%if(user.getStatus().equals("activated")){%>
					<%if(user.getRole().equals("chairman")){%>
					<li><a href="signUp?task=registerSingleUser">Upload Single User</a></li>
				    <li><a href="signUp?task=uploadStudentInfo">Upload Multiple Users</a></li>
				    <li><a href="signUp?task=<%="registerNewCourse" %>&type=theory">Theory Course Registration</a></li>
				    <li><a  href="signUp?task=<%="registerNewCourse" %>&type=lab">Lab Course Registration</a></li>
				    <li><a href="signUp?task=configureNewSemester">Configure New Semester</a>
				    <li><a href="signUp?task=addCourseToSemester">Add Course to Semester</a>
				    </li>
				    <%}else if(user.getRole().equals("student")){ %>
				    <li><a href="studentOperation?task=admitToSemester">Admit to a semester</a></li>
    				<li><a href="editPersonalDetails">Edit Personal Details</a></li>
					<%} %>
					<%} %>
					<li><a href="success.jsp">Back</a>
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