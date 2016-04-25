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
Encrypted Value is 
 <c:out value="${encryptedkey}"></c:out>
<br> </br>
 click here to return home page
<p><a href="${pageContext.request.contextPath}/afterlogin">click below to start a new transaction</a></p>

</body>
</html>