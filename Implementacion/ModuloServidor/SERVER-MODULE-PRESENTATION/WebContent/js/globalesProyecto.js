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

	this.sessionStorageUser = 'user_proyMPTecnologo';
	this.sessionStorageToken = 'token_proyMPTecnologo';
}

var varsProy = new VARS_PROY();
var confProy = new CTES_PROY();