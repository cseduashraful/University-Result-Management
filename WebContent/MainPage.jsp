<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "stermip.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Homepage</title>
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
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">About This Department</h1>
			<div class="entry">
				<p>The Department of Computer Science was established on 1st September 1992. This department changed its name to the department of Computer Science and Engineering on 27th March 2004. The
founder chairman of this department was Professor Dr. M. Lutfar Rahman. There are more than 470 students
in the department at present.</p>

<p>This Department started M.Sc. Course in 1993. In 1995 it started (3-year) B.Sc. (Honours) Programme and 4-year B.S. (Honours) Programme started two years later. At present the department offers B.S. Honours in CSE, M.S. in CSE and Ph.D. Programmes.</p>

<p>Bangla Phoneme; Computer Security; Distributed System; Image Processing; Signal Processing; Computer Graphics; VLSI Sensor Network etc. are the major research areas existing in the department.</p>

<p>While all teachers and students can use the broadband Internet facilities, the department uses Multimedia Projector in the classrooms and in laboratories.
			</div>
			<p class="meta"><a href="#" class="more">Read More</a> &nbsp;&nbsp;&nbsp; <a href="#" class="comments">Comments (33)</a></p>
		</div>
	</div>
	<!-- end content -->
	<!-- start sidebar -->
	<div id="sidebar">
		<ul>
			<li>
				<h2>Categories</h2>
				<ul>
					<%if(user==null){ %>
					<li>Not a member yet? <a href="registerSingleUser.jsp">Sign Up</a></li>
					<li>Registered user? <a href="login.jsp">Log in</a></li>
					<li>Forget password? click <a href="passwordRetrieve.jsp">here</a></li>
					<%} else{ %>
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
					<li><a href="success.jsp">My Profile</a></li>
					<li><a href="signUp?task=logOut">Log Out</a></li>
				
					<%} %>
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