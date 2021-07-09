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
				url: 'webapi/managerOrders/setActiveManager',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function (response) {
					generateProcessOrders()
					generatePreparationOrders()
					generateAllOrders()
				}
			});

		}
	});
}

function generateProcessOrders() {

	$.get({
		url: 'webapi/managerOrders/getProcessOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-process tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.customer + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + order.date + `</td > `
				newRowContent += `<td>` + `<a href="#" onclick=prepare(\"` + order.id + `\") >Prepare</a>` + `</td>`

				$('#rest-table-process tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No orders to show.</td>`

				$('#rest-table-process tbody').append(newRowContent);
			}


		}
	});

}


function generatePreparationOrders() {

	$.get({
		url: 'webapi/managerOrders/getPreparationOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-past tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.customer + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + order.date + `</td > `
				newRowContent += `<td>` + `<a href="#" onclick=readyOrder(\"` + order.id + `\") >Ready</a>` + `</td>`

				$('#rest-table-past tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No orders to show.</td>`

				$('#rest-table-past tbody').append(newRowContent);
			}

		}
	});

}

function prepare(orderId) {

	let data = {
		"id": orderId
	}

	$.post({
		url: 'webapi/managerOrders/prepareOrder',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("Order in preparation.")
			//window.location.href = "http://localhost:8080/PocetniREST/customerOrders.html";
			generatePreparationOrders()
			generateProcessOrders()
			generateAllOrders()

		}
	});
}

function readyOrder(orderId) {

	let data = {
		"id": orderId
	}

	$.post({
		url: 'webapi/managerOrders/readyOrder',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("Order awaiting deliverer.")
			//window.location.href = "http://localhost:8080/PocetniREST/managerOrders.html";
			generatePreparationOrders()
			generateProcessOrders()
			generateAllOrders()

		}
	});
}

function generateAllOrders() {

	$.get({
		url: 'webapi/managerOrders/getAllOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-all tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
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

function doSearch() {

	alert("search");

	// let data = {
	// 	"sort": document.getElementById('sort').value,
	// 	"status": document.getElementById('type').value,
	// 	"priceMin": document.getElementById('price-min').value,
	// 	"priceMax": document.getElementById('price-max').value,
	// 	"startDate": document.getElementById('start-date').value,
	// 	"endDate": document.getElementById('end-date').value
	// }

	// $.post({
	// 	url: 'webapi/managerOrders/getFilteredSearchManager',
	// 	data: JSON.stringify(data),
	// 	contentType: 'application/json',
	// 	success: function (response) {
	// 		$('#rest-table-all tbody').empty();

	// 		for (let order of response) {

	// 			newRowContent = `<tr>`
	// 			newRowContent += `<td>` + order.id + `</td>`
	// 			newRowContent += `<td>` + order.status + `</td>`
	// 			newRowContent += `<td>` + order.customer + `</td>`
	// 			newRowContent += `<td>` + order.price + `</td > `
	// 			newRowContent += `<td>` + order.date + `</td > `

	// 			$('#rest-table-all tbody').append(newRowContent);

	// 		}

	// 		if (response.length === 0) {
	// 			newRowContent = `<tr>`
	// 			newRowContent += `<td colspan="5">No orders to show.</td>`

	// 			$('#rest-table-all tbody').append(newRowContent);
	// 		}
	// 	}
	// });

}