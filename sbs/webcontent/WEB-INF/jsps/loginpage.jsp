<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
</head>
<body onload='document.f.j_username.focus();'>
	<h3>Login with Username and Password</h3>
	<c:if test="${param.error != null}"> 
		<p>Login Failed. Please enter correct username and password. </p>
	
	</c:if>
	
	<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' ></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</form>
	
<p><a href="${pageContext.request.contextPath}/frgtpasswrd">Forgot Password..??</a></p>	
	
</body>
</html>