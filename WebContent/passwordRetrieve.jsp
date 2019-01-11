<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Retrieve Password</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
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
			<h1 class="title">Insert your username</h1>
			<br>
			<br>
			<div class="entry">
			<form action="signUp"  method="post">
				<table align="center" id ="tab">
				<tr>
				<td>Username:</td>
				<td><input type="text" name="reg_id" size = 20px/></td>
				</tr>
				<tr>
				<td></td>
				<td style="padding-left: 47px">
				<input type="submit" name ="signUp" value = "retrieve"/>
				</td>
				</tr>
				</table>
				<br>
			</form>
			</div>
		</div>
	</div>
	<!-- end content -->
	<!-- start sidebar -->
	<div id="sidebar">
		<ul>
			<li>
				<h2>Categories</h2>
				<ul>
					<li>Not a member yet? <a href="#">Sign Up</a></li>
					<li>Go  back to <a href="MainPage.jsp">Homepage</a></li>
					<li>Password remembered? <a href="login.jsp">Log in</a></li>
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