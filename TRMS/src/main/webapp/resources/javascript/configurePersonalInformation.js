$(document).ready(function(){
	
	//add addition doc to reimbursement forms
	$("#add_doc").click(function(e){
		e.preventDefault();
		
		var row = $('<li class="list-group-item extra_doc"></div>');
		var label = $('<label class="sr-only">Extra Doc</label>');
		var file = $("<input type='file' class='form-control-file col-4'/>");
		var selection = $('<select class="form-control col-3"><option>Other</option><option>Supervisor Approval Email</option><option>Department Head Approval Email</option></select>');
		row.append(label);
		row.append(file);
		row.append(selection);
		$("#additional_doc").after(row);	
	});

	//remove the last added additional doc
	$("#remove_doc").click(function(e){
		e.preventDefault();
		$(".extra_doc").last().remove();
	});
	

	promiseGetEmployee()
		.then(function(employee){
			fillinPersonalInformation(employee);
		});
	
	
	
	
});


function fillinPersonalInformation(employee){
	console.log("in function for filling in personall information");
	$("#PID").val(employee.pid);
	$("#firstname").val(employee.firstName);;
	$("#lastname").val(employee.lastName);
	$("#email").val(employee.email);
	$("#phone_number").val(employee.phonenumber);
}

