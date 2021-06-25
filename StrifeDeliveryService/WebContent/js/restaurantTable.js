document.addEventListener('DOMContentLoaded', function () {

	getDataFromServer();

}, false);

function getDataFromServer() {

	$.get({
		url: 'webapi/restaurant/getAllDTO',
		contentType: 'application/json',
		success: function (response) {
			for (let restaurant of response) {

				newRowContent = `<tr>`
				newRowContent += `<td class="td-center"><a href="#"><img src="images/logos/Yummyyy.png"></a></td>"`
				newRowContent += `<td>` + restaurant.name + `</td>`
				newRowContent += `<td>` + restaurant.address + `</td>`
				newRowContent += `<td>` + restaurant.rating + `</td>`
				newRowContent += `<td>` + restaurant.type + `</td>`

				$('#rest-table tbody').append(newRowContent);

			}
				
		}
	});

}
