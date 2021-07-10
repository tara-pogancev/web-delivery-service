document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeUserObject',
		contentType: 'application/json',
		success: function (admin) {

			newRowContent = `<p><b>Username:</b> ` + admin.id + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>First name:</b> ` + admin.name + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Last name:</b> ` + admin.lastName + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Gender:</b> ` + admin.gender.toUpperCase() + `</p>`
			newRowContent += `<div class="r-gap"></div>`
			newRowContent += `<p><b>Date of birth:</b> ` + admin.dateOfBirth + `</p>`
			newRowContent += `<div class="r-gap"></div>`

			$('#user-data').append(newRowContent);

			$('#welcomeUser').text("Welcome, " + admin.name);

			generateComments();

		}
	});
}

function generateComments() {
	
	$.get({
		url: 'webapi/comments/getAll',
		contentType: 'application/json',
		success: function (response) {

			$('#rest-table-comment tbody').empty();

			for (let comment of response) {

				newRowContent = `<tr>`
				newRowContent += `<td>` + comment.id + `</td>`
				newRowContent += `<td>` + comment.author.id + `</td>`
				newRowContent += `<td>` + comment.restaurant.name + `</td>`
				newRowContent += `<td>` + comment.rating + `</td > `
				newRowContent += `<td style="width: 34%">` + comment.text + `</td > `
				newRowContent += `<td>` + comment.state + `</td > `

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
