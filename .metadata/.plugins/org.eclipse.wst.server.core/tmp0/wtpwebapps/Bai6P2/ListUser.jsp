<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List User</title>
</head>
<body>
	<center>
		<h1>Users Management</h1>
		<h2>---</h2>
	</center>
	<div align="center">
		<form action="list" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>List of Users</h2>
				</caption>
				<tr>
					<th>ID</th>
					<th>Username</th>
					<th>Password</th>
					<th>Action</th>
				</tr>
				<c:forEach var="user" items="${listUser}">
					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><c:out value="${user.userName}" /></td>
						<td><c:out value="${user.password}" /></td>
						<td>---</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
</body>
</html>