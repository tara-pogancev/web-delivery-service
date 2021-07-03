$(document).ready(function () {

	getDataFromServerOpen();
	getDataFromServerClosed();

}, false);

function getDataFromServerOpen() {

	$.get({
		url: 'webapi/restaurant/getAllOpen',
		contentType: 'application/json',
		success: function (response) {
			for (let restaurant of response) {

				newRowContent = `<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 features-restaurant-container" data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">`
				newRowContent += `<div class="features-restaurant">`
				newRowContent += `<div class="features-icon">`
				newRowContent += `<div class="features-restaurant-picture">`
				newRowContent += `<img src="images/restaurants/` + restaurant.type + `Type.jpg">`
				newRowContent += `</div>`
				newRowContent += `<h4>` + restaurant.name + `</h4>`
				newRowContent += `<p>` + restaurant.status + `</p>`
				newRowContent += `<a href="#" onclick=setCurrentRestaurantView(\"` + restaurant.name + `\") class="main-button">Preview</a>`
				newRowContent += `</div>`
				newRowContent += `</div>`
				newRowContent += `</div>`
				$('#restaurants').append(newRowContent);

			}

			if (response.length === 0) {

				newRowContent = `<p>No restaurants.</p>`

				$('#restaurants').append(newRowContent);
			}

		}
	});

}


function getDataFromServerClosed() {

	$.get({
		url: 'webapi/restaurant/getAllClosed',
		contentType: 'application/json',
		success: function (response) {
			for (let restaurant of response) {

				newRowContent = `<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 features-restaurant-container" data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">`
				newRowContent += `<div class="features-restaurant">`
				newRowContent += `<div class="features-icon">`
				newRowContent += `<div class="features-restaurant-picture">`
				newRowContent += `<img src="images/restaurants/` + restaurant.type + `Type.jpg">`
				newRowContent += `</div>`
				newRowContent += `<h4>` + restaurant.name + `</h4>`
				newRowContent += `<p>` + restaurant.status + `</p>`
				newRowContent += `<a href="#" onclick=setCurrentRestaurantView(\"` + restaurant.name + `\") class="main-button">Preview</a>`
				newRowContent += `</div>`
				newRowContent += `</div>`
				newRowContent += `</div>`
				$('#restaurants-closed').append(newRowContent);

			}

			if (response.length === 0) {

				newRowContent = `<p>No restaurants.</p>`


				$('#restaurants-closed').append(newRowContent);
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