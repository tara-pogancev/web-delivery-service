document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeManagerRestaurant',
		success: function (restaurant) {

			let data = {
				"name": restaurant.name
			}


			$.post({
				url: 'webapi/orders/getCustomersForRestaurant',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function (response) {
					for (let user of response) {

						if (user.blocked == false) {

							newRowContent = `<tr>`
							newRowContent += `<td>` + user.id + `</td>`
							newRowContent += `<td>` + user.name + `</td>`
							newRowContent += `<td>` + user.lastName + `</td>`
							newRowContent += `<td>` + user.points + `</td>`
							newRowContent += `<td>` + user.customerStatus + `</td>`

							$('#rest-table tbody').append(newRowContent);

						} else {

							newRowContent = `<tr bgcolor="#e1e3e3">`
							newRowContent += `<td>` + user.id + `</td>`
							newRowContent += `<td>` + user.name + `</td>`
							newRowContent += `<td>` + user.lastName + `</td>`
							newRowContent += `<td>` + user.points + `</td>`
							newRowContent += `<td>` + user.customerStatus + `</td>`

							$('#rest-table tbody').append(newRowContent);

						}
					}

					if (response.length === 0) {
						newRowContent = `<tr>`
						newRowContent += `<td colspan="6">No customers.</td>`

						$('#rest-table tbody').append(newRowContent);
					}

				}
			});

		}
	});

}
