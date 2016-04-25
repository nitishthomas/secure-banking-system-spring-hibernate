<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/staticres/script/jquery.js">
</script>	
<link href="${pageContext.request.contextPath}/staticres/css/main.css" rel="stylesheet" type="text/css">

<script type="text/javascript">

$(document).ready(function() {
	
	$("#password").keyup(checkpass);
	$("#passconfirm").keyup(checkpass);
	$("#register").submit(canSubmit);
	
	
	function canSubmit(){
		var password = $("#password").val();
		var passconfirm = $("#passconfirm").val();
		if (password != passconfirm) {
			alert("Passwords do not match..!!");
			return false;
		}
		else{
			return true;
		}
	}

	function checkpass() {
		var password = $("#password").val();
		var passconfirm = $("#passconfirm").val();
		if (password.length < 3 || passconfirm.length < 3) {
			return;
		}
		if (password == passconfirm) {
			$("#passworderror").text("Passwords Match");
			$("#passworderror").addClass("valid");
			$("#passworderror").removeClass("error");
		} 
		else {
			$("#passworderror").text("Passwords do not match");
			$("#passworderror").addClass("error");
			$("#passworderror").removeClass("error");
		}
	}

});

</script>
<title>Register new User/ Create Account</title>
</head>
<body>
	Hello Enter details here to create a new user...
	<br />
	<sf:form id="register" method="post"
		action="${pageContext.request.contextPath}/createaccountrequest"
		commandName="registeruser">

		<table align="left" class="formtable" >
			<tr>
				<td class="label">First Name:</td>
				<td><sf:input class="control" path="firstname" name="firstname"
						type="text" /><br />
					<div>
						<sf:errors path="firstname" cssClass="error"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label">Last Name:</td>
				<td><sf:input class="control" path="lastname" name="lastname"
						type="text" /><br />
					<div>
						<sf:errors path="lastname" cssClass="error"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Username:</td>
				<td><sf:input class="control" path="username" name="username"
						type="text" /><br />
					<div>
						<sf:errors path="username" cssClass="error"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input class="control" path="password" name="password"
						type="password" /><br />
				<div>
						<sf:errors id="password" path="password" cssClass="error"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label">Confirm Password:</td>
				<td><input id="passconfirm" class="control" name="confirmpass"
					type="password" /> 
				<div id="passworderror"></div></td>
			</tr>

			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" name="email"
						type="text" /><br />
				<div>
						<sf:errors path="email" cssClass="error"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label">SSN:</td>
				<td><sf:input class="control" path="ssn" name="ssn" type="text" /><br />
					<div>
						<sf:errors path="ssn" cssClass="error"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label">Date of Brith:</td>
				<td><sf:input class="control" path="dob" name="dob" type="text" /><br />
					<div>
						<sf:errors path="dob" cssClass="error"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label">Gender:</td>
				<td><sf:input class="control" path="gender" name="gender"
						type="text" /><br />
				<div>
						<sf:errors path="gender" cssClass="error"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label">Phone:</td>
				<td><sf:input class="control" path="phone" name="phone"
						type="text" /><br />
					<div>
						<sf:errors path="phone" cssClass="error"></sf:errors>
					</div></td>
			</tr>

			<tr>
				<td class="label"></td>
				<td><input class="control" value="submit" type="submit" /></td>
			</tr>
		</table>

	</sf:form>


</body>
</html>