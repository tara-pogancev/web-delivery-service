const	form = document.getElementById('contact'),
		submitInput = document.getElementById('form-submit');

function getFormData(e) {
	
	e.preventDefault();	
	sendEmail();	
}

function sendEmail() {	
	
	axios.post('webapi/email/sendEmail', { 
		"id": null,
		"name" : document.getElementById('name').value,
		"email" : document.getElementById('email').value,
		"content" : document.getElementById('form-message').value	
		
	});	
	
}

document.addEventListener('DOMContentLoaded', function() {
	
	submitInput.addEventListener('click', getFormData, false);	
	
}, false);

