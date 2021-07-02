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
			newRowContent = `<tr>`
			newRowContent += `<td class="td-center"><a href="#"><img src="images/logos/` + restaurant.name + `.png"></a></td>"`
			newRowContent += `<td>` + restaurant.name + `</td>`
			newRowContent += `<td>` + restaurant.address + `</td>`
			newRowContent += `<td>` + restaurant.rating + `</td>`
			newRowContent += `<td>` + restaurant.type + `</td>`

			$('#rest-table tbody').append(newRowContent);
		}

	});
}