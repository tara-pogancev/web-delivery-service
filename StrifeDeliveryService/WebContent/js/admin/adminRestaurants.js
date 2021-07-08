document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/restaurant/getAllDTO',
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table tbody').empty();
			for (let restaurant of response) {

				newRowContent = `<tr>`
				newRowContent += `<td class="td-center"><a href="#" onclick=setCurrentRestaurantView(\"` + restaurant.name + `\")><img src="images/logos/` + restaurant.name + `.png"></a></td>"`
				newRowContent += `<td>` + restaurant.name + `</td>`
				newRowContent += `<td>` + restaurant.address + `</td>`
				newRowContent += `<td><a href="#" onclick=addManager(\"` + restaurant.name + `\") >Add Manager</a></td>"`
				newRowContent += `<td><a href="#" onclick=deleteRestaurant(\"` + restaurant.name + `\") >Delete</a></td>"`

				$('#rest-table tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No restaurants found.</td>`

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
				newRowContent += `<td><a href="#" onclick=addManager(\"` + restaurant.name + `\") >Add Manager</a></td>"`
				newRowContent += `<td><a href="#" onclick=deleteRestaurant(\"` + restaurant.name + `\") >Delete</a></td>"`

				$('#rest-table tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No restaurants found.</td>`

				$('#rest-table tbody').append(newRowContent);
			}
		}
	});

}

function addManager(id) {

	let data = {
		"name": id
	}

	$.post({
		url: 'webapi/editRestaurant/setRestaurantById',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			window.location.href = "http://localhost:8080/PocetniREST/adminAddNewRestaurantManager.html";
		}
	})

}

function deleteRestaurant(id) {

	if (confirm("Are you sure you want to delete restaurant " + id + "?")) {

		let data = {
			"name": id
		}

		$.post({
			url: 'webapi/editRestaurant/deleteRestaurant',
			data: JSON.stringify(data),
			contentType: 'application/json',
			success: function (response) {
				alert("Restaurant deleted!")
				window.location.reload;
			}
		})
	}
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
