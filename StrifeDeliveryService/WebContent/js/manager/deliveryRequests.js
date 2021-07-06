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
					generateRequests()
				}
			});

		}
	});
}

function generateRequests() {

	$.get({
		url: 'webapi/managerOrders/getRequests',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.deliverer + `</td>`
				newRowContent += `<td>` + order.customer + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + order.date + `</td > `
				newRowContent += `<td>` + `<a href="#" onclick=acceptRequest(\"` + order.id + order.delivererId + `\") >Accept</a>` + `</td>`
				newRowContent += `<td>` + `<a href="#" onclick=declineRequest(\"` + order.id + order.delivererId + `\") >Decline</a>` + `</td>`

				$('#rest-table tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No requests to show.</td>`

				$('#rest-table tbody').append(newRowContent);
			}
		}
	});

}

function acceptRequest(requestId) {

	let data = {
		"id": requestId
	}

	$.post({
		url: 'webapi/managerOrders/acceptRequest',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("Request accepted!")
			generateRequests()
		}
	});

}

function declineRequest(requestId) {

		let data = {
		"id": requestId
	}

	$.post({
		url: 'webapi/managerOrders/declineRequest',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("Request declined!")
			generateRequests()
		}
	});

}