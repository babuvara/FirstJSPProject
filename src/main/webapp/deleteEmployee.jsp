<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Employee</title>
</head>
<body>
	<%
	Integer result = (Integer) request.getAttribute("dltemp");
	%>
	<div> Delete Employee : <%=result%>
	</div>
</body>
</html>