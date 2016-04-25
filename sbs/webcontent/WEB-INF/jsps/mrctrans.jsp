<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<%@page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript" type="text/javascript">
function noBack() 
{
	document.getElementById("start").value="1234";
}

</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Register new User/ Create Account</title>
</head>
<body>
	Enter the user account number and amount to be charged from user
	<br />
	<sf:form method="post" action="${pageContext.request.contextPath}/domrctrans"
		commandName="merchanttransaction">
        
        <c:out value="${errortransaction}"></c:out>
		
		<table>
		     <%-- <tr>
				<td class="label">OTP</td>
				<td><sf:input class="control" path="otp" name="otp"
						type="text" value="${otp}" readonly="true" /><br /></td>
			</tr>  --%>
			<tr>
				<td class="label">Customer Account number</td>
				<td><sf:input class="control" path="customeraccountnumber" name="customeraccountnumber"
						type="text"  /><br /> <sf:errors path="customeraccountnumber"
						cssClass="error" ></sf:errors></td>
			</tr>

			<tr>
				<td class="label">Amount </td>
				<td><sf:input class="control" path="amount" name="amount"
						type="text" /><br /> <sf:errors path="amount" cssClass="error"></sf:errors></td>
			</tr>
			
			
	     	<tr>
				<td class="label"></td>
				<td><input class="control" value="submit" type="submit" onclick="this.disabled=true; this.value='Please Wait...';"  /></td>
			</tr>
			
				
			
		</table>

	</sf:form>


</body>
</html>