$(document).ready(function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/restaurantView/getCurrentRestaurant',
		contentType: 'application/json',
		success: function (restaurant) {

			$('#restaurantName').text(restaurant.name);

			newRowContent = `<h4>` + restaurant.status + `</h4>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Type: </b>` + restaurant.type + `</p>`
			newRowContent += `<p><b>City: </b>` + restaurant.city + `, ` + restaurant.postal + `</p>`
			newRowContent += `<p><b>City: </b>` + restaurant.address + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			if (restaurant.rating === "-")
				newRowContent += `<h5>Restaurant not rated yet.</h5>`

			else
				newRowContent += `<h5>User rating: ` + restaurant.rating + `/5</h5>`

			$('#restaurant-data').append(newRowContent);

			newRowContent = `<img src="images/logos/` + restaurant.name + `.png" class="r-center"
							style="max-height: 200px; max-width: 300px;">`

			$('#restaurant-logo').append(newRowContent);

		}
	});

}
