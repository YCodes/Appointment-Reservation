<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="includes/header.jsp"%>
<title>Welcome</title>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h2>${greeting}</h2>
				<p>${tagline}</p>
				<p>
					<security:authorize access="isAuthenticated()">
  						Welcome, ${user.fullName }
					</security:authorize>
			</div>

			<div class="container">
				<security:authorize access="isAnonymous()">
					<a href="<spring:url value='/login' />"
						class="btn btn-success pull-right"> Login</a>
				</security:authorize>
			</div>
		</div>
	</section>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>
