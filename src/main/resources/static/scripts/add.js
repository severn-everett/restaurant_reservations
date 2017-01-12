$(function () {
	var currentDate = new Date();
	$("#datepicker").datepicker({
		defaultDate: currentDate,
		minDate: currentDate,
		maxDate: "+1m",
		currentText: "Today",
		dateFormat: 'yy-mm-dd'
	});
	$("#datepicker").change(function() {
		var selectedDate = $("#datepicker").val();
		$.ajax({url: "/availability/hours", data: {date: selectedDate}, success: function(result) {
			alert("TEST: " + result);
		}});
	});
	$("#datepicker").val(currentDate.toISOString().slice(0,10));
	$("#datepicker").change();
});