<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit Book</title>
</head>
<body>
	<h1>Edit Book</h1>
	<form method="post" action="${pageContext.request.contextPath}/books/update" >
		<input type="hidden" name="id" value="${book.id}" />
		<label for="title">Title:</label>
		<input type="text" id="title" name="title" value="${book.title}" />
		<br />
		<label for="author">Author:</label>
		<input type="text" id="author" name="author" value="${book.author}" />
		<br />
		<label for="price">Price:</label>
		<input type="text" id="price" name="price" value="${book.price}" />
		<br />
		<label for="qty">Qty:</label>
		<input type="text" id="qty" name="qty" value="${book.qty}" />
		<br />
		<input type="submit" value="Save" />
		<a href="${pageContext.request.contextPath}/books/list">Cancel</a>
	</form>
</body>
</html>
