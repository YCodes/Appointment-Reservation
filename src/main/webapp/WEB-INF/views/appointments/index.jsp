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
				<h2>${customer.fullName}'s Appointments</h2>
				<p>Appointment For TM Checking</p>
			</div>
			<a href="<spring:url value="/appointments/create" />"
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
			<c:forEach items="${appointments}" var="appointment">
				<div class="col-sm-6 col-md-4" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h4>Appointment Date - ${appointment.session.date}</h4>
							<hr />
							<p>Session StartTime - ${appointment.session.startTime}</p>
							<p>Session Duration - ${appointment.session.duration}</p>
							<p>Session Location - ${appointment.session.location}</p>
							<p>Seat Capacity - ${appointment.session.capacity}</p>
							<p>Available - ${appointment.session.occupied}</p>
							<p>Session Counsellor -
								${appointment.session.person.fullName}</p>
							<a
								href="<spring:url value="/appointments/cancel/${appointment.id}" />"
								class="btn btn-danger btn-sm delete"
								data-confirm="Are you sure you want to cancel this appointment?">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								Cancel
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
