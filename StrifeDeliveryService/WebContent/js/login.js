document.addEventListener('DOMContentLoaded', function () {

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', getFormData, false);

}, false);
		
function getFormData(e) {
	
	e.preventDefault();	
	
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	
	if (username && password)	
		sendLogin();	
	else 
		alert("Please fill in all fields!")
}

function sendLogin(username, password) {	
	
	let data = {
		"id" : username,
		"password" : password
	}

	$.post({
		url: 'webapi/login/userLogin',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function(response){
				alert(response)
		}


	});
}
