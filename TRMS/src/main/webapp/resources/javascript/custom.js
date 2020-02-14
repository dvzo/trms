

$(document).ready(function() {

	console.log("document loaded");
	//append nav menu
	$.get("nav.html", function(data) {
		$("#navbarPlaceHolder").replaceWith(data);
	});
	
	
});




