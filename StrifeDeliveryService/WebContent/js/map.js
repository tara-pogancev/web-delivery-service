function myMap() {
	
	$.get({
		url: 'webapi/restaurantView/getCurrentRestaurant',
		contentType: 'application/json',
		success: function (restaurant) {
			var location = restaurant.address + restaurant.city + restaurant.postal;
			geocode(location);
		}
	});
}

function geocode(location){
	axios.get('https://maps.googleapis.com/maps/api/geocode/json', {
		params:{
			address:location,
			key: 'AIzaSyAN9OBd2ZhIWfh489y3NRD7NMt33oXoxY0'
		}
	})
	.then(function(response){
		console.log(response);
		var lat = response.data.results[0].geometry.location.lat;
		var lng = response.data.results[0].geometry.location.lng;
		marker(lat, lng);
		})
	
	.catch(function(error){
        console.log(error);
	})
}

function marker(lat, lng){

	var mapProp = {
		center: new google.maps.LatLng(lat, lng),
		zoom: 14,
	};
	var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
	
	var marker = new google.maps.Marker({
		position: new google.maps.LatLng(lat, lng),
		animation: google.maps.Animation.BOUNCE
	});

	marker.setMap(map);

}

function getAddress(){


}