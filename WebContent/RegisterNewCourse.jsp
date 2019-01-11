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
course crs=(course)session.getAttribute("crs");
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
			<h1 class="title">Give information about this <%=crs.getType() %> course</h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
				<form action="signUp" method="post">
				<table id="tab" >
				<tr>
				  <td>Course Name</td>
				  <td><input type="text" name="courseName"size=20px/> </td>		
				</tr>
				<tr>
				  <td>Course Code</td>
				  <td><input type="text" name="courseId"size=20px/> </td>		
				</tr>
				<tr>
				  <td>Credit</td>
				  <td><input type="text" name="courseCredit"size=20px/> </td>		
				</tr>
				<tr>
				  <td>Course Area</td>
				  <td><input type="text" name="courseArea" size=20px/> </td>		
				</tr>
				<tr>
				<td></td>
				<td></td>
				</tr>
				<tr>
				<td></td>
				<td></td>
				</tr>
				</table>
				<h2>Choose Marks Distribution Criteria</h2>
				<table id="tab" width="300px">
				<tr><td></td> <tr>
				<%
				ArrayList <String> sectors = markDistributionSystem.getAvailableSectors();
				for(int i=0;i<sectors.size();i++){
					%>
					<tr><td> 
					<input  type="checkbox" value="<%=sectors.get(i) %>" name="markingSector"/><%=sectors.get(i) %>
					</td></tr>
					<%
				}
				%>
				</table>
				<p style="padding-top: 20px; padding-left: 140px" >
				<input  type="submit" name="courseOk" value="Add Course"/>
				</p>
				</form>			 
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