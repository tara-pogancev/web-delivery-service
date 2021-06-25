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
		alert("Please select a manager or create a new one.")
	else
		setManager(manager)

}

function setManager(manager) {

	let data = {
		"id": manager
	}

	$.post({
		url: 'webapi/newRestaurant/setManager',
		data: JSON.stringify(data),
		contentType: 'application/json',
		success: function (response) {
			alert("Restaurant successfully created!")
			window.location.href = "http://localhost:8080/PocetniREST/";
		}
	});

}