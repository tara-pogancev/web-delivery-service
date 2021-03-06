document.addEventListener('DOMContentLoaded', function() {

	getServerData();
	
	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', form, false);

}, false);

function getServerData() {

	$.get({
		url: 'webapi/login/activeManagerRestaurant',
		success: function (restaurant) {

			document.getElementById('rest-name').value = restaurant.name;
			document.getElementById('type').value = restaurant.type;
			document.getElementById('open-status').value = restaurant.status.toLowerCase();
			document.getElementById('city').value = restaurant.city;
			document.getElementById('postal').value = restaurant.postal;
			document.getElementById('address').value = restaurant.address;
		}
	});
}

function form(e) {

	e.preventDefault();

	var name = document.getElementById('rest-name').value;
	var type = document.getElementById('type').value;
	var status = document.getElementById('open-status').value;
	var city = document.getElementById('city').value;
	var postal = document.getElementById('postal').value;
	var address = document.getElementById('address').value;
	var picture = document.getElementById('logo-picture').value;

	if (name && type && status && city && postal && address)
		editRestaurant(name, type, status, city, postal, address)

	else
		alert("Please fill in all fields!")
}

function editRestaurant(name, type, status, city, postal, address) {
	let data = {
		"name": name,
		"type": type,
		"status": status,
		"city": city,
		"postal": postal,
		"address": address,
	}

	$.post({
		url: 'webapi/editRestaurant/editBasicParams',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function(response) {

			//uploadLogo(name);
			alert("Restaurant successfully edited")
			// Basic params edited
			window.location.href = "http://localhost:8080/PocetniREST/profileManager.html";

		}
	});

}

function uploadLogo(name) {

	let base64String = "";

	var file = document.querySelector(
		'input[type=file]')['files'][0];

	var reader = new FileReader();

	reader.onload = function() {
		base64String = reader.result.replace("data:", "").replace(/^.+,/, "");

		imageBase64Stringsep = base64String;

		//console.log(base64String);

		$.post({
			url: 'webapi/images/uploadImage',
			data: name,
			success: function(response) {

			}
		});

		$.post({
			url: 'webapi/images/uploadImageLogo',
			data: base64String,
			success: function(response) {
			}
		});

	}

	reader.readAsDataURL(file);

}

