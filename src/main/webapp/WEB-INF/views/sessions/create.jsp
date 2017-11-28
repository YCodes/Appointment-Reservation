<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../includes/header.jsp"%>
<title>Create Session</title>
</head>
<body>
	<%@ include file="../includes/navbar.jsp"%>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h2>Create Sessions</h2>
				<p>Add a new one
				<p>
			</div>
		</div>
	</section>
	<section class="container">

		<form:form modelAttribute="session" class="form-horizontal">
			<fieldset>

				<form:errors path="*" cssClass="alert alert-danger" element="div" />

				<div class="form-group">
					<label class="control-label col-lg-2" for="firstName">Date</label>
					<div class="col-lg-10">
						<form:input id="firstName" path="date" type="text"
							class="form-control" placeholder="mm/dd/yyyy" />
						<form:errors path="date" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="lastName">Start
						Time</label>
					<div class="col-lg-10">
						<form:input id="lastName" path="startTime" type="text"
							class="form-control" />
						<form:errors path="startTime" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="email">Duration</label>
					<div class="col-lg-10">
						<form:input id="email" path="duration" type="text"
							class="form-control" />
						<form:errors path="duration" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="userName">Location</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="username" path="location" type="text"
								class="form-control" />
							<form:errors path="location" cssClass="text-danger" />
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="control-label col-lg-2" for="password">Capacity</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="password" path="capacity" class="form-control" />
							<form:errors path="capacity" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="verifyPassword">
						Counsellor</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:select class="form-control" id="sel1" path="person.id">
								<%-- <form:option value="" label="Select Counselor" /> --%>
								<form:options items="${persons}" itemLabel="fullName"
									itemValue="id" />
							</form:select>
							<form:errors path="person.id" cssClass="text-danger" />
						</div>
					</div>
				</div>


				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary btn-block"
							value="Add" />
					</div>
				</div>

			</fieldset>
		</form:form>
	</section>

	<%@ include file="../includes/footer.jsp"%>
</body>
</html>
