<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	This is a test page...
	<br />
	<table cellspacing="2" border="1" align="center">
		<c:forEach var="login" items="${login}">
			<tr align="left">
				<td><c:out value="${login.username}"></c:out></td>
				<td><c:out value="${login.password}"></c:out></td>
				<td><c:out value="${login.firstname}"></c:out></td>
				<td><c:out value="${login.lastname}"></c:out></td>
				<td><c:out value="${login.email}"></c:out></td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>