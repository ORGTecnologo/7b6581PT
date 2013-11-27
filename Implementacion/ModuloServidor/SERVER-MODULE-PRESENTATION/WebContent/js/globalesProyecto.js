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
	this.idDescargaContenido = "";

//PARAMETROS DE BUSCQUEDA
	this.PARAM_BUSQ_APPS = true;
	this.PARAM_BUSQ_LIBROS = true;
	this.PARAM_BUSQ_MUSICA = true;
	this.PARAM_BUSQ_VIDEO = true;
	this.PARAM_BUSQ_PAGAS = true;
	this.PARAM_BUSQ_CATEGORIAS = 'all';
}

function CTES_PROY(){

//ITEMS DEL SESSION STORAGE
	this.sessionStorageUser   = 'user_proyMPTecnologo';
	this.sessionStorageToken  = 'token_proyMPTecnologo';
	this.sessionStorageRol    = 'rol_proyMPTecnologo';

	this.CANT_CONTENIDOS_INDEX = 4;
	
//ROLES
	this.ROL_ADMINISTRADOR	= 'usuario_administrador';
	this.ROL_PROVEEDOR 		= 'usuario_proveedor';
	this.ROL_CLIENTE 		= 'usuario_cliente';

//TIPOS DE CONTENIDOS
	this.TIPO_CONTENIDO_MUSICA= "tipo_contenido_tema";
	this.TIPO_CONTENIDO_APP   = "tipo_contenido_software";
	this.TIPO_CONTENIDO_VIDEO = "tipo_contenido_video";
	this.TIPO_CONTENIDO_LIBRO = "tipo_contenido_libro";

//URLS...
	this.HOST 		  = "http://localhost:8080";
	this.IP_SERVICIOS = this.HOST + "/SERVER-MODULE-PRESENTATION";
	this.IP_WEBSERVICES = this.IP_SERVICIOS + "/restws";
	this.URL_CONTENIDO = this.IP_SERVICIOS + '/views/content.html?id=';
	this.URL_PERFIL = this.IP_SERVICIOS + '/views/perfil.html?nick=';
	this.URL_ADMIN_HOME = this.IP_SERVICIOS + '/admin/admin.xhtml';
}

function JSON_PROY(){

	this.categorias = new Array();
	this.resultadoBusqueda  = new Array();
	this.contentidosACalificar = new Array();
	this.misContenidos = new Array();

	//TOPS POR TIPO
	this.top_musica = new Array();
	this.top_video = new Array();
	this.top_software = new Array();
	this.top_libros = new Array();
}

var varsProy = new VARS_PROY();
var confProy = new CTES_PROY();
var jsonProy = new JSON_PROY();