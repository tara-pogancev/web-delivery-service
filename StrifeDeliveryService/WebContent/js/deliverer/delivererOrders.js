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
