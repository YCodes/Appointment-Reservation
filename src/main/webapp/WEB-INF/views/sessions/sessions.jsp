<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../includes/header.jsp"%>
<title>List All Sessions</title>
</head>
<body>
	<%@ include file="../includes/navbar.jsp"%>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h2>Available Sessions</h2>
				<p>Session For TM Checking</p>
			</div>
			<a href="<spring:url value="/sessions/create" />"
				class="btn btn-primary btn-mini pull-right">Add a new one</a>

		</div>
	</section>

	<section class="container">
		<c:if test="${ not empty message}">
			<div class="alert alert-danger alert-dismissible fade in"
				role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				${message}
			</div>
		</c:if>
		<div class="row">
			<c:forEach items="${sessions}" var="session">
				<div class="col-sm-6 col-md-4" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h4>Session Date - ${session.date}</h4>
							<hr />
							<p>Session StartTime - ${session.startTime}</p>
							<p>Session Duration - ${session.duration}</p>
							<p>Session Location - ${session.location}</p>
							<p>Seat Capacity - ${session.capacity}</p>
							<p>Available - ${session.occupied}</p>
							<p>Session Counsellor - ${session.person.fullName}</p>
							<a href="<spring:url value="/sessions/${session.id}" />"
								class="btn btn-primary  btn-sm"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								Edit
							</a> <a href="<spring:url value="/sessions/delete/${session.id}" />"
								class="btn btn-danger btn-sm delete"
								data-confirm="Are you sure to delete this item?"> <span
								class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								Delete
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
	<%@ include file="../includes/footer.jsp"%>
</body>
</html>
