$(document).ready(function(){
	
	promiseGetListOfRequestedReviews()
	  .then(function(list){//an array of forms
		  configureView(list);
	  });
	
});

function configureView(list){
	
	$.each(list,function(index,value){
		console.log("in append");
		var row = $('<tr></tr>');
		var col1 = $('<td>'+ value.fid +'</td>'); //form id
		var col2 = $('<td>'+value.title+'</td>'); //title
		var col3 = $('<td>' + value.status + "</td>")//status
		var col4 = $('<td></td>'); //view
		var form = $('<form action="SelectedForm" method="get"><input type="hidden" name="formID" value='+value.fid+'></input><button class="btn btn-primary">View</button></form>')
		col4.append(form);
		row.append(col1);
		row.append(col2);
		row.append(col3);
		row.append(col4);
		$('tbody').append(row);
	});
	
}