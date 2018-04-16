<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Checkout</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<form id="logoutForm" method="POST" action="${contextPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<div class="col-lg-3">
			<c:forEach items="${cartList}" var="book">
				<div class="panel panel-default">
					<div class="panel-body">
						<c:if test="${pageContext.request.isUserInRole('ROLE_CUSTOMER')}">
							<b><a href=${contextPath}/customer/book/${book.id}>${book.title}</a></b>
							<br>
						</c:if>
						<b>Author:</b>${book.author}<br>
						<b>Genre:</b>${book.genre}<br>
						<b>Price:</b> &euro;${book.price}<br>
						<div id="mainwrap"></div>
					</div>

				</div>
			</c:forEach>
		</div>
		<div class="col-lg-4">
			<div id="logbox">
				<form:form method="POST" modelAttribute="userForm"
					class="form-signin" action="${contextPath}/customer/cart/checkout">
					<h2 class="form-signin-heading">Update Account</h2>
					
						Username
						<spring:bind path="username">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="username" value="${userForm.username}"
								class="form-control" placeholder="username"></form:input>
							<form:errors path="username"></form:errors>
						</div>
					</spring:bind>
					
						Address
						<spring:bind path="address">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="address"
								value="${userForm.address}" class="form-control"
								placeholder="Address"></form:input>
							<form:errors path="address"></form:errors>
						</div>
					</spring:bind>
						
						Debit Card 
						<spring:bind path="debitCard">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="debitCard"
								placeholder="${userForm.debitCard}" class="form-control"
								value="${userForm.debitCard}"></form:input>
							<form:errors path="debitCard"></form:errors>
						</div>
					</spring:bind>

					<button class="btn btn-lg btn-primary btn-block" type="submit">Checkout</button>
					<div class="text-center"></div>
				</form:form>
			</div>
		</div>
		<div class="col-lg-5"></div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</html>