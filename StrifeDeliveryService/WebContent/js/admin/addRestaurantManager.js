document.addEventListener('DOMContentLoaded', function () {

	var submitInput = document.getElementById('form-submit')
	submitInput.addEventListener('click', form, false);
	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/manager/getAvailableManagers',
		contentType: 'application/json',
		success: function (response) {
			for (let manager of response)
				$('#manager-sel').append($('<option>', {
					value: manager.id,
					text: manager.name
				}));
		}
	});

}

function form() {

	var manager = document.getElementById('manager-sel').value;

	if (manager == "null")
		alert("Please select a manager.")
	else
		setManager(manager)

}

function setManager(manager) {

	let data = {
		"id": manager
	}

	$.post({
		url: 'webapi/editRestaurant/addManager',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			alert("Manager successfully added!")
			window.location.href = "http://localhost:8080/PocetniREST/adminRestaurants.html";
		}
	});

}