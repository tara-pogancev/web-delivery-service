document.addEventListener('DOMContentLoaded', function() {

	getServerData();
	
	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', form, false);

}, false);

function getServerData() {

	$.get({
		url: 'webapi/products/getActiveProduct',
		success: function (product) {

			document.getElementById('product-name').value = product.name;
			document.getElementById('type').value = product.type;
			document.getElementById('product-price').value = product.price;
			if (product.quantity != 0)
				document.getElementById('quantity').value = product.quantity;
			document.getElementById('description').value = product.description;
		}
	});
}

function form(e) {

	e.preventDefault();

	var name = document.getElementById('product-name').value;
	var type = document.getElementById('type').value;
	var price = document.getElementById('product-price').value;
	var quantity = document.getElementById('quantity').value;
	var description = document.getElementById('description').value;

	if (name && type && price && quantity && description)
		editProduct(name, type, price, quantity, description)

	else
		alert("Please fill in all fields!")
}

function editProduct(name, type, price, quantity, description) {
	let data = {
		"name": name,
		"type": type,
		"price": price,
		"quantity": quantity,
		"description": description
	}

	$.post({
		url: 'webapi/products/editActiveProduct',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function(response) {

			//uploadLogo(name);
			alert("Product successfully edited")
			// Basic params edited
			window.location.href = "http://localhost:8080/PocetniREST/managerRestaurantItems.html";

		}
	});

}
