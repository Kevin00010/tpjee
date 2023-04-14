<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp"%>

<title>Domains</title>
</head>

<body class="app">

	<%@ include file="/WEB-INF/user_header.jsp"%>
	<!--//app-header-->


			


	<!--//app-wrapper-->
	<div class="app-wrapper">

		<div class="app-content pt-3 p-md-3 p-lg-4">
			<div class="container-xl">
				<div class="row g-3 mb-4 align-items-center justify-content-between">
					<div class="col-auto">
						<h1 class="app-page-title mb-0">Documents</h1>
					</div>
										
				</div>
				<!--//row-->
				<div class="row g-4" id="myDIV">
					<c:forEach var="document" items="${ documents }">
						<div class="col-6 col-md-4 col-xl-3 col-xxl-2 docs">
							<div class="app-card app-card-doc shadow-sm h-100">
								<img
									src='http://localhost:8080/tpjee/resources/assets/images/covers/<c:out
													value="${ document.getCover() }" />'
									style="width: 100%; height: 150px" class="card-img-top"
									alt="...">
								<div class="app-card-body p-3 has-card-actions">						
									<h4 class="app-doc-title truncate mb-0">
										<a href="#file-link" id="libelle"><c:out
												value="${ document.getLibelle() }" /> </a>
									</h4>
									<div class="app-doc-meta">
										<ul class="list-unstyled mb-0">
											<li><span class="text-muted">Type:</span> <c:out
													value="${ document.getType().getDesignation() }" /></li>
											<li><span class="text-muted">Author:</span> <c:out
													value="${ document.getAuteur().getNom() }" /> <c:out
													value="${ document.getAuteur().getPrenom() }" /></li>
											<li><span class="text-muted"> Edition:</span> <c:out
													value="${ document.getMaisonEdition().getNomMaison() }" /></li>
											<li><span class="text-muted"> Domain:</span> <c:out
													value="${ document.getDomain().getDesignation() }" /></li>
										</ul>
									</div>
									<!--//app-doc-meta-->
									<div class="app-card-actions">
										<div class="dropdown">
											<div class="dropdown-toggle no-toggle-arrow"
												data-bs-toggle="dropdown" aria-expanded="false">
												<svg width="1em" height="1em" viewBox="0 0 16 16"
													class="bi bi-three-dots-vertical" fill="currentColor"
													xmlns="http://www.w3.org/2000/svg">
												<path fill-rule="evenodd"
														d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
											</svg>
											</div>
											<!--//dropdown-toggle-->
											<ul class="dropdown-menu">
												<li><a class="dropdown-item" href="<c:url value='detail_document?id=${document.getISBN()}'/>">"><svg
															width="1em" height="1em" viewBox="0 0 16 16"
															class="bi bi-eye me-2" fill="currentColor"
															xmlns="http://www.w3.org/2000/svg">
														<path fill-rule="evenodd"
																d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.134 13.134 0 0 0 1.66 2.043C4.12 11.332 5.88 12.5 8 12.5c2.12 0 3.879-1.168 5.168-2.457A13.134 13.134 0 0 0 14.828 8a13.133 13.133 0 0 0-1.66-2.043C11.879 4.668 10.119 3.5 8 3.5c-2.12 0-3.879 1.168-5.168 2.457A13.133 13.133 0 0 0 1.172 8z" />
														<path fill-rule="evenodd"
																d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
													</svg>View</a></li>
												
											</ul>
										</div>
										<!--//dropdown-->
									</div>
									<!--//app-card-actions-->
								</div>
								<!--//app-card-body-->
							</div>
						</div>
						<!--//app-card-->
					</c:forEach>
				</div>
				<!--//col-->
				<!--//col-->
			</div>
		</div>
		<!--//container-fluid-->
	</div>
	<!--//app-content-->
	



	<!-- Javascript -->
	<%@ include file="/WEB-INF/javascript.jsp"%>
	<!--  search on key up -->
	<script> $(document).ready(function() {
		$("#search-docs").on("keyup",function() {
			var value = $(this).val().toLowerCase();

		$("#myDIV .docs ").filter(function() {$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});
		});
	</script>
</body>

</html>
