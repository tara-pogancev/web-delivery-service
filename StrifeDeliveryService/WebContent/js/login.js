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
	
	axios.post('webapi/login/userLogin', { 
		"username" : username,
		"password" : password,
		
	})
	.then(response => {
    	alert("Sign in, successful");
		window.location.reload();
	});	
	
}
