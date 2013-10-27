/**
 * Archivo de arranque de la aplicacion
 */

$(document).ready(function(){
    $('.carousel').carousel({
        interval: 3000
    });
    
    $( "#inputFecha" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: "dd-mm-yy",
	      maxDate: 0
    });
   
    //rol = new Rol(1,"Usuario");
    //modificarUsuarioX("canario","mouse","contrasenia","apellido",rol);
    //obtenerUsuariosServ();
//    registroUsuario("pelo","nombre","contrasenia","apellido","mail");
    
});

function validateEmail($email) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	if( !emailReg.test( $email ) )
		return false;
	else
		return true;
}


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
	
	if(validateEmail(mail)){
		//if continuar validando
		registroUsuario(usuario,contrasenia,mail,nombre,apellido,sexo,nacimiento,cel);
		document.getElementById('inputNombre').style = "";
		document.getElementById('inputNick').style = "";
		document.getElementById('inputPass').style = "";
		document.getElementById('inputCorreo').style = "";
		document.getElementById('inputApellido').style = "";
		document.getElementById('inputSexo').style = "";
		document.getElementById('inputFecha').style = "";
		document.getElementById('inputTel').style = "";
	}
	else{
		document.getElementById('inputCorreo').style = "border-color: red";
	}
//		console.warn("Email incorrecto!!!");
}

function IniciarSesion(){
	
	var correo 	= document.getElementById('loginCorreo').value;
	var pass = document.getElementById('loginPass').value;
	
	loginUsuario(correo,pass);
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