<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>


<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="description"
	content="My Docs">
<meta name="author" content="My documents">

<link rel="shortcut icon" href="favicon.ico">

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/fontawesome.min.css" rel="stylesheet" />

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" />
<!-- FontAwesome JS-->
<script defer><%@include file="/resources/assets/plugins/fontawesome/js/all.min.js"%> </script>

<!-- App CSS -->
<style> <%@ include file="/resources/assets/css/portal.css" %> </style>

<title>Type of Document</title>
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
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-download me-1" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
											<path fill-rule="evenodd"
												d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z" />
											<path fill-rule="evenodd"
												d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z" />
										</svg>

										New type
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

				<div class="modal fade" id="editDomain"
					data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="staticBackdropLabel">Update domain</h5>
								<button type="button" class="btn-close" id="btn-close2" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form class="settings-form" method="post" action="update_domain">

									<div class="mb-3">
										<div class="row">
											<div class="col-12">
												<label for="setting-input-2" class="form-label">Designation
													</label> <input type="text" class="form-control"
													id="setting-input-2" name="designation" value='<c:out value="${ domain.designation }" />' required>
											</div>
											<input type="hidden" class="form-control"
													id="setting-input-2" name="id" value='<c:out value="${ domain.id }" />' required>
											
											
										</div>
									</div>

									
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary" id="btn-close"
											data-bs-dismiss="modal" >Cancel</button>
										<button type="submit" class="btn app-btn-primary">Save modifications</button>
									</div>

								</form>
							</div>

						</div>
					</div>
				</div>

				<!--  Auteur Table -->
				<div class="tab-content" id="orders-table-tab-content">
					<div class="tab-pane fade show active" id="orders-all"
						role="tabpanel" aria-labelledby="orders-all-tab">
						<div class="app-card app-card-orders-table shadow-sm mb-5">
							<div class="app-card-body">

								<div class="card">
									<div class="card-header">
										<div class="col-auto">
											<h1 class="app-page-title mb-0">Domains</h1>
										</div>
									</div>
									<div class="card-body">
										<table id="example" class="stripe row-border"
											style="width: 100%">

											<thead>
												<tr>
													<th>Id</th>
													<th>Designation</th>
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="domains" items="${ domains }">
													<tr>
														<td><c:out value="${ domains.id }" /></td>
														<td><c:out value="${ domains.designation }" /></td>

														<td class="cell"><a class="btn-sm "
															style="color: darkorange"
															href="<c:url value='edit_domain?id=${domains.id}'/>"><i
																class="material-icons" data-toggle="tooltip"
																title="Edit">&#xE254;</i></a> <a class="btn-sm "
															style="color: red"
															href="<c:url value='delete_domain?id=${domains.id}'/>"><span><i
																	class="material-icons" data-toggle="tooltip"
																	title="Delete">&#xE872;</i></span></a></td>
													</tr>

												</c:forEach>

											</tbody>

										</table>
									</div>
								</div>

							</div>
							<!--//table-responsive-->

						</div>

					</div>
					<!--//tab-content-->
				</div>
				<!--//container-fluid-->
			</div>
			<!--//app-content-->

		</div>

	</div>
	<!--//app-wrapper-->

	<!-- Javascript -->
	<%@ include file="/WEB-INF/javascript.jsp"%>
	<script>
    $(document).ready(function(){
        $("#editDomain").modal('show');
        $('#btn-close').click(function () {
            /* when the submit button in the modal is clicked, submit the form */
            //alert('submitting');
            //$('#formfield').submit();
            window.location.href = "list_domain";
        });
        $('#btn-close2').click(function () {
            /* when the submit button in the modal is clicked, submit the form */
            //alert('submitting');
            //$('#formfield').submit();
            window.location.href = "list_domain";
        });
    });
</script>
</body>

</html>
