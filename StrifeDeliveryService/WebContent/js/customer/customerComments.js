document.addEventListener('DOMContentLoaded', function() {

	getDataFromServer();

}, false);


function getDataFromServer() {

	$.get({
		url: 'webapi/login/activeUserObject',
		contentType: 'application/json',
		success: function(response) {
		
		$('#welcomeUser').text(response.name + ", Please Leave a Review");
		
		}
	});

}