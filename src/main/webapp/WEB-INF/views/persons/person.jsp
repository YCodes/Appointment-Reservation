<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../includes/header.jsp"%>
<title>Person Details</title>
</head>
<body>
	<%@ include file="../includes/navbar.jsp"%>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>One of our People</h1>
				<p>An important contributor to our Community!!!</p>
			</div>
			<a href="<spring:url value="/persons/create" />"
				class="btn btn-primary btn-mini pull-right">Add a new one</a>


			<div class="pull-left">
				<h3>${SpecialBlurb}</h3>
			</div>

		</div>
	</section>

	<section class="container">
		<div class="row">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>First Name - ${person.firstName}</h3>
						<h3>Last Name - ${person.lastName}</h3>

					</div>
				</div>
			</div>

		</div>
	</section>
	<%@ include file="../includes/footer.jsp"%>
</body>
</html>
