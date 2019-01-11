<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "stermip.*"
    import = "Functions.*"
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
<%!
students ust;
teacher usf;
admin uc;
String photoString=null;
%>
<%
users user = (users)session.getAttribute("user");
photoString = (String) session.getAttribute("photoString");
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
			<h1 class="title">Welcome <%=user.getName() %></h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
			<%
			String str = "<img height='160' src=displayphoto?id=" +  dbManager.profilePhotoSerial(user.getReg_id()) + "></img> <p/>";
            System.out.println(str);
            %>
            <a href="displayphoto?id=<%=dbManager.profilePhotoSerial(user.getReg_id()) %>">
            <%
            out.println(str);
            %>
            </a><%
            if(dbManager.profilePhotoSerial(user.getReg_id())!=0){
            	%>
            	<a href="signUp?task=removeProfilePhoto"> Remove Profile Photo</a>
            	<%
            }
			%>
			<table id="tab">
			<tr><td>Name:</td><td><%=user.getName()%></td></tr>
			<tr><td>Registration Number:</td><td><%=user.getReg_id()%></td></tr>
			<tr><td>Email:</td><td><%=user.getEmail()%></td></tr>
			<tr><td>Mobile Number:</td><td><%=user.getMob()%></td></tr>
			<tr><td>Date of Birth:</td><td><%=user.printDob() %></td></tr>
			<tr><td></td><td></td></tr>
			<tr><td></td><td></td></tr>
			<%if(user.getRole().equals("student")) {
				ust = (students)user;
			%>
			<tr><td>Serial:</td><td><%=ust.getSerial()%></td></tr>
			<tr><td>Admission Year:</td><td><%=ust.getYearOfRegistration() %></td></tr>
			<%}%>
			<%if(user.getRole().equals("chairman")||user.getRole().equals("staff")) {
			%>
			<tr><td>Service Starting Year:</td><td><%=user.getYearOfRegistration()%></td></tr><%}%>
			<tr><td>Status:</td><td><%=user.getStatus() %></td></tr>
			<tr><td></td><td></td></tr>
			</table>
					 
			</div>
		</div>
		<div class="post">
			<h1 class="title">You Can Upload Photos Here</h1>
			<br>
			<br>
			<div class="entry">
					 <form id="form1" enctype="multipart/form-data" action="addphoto" method="post">
		             <table>
		                <tr>
		                    <td>Enter Title For Photo :</td>
		                    <td><input  type="text"  name="title"/></td>
		                </tr>
		                <tr>
		                    <td>Select Photo  </td>
		                    <td><input type="file"  name="photo" />
		                </tr>
		            </table>
		            <p/>
		            <input type="submit" value="Add Photo"/>
		        	</form>
        			<p/>
			        <a href="listphotos">List Photos </a>
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
					<%} else if(user.getRole().equalsIgnoreCase("teacher")){%>
					<li><a href="teacherOperation?task=myCourses">My courses</a></li>
					<%}} %>
					<li><a href="MainPage.jsp">Homepage</a></li>
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