
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script><%@ include file="/resources/assets/plugins/popper.min.js"%></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
<!-- Charts JS -->
<script><%@ include file="/resources/assets/plugins/chart.js/chart.min.js"%></script>
<script><%@ include file="/resources/assets/js/index-charts.js"%></script>

<!-- Page Specific JS -->
<script><%@ include file="/resources/assets/js/app.js"%></script>

<script
	src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
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
<script>
	$(document).ready(function() {
		$('.nav-link a').click(function() {
			$('nav-link ').removeClass("active");
			$(this).addClass("active");
		});
	});
</script>