window.onload = getDataFromServer();
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
				url: 'webapi/orders/setActiveUser',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function (response) {
					generateProcessOrders(activeUsername);
					generatePastOrders(activeUsername);
				}
			});

		}
	});
}

function generateProcessOrders(username) {

	$.get({
		url: 'webapi/orders/getProcessingOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-process tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.restaurant.name + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + `24/05/2018` + `</td > `
				newRowContent += `<td><a href="#" onclick=cancelOrder(\"` + order.id + `\")>Cancel</a></td>`

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


function generatePastOrders(username) {

	$.get({
		url: 'webapi/orders/getPastOrders',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table-past tbody').empty();

			for (let order of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + order.id + `</td>`
				newRowContent += `<td>` + order.status + `</td>`
				newRowContent += `<td>` + order.restaurant.name + `</td>`
				newRowContent += `<td>` + order.price + `</td > `
				newRowContent += `<td>` + `24/05/2018` + `</td > `

				$('#rest-table-past tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">No orders to show.</td>`

				$('#rest-table-past tbody').append(newRowContent);
			}


		}
	});

}