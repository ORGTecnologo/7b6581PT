/**
 * Llamadas Ajax de la aplicacion.
 */

var ip = "/SERVER-MODULE-PRESENTATION/restws";

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

		    mostrarElemento('Nick-Logout-Div',false);
		    ocultarElemento('Login-Registro-Div');
		}
		else
			alert(msg.respuesta);
	})
	.fail(function(msg){
		console.log('Entro en fail: ' + msg);
		alert(msg);
	});
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

		    mostrarElemento('Nick-Logout-Div',false);
		    ocultarElemento('Login-Registro-Div');
		}
		else
			alert(msg.respuesta);
	})
	.fail(function(msg){
		console.log('Entro en fail: ' + msg);
		alert(msg);
	});
};

function altaContenido(content){
	
	$.ajax({
		url: ip + '/contenidos/altaContenido',
		type: 'POST',
		dataType: 'application/json',
		contentType: 'application/json',
		
		data: JSON.stringify({
			nombre: 			content.Nombre,
			descripcion: 		content.Descripcion,
			imagenes: 			content.Imagen,
			calificacion: 		content.Calificacion.toString(),
			source: 			content.Source.toString(),
			precio:  			content.Precio.toString(),
			tipoContenido:  	content.tipoContenido.toString(),
			categoria : 		content.Categoria.toString(),
			subcategoria: 		content.Subcategoria.toString(),
			desarrollador: 		content.desarrollador,
			duracionTema: 		content.duracionTema.toString(),
			artistaTema: 		content.artistaTema.toString(),
			albumTema: 			content.albumTema.toString(),
			esTrial: 			content.esTrial.toString(),
			requisitosMinimos:  content.requisitosMinimos.toString(),
			fechaPublicacion: 	content.fechaPublicacion.toString(),
			autor: 				content.autor.toString(),
			paginas: 			content.Paginas.toString(),
			duracionVideo: 		content.duracionVideo.toString(),
			formatoVideo: 		content.formatoVideo.toString(),
			calidadVideo: 		content.calidadVideo.toString(	),
		}),
	})
	.done(function(msg) {
		console.log("success");
		console.log(msg);
	})
	.fail(function(msg) {
		console.log("error");
		console.log(msg);
	})
	.always(function(msg) {
		console.log("complete");
		console.log(msg);
	});
	
}

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

		    mostrarElemento('Nick-Logout-Div',false);
		    ocultarElemento('Login-Registro-Div');
		}
		else
			alert(msg.respuesta);
	})
	.fail(function(msg) {
		console.log(msg);
		alert('Nombre de usuario o contrasenia incorrectos!!');
	});
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

		    mostrarElemento('Login-Registro-Div',false);
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
	});
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
	});
}

//GET___________GET_____________GET
function obtenerCategoriasySubcategorias(){

	$.ajax({
		url: ip + '/categoriasContenido/obtenerCategoriasYSubcategorias',
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
	})
	.done(function(msg) {
		console.log("Estas son las categorias: " + msg);
		cargarCategoriasMemoria(msg);
		cargarComboCategorias();
	})
	.fail(function(msg) {
		console.log("Fallo: " + msg);
	});
}


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
			msg[i];
		};
		console.log("Estos son los usuarios: " + msg);
	})
	.fail(function(msg) {
		console.log("Fallo: " + msg);
	});
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
	});
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
	});
}



function respuestaPares(){

	this.filtro = "";
	this.valor = "";
}


/*var respuesta = new respuestaPares();
	respuesta.filtro = "categoria"
	respuesta.valor = "1,8,9,12"

	var arr = new Array()

	arr.add(respuesta)
*/

function obtenerContenidoPorId(idContenido){

	$.ajax({
	  url: ip + '/contenidos/obtenerInfoContenido/'+idContenido,
	  type: 'GET',
	  dataType: 'json',
	  data: '',
	})
	.done(function(msg) {
		console.log(msg);

		var content = new Contenido();

		content.Id 			 = msg.idContenido;
		content.Nombre       = msg.nombreContenido;
		content.Descripcion  = msg.descripcionContenido;
		content.Imagenes 	 = msg.fotos;
		content.Calificacion = msg.calificacion;
		content.Source 		 = msg.urlArchivoContenido;
		content.Descargas    = msg.cantidadDescargas;
		content.Tamanio		 = msg.tamanioContenido;
		content.tipoContenido= msg.tipoContenido;

		if (msg.precio === 0)	content.Precio = "gratis"; 
		else content.Precio = msg.precio;

		switch(msg.tipoContenido){
			case(confProy.TIPO_CONTENIDO_MUSICA):
				content.duracionTema = msg.duracionTema;
				content.artistaTema  = msg.artistaTema;
				content.albumTema    = msg.albumTema;
			break;
			case(confProy.TIPO_CONTENIDO_APP):
				content.esTrial = msg.esTrial;
				content.requisitosMinimos = msg.requisitosMinimos;
			break;
			case(confProy.TIPO_CONTENIDO_VIDEO):
				content.duracionVideo = msg.duracionVideo;
				content.formatoVideo = msg.formatoVideo;
				content.calidadVideo = msg.calidadVideo;
			break;
			case(confProy.TIPO_CONTENIDO_LIBRO):
				content.fechaPublicacion = msg.fechaPublicacion;
				content.autor = msg.autor;
				content.Paginas = msg.cantidadPaginas;
			break;
		}

		varsProy.contenidoActual = content;
		armarContenidoHTML();
	})
	.fail(function(msg) {
		console.log("Fallo el pedido " + msg);
	});
}
