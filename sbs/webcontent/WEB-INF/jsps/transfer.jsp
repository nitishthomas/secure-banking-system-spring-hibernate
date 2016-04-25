<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/staticres/css/main.css" rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer Money</title>
</head>
<body>
TRANSACTION PAGE:<br/>
PLease Enter the details below to perform a Transaction
<c:out value="${errortransaction}"></c:out>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/dotransfer1"
		commandName="Externalusertransaction">

		<table class="formtable">
			<tr>
				<td class="label">Receiver Account number:</td>
				<td><sf:input class="control" path="receiveraccountnumber"
						name="receiveraccountnumber" type="text" /><br /> <sf:errors
						path="receiveraccountnumber" cssClass="error"></sf:errors></td>
			</tr>

			<tr>
				<td class="label">Amount to Transfer:</td>
				<td><sf:input class="control" path="amount" name="amount"
						type="text" /><br /> <sf:errors path="amount" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="submit" type="submit" /></td>
			</tr>
		</table>

	</sf:form>


</body>
</html>