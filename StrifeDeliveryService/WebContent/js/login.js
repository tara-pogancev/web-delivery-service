const	form = document.getElementById('contact'),
		submitInput = document.getElementById('login-submit');
		
function getFormData(e) {
	
	e.preventDefault();	
	
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	
	if (name && email)	
		sendEmail();	
	else 
		alert("Please fill in all fields!")
}

function sendLogin() {	
	
	axios.post('webapi/login/sendLogin', { 
		"username" : document.getElementById('username').value,
		"password" : document.getElementById('password').value,
		
	})
	.then(response => {
    	alert("Sign in, succesfull");
		window.location.reload();
	});	
	
}

document.addEventListener('DOMContentLoaded', function() {
	
	submitInput.addEventListener('click', getFormData, false);	
	
}, false);