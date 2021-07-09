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
	alert("JEEEEEEEEEEJ")
	$.get({
		url: 'webapi/comments/getCurrentOrder',
		contentType: 'application/json',
		success: function(response){
			
			let data = 	{
				text = document.getElementById('review-text'),
				rating = document.getElementById('rating')
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
