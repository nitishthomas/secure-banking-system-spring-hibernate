<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html> --%>

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
	document.getElementById("start");
}

</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Register new User/ Create Account</title>
</head>
<body>
	Enter the user account number and amount to be charged from user
	<br />
	<form method="post" action="${pageContext.request.contextPath}/successfultrans">
        
       
		
		<table>
		    
			<tr>
				<td class="label">Transaction ID </td>
				<td><input class="control"  name="id"
						type="text"   id="id"/><br /></td>
			</tr>

			<tr>
				<td class="label">Encryption Key</td>
				<td><input class="control"  name="key"
						 id="key"/><br /></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="submit" type="submit" /></td>
			</tr>
		
			
		</table>

	</form>


</body>
</html>