
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"
	rel="stylesheet" />

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/fontawesome.min.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">




<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
body {
	margin: 0;
	font-family: "Lato", sans-serif;
}

.sidebar {
	margin: 0;
	padding: 0;
	width: 200px;
	background-color: #f1f1f1;
	position: fixed;
	height: 100%;
	overflow: auto;
}

.sidebar a {
	display: block;
	color: black;
	padding: 16px;
	text-decoration: none;
}

.sidebar a.active {
	background-color: #04AA6D;
	color: white;
}

.sidebar a:hover:not(.active) {
	background-color: #555;
	color: white;
}

div.content {
	margin-left: 200px;
	padding: 1px 16px;
	height: 1000px;
}

@media screen and (max-width: 700px) {
	.sidebar {
		width: 100%;
		height: auto;
		position: relative;
	}
	.sidebar a {
		float: left;
	}
	div.content {
		margin-left: 0;
	}
}

@media screen and (max-width: 400px) {
	.sidebar a {
		text-align: center;
		float: none;
	}
}
</style>
</head>
<body>
	<div class="sidebar">
		<a class="active" href="#home">Home</a> <a href="#news">News</a> <a
			href="#contact">Contact</a> <a href="#about">About</a>
	</div>

	<div class="content">
		<h2>Dashboard</h2>

		<div class="container" style="width: 90%">

			<div id="listdoc">
				<div class="col-auto mb-5">
					<div class="page-utilities mb-3">
						<div
							class="row g-2 justify-content-start justify-content-md-end align-items-center">

							<div class="col-auto">
								<button type="button" class="btn btn-primary"
									data-bs-toggle="modal" data-bs-target="#document">
									Ajout auteur</button>

							</div>
						</div>
						<!--//row-->
					</div>
					<div class="card">
						<div class="card-header">Auteurs</div>
						<div class="card-body">
							<table id="example" class="table table-striped"
								style="width: 100%">

								<thead>
									<tr>
										<th>Id</th>
										<th>Nom</th>
										<th>Prénom</th>
										<th>Nationalité</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="auteur" items="${ auteurs }">
										<tr>
											<td><c:out value="${ auteur.id }" /></td>
											<td><c:out value="${ auteur.nom }" /></td>
											<td><c:out value="${ auteur.prenom }" /></td>
											<td><c:out value="${ auteur.nationalite }" /></td>
											<td><button type="button" class="btn btn-primary"
													data-bs-toggle="modal" data-bs-target="#documentEdit" onclick="location.href='auteur?id=<c:out value='${auteur.id}' />'">
													<i class="material-icons" data-toggle="tooltip"
														title="Edit">&#xE254;</i>
													
												</button>
												<button type="button" class="btn btn-primary"
													data-bs-toggle="modal" data-bs-target="#docume">
													<i class="material-icons" data-toggle="tooltip"
														title="Delete">&#xE872;</i>
												</button></td>
										</tr>
										
									</c:forEach>

								</tbody>

							</table>
						</div>
					</div>

				</div>
				<!-- Modal -->

				<!--//table-utilities-->
				<div class="modal fade" id="document" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">

					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">Ajouter
									un auteur</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form action="auteur" class="form-group" method="POST">
									<div class="row">
										<div class="col-md-6 py-3">
											<label for="">Nom</label> <input type="text"
												class="form-control" name="nom">
										</div>
										<div class="col-md-6 py-3">
											<label for="">Prenom</label> <input type="text"
												class="form-control" name="prenom">
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 py-3">
											<label for="">Nationalité</label> <input type="text"
												class="form-control" name="nationalite">
										</div>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Annuler</button>
										<button type="submit" class="btn btn-primary">Enregistrer</button>
									</div>

								</form>
							</div>

						</div>
					</div>
				</div>
				<div class="modal fade" id="documentEdit" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">

					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">Modifier
									un auteur</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form action="bonjour" class="form-group" method="POST">
									<div class="row">
										<div class="col-md-6 py-3">
											<label for="">Nom</label> <input type="text"
												class="form-control" name="nom">
										</div>
										<div class="col-md-6 py-3">
											<label for="">Prenom</label> <input type="text"
												class="form-control" name="prenom">
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 py-3">
											<label for="">Nationalité</label> <input type="text"
												class="form-control" name="">
										</div>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Fermer</button>
										<button type="submit" class="btn btn-primary">Enregistrer modifications</button>
									</div>

								</form>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>

		<div>
			<div class="col-auto mb-5">
				<div class="page-utilities mb-3">
					<div
						class="row g-2 justify-content-start justify-content-md-end align-items-center">

						<div class="col-auto">
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#document">
								Nouvel auteur</button>

						</div>
					</div>
					<!--//row-->
				</div>
				<div class="card">
					<div class="card-header">Auteurs</div>
					<div class="card-body">
						<table id="auteur" class="table table-striped" style="width: 100%">

							<thead>
								<tr>
									<th>No</th>
									<th>Prénom</th>
									<th>Nom</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="utilisateur" items="${ utilisateurs }">
									<tr>
										<td><c:out value="${ utilisateur.id }" /></td>
										<td><c:out value="${ utilisateur.prenom }" /></td>
										<td><c:out value="${ utilisateur.nom }" /></td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
					</div>
				</div>

			</div>
			<!-- Modal -->

			<!--//table-utilities-->
			<div class="modal fade" id="document" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">

				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="exampleModalLabel">Ajoute
								un auteur</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form action="bonjour" class="form-group" method="POST">
								<div class="row">
									<div class="col-md-6 py-3">
										<label for="">ISBN</label> <input type="text"
											class="form-control" name="nom">
									</div>
									<div class="col-md-6 py-3">
										<label for="">Titre</label> <input type="text"
											class="form-control" name="prenom">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 py-3">
										<label for="">Auteur</label> <input type="text"
											class="form-control" name="">
									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Fermer</button>
									<button type="submit" class="btn btn-primary">Enregistrer</button>
								</div>

							</form>
						</div>

					</div>
				</div>
			</div>


		</div>
	</div>






	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>

	<script>
		$(document).ready(function() {
			$('#example').DataTable({
				columnDefs : [ {
					target : 0,
					visible : false,
					searchable : false,
				},

				],
			});
			$('#auteur').DataTable({
				columnDefs : [ {
					target : 0,
					visible : false,
					searchable : false,
				},

				],
			});
		});
	</script>
	<!-- 	<script type="text/javascript"> -->
	// $(function() { // $('[data-toggle="tooltip"]').tooltip(); // });
	<!-- 	</script> -->
</body>
</html>

