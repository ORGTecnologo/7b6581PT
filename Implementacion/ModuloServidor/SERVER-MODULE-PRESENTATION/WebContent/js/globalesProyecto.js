function VARS_PROY(){

//DATOS DE SESION
	this.token = "";
	this.nick  = "";
	this.mail  = "";

//DATOS DE TIPEO
	this.tipoUsuario = "";
	this.tipoRegistro = "";

//CONTENIDO
	this.contenidoActual = "";
	this.idContenido = "";
}

function CTES_PROY(){

	this.sessionStorageUser   = 'user_proyMPTecnologo';
	this.sessionStorageToken  = 'token_proyMPTecnologo';

	this.TIPO_CONTENIDO_MUSICA= "tipo_contenido_tema";
	this.TIPO_CONTENIDO_APP   = "tipo_contenido_software";
	this.TIPO_CONTENIDO_VIDEO = "tipo_contenido_video";
	this.TIPO_CONTENIDO_LIBRO = "tipo_contenido_libro";

	this.HOST 		  = "http://localhost:8080";
	this.IP_SERVICIOS = "http://localhost:8080/SERVER-MODULE-PRESENTATION";
}

function JSON_PROY(){

	this.categorias = new Array();
}

var varsProy = new VARS_PROY();
var confProy = new CTES_PROY();
var jsonProy = new JSON_PROY();