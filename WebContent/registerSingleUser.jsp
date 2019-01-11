<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "stermip.*"
    import = "Functions.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Registration Form</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<%
users user1 = (users)session.getAttribute("user");
users user = (users)session.getAttribute("newUser");
String photoString = (String) session.getAttribute("photoString");
%>

<!-- start header -->
<div id="header">
	<h1>University of Dhaka</h1>
	<p> Department of Computer Science and Engineering</p>
</div>
<!-- end header -->
<!-- start page -->
<%if(user1==null || user1.getRole().equalsIgnoreCase("chairman")) {%>
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">Give information</h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
			<h3 align="center">Insert User Details</h3>
			<% if(user==null){ %>
			<form action="signUp" method="post">
			<p align="center">Register New <select id="role" name="role">
			<option>teacher</option><option>student</option><option>chairman</option> 
				</select>
			<br><input type="submit" name ="roleOk" value = "ok"/>
			</p></form>
			<%} else{%>
			
			
			<form action="signUp" method="post" id="frm">
			
			<table id="tab" align="center">
			<tr>
			  <td>User is</td>
			  <td><%=user.getRole() %> </td>
			</tr>
			<tr>
			<%if(user.getRole().equalsIgnoreCase("student")){ %>
			  <td>Registration No.</td>
			  <%}else  {%>
			  <td>Teacher Id
			  <%} %>
			  <td><input type="text" name="regId"size=27px/> </td>
			</tr>
			<%
			if(user.getRole().equals("student")){
			%>
			<tr>
			  <td>Serial No.</td>
			  <td><input type="text" name="serial"size=27px/> </td>
			</tr>
			<%} %>
			<tr>
			  <td>Name</td>
			  <td><input type="text" name="name"size=27px/> </td>		
			</tr>
			<tr>
			  <td>Email</td>
			  <td><input type="text" name="email"size=27px/></td>		
			</tr>
			<tr>
			  <td>Mobile Number</td>
			  <td><input type="text" name="mob" size=27px/></td>		
			</tr>
			
			<tr>
			<%if(user.getRole().equalsIgnoreCase("student")){ %>
			  <td>Admission Year</td>
			  <%}else  {%>
			  <td>Service Starting Date
			  <%} %>
			  <td><input type="text" name="yearOfRegistration"size=27px/> </td>
			</tr>
			
			
			<tr>
			  <td>Date of Birth</td>
			  <td>
			  	<select name="month">
			  	<%for(int i=1;i<13;i++){ %>
			  	<option><%=i %> </option>
			  	<%} %>
			  	</select>
			  	<select name = "date">
			  	<%for(int i=1;i<32;i++){ %>
			  	<option><%=i %> </option>
			  	<%} %>
			  	</select>
			  	<select name = "year">
			  	<%for(int i=1900;i<2014;i++){ %>
			  	<option><%=i %> </option>
			  	<%} %>
			  	</select>
			  (mm/dd/yyyy)</td>		
			</tr>
			</table>
			<p style="padding-top: 20px; padding-left: 140px" >
				<input  type="submit" name="infoBack" value="Back" />
				<input  type="submit" name="infoCancel" value="Cancel" />
				<%if(user1==null){ %>
				<input  type="submit" name="infoOkNew" value="submit" />
				<%}else if(user1.getRole().equalsIgnoreCase("chairman")){ %>
				<input  type="submit" name="infoOk" value="submit" />
				<%} %>
			</p>
			
			</form>
			
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
				<h2>Operations</h2>
				<ul>
					<%if(user1!=null){ %>
					<li><a href="listphotos">My Photos </a></li>
					<li><a href="success.jsp">My Profile </a></li>
					<li><a href="signUp?task=logOut">Log Out</a></li>
					<%}else{ %>
					<li><a href="login.jsp">Log in</a></li>
					<%} %>
					<li><a href="Mainpage.jsp">Homepage</a></li>										
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