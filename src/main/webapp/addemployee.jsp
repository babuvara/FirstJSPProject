<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="com.chainsys.springmvc.pojo.Employee"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<h1>Added</h1>
	<%
	int result = (int) request.getAttribute("addemp");
	%>
	<div>
		AddEmployee:<%=result%></div>

</body>
</html>