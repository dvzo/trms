$(document).ready(function(){
	
	//add addition doc to reimbursement forms
	$("#add_comment").click(function(e){
		e.preventDefault();

		var date = new Date();
		var readDate = date.toDateString();
		var hours = date.getHours();
		var minutes = date.getMinutes();
		
		var row = $('<tr></tr>');
		var label = $('<label for="comment">Name, Title: </label>');
		var text = $('#comment').val();
		
		// append label, text
		row.append($('<br>'));
		row.append($('<br>'));
		row.append(label)
		row.append($('<br>'));
		row.append(text)
		row.append($('<br>'));
		row.append(readDate + " " + hours + ":" + minutes);
		row.append($('<br>'));
		$("#append_comment").after(row);
		
		// clear textbox
		$("#comment").val("");
	});
});