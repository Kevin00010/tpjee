<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp"%>

<title>Home page</title>

</head>

<body class="app app-login p-0">
	<nav class="navbar navbar-expand-sm bg-white navbar-white">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link active" href="index"><h2>My Docs</h2></a>
      </li>
    
    </ul>
  </div>
</nav>
	<div class="row g-0 app-auth-wrapper">
		<div class="col-12 col-md-12 col-lg-12 auth-main-col text-center p-5">
			<div class="d-flex flex-column align-content-end">
				<div class="app-auth-body mx-auto">
					<div class="app-auth-branding mb-4">
						
						<!-- 					<a class="app-logo" href="index"><img class="logo-icon me-2" src="assets/images/app-logo.svg" alt="logo"></a></div> -->
						<h2 class="auth-heading text-center mb-5">Admin login space</h2>
						<div class="auth-form-container text-start">
							<div class="<c:out value="${ color}" />"
								role="<c:out value="${role}" />">
								<c:out value="${ message }" />
							</div>
							<form class="auth-form login-form" action="login" method="post">
								<div class="email mb-3">
									<label class="sr-only" for="signin-email">Email</label> <input
										id="signin-email" name="email" type="email"
										class="form-control signin-email" placeholder="Email address"
										required="required">
								</div>
								<!--//form-group-->
								<div class="password mb-3">
									<label class="sr-only" for="signin-password">Password</label> <input
										id="signin-password" name="password" type="password"
										class="form-control signin-password" placeholder="Password"
										required="required">
									<div class="extra mt-3 row justify-content-between">
										<div class="col-6">
											<div class="form-check">
												<input class="form-check-input" type="checkbox" value=""
													id="RememberPassword"> <label
													class="form-check-label" for="RememberPassword">
													Remember me </label>
											</div>
										</div>
										<!--//col-6-->
										<div class="col-6">
											<div class="forgot-password text-end">
												<a href="reset-password">Forgot password?</a>
											</div>
										</div>
										<!--//col-6-->
									</div>
									<!--//extra-->
								</div>
								<!--//form-group-->
								<div class="text-center">
									<button type="submit"
										class="btn app-btn-primary w-100 theme-btn mx-auto">Log
										In</button>
								</div>
							</form>

							<div class="auth-option text-center pt-5">
								No Account? Sign up <a class="text-link" href="register">here</a>.
							</div>
						</div>
						<!--//auth-form-container-->

					</div>
					<!--//auth-body-->


				</div>
				<!--//flex-column-->
			</div>
			<!--//auth-main-col-->

		</div>
		<!--//row-->
		<%@ include file="/WEB-INF/javascript.jsp"%>
</body>

</html>