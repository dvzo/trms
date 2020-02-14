function promiseGetEmployee(){
	return new Promise(function(resolve,reject){
		$.ajax({
		    url:'getEmployee',
		    type:'GET'
		  })
		    .done(function(response){
//		      console.log("in success");
		      console.log(response);
//		      console.log("type: " +  typeof(response));
		      var employee = JSON.parse(response);
		      resolve(employee);
		    })
		    .fail(function(response){
//		      console.log("printed on error");
//		      console.log(response);
//		      console.log(jQuery.parseJSON(response));
		      reject(null);
		    });
	});
}

function promiseGetCurrentFormCount(){
	return new Promise(function(resolve,reject){
		$.ajax({
		    url:'getCurrentFormCount',
		    type:'GET'
		  })
		    .done(function(response){
		      resolve(response);
		    })
		    .fail(function(response){
		      console.log("error");
		      reject(null);
		    });
	});
}

function promiseGetPendingRequestCount(){
	return new Promise(function(resolve,reject){
		$.ajax({
		    url:'getPendingRequestCount',
		    type:'GET'
		  })
		    .done(function(response){
		      resolve(response);
		    })
		    .fail(function(response){
		      console.log("error");
		      reject(null);
		    });
	});
}

function promiseGetListOfCurrentForms(){
	return new Promise(function(resolve,reject){
		console.log("getting the forms");
		$.ajax({
		    url:'getListOfCurrentForms',
		    type:'GET'
		  })
		    .done(function(response){
		    	console.log(response);
		    	var forms = JSON.parse(response);
		    	console.log(forms);
		    	resolve(forms);
		    })
		    .fail(function(response){
		      console.log("error");
		      reject(null);
		    });
	});
}

function promiseGetListOfRequestedReviews(){
	return new Promise(function(resolve,reject){
		console.log("getting the forms");
		$.ajax({
		    url:'getListOfRequests',
		    type:'GET'
		  })
		    .done(function(response){
		    	console.log(response);
		    	var forms = JSON.parse(response);
		    	console.log(forms);
		    	resolve(forms);
		    })
		    .fail(function(response){
		      console.log("error");
		      reject(null);
		    });
	});
}