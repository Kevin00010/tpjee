<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp"%>

<title>Document preview</title>

</head>

<body class="app">
	<%@ include file="/WEB-INF/user_header.jsp"%>


	<div class="app-wrapper">

		<div class="app-content pt-3 p-md-3 p-lg-4">
			<div class="container-xl">

				<h1 class="app-page-title">{{ Detail }}</h1>
				<div class="row gy-4">
					<div class="col-12 col-lg-6 ps-5 pe-0 justify-content-center">
						<div class="app-card w-75  app-card-doc shadow-sm">
							<div class="app-card-thumb-holder px-5 w-100"
								style="height: 470px;">
								<div class="app-card-thumb">

									<img class="thumb-image"
										src='http://localhost:8080/tpjee/resources/assets/images/covers/<c:out
													value="${ document.getCover() }" />'
										style="width: 100%; height: 100%" class="card-img-top"
										alt="...">
								</div>
								<div class="row align-items-center gx-3">
									<div class="col-auto">
										<div class="app-icon-holder">
											<svg width="1em" height="1em" viewBox="0 0 16 16"
												class="bi bi-person" fill="currentColor"
												xmlns="http://www.w3.org/2000/svg">
												<path fill-rule="evenodd"
													d="M10 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
											</svg>
										</div>
										<!--//icon-holder-->

									</div>
									<!--//col-->
									<div class="col-auto">
										<h4 class="app-card-title">Title</h4>
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body px-4 w-100">
								<div class="item border-bottom py-1">
									<!-- <div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label mb-2"><strong>Photo</strong></div>
											<div class="item-data"><img class="profile-image"
													src="assets/images/user.png" alt=""></div>
										</div>//col
										<div class="col text-end">
											<a class="btn-sm app-btn-secondary" href="#">Change</a>
										</div>//col
									</div>//row -->
								</div>
								<!--//item-->


							</div>
							<!--//app-card-body-->
							<div class="app-card-footer py-2 p-3">
								<div class="item-label py-3">
									<strong>Titre: </strong>
								</div>
								<div class="item-label">
									<strong>Auteur: </strong>
								</div>

							</div>
						</div>
						<!--//app-card-->
					</div>

					<div class="col-12 col-lg-6 ms-0">
						<div
							class="app-card app-card-account shadow-sm d-flex flex-column align-items-start">
							<div class="app-card-header  p-3 border-bottom-0">

								<div class="row align-items-center gx-3">
									<div class="col-auto">
										<div class="app-icon-holder">
											<svg width="1em" height="1em" viewBox="0 0 16 16"
												class="bi bi-sliders" fill="currentColor"
												xmlns="http://www.w3.org/2000/svg">
												<path fill-rule="evenodd"
													d="M11.5 2a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zM9.05 3a2.5 2.5 0 0 1 4.9 0H16v1h-2.05a2.5 2.5 0 0 1-4.9 0H0V3h9.05zM4.5 7a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zM2.05 8a2.5 2.5 0 0 1 4.9 0H16v1H6.95a2.5 2.5 0 0 1-4.9 0H0V8h2.05zm9.45 4a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zm-2.45 1a2.5 2.5 0 0 1 4.9 0H16v1h-2.05a2.5 2.5 0 0 1-4.9 0H0v-1h9.05z" />
											</svg>
										</div>
										<!--//icon-holder-->

									</div>
									<!--//col-->
									<div class="col-auto">
										<h4 class="app-card-title">Autres</h4>
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body px-4 w-100">

								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto d-flex ">
											<div class="item-label pe-5">
												<strong>Domaines </strong>

											</div>
											<span class="item-data ps-5 ">Informatiques</span>
										</div>
										<!--//col-->
										<!-- <div class="col text-end">
											<a class="btn-sm app-btn-secondary" href="#">Change</a>
										</div>//col -->
									</div>
									<!--//row-->
								</div>
								<!--//item-->
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto d-flex ">
											<div class="item-label pe-5">
												<strong>Maison d'edition </strong>

											</div>
											<span class="item-data ps-5 ">IUT</span>
										</div>
										<!--//col-->
										<!-- <div class="col text-end">
											<a class="btn-sm app-btn-secondary" href="#">Change</a>
										</div>//col -->
									</div>
									<!--//row-->
								</div>
								<!--//item-->
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto d-flex ">
											<div class="item-label pe-5">
												<strong>Type de document </strong>

											</div>
											<span class="item-data ps-5 ">Memoire</span>
										</div>
										<!--//col-->
										<!-- <div class="col text-end">
											<a class="btn-sm app-btn-secondary" href="#">Change</a>
										</div>//col -->
									</div>
									<!--//row-->
								</div>
								<div class="item border-bottom py-3">
									<div class="row justify-content-between align-items-center">
										<div class="col-auto">
											<div class="item-label">
												<strong>Descriptions</strong>
											</div>
											<div class="item-data">Descriptions Descriptions
												DescriptionsDescriptions Descriptions Descriptions
												Descriptions</div>
											<div class="item-data">Descriptions Descriptions
												Descriptions vDescriptions Descriptions Descriptions
												Descriptions</div>
											<div class="item-data">Descriptions Descriptions
												Descriptions Descriptions vDescriptions Descriptions
												Descriptions Descriptions</div>
											<div class="item-data">Descriptions Descriptions
												Descriptions Descriptions vDescriptions Descriptions
												Descriptions Descriptions</div>
											<div class="item-data">Descriptions Descriptions
												Descriptions Descriptions vDescriptions Descriptions
												Descriptions Descriptions</div>
											<div class="item-data">Descriptions Descriptions
												Descriptions Descriptions vDescriptions Descriptions
												Descriptions Descriptions</div>
										</div>
										<!-- <div class="col text-end">
											<a class="btn-sm app-btn-secondary" href="#">Change</a>
										</div> -->
									</div>


								</div>


							</div>
							<!--//app-card-body-->
							<!-- <div class="app-card-footer p-4 mt-auto">
								<a class="btn app-btn-secondary" href="#">Manage Preferences</a>
							</div>//app-card-footer -->

						</div>
						<!--//app-card-->
					</div>


				</div>
				<div class="row gy-4 my-5">
					<h1 class="app-page-title">Documents Similaires</h1>
					<div class="row g-4" id="myDIV">


						<div class="col-6 col-md-4 col-xl-3 col-xxl-2">

							<div class="app-card app-card-doc shadow-sm h-100">
								<img
									src='http://localhost:8080/tpjee/resources/assets/images/covers/<c:out
													value="${ document.getCover() }" />'
									style="width: 100%; height: 150px" class="card-img-top"
									alt="...">


								<div class="app-card-body p-3 has-card-actions">

									<h4 class="app-doc-title truncate mb-0">
										<a href="#file-link"><c:out
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
											<li><span class="text-muted"> Domaines:</span> <c:out
													value="${ document.getDomaine().getDesignation() }" /></li>
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
												<li><a class="dropdown-item"
													href="<c:url value='detail_document?id=${document.getISBN()}'/>"><svg
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

					</div>
				</div>
				<!--//container-fluid-->
			</div>

		</div>
	</div>

	<!--//app-footer-->

	


		<%@ include file="/WEB-INF/js.jsp"%>
</body>
</html>