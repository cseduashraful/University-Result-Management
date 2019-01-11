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
ArrayList<semester>sems = dbManager.fetchSemestersWhereStatusIs("AdmissionNotStarted");
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
			<h1 class="title">Course Assignment</h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
				<h3>Choose the semester to add course:</h3>
				<form action="signUp" method="post" id="frm">
				<select style="font-size: large;" name="semester" >
				<%for(int i = 0;i<sems.size();i++){ 
				semester sem = sems.get(i);
				%>
				<option value = "<%=i %>" ><%=sem.getSemName()%>,<%=sem.getSemSession() %></option>
				<%} %>
				</select>
				<br>
				<input type="submit" name = "selectSem" value="Submit" />
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