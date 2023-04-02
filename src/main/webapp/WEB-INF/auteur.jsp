<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href ="/tpjee/css/auteur.css" rel="stylesheet" />

<link
	href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"
	rel="stylesheet" />



</head>
<body>
	<!-- Button to open the modal -->
<button onclick="document.getElementById('id01').style.display='block'">Nouvel auteur</button>

<!-- The Modal (contains the Sign Up form) -->
<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">times;</span>
  <form class="modal-content" method="post" action="auteur">
    <div class="container">
      <h1>Enregistrement auteur</h1>
      <hr>
      <label for="nom"><b>nom</b></label>
      <input type="text" placeholder="Enter Nom" name="nom" required>

      <label for="prenom"><b>Prenom</b></label>
      <input type="text" placeholder="Prenom" name="prenom" required>

      <label for="nationalite"><b> Nationalité</b></label>
      <input type="text" placeholder="nationalité" name="nationalite" required>

     <!-- <label>
        <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
      </label> --> 

<!--       <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms and Privacy</a>.</p> -->

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Annuler</button>
        <button type="submit" class="signup">Ajouter</button>
      </div>
    </div>
  </form>
</div>

<table id="example" class="table table-striped" style="width: 100%">

		<thead>
			<tr>
				<th>No</th>
				<th>Prénom</th>
				<th>Nom</th>
				<th>Nationalité</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="auteur" items="${ auteurs }">
				<tr>
					<td class="hidden"><c:out value="${ auteur.id }" /></td>
					<td><c:out value="${ auteur.prenom }" /></td>
					<td><c:out value="${ auteur.nom }" /></td>
					<td><c:out value="${ auteur.nationalite }" /></td>
				</tr>
			</c:forEach>

		</tbody>
		<tfoot>
			<tr>
			<th>No</th>		
				<th>Prénom</th>
				<th>Nom</th>
				<th>Nationalité</th>
			</tr>
		</tfoot>
	</table>

	<script src="/tpjee/WEB-INF/css/auteur.js"></script>
	
	<script 
		src="https://code.jquery.com/jquery-3.5.1.js"></script>
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
