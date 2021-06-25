document.addEventListener('DOMContentLoaded', function () {

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', form, false);

}, false);

function form(e) {

	e.preventDefault();

	$.post({
		url: 'webapi/newRestaurant/init',
		contentType: 'application/json',
		success: function (response) {
			// Init new restaurant 
		}
	});

	var name = document.getElementById('rest-name').value;
	var type = document.getElementById('type').value;
	var status = document.getElementById('open-status').value;
	var picture = document.getElementById('logo-picture').value;

	if (name && type && status)
		isUnique(name, type, status)

	else
		alert("Please fill in all fields!")
}

function isUnique(name, type, status) {

	let data = {
		"name": name
	}

	$.post({
		url: 'webapi/newRestaurant/unique',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			if (response == "false") {
				alert("Restaurant name already exists! Please pick something else.")
			} else {
				setBasicParams(name, type, status)
			}

		}
	});
}

function setBasicParams(name, type, status) {

	let data = {
		"name": name,
		"type": type,
		"status": status
	}

	$.post({
		url: 'webapi/newRestaurant/setBasicParams',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			// Basic params set
			window.location.href = "http://localhost:8080/PocetniREST/addRestaurantAddress.html";

		}
	});

}
