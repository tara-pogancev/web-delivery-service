document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/restaurant/getAllDTO',
		contentType: 'application/json',
		success: function (response) {
			for (let restaurant of response) {

				newRowContent = `<tr>`
				newRowContent += `<td class="td-center"><a href="#" onclick=setCurrentRestaurantView(\"` + restaurant.name + `\")><img src="images/logos/` + restaurant.name + `.png"></a></td>"`
				newRowContent += `<td>` + restaurant.name + `</td>`
				newRowContent += `<td>` + restaurant.address + `</td>`
				newRowContent += `<td>` + restaurant.rating + `</td>`
				newRowContent += `<td>` + restaurant.type + `</td>`

				$('#rest-table tbody').append(newRowContent);

			}

		}
	});

}


function doSearch() {

	let data = {
		"text": document.getElementById('searchField').value,
		"selection": document.getElementById('type').value,
		"checkbox": document.getElementById('open-only').checked
	}

	$.post({
		url: 'webapi/restaurant/getFilteredSearch',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table tbody').empty();
			for (let restaurant of response) {

				newRowContent = `<tr>`
				newRowContent += `<td class="td-center"><a href="#" onclick=setCurrentRestaurantView(\"` + restaurant.name + `\")><img src="images/logos/` + restaurant.name + `.png"></a></td>"`
				newRowContent += `<td>` + restaurant.name + `</td>`
				newRowContent += `<td>` + restaurant.address + `</td>`
				newRowContent += `<td>` + restaurant.rating + `</td>`
				newRowContent += `<td>` + restaurant.type + `</td>`

				$('#rest-table tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">No restaurants found.</td>`

				$('#rest-table tbody').append(newRowContent);
			}
		}
	});

}

function setCurrentRestaurantView(name) {

	let data = {
		"name": name
	}

	$.post({
		url: 'webapi/restaurantView/setCurrentRestaurant',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			window.location.href = "http://localhost:8080/PocetniREST/restaurantView.html";
		}
	});
}
