<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Employees</title>
</head>
<body>
	<form action="Employees" method="post">
		<!-- -need to mention method=post -->
		<center>
			<div>
				ID: <input type='text' name='id'>
			</div>
			<div>
				<input type='submit' value='DELETE' name='submit'>
			</div>

		</center>
	</form>
</body>
</html>