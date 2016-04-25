<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/staticres/css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript">
function noBack()                   
{
	window.history.forward();
}

window.history.forward(1);
</script>
<script>
<%
response.setHeader("Pragma","no-cache");  
response.setHeader("Cache-Control","no-store");  
response.setHeader("Expires","0");  
response.setDateHeader("Expires",-1);  
%> 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="noBack();"  onunload="">
Welcome, User: <c:out value="${login.username}"></c:out>  <sec:authorize access="isAuthenticated()">
<p align="right"><a href="<c:url value='/j_spring_security_logout' />">Log out</a></p>
</sec:authorize>

<br />
<br />
<br />
<br />
<table align="centre" cellspacing="20" border="0" width="700" bgcolor="aqua">
<tr>
	<td> <sec:authorize access="hasRole('admin')">
		<p><a href="<c:url value="/admin" />">Admin Home</a></p>
		</sec:authorize><br />
	</td>

	<td><p><a href="${pageContext.request.contextPath}/transfer">Click here to access bank services..</a></p>
		
	</td>
	<td><p><a href="${pageContext.request.contextPath}/externalusertransaction">User Transactions</a></p>
	</td>
	<td>
	 <sec:authorize access="hasRole('merchant')">
		<p><a href="${pageContext.request.contextPath}/merchant">Click here to access Merchant services..</a></p>
	</sec:authorize>
</td>
</tr>
</table>

<c:out value="${username}"></c:out><br />	
<table class="formtable" border="1" title="User Account Info">
		<tr>
			<td>User Name:<td/>
			<td><c:out value="${login.username}"></c:out> </td> 
		</tr>
		<tr>
			<td>First Name:<td/>
			<td><c:out value="${login.firstname}"></c:out> </td> 
		</tr>
		<tr>
			<td>Last Name:<td/>
			<td><c:out value="${login.lastname}"></c:out> </td> 
		</tr>
		<tr>
			<td>Email:<td/>
			<td><c:out value="${login.email}"></c:out> </td> 
		</tr>
		<tr>
			<td>Date Of Birth<td/>
			<td><c:out value="${login.dob}"></c:out> </td> 
		</tr>
		<tr>
			<td>Gender:<td/>
			<td><c:out value="${login.gender}"></c:out> </td> 
		</tr>
		<tr>
			<td>Phone:<td/>
			<td><c:out value="${login.phone}"></c:out> </td> 
		</tr>
		<tr>
			<td>User Account Number:<td/>
			<td><c:out value="${useraccount.accountnumber}"></c:out> </td> 
		</tr>
		<tr>
			<td>Balance:<td/>
			<td><c:out value="${useraccount.balance}"></c:out> </td> 
		</tr>
	
	</table> <br />



</body>
</html>