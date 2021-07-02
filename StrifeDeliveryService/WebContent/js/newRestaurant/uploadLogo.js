function upload() {

	let base64String = "";

	var file = document.querySelector(
		'input[type=file]')['files'][0];

	var reader = new FileReader();

	reader.onload = function () {
		base64String = reader.result.replace("data:", "").replace(/^.+,/, "");

		imageBase64Stringsep = base64String;

		//console.log(base64String);

		$.post({
			url: 'webapi/images/uploadImage',
			data: base64String,
			success: function (response) {
			}
		});

	}

	reader.readAsDataURL(file);

}

