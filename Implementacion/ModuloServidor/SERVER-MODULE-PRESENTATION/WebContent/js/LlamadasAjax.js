/**
 * Llamadas Ajax de la aplicacion.
 */

var ip = "/restws";

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
				nick.href = confProy.URL_PERFIL+varsProy.nick;
				nick.innerHTML = "<i class='glyphicon glyphicon-user'></i>" + varsProy.nick;

			var btnVerPerfil = document.getElementById("dd_verPerfil");
				btnVerPerfil.href = confProy.URL_PERFIL + varsProy.nick;

		    mostrarElemento('Nick-Logout-Div',false);
		    ocultarElemento('Login-Registro-Div');

		    alertify.success("Usuario creado con exito!");
		}
		else
		    alertify.error(msg.respuesta);
	})
	.fail(function(msg){
		alertify.error('Ocurrio un error inesperado. Intene nuevamente.');
	});
};

function registroProveedor(usuario,pass,pass2,mail,nombre,apellido,sexo,nacimiento,cel,sitioWeb){
	
	bloquearPantalla();
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
				nick.href = confProy.URL_PERFIL+varsProy.nick;
				nick.innerHTML = "<i class='glyphicon glyphicon-user'></i>" + varsProy.nick;

			var btnVerPerfil = document.getElementById("dd_verPerfil");
				btnVerPerfil.href = confProy.URL_PERFIL + varsProy.nick;;

		    mostrarElemento('Nick-Logout-Div',false);
		    ocultarElemento('Login-Registro-Div');
		    ocultarElemento('registroUsuario')
			alertify.success('Usuario creado exitosamente!');
		}
		else
			alertify.error(msg.respuesta);
	})
	.fail(function(msg){
		alertify.error('Ocurrio un error inesperado. Intene nuevamente.');
	})
	.always(function(msg) {
		desbloquearPantalla();
	});
};

function altaContenido(content){
	
	bloquearPantalla();

	$.ajax({
		url: ip + '/contenidos/altaContenido',
		type: 'POST',
		dataType: 'json',
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
		$('#con-tenedor').hide();
		mostrarElemento('mensajeContenidoExito');
		alertify.success('Contenido creado exitosamente!!');
	})
	.fail(function(msg) {
		console.debug('FALLO AL CREAR CONTENIDO: ' + msg);
		$('#con-tenedor').hide();
		mostrarElemento('mensajeContenidoError');
		alertify.success('Ocurrio un error inesperado!!');
	})
	.always(function(msg) {
		desbloquearPantalla();
	});
	
}

function enviarReclamo(idDescarga, titulo, comentario){
 
 	bloquearPantalla();
	$.ajax({
		url: ip + '/contenidos/registrarReclamoContenido',
		type: 'POST',
		dataType: 'json',
        contentType: "application/json",
		data: JSON.stringify({
			'idDescarga' : idDescarga,
			'titulo' 	 : titulo,
			'descripcion' : comentario
		}),
	})
	.done(function(msg) {
		console.log("success: " + msg);
		alertify.success('Reclamo enviado con exito, alguien del staff se comunicara con ud a la brevedad!');
	})
	.fail(function(msg) {
		console.log("error: " + msg);
		alertify.error('Ocurrio un error inesperado!');		
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
}


//PUT__________PUT__________PUT
function loginUsuario(usuario, contrasenia){
	bloquearPantalla();
	
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
			    window.localStorage.setItem(confProy.sessionStorageRol, msg.tipoUsuario);
				
			ocultarElemento('loginUsuario');
			var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
				nick.href = confProy.URL_PERFIL+varsProy.nick;
				nick.innerHTML = "<i class='glyphicon glyphicon-user'></i>" + varsProy.nick;

			var btnVerPerfil = document.getElementById("dd_verPerfil");
				btnVerPerfil.href = confProy.URL_PERFIL + varsProy.nick;;

		    mostrarElemento('Nick-Logout-Div',false);
		    ocultarElemento('Login-Registro-Div');

			mostrarElementosSegunUsuario(msg.tipoUsuario);
			
			alertify.success('Bienvenido ' + varsProy.nick + '.');

			if(msg.tipoUsuario === confProy.ROL_ADMINISTRADOR)
				location.href = confProy.URL_ADMIN_HOME;
		}
		else{
			var mensaje = msg.respuesta.split('|')
			alertify.error(mensaje[1]);
		}
	})
	.fail(function(msg) {
		console.log(msg);
		alertify.error(msg);//'Nombre de usuario o contrasenia incorrectos!!');
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
}

function logoutUsuario(){

	bloquearPantalla();

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

		    window.location.href = confProy.IP_SERVICIOS;

		    alertify.log('Regresa pronto ' + varsProy.nick);
		}
		else
			alertify.error(msg.resultadoOperacion);
	})
	.fail(function(msg) {
//		console.log(msg);
		alertify.error("Fallo del sistema, intente de nuevo o contacte con el administrador!!");
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});

}

