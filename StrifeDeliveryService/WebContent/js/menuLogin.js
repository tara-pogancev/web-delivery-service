document.addEventListener('DOMContentLoaded', function () {

	setLoginMenu();

}, false);

function setLoginMenu() {

	$.get({
		url: 'webapi/login/activeUser',
		contentType: 'application/json',
		success: function (response) {

			if (response.id == null || response.id == "") {

				newRowContent = `<a href="login.html">Login</a>`
				newRowContent += `<ul>`
				newRowContent += `<li><a href="registerUser.html" class="menu-item">Register account</a></li>`
				newRowContent += `</ul>`


				$('#login-menu').append(newRowContent);

			}

			else if (response.name == "CUSTOMER") {

				newRowContent = `<a href="profileCustomer.html">` + response.id + `</a>`

				newRowContent += `<ul>`

				newRowContent += `<li><a href="cartPage.html" class="menu-item">Cart</a></li>`
				newRowContent += `<li><a href="customerOrders.html" class="menu-item">My Orders</a></li>`
				newRowContent += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContent += `</ul>`


				$('#login-menu').append(newRowContent);

			}

			else if (response.name == "ADMIN") {

				newRowContent = `<a href="profileAdmin.html">` + response.id + `</a>`

				newRowContent += `<ul>`

				newRowContent += `<li><a href="adminUsersView.html" class="menu-item">Manage Users</a></li>`
				newRowContent += `<li><a href="addRestaurant.html" class="menu-item">Add Restaurant</a></li>`
				newRowContent += `<li><a href="addManager.html" class="menu-item">Add Manager</a></li>`
				newRowContent += `<li><a href="addDeliverer.html" class="menu-item">Add Deliverer</a></li>`
				newRowContent += `<li><a href="#" class="menu-item">Manage Customers</a></li>`
				newRowContent += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContent += `</ul>`


				$('#login-menu').append(newRowContent);

			}

			else if (response.name == "MANAGER") {

				newRowContent = `<a href="profileManager.html">` + response.id + `</a>`

				newRowContent += `<ul>`

				newRowContent += `<li><a href="#" class="menu-item">My Restaurant</a></li>`
				newRowContent += `<li><a href="#" class="menu-item">Add Menu Item</a></li>`
				newRowContent += `<li><a href="#" class="menu-item">Manage Menu Items</a></li>`
				newRowContent += `<li><a href="#" class="menu-item">Manage Orders</a></li>`
				newRowContent += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContent += `</ul>`


				$('#login-menu').append(newRowContent);

			}

			else if (response.name == "DELIVERER") {

				newRowContent = `<a href="profileManager.html">` + response.id + `</a>`

				newRowContent += `<ul>`

				newRowContent += `<li><a href="#" class="menu-item">My Orders</a></li>`
				newRowContent += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContent += `</ul>`


				$('#login-menu').append(newRowContent);

			}

		}
	});

}

function logout() {

	var response = confirm("Are you sure you want log out?");

	if (response == true) {
		$.post({
			url: 'webapi/login/logOut',
			success: function (response) {

				window.location.href = "http://localhost:8080/PocetniREST/";
				setLoginMenu();

			}

		})

	}
}
