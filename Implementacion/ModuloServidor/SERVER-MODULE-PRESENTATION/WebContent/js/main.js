/**
 * Archivo de arranque de la aplicacion
 */

$(document).ready(function(){
    $('.carousel').carousel({
        interval: 3000
    });
    
    //rol = new Rol(1,"Usuario");
    //modificarUsuarioX("canario","mouse","contrasenia","apellido",rol);
    //obtenerUsuariosServ();
//    registroUsuario("pelo","nombre","contrasenia","apellido","mail");
    
});

function clickRegistroUsuario(){
	var div = $("#registroUsuario");
	div.show();
}

function clickLogin(){
	var div = $("#loginUsuario");
		div.show();
}

function cerrarPanelRegistro(){
	var div = $("#registroUsuario");
	div.hide();
}

function cerrarPanelLogin(){
	var div = $("#loginUsuario");
	div.hide();
}