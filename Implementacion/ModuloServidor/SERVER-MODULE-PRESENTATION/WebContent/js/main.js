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

function crearUsuario(){
	
	var nombre 		= document.getElementById('inputNombre').value;
	var usuario 	= document.getElementById('inputNick').value;
	var contrasenia = document.getElementById('inputPass').value;
	var mail 		= document.getElementById('inputCorreo').value;
	var apellido 	= document.getElementById('inputApellido').value;
	var sexo 		= document.getElementById('inputSexo').value;
	var nacimiento  = document.getElementById('inputFecha').value;
	var cel 		= document.getElementById('inputTel').value;
	
	registroUsuario(usuario,contrasenia,mail,nombre,apellido,sexo,nacimiento,cel);
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