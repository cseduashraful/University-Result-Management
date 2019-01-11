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
csAssignment cs = (csAssignment) session.getAttribute("cs");
%>

<!-- start header -->
<div id="header">
	<h1>University of Dhaka</h1>
	<p> Department of Computer Science and Engineering</p>
</div>
<!-- end header -->
<!-- start page -->
<%if(user.getRole().equalsIgnoreCase("teacher")) {%>
<div id="page">
	<!-- start content -->
			<h1 class="title">Marks and Grades</h1>
			  <%if(photoString!=null) {%>
			        <h2><%=photoString %></h2>
			        <%
			        session.setAttribute("photoString",null);
			        } %>
			<br>
			<br>
			<div class="entry">
				<h2><%=cs.getSem().getSemName() %>:::Session: <%=cs.getSem().getSemSession() %></h2>
				<h3><%=cs.getCrs().getCourseName() %>:::Code: <%=cs.getCrs().getCourseId() %></h3>
				<%
				ArrayList<students>stus = cs.getSem().getStus();
				%>				
				<form action="teacherOperation" method="post" id="frm">
				<table id= "tab">
				<tr>
				<td>Reg. Id</td><td>Total Marks</td><td>Grade</td><td>Grade Point</td>
				</tr>
				<%
			
				for(int j=0;j<stus.size();j++){
					%>
					<tr>
					<td><%=stus.get(j).getReg_id() %></td>
					<% 
						markDistributionSystem mds = dbManager.extractMarks(cs.getCrs().getCourseId(),stus.get(j).getReg_id(),cs.getSem().getSemName(),cs.getSem().getSemSession());
						double tMarks = mds.getTotalMarks();
						tMarks = Math.ceil(tMarks);
						double gradePoint = gradeCalculation.getInstanceOfGradeCalculation().gradePoint(tMarks);
						String Grade = gradeCalculation.getInstanceOfGradeCalculation().grade(gradePoint);
					%>
					<td><%=tMarks %></td>
					<td><%=Grade %></td>
					<td><%=gradePoint %></td>
					</tr>
					
					<%
				}
				%>
				</table>
				</form>
				
		<br><a href="success.jsp">Back</a>
<br><a href="listphotos">My Photos </a>
<br><a href="signUp?task=logOut">Log Out</a>
	</div>
<%}else{ %>
<h1>This not a valid link for you</h1>
<br><a href="success.jsp">Back</a>
<br><a href="listphotos">My Photos </a>
<br><a href="signUp?task=logOut">Log Out</a>
	
<%} %>

</div>
<!-- end page -->
<div id="footer">
	<p>Design by Fatema Tuj Jahura Jamy</p>
</div>
<div align=center>Oracle Support: Md. Salman Ahmed.....Java Support: Md. Ashraful Islam</div>
</body>
</html>