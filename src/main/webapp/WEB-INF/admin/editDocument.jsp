<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

<title>Domains</title>
</head>

<body class="app">

	<%@ include file="/WEB-INF/admin/admin_header.jsp"%>
	<!--//app-header-->

	<div class="app-wrapper">

		<div class="app-content pt-3 p-md-3 p-lg-4">
			<div class="container-xl">
				<div class="row g-3 mb-4 align-items-center justify-content-between">

					<div class="col-auto">
						<div class="page-utilities">
							<div
								class="row g-2 justify-content-start justify-content-md-end align-items-center">

								<div class="col-auto">
									<button type="button" class="btn app-btn-secondary"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop">
										<svg width="1em" height="1em" viewBox="0 0 16 16"
											class="bi bi-download me-1" fill="currentColor"
											xmlns="http://www.w3.org/2000/svg">
											<path fill-rule="evenodd"
												d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z" />
											<path fill-rule="evenodd"
												d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z" />
										</svg>

										New document
									</button>
								</div>
							</div>
							<!--//row-->
						</div>
						<!--//table-utilities-->
					</div>
					<!--//col-auto-->
				</div>
				<!--//row-->

				<div class="modal modal-lg fade" id="editDocument"
					data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="staticBackdropLabel">Update
									document</h5>
								<button type="button" class="btn-close2" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form class="settings-form" method="post"
									action="insert_document">

									<div class="col-12">
										<div class="app-card app-card-settings shadow-sm p-4">

											<div class="app-card-body">

												<div class="mb-3">
													<div class="row">
														<div class="col-6">
															<label for="setting-input-2" class="form-label">IBSN</label>
															<input type="text" class="form-control" value='<c:out value="${ document.getISBN() }" />' id="setting-input-2" name="ISBN" required>
														</div>
														<div class="col-6">
															<label for="setting-input-2" class="form-label">Title</label>
															<input type="text" class="form-control" id="setting-input-2" name="libelle" value='<c:out value="${ document.getLibelle() }" />' required>																
														</div>
													</div>
												</div>

												<div class="mb-3">
													<div class="row">
														<div class="col-6">
															<label for="setting-input-2" class="form-label">Domain</label>
															<select class="form-select" name="domaine" required>
																<option selected='<c:out value="${ document.getDomaine().getId() }" />' value='<c:out value="${ document.getDomaine().getId() }" />'><c:out value="${ document.getDomaine().getDesignation() }" /></option>
																<c:forEach var="domain" items="${ domains }">
																	<option value='<c:out value="${ domain.id }" />'><c:out value="${ domain.designation }" /></option>			
																</c:forEach>
															</select>
														</div>
														<div class="col-6">
															<label for="setting-input-2" class="form-label">Author</label>
															<select class="form-select" name="auteur" required>
																<option selected='<c:out value="${ document.getAuteur().getId() }" />' value='<c:out value="${ document.getAuteur().getId() }" />'><c:out value="${ document.getAuteur().getNom() }" /> <c:out value="${ document.getAuteur().getPrenom() }" /></option>
																<c:forEach var="auteur" items="${ auteurs }">
																	<option value='<c:out value="${ auteur.id }" />'><c:out value="${ auteur.nom }" /> <c:out value="${ auteur.prenom }" /></option>
																</c:forEach>

															</select>
														</div>
													</div>
												</div>
												<div class="mb-3">
													<div class="row">
														<div class="col-6">
															<label for="setting-input-2" class="form-label">Publishing
																house</label> <select class="form-select" name="maisonEdition"
																required>

																<option selected='<c:out value="${ document.getMaisonEdition().getId() }" />' value='<c:out value="${ document.getMaisonEdition().getId() }" />'><c:out value="${ document.getMaisonEdition().getNomMaison() }" /></option>
																<c:forEach var="maisonEdition"
																	items="${ maisonEditions }">
																	<option value='<c:out value="${ maisonEdition.id }" />'><c:out
																			value="${ maisonEdition.nomMaison }" /></option>
																</c:forEach>
															</select>
														</div>
														<div class="col-6">
															<label for="setting-input-2" class="form-label">Type
																of Document</label> <select class="form-select"
																name="typeDocument" required>
																<option selected='<c:out value="${ document.getType().getId() }" />' value='<c:out value="${ document.getType().getId() }" />'><c:out value="${ document.getType().getDesignation() }" /></option>
																<c:forEach var="typeDocument" items="${ typeDocuments }">
																	<option value='<c:out value="${ typeDocument.id }" />'><c:out value="${ typeDocument.designation }" /></option>
																</c:forEach>
															</select>



														</div>
													</div>
												</div>
												<div class="mb-3">

													<label for="setting-input-2" class="form-label">Description</label>
													<input type="text" class="form-control" id="setting-input-2" value='<c:out value="${ document.getDescription() }" />' name="description">
													<input type="hidden" class="form-control" id="setting-input-2" value='<c:out value="${ document.getISBN() }" />' name="description">	
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" id="btn-close"
														data-bs-dismiss="modal">Close</button>
													<button type="submit" class="btn app-btn-primary">Save modifications</button>
												</div>


											</div>
											<!--//app-card-body-->

										</div>
										<!--//app-card-->
									</div>


								</form>
							</div>

						</div>
					</div>
				</div>

			</div>
			<!--//container-fluid-->
		</div>
		<!--//app-content-->


	</div>
	<!--//app-wrapper-->
	<div class="app-wrapper">

		<div class="app-content pt-3 p-md-3 p-lg-4">
			<div class="container-xl">
				<div class="row g-3 mb-4 align-items-center justify-content-between">
					<div class="col-auto">
						<h1 class="app-page-title mb-0">Documents</h1>
					</div>


				<div class="row g-4" id="myDIV">
					<c:forEach var="document" items="${ documents }">
					<div class="col-6 col-md-4 col-xl-3 col-xxl-2">
						
							<div class="app-card app-card-doc shadow-sm h-100">
								<div class="app-card-thumb-holder p-3">
									<span class="icon-holder"> <i
										class="fas fa-file-alt text-file"></i>
									</span> <span class="badge bg-success"></span> <a
										class="app-card-link-mask" href="#file-link"></a>
								</div>
								<div class="app-card-body p-3 has-card-actions">

									<h4 class="app-doc-title truncate mb-0">
										<a href="#file-link"><c:out value="${ document.getLibelle() }" /> </a>
									</h4>
									<div class="app-doc-meta">
										<ul class="list-unstyled mb-0">
											<li><span class="text-muted">Type:</span> <c:out
													value="${ document.getType().getDesignation() }" /></li>
											<li><span class="text-muted">Author:</span> 
											<c:out value="${ document.getAuteur().getNom() }" /> 
											<c:out value="${ document.getAuteur().getPrenom() }" /></li>
											<li><span class="text-muted"> Edition:</span> <c:out value="${ document.getMaisonEdition().getNomMaison() }"/></li>
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
												<li><a class="dropdown-item" href="#"><svg
															width="1em" height="1em" viewBox="0 0 16 16"
															class="bi bi-eye me-2" fill="currentColor"
															xmlns="http://www.w3.org/2000/svg">
														<path fill-rule="evenodd"
																d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.134 13.134 0 0 0 1.66 2.043C4.12 11.332 5.88 12.5 8 12.5c2.12 0 3.879-1.168 5.168-2.457A13.134 13.134 0 0 0 14.828 8a13.133 13.133 0 0 0-1.66-2.043C11.879 4.668 10.119 3.5 8 3.5c-2.12 0-3.879 1.168-5.168 2.457A13.133 13.133 0 0 0 1.172 8z" />
														<path fill-rule="evenodd"
																d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
													</svg>View</a></li>
												<li><a class="dropdown-item" href="#"><svg
															width="1em" height="1em" viewBox="0 0 16 16"
															class="bi bi-pencil me-2" fill="currentColor"
															xmlns="http://www.w3.org/2000/svg">
														<path fill-rule="evenodd"
																d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
													</svg>Edit</a></li>
												<li><a class="dropdown-item" href="#"><svg
															width="1em" height="1em" viewBox="0 0 16 16"
															class="bi bi-download me-2" fill="currentColor"
															xmlns="http://www.w3.org/2000/svg">
														<path fill-rule="evenodd"
																d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z" />
														<path fill-rule="evenodd"
																d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z" />
													</svg>Download</a></li>
												<li>
													<hr class="dropdown-divider">
												</li>
												<li><a class="dropdown-item" href="#"><svg
															width="1em" height="1em" viewBox="0 0 16 16"
															class="bi bi-trash me-2" fill="currentColor"
															xmlns="http://www.w3.org/2000/svg">
														<path
																d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
														<path fill-rule="evenodd"
																d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
													</svg>Delete</a></li>
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
	<script>
	 $(document).ready(function(){
	        $("#editDocument").modal('show');
	        $('#btn-close').click(function () {
	            /* when the submit button in the modal is clicked, submit the form */
	            //alert('submitting');
	            //$('#formfield').submit();
	            window.location.href = "list_document";
	        });
	        $('#btn-close2').click(function () {
	            /* when the submit button in the modal is clicked, submit the form */
	            //alert('submitting');
	            //$('#formfield').submit();
	            window.location.href = "list_document";
	        });
	    });
	</script>
</body>

</html>
