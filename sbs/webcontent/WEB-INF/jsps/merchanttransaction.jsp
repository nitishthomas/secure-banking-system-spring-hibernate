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
<title>Merchant Transactions</title>
</head>
<body>
List of all The Transactions for Merchant
<br />
<form id="admintransactionrequest" method="post"
		action="${pageContext.request.contextPath}/adminrequestaccept">

	<table class="formtable">
		<tr>
				<td>Transaction ID </td>
				<td>Username </td>
				<td>Account number </td>
				<td> Sender Account no.</td>
				<td>Receiver Account no. </td>
				<td>Amount </td>
				<td> Date</td>
				<td> Transaction Status</td>
				<td> Set Status</td>
				<td> Set Status</td>
				<td> Submit</td>
			</tr>
			
		<c:forEach var="admintransactionrequest" items="${admintransactionrequest}" >
			<tr align="left">
				 <td><c:out value="${admintransactionrequest.transactionid}"> </c:out></td> 
				<td><c:out value="${admintransactionrequest.username}"></c:out></td>
				<td><c:out value="${admintransactionrequest.accountnumber}"></c:out></td>
				<td><c:out value="${admintransactionrequest.senderaccountnumber}"></c:out></td>
				<td><c:out value="${admintransactionrequest.receiveraccountnumber}"></c:out></td>
				<td><c:out value="${admintransactionrequest.amount}"></c:out></td>
				<td><c:out value="${admintransactionrequest.date}"></c:out></td>
				<td><c:out value="${admintransactionrequest.transactionstatus}"></c:out></td>
				<td><input type="radio" name="status" value="decline${admintransactionrequest.transactionid}"/>Decline</td>
				<td><input type="radio" name="status" value="submit${admintransactionrequest.transactionid}" />Submit</td>	
				<td> <input  class="control" value="submit" type="Submit" /> </td>
			</tr>
		</c:forEach>
	</table>
		</form>
	
</body>
</html>