<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/staticres/css/main.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot/Reset Your Password</title>
</head>
<body>
<sf:form id="frgtpasswrd" method="post"
		action="${pageContext.request.contextPath}/frgt1"
		commandName="registeruser">

		<table align="left" class="formtable" >
			<tr>
				<td class="label">User Name:</td>
				<td><sf:input class="control" path="username" name="username"
						type="text" /><br />
					<div>
						<sf:errors path="username" cssClass="error"></sf:errors>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="submit" type="submit" /></td>
			</tr>
			</table>
			</sf:form>

</body>
</html>