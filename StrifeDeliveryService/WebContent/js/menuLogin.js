document.addEventListener('DOMContentLoaded', function () {

	setLoginMenu();

}, false);

function setLoginMenu() {

	$.get({
		url: 'webapi/login/activeUser',
		contentType: 'application/json',
		success: function (response) {

			if (response.id == null || response.id == "") {

				newRowContentLogin = `<a href="login.html">Login</a>`
				newRowContentLogin += `<ul>`
				newRowContentLogin += `<li><a href="registerUser.html" class="menu-item">Register account</a></li>`
				newRowContentLogin += `</ul>`


				$('#login-menu').append(newRowContentLogin);

			}

			else if (response.name == "CUSTOMER") {

				newRowContentLogin = `<a href="profileCustomer.html">` + response.id + `</a>`

				newRowContentLogin += `<ul>`

				newRowContentLogin += `<li><a href="cartPage.html" class="menu-item">Cart</a></li>`
				newRowContentLogin += `<li><a href="customerOrders.html" class="menu-item">My Orders</a></li>`
				newRowContentLogin += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContentLogin += `</ul>`


				$('#login-menu').append(newRowContentLogin);

			}

			else if (response.name == "ADMIN") {

				newRowContentLogin = `<a href="profileAdmin.html">` + response.id + `</a>`

				newRowContentLogin += `<ul>`

				newRowContentLogin += `<li><a href="adminUsersView.html" class="menu-item">Manage Users</a></li>`
				newRowContentLogin += `<li><a href="addRestaurant.html" class="menu-item">Add Restaurant</a></li>`
				newRowContentLogin += `<li><a href="addManager.html" class="menu-item">Add Manager</a></li>`
				newRowContentLogin += `<li><a href="addDeliverer.html" class="menu-item">Add Deliverer</a></li>`
				newRowContentLogin += `<li><a href="adminCustomerView.html" class="menu-item">Manage Customers</a></li>`
				newRowContentLogin += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContentLogin += `</ul>`


				$('#login-menu').append(newRowContentLogin);

			}

			else if (response.name == "MANAGER") {

				newRowContentLogin = `<a href="profileManager.html">` + response.id + `</a>`

				newRowContentLogin += `<ul>`

				newRowContentLogin += `<li><a href="#" class="menu-item">My Restaurant</a></li>`
				newRowContentLogin += `<li><a href="#" class="menu-item">Add Menu Item</a></li>`
				newRowContentLogin += `<li><a href="#" class="menu-item">Manage Menu Items</a></li>`
				newRowContentLogin += `<li><a href="#" class="menu-item">Manage Orders</a></li>`
				newRowContentLogin += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContentLogin += `</ul>`


				$('#login-menu').append(newRowContentLogin);

			}

			else if (response.name == "DELIVERER") {

				newRowContentLogin = `<a href="profileManager.html">` + response.id + `</a>`

				newRowContentLogin += `<ul>`

				newRowContentLogin += `<li><a href="#" class="menu-item">My Orders</a></li>`
				newRowContentLogin += `<li><a href="#" class="menu-item" onclick="logout();">LOGOUT</a></li>`

				newRowContentLogin += `</ul>`


				$('#login-menu').append(newRowContentLoginLogin);

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
