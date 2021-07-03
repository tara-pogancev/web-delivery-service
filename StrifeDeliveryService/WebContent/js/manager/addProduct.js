document.addEventListener('DOMContentLoaded', function () {

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', form, false);

}, false);

function form(e) {

	e.preventDefault();

	var name = document.getElementById('product-name').value;
	var type = document.getElementById('type').value;
	var price = document.getElementById('product-price').value;
	var quantity = document.getElementById('quantity').value;
	var description = document.getElementById('description').value;
	var picture = document.getElementById('logo-picture').value;

	if (name && type && price && picture)
		isUnique(name, type, price, quantity, description, picture)

	else
		alert("Please fill in name, price, type and picture fields!")
}

function isUnique(name, type, price, quantity, description, picture) {

	let data = {
		"name": name
	}

	$.post({
		url: 'webapi/products/unique',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			if (response == "false") {
				alert("Product name already exists! Please pick something else.")
			} else {
				setBasicParams(name, type, price, quantity, description)
			}

		}
	});
}


function setBasicParams(name, type, price, quantity, description) {

	let data = {
		"name": name,
		"type": type,
		"price": price,
		"quantity": quantity,
		"description": description
	}

	$.post({
		url: 'webapi/products/setBasicParams',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			uploadLogo(response)
			addToRestaurant(response)		

		}
	});

}

function uploadLogo(name) {

	let base64String = "";

	var file = document.querySelector(
		'input[type=file]')['files'][0];

	var reader = new FileReader();

	reader.onload = function () {
		base64String = reader.result.replace("data:", "").replace(/^.+,/, "");

		imageBase64Stringsep = base64String;

		//console.log(base64String);

		$.post({
			url: 'webapi/images/uploadImage',
			data: name,
			success: function (response) {

			}
		});

		$.post({
			url: 'webapi/images/uploadImageMenuItem',
			data: base64String,
			success: function (response) {

			}
		});

	}

	reader.readAsDataURL(file);

}

function addToRestaurant(id) {

	$.get({
		url: 'webapi/login/activeManagerRestaurant',
		success: function (restaurant) {

			let data = {
				"id": id,
				"name": restaurant.name
			}

			$.post({
				url: 'webapi/editRestaurant/addProduct',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function (response) {

					alert("Product successfully added!")
					window.location.href = "http://localhost:8080/PocetniREST/profileManager.html";
				}
			});


		}
	});

}