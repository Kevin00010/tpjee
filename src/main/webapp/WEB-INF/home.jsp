<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>

 <c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
        <p>Vous êtes ${ sessionScope.prenom } ${ sessionScope.nom } !</p>
    </c:if>
    
<div
		class="container mx-auto w-100  justify-content-center align-items-center">
		<div class=" w-25 mx-auto mt-5  px-5 py-5  border border-primary rounded-3">
			<form action="/login_page.jsp">
				<div class="mb-3 mt-3">
					<label for="email" class="form-label">Email:</label> <input
						type="email" class="form-control" id="email"
						placeholder="Enter email" name="email">
				</div>
				<div class="mb-3">
					<label for="pwd" class="form-label">Password:</label> <input
						type="password" class="form-control" id="pwd"
						placeholder="Enter password" name="pswd">
				</div>
				<div class="form-check mb-3">
					<label class="form-check-label"> <input
						class="form-check-input" type="checkbox" name="remember">
						Remember me
					</label>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>

</body>
</html>