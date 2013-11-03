/**
 * Llamadas Ajax de la aplicacion.
 */

//var ip = "http://192.168.1.43:8080/SERVER-MODULE-SERVICES/restws";//chile
var ip = "/SERVER-MODULE-SERVICES/restws";

//Objeto usario
function Usuario(nick,nombre,apellido){
	this.User 	= nick;
	this.Nombre	= nombre;
	this.Apellido	= apellido;
	this.Descripcion = "--";
}

function Rol(id,Nombre){
	this.id = id;
	this.descripcion = Nombre;
}

//Registro de Usuario
function registroUsuario(usuario,pass,pass2,mail,nombre,apellido,sexo,nacimiento,cel){
	$.ajax({
		url: ip + '/usuarios/registrarUsuario',
		type: 'PUT',
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

/*
msg: Object
respuesta: "OK"
tipoUsuario: "usuario_cliente"
token: "7yyoc8vomm1bpnrf4iwt"
usuario: "chile"
*/

//modificacion de datos
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

function loginUsuario(usuario, contrasenia){
	$.ajax({
	   url: ip + '/usuarios/loginCliente',
	   type: 'POST',
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
		   usuario : varsProy.usuario,
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
		}
		else
			alert(msg.resultadoOperacion);
	})
	.fail(function(msg) {
		console.log(msg);
		alert("Fallo del sistema, intente de nuevo o contacte con el administrador!!");
	})
}