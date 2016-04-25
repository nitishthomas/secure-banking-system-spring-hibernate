<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	Enter the user account number and amount to be charged from user
	<br />
	<sf:form method="post" action="${pageContext.request.contextPath}/domrctrans"
		commandName="merchanttransaction">
        
        <c:out value="${errortransaction}"></c:out>
		click below to start a new transaction
		<p><a href="${pageContext.request.contextPath}/mrctrans">click below to start a new transaction</a></p>
		<%-- <table>
		     <tr>
				<td class="label">OTP</td>
				<td><sf:input class="control" path="otp" name="otp"
						type="text" value="${otp}" readonly="true" /><br /></td>
			</tr> 
			
			
	     	<tr>
				<td class="label"></td>
				<td><input class="control" value="submit" type="submit" /></td>
			</tr>
			
				
			
		</table> --%>

	</sf:form>


</body>
</body>
</html>