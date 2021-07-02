document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/users/getAllForAdmin',
		contentType: 'application/json',
		success: function (response) {
			printTable(response);
		}
	});

}

function confirmDelete(username) {

	var response = confirm("Are you sure you want to delete user: " + username + "?");
	if (response == true)
		deleteUser(username);

}

function deleteUser(username) {

	let data = {
		"id": username
	}

	$.post({
		url: 'webapi/users/deleteUser',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("User deleted!");
			window.location.reload();
		}
	});

}

function doSearch() {

	let data = {
		"text": document.getElementById('searchField').value,
		"selection": document.getElementById('type').value
	}

	$.post({
		url: 'webapi/users/getFilteredSearch',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			printTable(response);

		}
	});

}


function confirmBan(username) {

	var response = confirm("Are you sure you want to ban user: " + username + "?");
	if (response == true)
		banUser(username);

}


function banUser(username) {

	let data = {
		"id": username
	}

	$.post({
		url: 'webapi/users/banUser',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("User banned!");
			window.location.reload();
		}
	});

}


function unbanUser(username) {

	let data = {
		"id": username
	}

	$.post({
		url: 'webapi/users/unbanUser',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("User unbanned!");
			window.location.reload();
		}
	});

}

function printTable(response) {

	$('#rest-table tbody').empty();
	for (let user of response) {

		if (user.blocked == false) {

			newRowContent = `<tr>`
			newRowContent += `<td>` + user.category + `</td>`
			newRowContent += `<td>` + user.id + `</td>`
			newRowContent += `<td>` + user.name + `</td>`
			newRowContent += `<td>` + user.lastName + `</td>`
			newRowContent += `<td>` + user.password + `</td>`
			newRowContent += `<td>` + `<a href="#" onclick=confirmBan(\"` + user.id + `\") >Ban</a>` + `</td>`
			newRowContent += `<td>` + `<a href="#" onclick=confirmDelete(\"` + user.id + `\") >Delete</a>` + `</td>`

			$('#rest-table tbody').append(newRowContent);

		} else {

			newRowContent = `<tr bgcolor="#e1e3e3">`
			newRowContent += `<td>` + user.category + `</td>`
			newRowContent += `<td>` + user.id + `</td>`
			newRowContent += `<td>` + user.name + `</td>`
			newRowContent += `<td>` + user.lastName + `</td>`
			newRowContent += `<td>` + user.password + `</td>`
			newRowContent += `<td>` + `<a href="#" onclick=unbanUser(\"` + user.id + `\") >Unban</a>` + `</td>`
			newRowContent += `<td>` + `<a href="#" onclick=confirmDelete(\"` + user.id + `\") >Delete</a>` + `</td>`

			$('#rest-table tbody').append(newRowContent);

		}

		if (response.length === 0) {
			newRowContent = `<tr>`
			newRowContent += `<td colspan="6">No users found.</td>`

			$('#rest-table tbody').append(newRowContent);
		}


	}
}
