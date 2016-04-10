<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<section id="content" class="cf">
	<!-- ajax loader -->
	<div id="loader"></div>
	<h2 class="head">Login</h2>
	<section id="login" class="cf">
		<nav>
			<ul>
				<li><a href="<c:url value="/login-linkedin" />" class="linkedin">Login with LinkedIn</a></li>
			</ul>
		</nav>
	</section>
</section>
</body>
</html>