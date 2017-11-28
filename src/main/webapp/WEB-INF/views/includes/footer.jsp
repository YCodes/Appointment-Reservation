<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="<spring:url value="/resource/js/wickedpicker.js" />"></script>


<style>
.logout {
	position: relative;
	top: 8px;
	color: #999;
	text-decoration: none;
}
</style>


<script>
	var deleteLinks = document.querySelectorAll('.delete');

	for (var i = 0; i < deleteLinks.length; i++) {
		deleteLinks[i].addEventListener('click', function(event) {
			event.preventDefault();

			var choice = confirm(this.getAttribute('data-confirm'));

			if (choice) {
				window.location.href = this.getAttribute('href');
			}
		});
	}

	$(document).ready(function() {
		$("#firstName").datepicker();
		$("#lastName").wickedpicker();

	});

	$(document).ready(function() {
		$(".btn-submit").click(function(e) {
			e.preventDefault();
			$("#put-session").val($(this).data("session"));
			$("#form").submit();
		});
	});
</script>