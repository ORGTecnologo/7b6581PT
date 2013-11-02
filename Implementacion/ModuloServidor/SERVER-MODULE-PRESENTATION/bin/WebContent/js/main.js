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

function onlyNumbersDano(evt){
    var keyPressed = (evt.which) ? evt.which : event.keyCode
            return !(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57));
	
}

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

function Pintar_hasError(input){

	if (input.value == ""){
		input.parentElement.setAttribute('class','has-error');
		return true;
	}
	input.parentElement.setAttribute('class','has-succes');
	return false;
}

function errorControlVaciosFormularioRegistro() {
	
	var error0 = Pintar_hasError(document.getElementById('inputNombre'));
	var error1 = Pintar_hasError(document.getElementById('inputNick'));
	var error2 = Pintar_hasError(document.getElementById('inputPass'));
	var error3 = Pintar_hasError(document.getElementById('inputCorreo'));
	var error4 = Pintar_hasError(document.getElementById('inputApellido'));
	var error5 = Pintar_hasError(document.getElementById('inputSexo'));
	var error6 = Pintar_hasError(document.getElementById('inputFecha'));
	var error7 = Pintar_hasError(document.getElementById('inputTel'));
	
	if(error0 || error1 || error2 || error3 || error4 || error5 || error6 || error7){
		alert('Ha dejado campos sin completar!!!');
		return true;
	}
	else
		return false;
}


function crearUsuario(){
	
	if (!errorControlVaciosFormularioRegistro()){
	
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
		}
		else{
			var email = document.getElementById('inputCorreo');
			email.parentElement.setAttribute('class','has-error');
			alert('El correo no es valido!!');
		}
	}
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