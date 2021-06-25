$(document).ready(function(){

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/restaurant/getAllDTO',
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
				newRowContent += `<a href="#" class="main-button">Preview</a>`
				newRowContent += `</div>`
				newRowContent += `</div>`
				newRowContent += `</div>`
				$('#restaurants').append(newRowContent);

			}

		}
	});

}