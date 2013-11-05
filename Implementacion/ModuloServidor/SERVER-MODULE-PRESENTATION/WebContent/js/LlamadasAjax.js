/**
 * Llamadas Ajax de la aplicacion.
 */

var ip = "/SERVER-MODULE-SERVICES/restws";

//POST__________POST__________POST
function registroUsuario(usuario,pass,pass2,mail,nombre,apellido,sexo,nacimiento,cel){
	$.ajax({
		url: ip + '/usuarios/registrarUsuario',
		type: 'POST',
		data:JSON.stringify({
			usuario           : usuario,
			contrasenia       : pass,
			contrasenia2	  : pass2,
			nombres           : nombre,
			apellidos   	  : apellido,
			sexo			  : sexo,
			fechaNacimiento   : nacimiento,
			telefonoMovil	  : cel,
			correoElectronico : mail,
		}),
		datatype: "json",
		contentType: "application/json",
	})
	.done(function(msg){

		if (msg.respuesta === "OK") {

		    varsProy.nick = msg.usuario;
		    varsProy.token = msg.token;
		    varsProy.tipoUsuario = msg.tipoUsuario;

		    //GUARDO LOS DATOS DE SESION EN EL NAVEGADOR
			    window.localStorage.setItem(confProy.sessionStorageUser, msg.usuario);
			    window.localStorage.setItem(confProy.sessionStorageToken, msg.token);

			ocultarElemento('registroUsuario');
			var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
				nick.innerText = varsProy.nick;

		    mostrarElemento('Nick-Logout-Div');
		    ocultarElemento('Login-Registro-Div');
		}
		else
			alert(msg.respuesta);
	})
	.fail(function(msg){
		console.log('Entro en fail: ' + msg);
		alert(msg);
	})
};

function registroProveedor(usuario,pass,pass2,mail,nombre,apellido,sexo,nacimiento,cel,sitioWeb){
	$.ajax({
		url: ip + '/usuarios/registrarProveedor',
		type: 'POST',
		data:JSON.stringify({
			usuario           : usuario,
			contrasenia       : pass,
			contrasenia2	  : pass2,
			nombres           : nombre,
			apellidos   	  : apellido,
			sexo			  : sexo,
			fechaNacimiento   : nacimiento,
			telefonoMovil	  : cel,
			correoElectronico : mail,
			sitioWeb		  : sitioWeb,
		}),
		datatype: "json",
		contentType: "application/json",
	})
	.done(function(msg){

		if (msg.respuesta === "OK") {

		    varsProy.nick = msg.usuario;
		    varsProy.token = msg.token;
		    varsProy.tipoUsuario = msg.tipoUsuario;

		    window.localStorage.setItem(confProy.sessionStorageUser, msg.usuario);
		    window.localStorage.setItem(confProy.sessionStorageToken, msg.token);

			ocultarElemento('registroUsuario');
			var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
				nick.innerText = varsProy.nick;

		    mostrarElemento('Nick-Logout-Div');
		    ocultarElemento('Login-Registro-Div');
		}
		else
			alert(msg.respuesta);
	})
	.fail(function(msg){
		console.log('Entro en fail: ' + msg);
		alert(msg);
	})
};

//PUT__________PUT__________PUT
function loginUsuario(usuario, contrasenia){
	$.ajax({
	   url: ip + '/usuarios/loginCliente',
	   type: 'PUT',
	   data: JSON.stringify({
		   usuario : usuario,
		   contrasenia : contrasenia
	   }),
	   datatype: "json",
	   contentType: "application/json",
	})
	.done(function(msg) {

		if (msg.respuesta === "OK") {

		    varsProy.nick = msg.usuario;
		    varsProy.token = msg.token;
		    varsProy.tipoUsuario = msg.tipoUsuario;

			    window.localStorage.setItem(confProy.sessionStorageUser, msg.usuario);
			    window.localStorage.setItem(confProy.sessionStorageToken, msg.token);

			ocultarElemento('loginUsuario');
			var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
				nick.innerText = varsProy.nick;

		    mostrarElemento('Nick-Logout-Div');
		    ocultarElemento('Login-Registro-Div');
		}
		else
			alert(msg.respuesta);
	})
	.fail(function(msg) {
		console.log(msg);
		alert('Nombre de usuario o contrasenia incorrectos!!');
	})
}

