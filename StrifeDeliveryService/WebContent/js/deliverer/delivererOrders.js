$(document).ready(function () {

	getDataFromServer();

}, false);

var activeUsername = "";

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeUserObject',
		contentType: 'application/json',
		success: function (profile) {

			activeUsername = profile.id;

			let data = {
				id: activeUsername
			}

			$.post({
				url: 'webapi/delOrders/setActiveDeliverer',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function (response) {
					generateAvailableOrders()
					generateActiveOrders()
					generateAllOrders()
				}
			});

		}
	});
}

function generateAvailableOrders() {

	$.get({
		url: 'webapi/delOrders/getAvailableOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-available tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.restaurantName + `</td>`
				newRowContent += `<td>` + order.customer + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + order.date + `</td > `
				newRowContent += `<td>` + `<a href="#" onclick=request(\"` + order.id + `\") >Request</a>` + `</td>`

				$('#rest-table-available tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No orders to show.</td>`

				$('#rest-table-available tbody').append(newRowContent);
			}
		}
	});
}

function generateActiveOrders() {

	$.get({
		url: 'webapi/delOrders/getActiveOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-active tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.restaurantName + `</td>`
				newRowContent += `<td>` + order.customer + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + order.date + `</td > `
				newRowContent += `<td>` + `<a href="#" onclick=deliver(\"` + order.id + `\") >Deliver</a>` + `</td>`

				$('#rest-table-active tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No orders to show.</td>`

				$('#rest-table-active tbody').append(newRowContent);
			}
		}
	});
}

function generateAllOrders() {

	$.get({
		url: 'webapi/delOrders/getAllOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-all tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.restaurantName + `</td>`
				newRowContent += `<td>` + order.customer + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + order.date + `</td > `

				$('#rest-table-all tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No orders to show.</td>`

				$('#rest-table-all tbody').append(newRowContent);
			}
		}
	});
}

function request(id) {

	let data = {
		"id": id
	}

	$.post({
		url: 'webapi/delOrders/requestOrder',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			alert("Order successfully requested!");

			generateActiveOrders();
			generateAvailableOrders();
			generateAllOrders();
			doSearch();
		}
	});
}

function deliver(id) {

	let data = {
		"id": id
	}

	$.post({
		url: 'webapi/delOrders/deliverOrder',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			alert("Order successfully delivered!");

			generateActiveOrders();
			generateAvailableOrders();
			generateAllOrders();
			doSearch();
		}
	});
}

function doSearch() {

	let data = {
		"searchField": document.getElementById('searchField').value,
		"sort": document.getElementById('sort').value,
		"status": document.getElementById('type').value,
		"priceMin": document.getElementById('price-min').value,
		"priceMax": document.getElementById('price-max').value,
		"restType": document.getElementById('type-rest').value,
		"startDate": document.getElementById('start-date').value,
		"endDate": document.getElementById('end-date').value
	}

	$.post({
		url: 'webapi/delOrders/getFilteredSearchDel',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-all tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.restaurantName + `</td>`
				newRowContent += `<td>` + order.customer + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + order.date + `</td > `

				$('#rest-table-all tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No orders to show.</td>`

				$('#rest-table-all tbody').append(newRowContent);
			}
		}
	});

}