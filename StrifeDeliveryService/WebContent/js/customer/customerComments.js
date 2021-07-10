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

function addComment() {
	$.get({
		url: 'webapi/comments/getCurrentOrder',
		contentType: 'application/json',
		success: function(response){
			alert("ovde")
			let data = 	{
				"text" : document.getElementById('review-text').value,
				"rating" : document.getElementById('rating').value
			}

			$.post({
				url: 'webapi/comments/addComment',
				contentType: 'application/json',
				data: JSON.stringify(data),
				success: function(response) {
					alert(response);
				}
			});

		}
	});

}
