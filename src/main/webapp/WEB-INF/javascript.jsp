
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script><%@ include file="/resources/assets/plugins/popper.min.js"%></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<script><%@ include file="/resources/assets/js/app.js"%></script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.min.js"></script>

<script>
	$(document).ready(function() {
		$('#example').DataTable({
			columnDefs : [ {
				target : 0,
				visible : false,
				searchable : false,
			}, ],
			"lengthMenu" : [ [ 5, 10, 25, 50, -1 ], [ 5, 10, 25, 50, "All" ] ]
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
	<script> $(document).ready(function() {
		$("#search-docs").on("keyup",function() {
			var value = $(this).val().toLowerCase();
		$("#myDIV .docs ").filter(function() {$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});
		});
	</script>

<!-- Charts JS -->
<%-- <script><%@ include file="/resources/assets/plugins/chart.js/chart.min.js"%></script> --%>
<%-- <script><%@ include file="/resources/assets/js/index-charts.js"%></script> --%>

<!-- Page Specific JS -->