const	form = document.getElementById('contact'),
		submitInput = document.getElementById('form-submit');

function getFormData(e) {
	
	e.preventDefault();	
	
	var name = document.getElementById('name').value;
	var email = document.getElementById('email').value;
	var content = document.getElementById('form-message').value;
	
	if (name && email && content)	
		sendEmail();	
		
	else 
		alert("Please fill in all fields!")
}

function sendEmail() {	
	
	axios.post('webapi/email/sendEmail', { 
		"id" : "",
		"name" : document.getElementById('name').value,
		"email" : document.getElementById('email').value,
		"content" : document.getElementById('form-message').value	
		
	})
	.then(response => {
    	alert("Thank you! Your email has been sent!");
		window.location.reload();
	});	
	
}

document.addEventListener('DOMContentLoaded', function() {
	
	submitInput.addEventListener('click', getFormData, false);	
	
}, false);

