var rest;

document.addEventListener('DOMContentLoaded', function() {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeUserObject',
		contentType: 'application/json',
		success: function(manager) {
			if (manager.customerStatus == "INTRUDER") {
				window.location.href = "http://localhost:8080/PocetniREST/403Forbidden.html";
			}
			else {

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

				generateRestaurant();
			}
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

				generateComments(restaurant);
				generateCommentsReviewed(restaurant);
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

function generateComments(restaurant) {

	let data = {
		"name": restaurant.name
	}

	$.post({
		url: 'webapi/comments/getManagerComments',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(response) {

			$('#rest-table-comment tbody').empty();

			for (let comment of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + comment.author.id + `</td>`
				newRowContent += `<td>` + comment.rating + `</td > `
				newRowContent += `<td style="width: 34%">` + comment.text + `</td > `
				newRowContent += `<td>` + comment.state + `</td > `
				newRowContent += `<td>` + `<a onclick=approveComment(\"` + comment.id + `\")  href="http://localhost:8080/PocetniREST/profileManager.html">Approve</a>` + `</td>`
				newRowContent += `<td>` + `<a onclick=denyComment(\"` + comment.id + `\")  href="http://localhost:8080/PocetniREST/profileManager.html">Deny</a>` + `</td>`

				$('#rest-table-comment tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">There are no customer reviews.</td>`

				$('#rest-table-comment tbody').append(newRowContent);
			}

		}
	});
}

function generateCommentsReviewed(restaurant) {

	let data = {
		"name": restaurant.name
	}

	$.post({
		url: 'webapi/comments/getManagerCommentsReviewed',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(response) {

			$('#rest-table-comment-reviewed tbody').empty();

			for (let comment of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + comment.id + `</td>`
				newRowContent += `<td>` + comment.author.id + `</td>`
				newRowContent += `<td>` + comment.rating + `</td > `
				newRowContent += `<td style="width: 34%">` + comment.text + `</td > `
				newRowContent += `<td>` + comment.state + `</td > `

				$('#rest-table-comment-reviewed tbody').append(newRowContent);

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">There are no customer reviews.</td>`

				$('#rest-table-comment-reviewed tbody').append(newRowContent);
			}

		}
	});
}

function approveComment(id) {
	let data = {
		"id": id
	}

	$.post({
		url: 'webapi/comments/approveComment',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(response) {
			alert(response);
		}
	});




}

function denyComment(id) {

	let data = {
		"id": id
	}

	$.post({
		url: 'webapi/comments/denyComment',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(response) {
			alert(response);
		}
	});
}