/*   Document   : login.js

    Author     : Bharat
    */
/*
$(document)
		.ready(
				function() {
					
					//alert("Before if");
					
					if(sessionStorage.getItem("type") == null){
						
						//alert("in if");
						
						window.location="index.jsp";
					}
					
				});*/
$(document).ready(function() {
	$("#reg").click(function() {
		window.location = "Signup.html";
	});
});


$(document).ready(function() {
	
	$("#forgetform").hide();
	$("#loginform").show();
	
	$("#forget_id").click(function() {
		$("#loginform").hide();
		$("#forgetform").show();
	});
	$("#Login_id").click(function() {
		$("#forgetform").hide();
		$("#loginform").show();
	});
});
$(document).ready(function() {
	$("#forgetPassword").click(function() {

		var forget_user_id = $("#forget_user_id").val();
		
		if (forget_user_id == "") {

			bootbox.alert("Please Enter User ID");
			return false;
		}
		
		var data1 = {}
		data1["userName"] = forget_user_id;
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/WanderProject/forgotpassword/"+forget_user_id,
			dataType : "JSON",
			success : function(data) {
				//alert("Result--->"+data.result);
				if (data.result == "Success") {

					bootbox.alert("Password has been successfully sent to your registered mail ID");
					return false;
					
					window.location = "index.html";
					
				} else if (data.errorMessage =="User has not yet Registered") {

					bootbox.alert("User has not yet Registered");
					return false;
				}else {
					
					bootbox.alert("Not Sent");
					
					return false;

					window.location = "index.html";

				}
			}
		});
		return false;
	});
});



$(document).ready(function() {
	$("#login").click(function() {
		var user_id = $("#user_id").val();
		var user_password = $("#user_password").val();

		if (user_id == "") {

			bootbox.alert("Please Enter User ID");
			return false;
		}
		
		if (user_password == "") {

			bootbox.alert("Please Enter Password");
			return false;
		}
		var data1 = {}
		data1["userName"] = user_id;
		data1["password"] = user_password;

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/WanderProject/login",
			data : JSON.stringify(data1),
			dataType : "JSON",
			success : function(data) {
				
				if (data.result == "Success") {
						sessionStorage.setItem("roleId", data.roleId);
						sessionStorage.setItem("email", data.email);
						sessionStorage.setItem("name", data.name);
						window.location = "home.html";
				} else {
					bootbox.alert("Invalid UserId and Passsword");
					return false;
				}
			}
		});
		return false;
	});
});