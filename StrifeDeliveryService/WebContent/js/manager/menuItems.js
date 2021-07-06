document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

var restaurantName = ""

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeManagerRestaurant',
		success: function (restaurant) {

			restaurantName = restaurant.name;
			generateItems();

		}
	});

}

function generateItems() {

	let data = {
		"name": restaurantName
	}

	$.post({
		url: 'webapi/editRestaurant/getRestaurantItems',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			for (let product of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + product.id + `</td>`
				newRowContent += `<td>` + product.name + `</td>`
				newRowContent += `<td>` + product.type + `</td>`
				newRowContent += `<td>` + product.price + `</td>`
				newRowContent += `<td>` + `<a href="#" onclick=editProduct(\"` + product.id + `\") >Edit</a>` + `</td>`

				$('#rest-table tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">No products yet.</td>`

				$('#rest-table tbody').append(newRowContent);
			}

		}
	});
}


function editProduct(prodId) {

	let data = {
		"id": prodId
	}

	
	$.post({
		url: 'webapi/products/setActiveProduct',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			window.location.href = "http://localhost:8080/PocetniREST/editMenuItem.html";

		}
	});

}
