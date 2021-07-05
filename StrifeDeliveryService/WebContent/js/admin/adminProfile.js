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

}

function acceptComment(commentId) {

}
function declineComment(commentId) {
	
}