function guardarCambiosPerfil(nombre,apellido,sexo,fechaNacimiento,tel,sitioWeb){

	bloquearPantalla();
	$.ajax({
		url: ip + '/usuarios/editarUsuario',
		type: 'PUT',
		data:JSON.stringify({
			'nombres'     	 : nombre,
			'apellidos'   	 : apellido,
			'sitioWeb'		 : sitioWeb,
			'telefonoMovil'	 : tel,
			'sexo'			 : sexo,
			'fechaNacimiento': fechaNacimiento,
		}),
		datatype: "json",
		contentType: "application/json",
	})
	.done(function(msg){
		console.log(msg);
		alertify.success("Perfil actualizado con exito!");
	})
	.fail(function(msg){
		//console.log(msg);
		alertify.error("Ocurrio un error inesperado al intentar actualizar el perfil.");		
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
}

function enviarCalificacion(idDescarga, puntaje, comentario){
 
 	bloquearPantalla();

	$.ajax({
		url: ip + '/contenidos/calificarDescarga',
		type: 'PUT',
		dataType: 'json',
		contentType : 'application/json',
		data: JSON.stringify({
			'idDescarga'   : idDescarga, 
			'calificacion' : puntaje,
			'comentario'   : comentario
		}),
	})
	.done(function(msg) {
		alertify.success('Calificacion enviada con exito!');
	})
	.fail(function(msg) {
		alertify.error('Ocurrio un error inesperado!');
	})
	.always(function(msg) {
	 	desbloquearPantalla();
		obtenerContenidosUsuario();
	});
}

function enviarCambioPassword(nick,pass,newPass,newPass2){

	bloquearPantalla();

	$.ajax({
		url: ip + '/usuarios/cambiarContrasenia',
		type: 'PUT',
		dataType: 'json',
		contentType : 'application/json',		
		data: JSON.stringify({
				'usuario' 					  : nick,
				'contraseniaAnterior' 		  : pass,
				'contraseniaNueva' 			  : newPass,
				'confirmacionContraseniaNueva': newPass2,
			}),
	})
	.done(function(msg) {
		console.log("success: " + msg);
		if (msg.resultadoOperacion === 'OK'){
			alertify.success('Contraseña actualizada con exito');
			ocultarElemento('password_modal')
		}
		else
			alertify.error(msg.mensajeOperacion);
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
		desbloquearPantalla();
	});
	
}

//GET___________GET_____________GET

function buscarContenidos(){

	bloquearPantalla();

	var JSONstring = new ParametrosBusqueda();

	JSONstring.libros = varsProy.PARAM_BUSQ_LIBROS;
	JSONstring.musica = varsProy.PARAM_BUSQ_MUSICA;
	JSONstring.apps = varsProy.PARAM_BUSQ_APPS;
	JSONstring.videos = varsProy.PARAM_BUSQ_VIDEO;
	JSONstring.pagas = varsProy.PARAM_BUSQ_PAGAS;
	JSONstring.categorias = varsProy.PARAM_BUSQ_CATEGORIAS;
	JSONstring.keyword = document.getElementById("input-busqueda").value;

	var msjServidor = JSON.stringify(JSONstring);

	$.ajax({
		url: ip + '/contenidos/filtrarContenidos/' + msjServidor,
		type: 'GET',
		dataType: 'json',
	})
	.done(function(msg) {
		console.log("success");
		console.log(msg);

		//Cargo los resultados en memoria
		jsonProy.resultadoBusqueda = msg;
		
		cargarResultadoBusqueda();
		cargarPaginadoDinamico();
		
		ocultarElemento('con-tenedor');
		mostrarElemento('resultadoBusqueda');
	})
	.fail(function(msg) {
		console.log("error");
		console.log(msg);
		jsonProy.resultadoBusqueda = msg;
		ocultarElemento('con-tenedor');
		mostrarElemento('resultadoBusqueda');
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
}

function obtenerTopContenidos(tipo){

	bloquearPantalla();

	$.ajax({
		url: ip + "/contenidos/obtenerTopContenidos?cantidad="+ confProy.CANT_CONTENIDOS_INDEX +"&tipo=" + tipo,
		type: 'GET',
		dataType: 'json',
	})
	.done(function(msg) {
		cargarTopEnMemoria(tipo,msg);
		cargarIndexTop(tipo);
	})
	.fail(function(msg) {
		console.log("error");
	})
	.always(function(msg) {
		console.log("complete");
		desbloquearPantalla();
	});
}

function obtenerDatosdeUsuario(nick){
	bloquearPantalla();

	$.ajax({
		url: ip + '/usuarios/verUsuario/' + nick,
		type: 'GET',
		dataType: 'json',
	})
	.done(function(msg) {
		console.log(msg);
		cargarHtmlDatosdeUsuario(msg);
	})
	.fail(function(msg) {
		console.log("error");
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
}

function obtenerCategoriasySubcategorias(idSelect){
	idSelect || (idSelect = 'id_categoria');

	$.ajax({
		url: ip + '/categoriasContenido/obtenerCategoriasYSubcategorias',
		type: 'GET',
		dataType: 'json',
		contentType: "application/json",
	})
	.done(function(msg) {
		console.log("Estas son las categorias: " + msg);
		cargarCategoriasMemoria(msg);
		if(idSelect === 'multiplesCat')
			cargarComboMultCategorias(idSelect);

		cargarComboCategorias('id_categoria');
	})
	.fail(function(msg) {
		console.log("Fallo: " + msg);
	});
}

function existeSesionServ(user){

	bloquearPantalla();

	$.ajax({
		url: ip + '/usuarios/checkSession/' + user,
		type: 'GET',
		datatype: "json",
		contentType: "application/json",
		async: false,
	})
	.done(function(msg) {
		console.log("success = " + msg);
		if(msg === undefined)
			window.localStorage.clear();
		else if (msg != "FALLA"){
			var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
				nick.href = confProy.URL_PERFIL+varsProy.nick;
				nick.innerHTML = "<i class='glyphicon glyphicon-user'></i>" + varsProy.nick;

			var btnVerPerfil = document.getElementById("dd_verPerfil");
				btnVerPerfil.href = confProy.URL_PERFIL + varsProy.nick;;
	    	
	    	mostrarElemento('Nick-Logout-Div');
	    	ocultarElemento('Login-Registro-Div');
			window.localStorage.setItem(confProy.sessionStorageRol,msg.tipoUsuario);

			mostrarElementosSegunUsuario(msg.tipoUsuario);
		}
		else{
			window.localStorage.clear();
		}
		return true;
	})
	.fail(function(msg) {
		console.log("error");
		console.log(msg);
		return false;
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
}

function obtenerUsuariosServ(){
	$.ajax({
	   url: ip + '/usuarios/listarUsuarios',
	   type: 'GET',
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
	   data: JSON.stringify({'usuario' : nick,}),
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

	bloquearPantalla();

	$.ajax({
	  url: ip + '/contenidos/obtenerInfoContenido/'+idContenido,
	  type: 'GET',
	  dataType: 'json',
	  contentType: 'application/json',
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
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
}

function obtenerContenidosACalificar(){

	bloquearPantalla();

	$.ajax({
		url: ip + '/contenidos/obtenerDescargasACalificar',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
	})
	.done(function(msg) {
		console.log("success " + msg);
		jsonProy.contentidosACalificar = msg;

		cargarTablaPendientesCalificacion();
		cargarPaginadoPendientes();
	})
	.fail(function(msg) {
		console.log("error" + msg);
	})
	.always(function(msg) {
		desbloquearPantalla();
	});
	
}

function obtenerContenidosUsuario(){

	bloquearPantalla();

	$.ajax({
		url: ip + '/contenidos/obtenerTodasLasDescargas',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
	})
	.done(function(msg) {
		console.log("success: " + msg);
		jsonProy.misContenidos = msg;

		cargarTablaMisContenidos();
		cargarPaginadoMisContenidos();
	})
	.fail(function(msg) {
		console.log("error: " + msg);
	})
	.always(function(msg) {
	 	desbloquearPantalla();
	});
	
}