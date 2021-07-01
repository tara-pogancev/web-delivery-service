function upload() {

	let base64String = "";

	var file = document.querySelector(
        'input[type=file]')['files'][0];

	var reader = new FileReader();

	reader.onload = function () {
		base64String = reader.result.replace("data:", "").replace(/^.+,/, "");

		imageBase64Stringsep = base64String;

		//console.log(base64String);
		
	}

	reader.readAsDataURL(file);

	$.post({
		url: 'webapi/images/uploadImage',
		//data: JSON.stringify(data),
		data: base64String,
		contentType: 'text/plain',
		success: function (response) {
			alert(base64String);
		}
	});

}

