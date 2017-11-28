<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../includes/header.jsp" %>
<title>List All Persons</title>
</head>
<body>
	<%@ include file="../includes/navbar.jsp" %>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Our People</h1>
				<p>All of the contributors to our Community!!!</p>

				<div class="pull-left">
					<h3>${SpecialBlurb}</h3>
				</div>
			</div>
			<a href="<spring:url value="/persons/create" />"
				class="btn btn-primary btn-mini pull-right">Add a new one</a>

		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${persons}" var="person">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h4>First Name - ${person.firstName}</h4>
							<h4>
								Last Name - ${person.lastName} <a
									href="<spring:url value="/persons/${person.id}" />"
									class="btn btn-primary  btn-mini  ">View</a>
							</h4>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
	<%@ include file="../includes/footer.jsp"%>
</body>
</html>
