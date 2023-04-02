<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"
	rel="stylesheet" />

</head>
<body>
	<form method="post" action="bonjour">
		<p>
			<label for="nom">Nom : </label> <input type="text" name="nom"
				id="nom" />
		</p>
		<p>
			<label for="prenom">Prénom : </label> <input type="text"
				name="prenom" id="prenom" />
		</p>

		<input type="submit" />
	</form>

	<!-- 	<ul> -->
	<%-- 		<c:forEach var="utilisateur" items="${ utilisateurs }"> --%>
	<%-- 			<li><c:out value="${ utilisateur.prenom }" /> <c:out --%>
	<%-- 					value="${ utilisateur.nom }" /></li> --%>
	<%-- 		</c:forEach> --%>

	<!-- 	</ul> -->
	<div class="container" style="width: 90%">
		<table id="example" class="table table-striped" style="width: 100%">

			<thead>
				<tr>
					<th>Prénom</th>
					<th>Nom</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="utilisateur" items="${ utilisateurs }">
					<tr>
						<td><c:out value="${ utilisateur.prenom }" /></td>
						<td><c:out value="${ utilisateur.nom }" /></td>
					</tr>
				</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<th>Prénom</th>
					<th>Nom</th>

				</tr>
			</tfoot>
		</table>
	</div>
	<select class="form-select form-select-lg mb-3"
		aria-label=".form-select-lg example">
		<option selected>Select auteur</option>
		<c:forEach var="utilisateur" items="${ utilisateurs }">
				<option value='<c:out value="${ utilisateur.id }" />' ><c:out value="${ utilisateur.prenom}  ${ utilisateur.nom}" /></option>

		</c:forEach>


	</select>
	<div>
	 <label for="browser">Selectionner Auteur:</label>
  <input list="browsers" name="browser" id="browser">
  <datalist id="browsers">
    <option value="Edge">
    <option value="Firefox">
    <option value="Chrome">
    <option value="Opera">
    <option value="Safari">
  </datalist>
</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>

	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</body>
</html>
