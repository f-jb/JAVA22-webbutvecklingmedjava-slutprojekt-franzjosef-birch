<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Create an account</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="header.html" />

<h1>Create an account</h1>
<form action="login.do" method="post">
<input hidden="action" id="action"  name="action" value="newuser">
<jsp:include page="userform.jsp" />
</form>
<jsp:include page="footer.jsp" />
</body>
</html>