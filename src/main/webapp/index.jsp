<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>FJBs WAP</title>
</head>
<body>

	<jsp:include page="views/header.html" />
	<section class="login">
		<form action="login.do" method="post">
			<input hidden="action" id="action" name="action" value="login">


			<c:if test="${not empty error}">
				<h3>
					<c:out value='${error}' />
				</h3>
			</c:if>
			<label for="username">Username:</label> <input required type="text"
				name="username" id="username"> <label for="password">Password:</label>
			<input required type="password" name="password" id="password">
			<label for="cookies"> Cookies? </label> <select name="cookies"
				id="cookies">
				<option value="no">No, thank you.</option>
				<option value="yes">I AM A COOKIE MONSTER</option>
			</select> <input type="submit" value="Login">
		</form>
		<a href="views/newuser.jsp">New user</a> <a href="lostpassword.jsp">Lost
			password</a>
	</section>
	<jsp:include page="views/footer.jsp" />
</body>
</html>