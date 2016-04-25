<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Authorized Users Only.. 
<sec:authorize access="hasRole('admin')">
<p><a href="${pageContext.request.contextPath}/admintransactionrequest">Click here to check all pending transaction request</a></p>
</sec:authorize>
<sec:authorize access="hasRole('admin')">
<p><a href="${pageContext.request.contextPath}/externalaccountrequest">Click here to check all external users account creation request</a></p>
</sec:authorize>
</body>
</html>