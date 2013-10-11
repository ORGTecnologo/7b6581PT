/**
 * 
 */

//var ip = "http://192.168.1.43:8080/SERVER-MODULE-SERVICES/restws";//chile
var ip = "/SERVER-MODULE-SERVICES/restws";//local

//Llamadas Ajax
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
function registroUsuario(usuario,nombre,contrasenia,apellido,mail){
	$.ajax({
		url: ip + '/usuarios/registrarUsuario',
		type: 'PUT',
		data:JSON.stringify({
			usuario     : usuario,
			contrasenia : contrasenia,
			nombres     : nombre,
			apellidos   : apellido,
			mail	    : mail
		}),
		datatype: "json",
		contentType: "application/json",
	})
	.done(function(msg){
		console.log('Guardado con exito!!');
	})
	.fail(function(){
		console.log('Fallo al guardar!!');
	})
}



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
		console.log('Guardado con exito!!');
	})
	.fail(function(){
		console.log('Fallo al guardar!!');
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

function loginUsuario(){
	$.ajax({
	   url: ip + '/usuarios/loginCliente',
	   type: 'POST',
	   data: JSON.stringify({
		   usuario : 'pelo',
		   contrasenia : 'bolso'
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
