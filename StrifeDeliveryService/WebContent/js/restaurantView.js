$(document).ready(function () {

	getDataFromServer();

}, false);

var restaurantStatus;

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

			getProducts(restaurant.name)

			restaurantStatus = restaurant.status;
		}
	});

}

function getProducts(restName) {

	let data = {
		"name": restName
	}

	$.post({
		url: 'webapi/products/getProductsByRestaurant',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (products) {

			if (products.length === 0) {
				printEmptyRestaurant();
			}

			if (restaurantStatus === "CLOSED")
				printClosedRestaurant(products)

			else
				printOpenRestaurant(products)


		}

	});

}

function printClosedRestaurant(products) {
	for (product of products) {

		newRowContent = `<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 features-restaurant-container"
					data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">`
		newRowContent += `<div class="features-restaurant">`
		newRowContent += `<div class="features-icon">`
		newRowContent += `<div class="features-restaurant-picture">`
		newRowContent += `<img src="images/menuitems/` + product.id + `.jpg">`
		newRowContent += `</div>`

		if (product.quantity === 0)
			newRowContent += `<h4>` + product.name + `, $` + product.price + `</h4>`
		else
			if (product.type === "FOOD")
				newRowContent += `<h4>` + product.name + ` ` + product.quantity + `g, $` + product.price + `</h4>`
			else
				newRowContent += `<h4>` + product.name + ` ` + product.quantity + `ml, $` + product.price + `</h4>`

		newRowContent += `<p style="margin: 0px 30px;">` + product.description + `</p>`
		newRowContent += `</div>`
		newRowContent += `</div>`
		newRowContent += `</div>`

		$('#menu-items').append(newRowContent);

	}
}

function printOpenRestaurant(products) {
	for (product of products) {

		newRowContent = `<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 features-restaurant-container"
					data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">`
		newRowContent += `<div class="features-restaurant">`
		newRowContent += `<div class="features-icon">`
		newRowContent += `<div class="features-restaurant-picture">`
		newRowContent += `<img src="images/menuitems/` + product.id + `.jpg">`
		newRowContent += `</div>`
		if (product.quantity === 0)
			newRowContent += `<h4>` + product.name + `, $` + product.price + `</h4>`
		else
			if (product.type === "FOOD")
				newRowContent += `<h4>` + product.name + ` ` + product.quantity + `g, $` + product.price + `</h4>`
			else
				newRowContent += `<h4>` + product.name + ` ` + product.quantity + `ml, $` + product.price + `</h4>`

		newRowContent += `<p style="margin: 0px 30px;">` + product.description + `</p>`
		newRowContent += `<a href="#" onclick=addToCart(\"` + product.id + `\") class="main-button">Add to cart</a>`
		newRowContent += `<div><label style="color: #a6a6a6;">Quantity: </label>`
		newRowContent += `<input class="cart-number-input" type="number" value="1" max="10" min="1"  id="amount-` + product.id + `">`
		newRowContent += `</div>`
		newRowContent += `</div>`
		newRowContent += `</div>`
		newRowContent += `</div>`

		$('#menu-items').append(newRowContent);

	}
}

function printEmptyRestaurant() {

	newRowContent = `<p>No items to show.</p>`

	$('#menu-items').append(newRowContent);

}

function addToCart(id) {

	$.get({
		url: 'webapi/login/activeUser',
		contentType: 'application/json',
		success: function (response) {
			switch (response.name) {
				case "CUSTOMER":		
					var string = "amount-" + id			
					var amount = document.getElementById(string).value;
					addItem(id, amount, response.id);
					break;
				case "ADMIN":
					window.location.href = "http://localhost:8080/PocetniREST/401Unauthorized.html";
					break;
				case "MANAGER":
					window.location.href = "http://localhost:8080/PocetniREST/401Unauthorized.html";
					break;
				case "DELIVERER":
					window.location.href = "http://localhost:8080/PocetniREST/401Unauthorized.html";
					break;
				default:
					window.location.href = "http://localhost:8080/PocetniREST/login.html";
			}
		}
	});
}

function addItem(id, amount, username) {

	let data = {
		id: username
	}

	$.post({
		url: 'webapi/cart/setActiveUser',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

		}
	});

	let data2 = {
		productId: id,
		amount: amount
	}

	$.post({
		url: 'webapi/cart/addCartItem',
		data: JSON.stringify(data2),
		contentType: 'application/json',
		success: function (response) {
			alert("Item(s) added to cart!")
			window.location.reload;
		}
	});


}

