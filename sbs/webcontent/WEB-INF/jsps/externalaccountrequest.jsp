<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>External account creation for bank customers.</title>
</head>
<body>
Account creation request for bank customers.
<br />
<sf:form id="externalaccountrequest" method="post"
		action="${pageContext.request.contextPath}/externalrequestaccept"
		commandName="externalaccountrequest">
	<table cellspacing="2" border="1" align="center" class="formtable">
		<c:forEach var="registeruser" items="${registeruser}">
			<tr align="left">
				<td><c:out value="${registeruser.username}"></c:out></td>
				<td><c:out value="${registeruser.firstname}"></c:out></td>
				<td><c:out value="${registeruser.lastname}"></c:out></td>
				<td><c:out value="${registeruser.password}"></c:out></td>
				<td><c:out value="${registeruser.email}"></c:out></td>
				<td><c:out value="${registeruser.ssn}"></c:out></td>
				<td><c:out value="${registeruser.dob}"></c:out></td>
				<td><c:out value="${registeruser.gender}"></c:out></td>
				<td><c:out value="${registeruser.phone}"></c:out></td>
				<td><c:out value="${registeruser.activestatus}"></c:out></td>
				<td><input type="radio" name="status" value="decline${registeruser.id}"/>Decline</td>
				<td><input type="radio" name="status" value="submit${registeruser.id}" />Submit</td>	
				<td> <input  class="control" value="submit" type="Submit" /> </td>
			</tr>

		</c:forEach>
	</table>
	</sf:form>

</body>
</html>