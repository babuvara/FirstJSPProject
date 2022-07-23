<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Employee</title>
</head>
<body>
	<%
	Integer result = (Integer) request.getAttribute("updateemp");
	%>
	<div>Update Employee : <%=result%>
		
	</div>
</body>
</html>