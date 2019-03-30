/**
 * 
 */

$(document).ready(function() {
	$("#log").click(function() {
		window.location = "index.html";
	});
	
});

$(document).ready(function() {
	
	$("#register").click(function() {
		var email_id = $("#email_id").val();

		var user_id = $("#user_id").val();

		var password_id = $("#password_id").val();
		
		var confirmPassword_id = $("#confirmPassword_id").val();
		
		var name = $("#name").val();

		var validations_pattern = /^[ A-Z a-z]*$/;
		
		if (email_id == "") {

			bootbox.alert("Please Enter Email");
			return false;
		}else {
			var emailpattern = /^[a-zA-Z 0-9._-]+@[a-z A-Z]+\.[a-zA-Z]{2,4}$/;
			if (!emailpattern.test(email_id)) {
				bootbox.alert('Email Id is not valid!');
				return false;
			}
		}

		if (user_id == "") {

			bootbox.alert("Please Enter User Id");
			return false;
		}else {
			
			if (!validations_pattern.test(user_id)) {
				bootbox.alert('Enter Valid User Id');
				return false;
			}
		}


		if (password_id == "") {

			bootbox.alert("Please Enter Password");
			return false;
		} 
		
		if (confirmPassword_id == "") {
			bootbox.alert("Please Re-Enter Password");
			return false;
		} else if (confirmPassword_id != password_id) {
			bootbox.alert('Password Doesnot Match!');
			return false;
		}
		
		if (name == "") {

			bootbox.alert("Please Enter name");
			return false;
		}else {
			
			if (!validations_pattern.test(name)) {
				bootbox.alert('Enter Valid name');
				return false;
			}
		}
		
		var data1 = {}
		data1["email"] = email_id;
		data1["userName"] = user_id;
		data1["password"] = password_id;
		data1["name"] = name;
		data1["activeFlag"] = "1";
		
		//alert(JSON.stringify(data1));
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/WanderProject/data/signup/add",
			data : JSON.stringify(data1),
			dataType : "JSON",
			success : function(data) {

				//alert(data);
				
				if (data.result == "Sucess") {
					bootbox
						.confirm(
							"Register successfully!",
							function(
								result) {
								window.location = "home.html";
							});

				}else if (data.errorMessage == "Email ID Already Exist") {

					bootbox.alert('Email ID Already Exist');
					return false;

				} else if (data.errorMessage == "User ID Already Exist") {

					bootbox.alert('User ID Already Exist');
					return false;

				} else {
					window.location = "index.html";
				}
			}
		});
	});
	return false;
});