<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../includes/header.jsp"%>
<title>Create Person</title>
</head>
<body>
	<%@ include file="../includes/navbar.jsp"%>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Valued Members</h1>
				<p>Add a new one
				<p>
			</div>
		</div>
	</section>
	<section class="container">

		<form:form modelAttribute="newPerson" class="form-horizontal">
			<fieldset>
				<legend>Add new member</legend>

				<form:errors path="*" cssClass="alert alert-danger" element="div" />

				<div class="form-group">
					<label class="control-label col-lg-2" for="firstName">First
						Name</label>
					<div class="col-lg-10">
						<form:input id="firstNamee" path="firstName" type="text"
							class="form-control" />
						<form:errors path="firstName" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="lastName">Last
						Name</label>
					<div class="col-lg-10">
						<form:input id="lastNamee" path="lastName" type="text"
							class="form-control" />
						<form:errors path="lastName" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="email">Email
						Address</label>
					<div class="col-lg-10">
						<form:input id="email" path="emailAddress" type="email"
							class="form-control" />
						<form:errors path="emailAddress" cssClass="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="userName">User
						Name</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="username" path="username" type="text"
								class="form-control" />
							<form:errors path="username" cssClass="text-danger" />
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="control-label col-lg-2" for="password">Password</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:password id="password" path="password" class="form-control" />
							<form:errors path="password" cssClass="text-danger" />
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="control-label col-lg-2" for="verifyPassword">Verify
						Password</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:password id="verifyPassword" path="verifyPassword" class="form-control" />
							<form:errors path="verifyPassword" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="verifyPassword">
						Role</label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:select class="form-control" id="sel1"
								path="authority[0].authority">
								<option value="ROLE_USER">User</option>
								<option value="ROLE_COUNSELOR">Counselor</option>
							</form:select>

							<form:errors path="authority[0].authority" cssClass="text-danger" />
						</div>
					</div>
				</div>

				<form:hidden path="enabled" value="TRUE" />


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
