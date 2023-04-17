<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>

    <head>
        <title>Add Book</title>
    </head>

    <body>
        <h1>Add Book</h1>
        <form method="post" action="${pageContext.request.contextPath}/books/add">
            <table>
                <tr>
                    <td>Title:</td>
                    <td><input type="text" name="title" /></td>
                </tr>
                <tr>
                    <td>Author:</td>
                    <td><input type="text" name="author" /></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><textarea name="price"></textarea></td>
                </tr>
                <tr>
                    <td>QTY:</td>
                    <td><input type="text" name="qty" /></td>
                </tr>
               
                <tr>
                    <td><input type="submit" value="Add Book" /></td>
                    <td><a href="${pageContext.request.contextPath}/books/list">Cancel</a></td>
                </tr>
            </table>
        </form>
    </body>

    </html>