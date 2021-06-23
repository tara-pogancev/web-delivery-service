var app = new Vue({
	
	el: '#userElem',
	
	data: {
		
		name: "Ja",
		lastName: "OpetJa",
		
	}
	
});

new Vue({

	el: '#adminButton',

	methods: {
    newAdmin: function (event) {
      	axios
		.get('webapi/customers/new')

      //alert('Hello!')
      
	}	
	}

});

