document.addEventListener('DOMContentLoaded', function() {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeUserObject',
		contentType: 'application/json',
		success: function(user) {


			if (user.customerStatus == "INTRUDER") {
				window.location.href = "http://localhost:8080/PocetniREST/403Forbidden.html";
			}
			else {

				newRowContent = `<p><b>Username:</b> ` + user.id + `</p>`
				newRowContent += `<div class="r-gap"></div>`
				newRowContent += `<p><b>First name:</b> ` + user.name + `</p>`
				newRowContent += `<div class="r-gap"></div>`
				newRowContent += `<p><b>Last name:</b> ` + user.lastName + `</p>`
				newRowContent += `<div class="r-gap"></div>`
				newRowContent += `<p><b>Gender:</b> ` + user.gender.toUpperCase() + `</p>`
				newRowContent += `<div class="r-gap"></div>`
				newRowContent += `<p><b>Date of birth:</b> ` + user.dateOfBirth + `</p>`
				newRowContent += `<div class="r-gap"></div>`

				$('#user-data').append(newRowContent);

				$('#welcomeUser').text("Welcome, " + user.name);

				generateComments();
			}
		}
	});

}
