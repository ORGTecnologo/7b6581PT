//admin.js

/* funciones que cargan los abms en el backend del administrador */

function logoutUsuario(){

	$.ajax({
	   url: confProy.IP_WEBSERVICES + '/usuarios/logout',
	   type: 'PUT',
	   data: JSON.stringify({
		   usuario : window.localStorage.getItem(confProy.sessionStorageUser),
		   token : '',
	   }),
	   datatype: "json",
	   contentType: "application/json",
	})
	.done(function(msg) {
		
	})
	.fail(function(msg) {
		console.log(msg);
		alert("Fallo del sistema, intente de nuevo o contacte con el administrador!!");
	})
	.always(function(msg) {
		window.location.href = "/SERVER-MODULE-PRESENTATION/index.html";
	});

}
