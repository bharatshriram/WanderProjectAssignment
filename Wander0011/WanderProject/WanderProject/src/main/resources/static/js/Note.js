/*   Document   : Note.js

    Author     : Bharat
 */

/*$(document)
		.ready(
				function() {
					
					//alert("Before if");
					
					if(sessionStorage.getItem("type") == null){
						
						//alert("in if");
						
						//window.location="index.jsp";
					}
					
				});*/

$(document)
	.ready(
		function() {
			var roleId=sessionStorage.getItem("roleId");
			if(roleId == null){
				window.location="index.html";
			}
		});


$(document).ready(function() {
	$("#count").click(function() {
		window.location = "logout.html";
	});
	
});

$(document)
	.ready(
		function() {
			$
				.getJSON(
					"/WanderProject/note/list/"+sessionStorage.getItem("roleId"),
					function(data) {
						$
							.each(
								data.noteList,
								function(i, item) {

									var tr = "<tr>"
										
										+ "<td align=center>"
										+ "Note-"+item.noteId
										+ "</td>"
										+ "<td align=center>"
										+ item.roleId
										+ "</td>"
										+ "<td align=center>"
										+ item.title
										+ "</td>"
										+ "<td align=center>"
										+ item.description
										+ "</td>"
										+ "<td align=center>"
										+ item.creation
										+ "</td>"
										+ "<td align=center>"
										+ item.updateTime
										+ "</td>"
										+ "<td align=center>"
										+ "<a href=# id=NoteEdit data-toggle=modal	data-target=#myEditNote onclick='getNoteFormEdit(\""
										+ item.noteId+","+ item.roleId+"\")'>"
										+ "<span class=glyphicon glyphicon-pencil style=font-size: 15px; color: #ed9c28>"
										+ "</span>"
										+ "</a>"
										+ "</td>"
										+ "<td align=center>"
										+ "<a href=# id=NoteDelete onclick='getNoteDeleteForm("
										+ item.noteId
										+ ")'>"
										+ "<span class=glyphicon glyphicon-trash style=font-size: 15px; color: #ed9c28>"
										+ "</span>"
										+ "</a>"
										+ "</td>"
										+ "</tr>";
									$("#notetable")
										.append(tr);
								});
						/*$("#count")
						.append("<h4 style=font-size:20px;color:white><i><b>Total : "+data.size+"</b></i></h4>");*/
					});
		});

/* /FOR ADD Note */

$(document).ready(function() {

	
	
	$("#Note_Add").click(function() {

		var title_add_Name = $("#title_add_Name").val();

		var description_add_Name = $("#description_add_Name").val();

		var creation_add = $("#creation_add").val();

		var validations_pattern = /^[ A-Z a-z]*$/;
		
		if (title_add_Name == "") {

			bootbox.alert("Please Enter Title");
			return false;
		}else {
			
			if (!validations_pattern.test(title_add_Name)) {
				bootbox.alert('Enter Valid Title Name');
				return false;
			}
		}

		if (description_add_Name == "") {

			bootbox.alert("Please Enter Dedscription");
			return false;
		}else {
			
			if (!validations_pattern.test(description_add_Name)) {
				bootbox.alert('Enter Valid Description Name');
				return false;
			}
		}


		if (creation_add == "") {

			bootbox.alert("Please Enter Creation");
			return false;
		} else {
			
			if (!validations_pattern.test(creation_add)) {
				bootbox.alert('Enter Valid Creation Name');
				return false;
			}
		}
		var data1 = {}

		data1["title"] = title_add_Name;
		data1["description"] = description_add_Name;
		data1["creation"] = creation_add;
		data1["roleId"] = sessionStorage.getItem("roleId");
		//alert(JSON.stringify(data1));
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/WanderProject/data/note/add/edit/"+0,
			data : JSON.stringify(data1),
			dataType : "JSON",
			success : function(data) {

				//alert(data);
				
				if (data.result == "Sucess") {
					bootbox
						.confirm(
							"Added successfully!",
							function(
								result) {

								window.location = "home.html";
							});

				} /*else {

					window.location = "Customer_home.jsp";
				}*/

			}
		});

	});
	return false;

});

$(document)
	.ready(
		function() {
			$("#Note_Edit")
				.click(
					function() {

						var NoteId = $("#NoteIdHidden").val();
						var title_edit_Name = $("#title_edit_Name").val();

						var description_edit_Name = $("#description_edit_Name").val();

						var creation_edit = $("#creation_edit").val();

						var validations_pattern = /^[ A-Z a-z]*$/;
						
						if (title_edit_Name == "") {

							bootbox.alert("Please Enter Title");
							return false;
						}else {
							
							if (!validations_pattern.test(title_edit_Name)) {
								bootbox.alert('Enter Valid Title Name');
								return false;
							}
						}

						if (description_edit_Name == "") {

							bootbox.alert("Please Enter Dedscription");
							return false;
						}else {
							
							if (!validations_pattern.test(description_edit_Name)) {
								bootbox.alert('Enter Valid Description Name');
								return false;
							}
						}


						if (creation_edit == "") {

							bootbox.alert("Please Enter Creation");
							return false;
						} else {
							
							if (!validations_pattern.test(creation_edit)) {
								bootbox.alert('Enter Valid Creation Name');
								return false;
							}
						}
						var data1 = {}

						data1["title"] = title_edit_Name;
						data1["description"] = description_edit_Name;
						data1["creation"] = creation_edit;
						data1["roleId"] = sessionStorage.getItem("roleId");
						data1["noteId"] = NoteId;
						
						$
							.ajax({
								type : "POST",
								contentType : "application/json",
								url : "/WanderProject/data/note/add/edit/"+NoteId
									,
								data : JSON
									.stringify(data1),
								dataType : "JSON",
								success : function(data) {
									if (data.result == "Sucess") {
										bootbox
											.confirm(
												"Updated successfully!",
												function(
													result) {
													window.location = "home.html";
												});
									} 
								}
							} );
					});
			return false;
		});

/* For Note Edit */

function getNoteFormEdit(alldata) {
	
	var dataSplit=alldata.split(",");
	var noteId=dataSplit[0];
	var roleId= dataSplit[1];
	
	
	$.getJSON("/WanderProject/note/list/"+sessionStorage.getItem("roleId"), function(data) {
		$.each(data.noteList, function(i, item) {
			if (noteId == item.noteId) {
				
				$('#NoteIdHidden').val(item.noteId);
				$('#title_edit_Name').val(item.title);
				$('#description_edit_Name').val(item.description);
				$('#creation_edit').val(item.creation);
				
				
			} else {}
		});
	});
	$('#myEditNote').modal('show');
}


function getNoteDeleteForm(id) {

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/WanderProject/note/delete/" + id,
		dataType : "JSON",
		success : function(data) {
			//alert("Success====" + data.result);
			if (data.result == "Success") {
				bootbox
					.confirm(
						"Deleted successfully!",
						function(
							result) {
							window.location = "home.html";
						});

			} else {
				window.location = "home.html";
			}
		}
	});
}