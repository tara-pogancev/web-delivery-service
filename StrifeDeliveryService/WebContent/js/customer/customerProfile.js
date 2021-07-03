window.onload = getDataFromServer();

document.addEventListener('DOMContentLoaded', function () {

	//getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeUserObject',
		contentType: 'application/json',
		success: function (profile) {

			newRowContent = `<p><b>Username:</b> ` + profile.id + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>First name:</b> ` + profile.name + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Last name:</b> ` + profile.lastName + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Gender:</b> ` + profile.gender.toUpperCase() + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Date of birth:</b> ` + profile.dateOfBirth + `</p>`
			newRowContent += `<div class="r-gap"></div>`

			$('#user-data').append(newRowContent);

			$('#welcomeUser').text("Welcome, " + profile.name);

			generateCart(profile.id);

		}
	});

}

function generateCart(username) {

	let data = {
		id: username
	}

	$.post({
		url: 'webapi/cart/setActiveUser',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

		}
	});

	$.get({
		url: 'webapi/cart/getUserCartItems',
		contentType: 'application/json',
		success: function (response) {
			for (let item of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + item.product.name + `</td>`
				newRowContent += `<td>` + item.amount + `</td>`
				newRowContent += `<td>` + item.product.price + `</td>`
				newRowContent += `<td>` + (item.product.price * item.amount) + `</td>`
				newRowContent += `<td><a href="#" onclick=removeItem(\"` + item.product.id + `\")>Remove</a></td>`

				$('#rest-table tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No items in cart.</td>`

				$('#rest-table tbody').append(newRowContent);
			}


		}
	});


	$.get({
		url: 'webapi/cart/getUserCart',
		contentType: 'application/json',
		success: function (cart) {

			$('#totalPrice').text("Total price:  $" + cart.totalPrice);

		}
	});

}

function removeItem(id) {

	if (confirm('Are you sure you want to remove this item from cart?')) {

		let data = {
			productId: id
		}

		$.post({
			url: 'webapi/cart/removeItem',
			data: JSON.stringify(data),
			contentType: 'application/json',
			success: function (response) {

				window.location.reload();

			}
		});

	}


}
