<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Emp</title>
</head>
<body style="background-color: powderblue;">
	<form action="Employees" method="post">
		<!-- -need to mention method=post -->
		<h1>ADDING EMPLOYEE DETAILS</h1>
		<center>
			<div>
				ID: <input type='text' name='id'>
			</div>
			<div>
				<!--  key should be in lower case -->
				First Name: <input type='text' name='fname'>
			</div>
			<div>
				<!--  key should be in lower case -->
				Last Name: <input type='text' name='lname'>
			</div>
			<div>
				Email: <input type='text' name='email'>
			</div>
			<div>
				Hire Date: <input type='text' name='hdate'>
			</div>
			<div>
				Job_Id: <input type='text' name='jobid'>
			</div>
			<div>
				Salary: <input type='text' name='salary'>
			</div>
			<div>
				<input type='submit' value='ADD' name='submit'>
			</div>
		</center>
	</form>
</body>
</html>