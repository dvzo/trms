
$(document).ready(function(){
	promiseGetEmployee()
		.then(function(employee){
			configureWelcomeTag(employee);
		});

	promiseGetPendingRequestCount()
		.then(function(pendingRequestCount){
			configurePendingRequestCount(pendingRequestCount);
		});

	promiseGetCurrentFormCount()
		.then(function(currentFormCount){
			configureCurrentFormCount(currentFormCount);
		});


});

function configureWelcomeTag(name){
	$("#welcome").text("WELCOME BACK " + name.firstName +" "+ name.lastName +"!");
}

function configurePendingRequestCount(pendingRequestCount) {
	$("#request").text(pendingRequestCount + " pending requests");
}

function configureCurrentFormCount(currentFormCount) {
	$("#current").text("Submitted "+ currentFormCount + " forms!");
}


