window.onload = getDataFromServer();
var activeUsername = "";

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

			$('#points').text("Points: " + profile.points);
			$('#category').text("Category: " + profile.customerStatus);
			$('#discount').text("Discount: " + profile.discount + "%");

			activeUsername = profile.id;
			generateCart(activeUsername);
			
			let data = {
				id: activeUsername
			}
			
			$.post({
				url: 'webapi/orders/setActiveUser',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function (response) {
					generateDeliveredOrders(activeUsername);
				}
			});

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
				newRowContent += `<td><input class="cart-number-input" type="number" id="input-` + item.product.id + `" onchange=changeAmount(\"` + item.product.id + `\") 
				min="1" value="`+ item.amount + `"></td>`
				newRowContent += `<td>` + item.product.price + `</td>`
				newRowContent += `<td>` + (item.product.price * item.amount) + `</td>`
				newRowContent += `<td><a href="#" onclick=removeItem(\"` + item.product.id + `\")>Remove</a></td>`

				$('#rest-table tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No items in cart.</td>`

				$('#rest-table tbody').append(newRowContent);
				$('#order-button').attr('disabled', 'disabled')
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

function changeAmount(id) {

	var string = "input-" + id

	let data = {
		productId: id,
		amount: document.getElementById(string).value
	}

	$.post({
		url: 'webapi/cart/updateItem',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			$('#rest-table tbody').empty();
			generateCart(activeUsername);

		}
	});

}

function placeOrder() {

	if (confirm('Are you sure you want to place your order(s)?')) {

		let data = {
			id: activeUsername
		}

		$.post({
			url: 'webapi/orders/setActiveUser',
			data: JSON.stringify(data),
			contentType: 'application/json',
			success: function (response) {
			}
		});

		$.post({
			url: 'webapi/orders/makeOrders',
			data: JSON.stringify(data),
			contentType: 'application/json',
			success: function (response) {
				window.location.href = "http://localhost:8080/PocetniREST/customerOrders.html";
			}
		});


	}
}

function generateDeliveredOrders(username) {

	$.get({
		url: 'webapi/orders/getDeliveredOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-comment tbody').empty();
			
			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.restaurantName + `</td>`
				newRowContent += `<td>` + order.date + `</td > `
				newRowContent += `<td>` + `<a href="http://localhost:8080/PocetniREST/addComment.html">AddComment</a>` + `</td>`

				$('#rest-table-comment tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">No orders to review.</td>`

				$('#rest-table-comment tbody').append(newRowContent);
			}


		}
	});

}