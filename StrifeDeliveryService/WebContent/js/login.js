const	form = document.getElementById('login-form'),
		submitInput = document.getElementById('login-submit');
		
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
		contentType: 'aplication/json',
		success: function(response){
			Alert(response)
		}


	});
}