function logoutUsuario(){
	$.ajax({
	   url: ip + '/usuarios/logout',
	   type: 'PUT',
	   data: JSON.stringify({
		   usuario : varsProy.nick,
		   token : varsProy.token
	   }),
	   datatype: "json",
	   contentType: "application/json",
	})
	.done(function(msg) {

		if (msg.resultadoOperacion === "OK") {

		    varsProy.nick = "";
		    varsProy.token = "";
		    varsProy.tipoUsuario = "";
		    varsProy.mail = "";

		    mostrarElemento('Login-Registro-Div');
		    ocultarElemento('Nick-Logout-Div');

		    window.localStorage.removeItem(confProy.sessionStorageUser);
		    window.localStorage.removeItem(confProy.sessionStorageToken);
		}
		else
			alert(msg.resultadoOperacion);
	})
	.fail(function(msg) {
		console.log(msg);
		alert("Fallo del sistema, intente de nuevo o contacte con el administrador!!");
	})
}

function modificarUsuarioX(usuario,nombre,contrasenia,apellido,rol){
	$.ajax({
		url: ip + '/usuarios/modificarUsuario',
		type: 'PUT',
		data:JSON.stringify({
			'usuario'	  : usuario,
			'contrasenia' : contrasenia,
			'nombres'     : nombre,
			'apellidos'   : apellido,
			'rol'		  : rol
		}),
		datatype: "json",
		contentType: "application/json",
	})
	.done(function(msg){
		console.log(msg);
	})
	.fail(function(){
		console.log(msg);
	})
}

//GET___________GET_____________GET
function obtenerUsuariosServ(){
	$.ajax({
	   url: ip + '/usuarios/listarUsuarios',
	   type: 'GET',
	   data: '',
	   datatype: "json",
	   contentType: "application/json",
	})
	.done(function(msg) {
		for (var i = 0; i < msg.length; i++) {
			msg[i]
		};
		console.log("Estos son los usuarios: " + msg);
	})
	.fail(function(msg) {
		console.log("Fallo: " + msg);
	})
}

function esUnicoCorreo(mail){
	$.ajax({
	   url: ip + '/usuarios/existeUsuarioPorMail',
	   type: 'GET',
	   data: JSON.stringify({
			mail : mail
	   }),
	   datatype: "json",
	   contentType: "application/json",
	})
	.done(function(msg) {
		console.log(msg);
	})
	.fail(function(msg) {
		console.log(msg);
	})
}

function esUnicoNick(nick){
	$.ajax({
	   url: ip + '/usuarios/',
	   type: 'GET',
	   data: {'usuario' : nick,},
	   datatype: "json",
	   contentType: "application/json",
	})
	.done(function(msg) {
		console.log(msg);
	})
	.fail(function(msg) {
		console.log(msg);
	})
}

function obtenerContenidoPorId(idContenido){

	$.ajax({
	  url: ip + '/contenido/id='+idContenido,
	  type: 'GET',
	  dataType: 'json',
	  data: '',
	})
	.done(function(msg) {
		console.log(msg);

		var content = new Contenido();

		content.Id = msg[0].id;
		content.Descripcion = msg[0].descripcion;
		content.Nombre = msg[0].nombre;
		content.Imagen = msg[0].imagen;
		content.Precio = msg[0].precio;
		content.Calificacion = msg[0].calificacion;
		content.Source = msg[0].source;
		// ..
		// ..
		// ..

		varsProy.contenidoActual = content;
	})
	.fail(function(msg) {
		console.log(msg);
	})
}