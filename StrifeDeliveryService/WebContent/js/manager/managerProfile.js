document.addEventListener('DOMContentLoaded', function() {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeUserObject',
		contentType: 'application/json',
		success: function(manager) {

			newRowContent = `<p><b>Username:</b> ` + manager.id + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>First name:</b> ` + manager.name + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Last name:</b> ` + manager.lastName + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Gender:</b> ` + manager.gender.toUpperCase() + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Date of birth:</b> ` + manager.dateOfBirth + `</p>`
			newRowContent += `<div class="r-gap"></div>`

			$('#user-data').append(newRowContent);

			$('#welcomeUser').text("Welcome, " + manager.name);

			generateRestaurant();

		}
	});

}

function generateRestaurant() {

	$.get({
		url: 'webapi/login/activeManagerRestaurant',
		contentType: 'application/json',
		success: function(restaurant) {
			if (restaurant.name === "NO_RESTAURANT") {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6" >No restaurant assigned.</td>`

				$('#rest-table tbody').append(newRowContent)

				$('#b1').attr('disabled', 'disabled')
				$('#b2').attr('disabled', 'disabled')
				$('#b3').attr('disabled', 'disabled')
				$('#b4').attr('disabled', 'disabled')
				$('#b5').attr('disabled', 'disabled')
				$('#b6').attr('disabled', 'disabled')

			} else {
				
				setCurrentRestaurantView(restaurant.name);
				
				newRowContent = `<tr>`
				newRowContent += `<td class="td-center"><a href="restaurantView.html"><img src="images/logos/` + restaurant.name + `.png"></a></td>"`
				newRowContent += `<td>` + restaurant.name + `</td>`
				newRowContent += `<td>` + restaurant.address + `</td>`
				newRowContent += `<td>` + restaurant.rating + `</td>`
				newRowContent += `<td>` + restaurant.type + `</td>`
				
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
		contentType: 'application/json'
	});
}