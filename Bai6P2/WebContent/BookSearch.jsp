<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Store Application</title>
</head>
<body>
	<center>
		<h1>Books Management</h1>
	</center>
	<div align="center">
		<form action="search" method="post">
			<input type="text" name="keyword" size="45"> <input
				type="submit" value="Search" />
		</form>
	</div>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Books</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${searchBookList}">
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td>---</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>