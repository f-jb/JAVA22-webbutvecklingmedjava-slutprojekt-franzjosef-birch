<%@page import="accountManagement.Account"%>
<%@page import="accountManagement.AccountsManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<jsp:useBean id="account" type="accountManagement.Account"
	scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.html" />
<section class="search">
	<h1>Welcome, ${account.name}</h1>

	<form action="search.do" method="POST">
		<label for="city">Enter city</label> <input id="city" name="city"
			type="text" /> <input type="submit" />
	</form>
	<c:if test="${not empty error}">
		<h3>
			<c:out value='${error}' />
		</h3>
	</c:if>

	<section class="result">
		<c:if test="${not empty weatherdata}">
			<h3>${weatherdata[0].name}</h3>
			<img
				src="http://openweathermap.org/img/wn/${weatherdata[0].icon}@2x.png">
			<p>Temperature: ${weatherdata[0].temperature} °C</p>
			<p>Clouds: ${weatherdata[0].clouds}</p>
			<p>Pressure: ${weatherdata[0].pressure}</p>

		</c:if>
	</section>
</section>
	<hr>


	<section class="searches">
		<c:if test="${weatherdata.size() > 1 }">
			<c:forEach var="listElement" items="${weatherdata}"
				end="${weatherdata.size() -1 }" begin="1">
				<div>
					<h3>${listElement.name}</h3>
					<img
						src="http://openweathermap.org/img/wn/${listElement.icon}@2x.png">
					<p>Temperature: ${listElement.temperature} °C</p>
					<p>Clouds: ${listElement.clouds}</p>
					<p>Pressure: ${listElement.pressure}</p>
				</div>
			</c:forEach>
		</c:if>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>