 taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml"
	xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html" />

<title>Edit User</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">

		<div class="row main">
			<div class="col-lg-3 description"></div>
			
			<div class="col-lg-3 description">
				<form action="${contextPath}/customer/purchase/purchaseHistory" method="get">
				<button type="submit">Purchase History</button></form>
			</div>

			<div class="col-lg-7">
				<div id="logbox">
					<form:form method="POST" modelAttribute="userForm"
						class="form-signin" action="${contextPath}/customer/update">
						<h2 class="form-signin-heading">Update Account</h2>
						First Name
						<spring:bind path="firstName">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="firstName"
									value="${currentUser.firstName}" class="form-control"
									placeholder="First Name"></form:input>
								<form:errors path="firstName"></form:errors>
							</div>
						</spring:bind>
						Last Name
						<spring:bind path="lastName">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="lastName"
									value="${currentUser.lastName}" class="form-control"
									placeholder="Last Name"></form:input>
								<form:errors path="lastName"></form:errors>
							</div>
						</spring:bind>
						Address:
						<spring:bind path="address">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="address"
									value="${currentUser.address}" class="form-control"
									placeholder="Address"></form:input>
								<form:errors path="address"></form:errors>
							</div>
						</spring:bind>
						Debit Card:
						<spring:bind path="debitCard">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="debitCard"
									placeholder="Debit Card" class="form-control"
									value="${currentUser.debitCard}"></form:input>
								<form:errors path="debitCard"></form:errors>
							</div>
						</spring:bind>

						<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
						<div class="text-center"></div>
					</form:form>
				</div>
			</div>
		</div>
	</div>


</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</html>