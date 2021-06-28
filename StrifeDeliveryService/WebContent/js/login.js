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

function sendLogin() {	
	
	axios.post('webapi/login/sendLogin', { 
		"username" : username,
		"password" : password,
		
	})
	.then(response => {
    	alert("Sign in, successful");
		window.location.reload();
	});	
	
}

function isUnique(username, password, name, last, date, gender) {

	let data = {
		"id": username
	}

	$.post({
		url: 'webapi/login/unique',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			if (response == "false") {
				alert("Username taken! Please pick something else.")
			} else {
				createAccount(username, password, name, last, date, gender)
			}

		}
	});