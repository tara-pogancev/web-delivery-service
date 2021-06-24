document.addEventListener('DOMContentLoaded', function () {

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', getFormData, false);

}, false);

function getFormData(e) {

	e.preventDefault();

	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var name = document.getElementById('name').value;
	var last = document.getElementById('last').value;
	var date = document.getElementById('date-of-birth').value;
	var gender = document.getElementById('gender').value;

	if (username && password && name && last && date && gender)	
		checkUsername(username)
		
	else 
		alert("Please fill in all fields!")
}

function checkUsername(username) {

	if (isUnique(username))
		createAccount(username, password, name, last, date, gender)
	else
		alert("Username taken! Please pick something else.")

}

function createAccount(username, password, name, last, date, gender) {

	alert("ok")

	axios.post('webapi/newCustomer/create', {
		"id": username,
		"password": password,
		"name": name,
		"lastName": last,
		"gender": gender,
		"dateOfBirth": date

	})
		.then(response => {
			alert("User succesfully registered!");
			window.location.href = "http://localhost:8080/PocetniREST/";
		});
}

function isUnique(username) {

	let data = {
		"id": username
	}

	$.post({
		url: 'webapi/newCustomer/unique',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			if (response == "false") {
				return false;
			}
			return true;
		}
	});

}