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
			<h1 class="title">Mark Insertion </h1>
			<h3>(Insert marks out of 100 for each sector)</h3>
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
				ArrayList<String>sectors = cs.getCrs().nameOfMarkingSectors();
				%>				
				<form action="teacherOperation" method="post" id="frm">
				<table id= "tab">
				<tr>
				<td>Reg. Id</td>
				<%
				for(int i=0;i<sectors.size();i++){
					System.out.println(sectors.get(i));
					%>
					<td><%=sectors.get(i) %></td>
					<%
				}
				
			
				
				%>
				</tr>
				<%
				ArrayList<students>stus = cs.getSem().getStus();
				for(int j=0;j<stus.size();j++){
					%>
					<tr>
					<td><%=stus.get(j).getReg_id() %></td>
					<%
					for(int i=0;i<sectors.size();i++){
						
						String name = ""+j+","+i;
						System.out.println(name);
						%>
						<td><input type="text" name="<%=name %>" size=10px/></td>
						<%
					}

					%>
					</tr>
					
					<%
				}
				%>
				</table>
				<p style="padding-top: 20px; padding-left: 140px" >
				<input  type="submit" name="calculateMarks" value="Submit" />
				</p>
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