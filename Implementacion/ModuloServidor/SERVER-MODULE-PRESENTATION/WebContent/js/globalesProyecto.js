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

//PARAMETROS DE BUSCQUEDA
	this.PARAM_BUSQ_APPS = true;
	this.PARAM_BUSQ_LIBROS = true;
	this.PARAM_BUSQ_MUSICA = true;
	this.PARAM_BUSQ_VIDEO = true;
	this.PARAM_BUSQ_PAGAS = true;
	this.PARAM_BUSQ_CATEGORIAS = 'all';
}

function CTES_PROY(){

	this.sessionStorageUser   = 'user_proyMPTecnologo';
	this.sessionStorageToken  = 'token_proyMPTecnologo';
	this.sessionStorageRol    = 'rol_proyMPTecnologo';

	this.TIPO_CONTENIDO_MUSICA= "tipo_contenido_tema";
	this.TIPO_CONTENIDO_APP   = "tipo_contenido_software";
	this.TIPO_CONTENIDO_VIDEO = "tipo_contenido_video";
	this.TIPO_CONTENIDO_LIBRO = "tipo_contenido_libro";

	this.HOST 		  = "http://127.0.0.1:8080";
	this.IP_SERVICIOS = this.HOST + "/SERVER-MODULE-PRESENTATION";
}

function JSON_PROY(){

	this.categorias = new Array();
	this.resultadoBusqueda  = new Array();
}

var varsProy = new VARS_PROY();
var confProy = new CTES_PROY();
var jsonProy = new JSON_PROY();