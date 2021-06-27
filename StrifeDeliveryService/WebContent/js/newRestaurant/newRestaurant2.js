document.addEventListener('DOMContentLoaded', function () {

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', form, false);

}, false);

function form(e) {

	e.preventDefault();

	var city = document.getElementById('city').value;
	var postal = document.getElementById('postal').value;
	var address = document.getElementById('address').value;

	if (city && postal && address)
		setAddress(city, postal, address)

	else
		alert("Please fill in all fields!")
}

function setAddress(city, postal, address) {

	let data = {
		"city": city,
		"postal": postal,
		"address": address
	}

	$.post({
		url: 'webapi/newRestaurant/setAddress',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			// Address set
			window.location.href = "http://localhost:8080/PocetniREST/addRestaurantManager.html";

		}
	});

}