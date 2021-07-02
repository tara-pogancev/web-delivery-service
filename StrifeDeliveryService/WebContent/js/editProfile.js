document.addEventListener('DOMContentLoaded', function () {

	getServerData();

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', getFormData, false);

}, false);

function getServerData() {

	$.get({
		url: 'webapi/login/activeUserObject',
		success: function (user) {

			document.getElementById('password').value = user.password;
			document.getElementById('username').value = user.id;
			document.getElementById('name').value = user.name;
			document.getElementById('last').value = user.lastName;
			document.getElementById('date-of-birth').value = user.dateOfBirth;
			document.getElementById('gender').value = user.gender;
		}
	});
}

function getFormData(e) {

	e.preventDefault();

	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var name = document.getElementById('name').value;
	var last = document.getElementById('last').value;
	var date = document.getElementById('date-of-birth').value;
	var gender = document.getElementById('gender').value;

	if (username && password && name && last && date && gender)
		changeData(username, password, name, last, date, gender)

	else
		alert("Please fill in all fields!")
}


function changeData(username, password, name, last, date, gender) {

	axios.post('webapi/users/editUserProfile', {
		"id": username,
		"password": password,
		"name": name,
		"lastName": last,
		"gender": gender,
		"dateOfBirth": date

	})
		.then(response => {
			alert("Changes successfully made!");
			window.location.href = "http://localhost:8080/PocetniREST/";
		});
}
