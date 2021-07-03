document.addEventListener('DOMContentLoaded', function() {

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', getFormData, false);

}, false);

function getFormData(e) {

	e.preventDefault();

	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;

	if (username && password) {
		sendLogin(username, password);
	}
	else
		alert("Please fill in all fields!")
}

function sendLogin(username, password) {

	let data = {
		"id": username,
		"password": password
	}

	$.post({
		url: 'webapi/login/userLogin',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function(response) {
			if (response === "Loggin successful") {
				$.get({
					url: 'webapi/login/activeUser',
					contentType: 'application/json',
					success: function(response) {
						switch (response.name) {
							case "CUSTOMER":
								window.location.href = "http://localhost:8080/PocetniREST/profileCustomer.html";
								break;
							case "ADMIN":
								window.location.href = "http://localhost:8080/PocetniREST/profileAdmin.html";
								break;
							case "MANAGER":
								window.location.href = "http://localhost:8080/PocetniREST/profileManager.html";
								break;
							case "DELIVERER":
								window.location.href = "http://localhost:8080/PocetniREST/prfileDeliverer.html";
								break;
							default:
								window.location.href = "http://localhost:8080/PocetniREST/";
						}
					}
				});
			}
			else {
				alert(response)
			}

		}
	});
}
