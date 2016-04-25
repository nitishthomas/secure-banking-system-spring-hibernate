
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

<body onload="noBack();"  onunload="" class="header">

<table align="centre" cellspacing="20" border="0" width="700" bgcolor="grey">
<tr>
<td> <a href="${pageContext.request.contextPath}/afterlogin">Click here for login Page.</a> </td>
<td> <a href="${pageContext.request.contextPath}/afterlogin">SECURE BANKING SYSTEM</a> </td>
<td> <a href="${pageContext.request.contextPath}/">Home Page</a> </td>

<td>
<sec:authorize access="isAuthenticated()">
<a href="<c:url value='/j_spring_security_logout' />">Log out</a>
</sec:authorize>
</td>
</tr>
</table>