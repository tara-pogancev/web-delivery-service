document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/customers/getAllCustomers',
		success: function (response) {
			for (let user of response) {

				if (user.blocked == false) {

					newRowContent = `<tr>`
					newRowContent += `<td>` + user.id + `</td>`
					newRowContent += `<td>` + user.name + `</td>`
					newRowContent += `<td>` + user.lastName + `</td>`
					newRowContent += `<td>` + user.points + `</td>`
					newRowContent += `<td>` + user.customerStatus + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=confirmBan(\"` + user.id + `\") >Ban</a>` + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=confirmDelete(\"` + user.id + `\") >Delete</a>` + `</td>`

					$('#rest-table tbody').append(newRowContent);

				} else {

					newRowContent = `<tr bgcolor="#e1e3e3">`
					newRowContent += `<td>` + user.id + `</td>`
					newRowContent += `<td>` + user.name + `</td>`
					newRowContent += `<td>` + user.lastName + `</td>`
					newRowContent += `<td>` + user.points + `</td>`
					newRowContent += `<td>` + user.customerStatus + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=unbanUser(\"` + user.id + `\") >Unban</a>` + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=confirmDelete(\"` + user.id + `\") >Delete</a>` + `</td>`

					$('#rest-table tbody').append(newRowContent);

				}
			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">No customers.</td>`

				$('#rest-table tbody').append(newRowContent);
			}

		}
	});


	$.get({
		url: 'webapi/customers/getSusCustomers',
		success: function (response) {

			for (let user of response) {

				if (user.blocked == false) {

					newRowContent = `<tr>`
					newRowContent += `<td>` + user.id + `</td>`
					newRowContent += `<td>` + user.name + `</td>`
					newRowContent += `<td>` + user.lastName + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=confirmBan(\"` + user.id + `\") >Ban</a>` + `</td>`

					$('#rest-table-sus tbody').append(newRowContent);

				} else {

					newRowContent = `<tr bgcolor="#e1e3e3">`
					newRowContent += `<td>` + user.id + `</td>`
					newRowContent += `<td>` + user.name + `</td>`
					newRowContent += `<td>` + user.lastName + `</td>`
					newRowContent += `<td>` + user.points + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=unbanUser(\"` + user.id + `\") >Unban</a>` + `</td>`

					$('#rest-table-sus tbody').append(newRowContent);

				}
			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="5">No customers to show.</td>`

				$('#rest-table-sus tbody').append(newRowContent);
			}

		}
	});

}

function confirmDelete(username) {

	var response = confirm("Are you sure you want to delete user: " + username + "?");
	if (response == true)
		deleteUser(username);

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
		url: 'webapi/customers/banUser',
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
		url: 'webapi/customers/unbanUser',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {

			alert("User unbanned!");
			window.location.reload();
		}
	});

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
		"sort": document.getElementById('sort').value
	}

	$.post({
		url: 'webapi/customers/getFilteredSearch',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			$('#rest-table tbody').empty();
			for (let user of response) {

				if (user.blocked == false) {

					newRowContent = `<tr>`
					newRowContent += `<td>` + user.id + `</td>`
					newRowContent += `<td>` + user.name + `</td>`
					newRowContent += `<td>` + user.lastName + `</td>`
					newRowContent += `<td>` + user.points + `</td>`
					newRowContent += `<td>` + user.customerStatus + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=confirmBan(\"` + user.id + `\") >Ban</a>` + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=confirmDelete(\"` + user.id + `\") >Delete</a>` + `</td>`

					$('#rest-table tbody').append(newRowContent);

				} else {

					newRowContent = `<tr bgcolor="#e1e3e3">`
					newRowContent += `<td>` + user.id + `</td>`
					newRowContent += `<td>` + user.name + `</td>`
					newRowContent += `<td>` + user.lastName + `</td>`
					newRowContent += `<td>` + user.points + `</td>`
					newRowContent += `<td>` + user.customerStatus + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=unbanUser(\"` + user.id + `\") >Unban</a>` + `</td>`
					newRowContent += `<td>` + `<a href="#" onclick=confirmDelete(\"` + user.id + `\") >Delete</a>` + `</td>`

					$('#rest-table tbody').append(newRowContent);

				}

			}

			if (response.length === 0) {
				newRowContent = `<tr>`
				newRowContent += `<td colspan="6">No customers found.</td>`

				$('#rest-table tbody').append(newRowContent);
			}
		}
	});

}
