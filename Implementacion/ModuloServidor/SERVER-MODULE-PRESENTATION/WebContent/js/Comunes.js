//Comunes.js
function checkearSesionAbierta(){

	var usuario = window.localStorage.getItem(confProy.sessionStorageUser);
	var token = window.localStorage.getItem(confProy.sessionStorageToken);

	if (usuario === null)
		return false;

	varsProy.nick = usuario;
	varsProy.token = token;

	return true;
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
	var error8 = Pintar_hasError(document.getElementById('inputPass2'));
	var error9 = false;

	if (varsProy.tipoRegistro == 'proveedor')
		error9 = Pintar_hasError(document.getElementById('inputWeb'));
	
	if(error0 || error1 || error2 || error3 || error4 || error5 || error6 || error7 || error8 || error9){
		alert('Ha dejado campos sin completar!!!');
		return true;
	}
	else
		return false;
}

function crearMiembro(){

	switch(varsProy.tipoRegistro){
		case 'cliente':
			crearUsuario();
		break;
		case 'proveedor':
			crearProveedor();
		break;
	}
}

function crearUsuario(){

	if (!errorControlVaciosFormularioRegistro()){

		var nombre 		 = document.getElementById('inputNombre').value;
		var usuario 	 = document.getElementById('inputNick').value;
		var contrasenia  = document.getElementById('inputPass').value;
		var contrasenia2 = document.getElementById('inputPass2').value;
		var mail 		 = document.getElementById('inputCorreo').value;
		var apellido 	 = document.getElementById('inputApellido').value;
		var sexo 		 = document.getElementById('inputSexo').value;
		var nacimiento   = document.getElementById('inputFecha').value;
		var cel 		 = document.getElementById('inputTel').value;

		if(mail.validarMail()){
			if(contrasenia===contrasenia2){
				registroUsuario(usuario,contrasenia,contrasenia2,mail,nombre,apellido,sexo,nacimiento,cel);
			}
			else{
				document.getElementById('inputPass').value = "";
				document.getElementById('inputPass2').value = "";
				alert('Las contrasenas no coinciden, intentelo nuevamente!!');
			}
		}
		else{
			var email = document.getElementById('inputCorreo');
			email.parentElement.setAttribute('class','has-error');
			alert('El correo no es valido!!');
		}
	}
}

function crearProveedor(){

	if (!errorControlVaciosFormularioRegistro()){

		var nombre 		 = document.getElementById('inputNombre').value;
		var usuario 	 = document.getElementById('inputNick').value;
		var contrasenia  = document.getElementById('inputPass').value;
		var contrasenia2 = document.getElementById('inputPass2').value;
		var mail 		 = document.getElementById('inputCorreo').value;
		var apellido 	 = document.getElementById('inputApellido').value;
		var sexo 		 = document.getElementById('inputSexo').value;
		var nacimiento   = document.getElementById('inputFecha').value;
		var cel 		 = document.getElementById('inputTel').value;
		var sitioWeb	 = document.getElementById('inputWeb').value;

		if(mail.validarMail()){
			if(contrasenia===contrasenia2){
				registroProveedor(usuario,contrasenia,contrasenia2,mail,nombre,apellido,sexo,nacimiento,cel,sitioWeb);
			}
			else{
				document.getElementById('inputPass').value = "";
				document.getElementById('inputPass2').value = "";
				alert('Las contrasenas no coinciden, intentelo nuevamente!!');
			}
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

	if (correo.validarMail())
		loginUsuario(correo,pass);
	else
		alert("Correo Invalido");
}

function cargarPuntuacion(puntuacion,origen){
	origen || (origen = ''); //Ruta por defecto

	var star1 = document.getElementById('star1');
	var star2 = document.getElementById('star2');
	var star3 = document.getElementById('star3');
	var star4 = document.getElementById('star4');
	var star5 = document.getElementById('star5');

	switch(puntuacion){
		case 1:
			star1.setAttribute('src',origen + 'img/estrellaAct.png');
			star2.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star3.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star4.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('src',origen + 'img/estrellaNoAct.png');		

			star1.setAttribute('alt',origen + 'img/estrellaAct.png');
			star2.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star3.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star4.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('alt',origen + 'img/estrellaNoAct.png');		
		break;
		case 2:
			star1.setAttribute('src',origen + 'img/estrellaAct.png');
			star2.setAttribute('src',origen + 'img/estrellaAct.png');
			star3.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star4.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('src',origen + 'img/estrellaNoAct.png');

			star1.setAttribute('alt',origen + 'img/estrellaAct.png');
			star2.setAttribute('alt',origen + 'img/estrellaAct.png');
			star3.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star4.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('alt',origen + 'img/estrellaNoAct.png');

		break;
		case 3:
			star1.setAttribute('src',origen + 'img/estrellaAct.png');
			star2.setAttribute('src',origen + 'img/estrellaAct.png');
			star3.setAttribute('src',origen + 'img/estrellaAct.png');
			star4.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('src',origen + 'img/estrellaNoAct.png');		

			star1.setAttribute('alt',origen + 'img/estrellaAct.png');
			star2.setAttribute('alt',origen + 'img/estrellaAct.png');
			star3.setAttribute('alt',origen + 'img/estrellaAct.png');
			star4.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('alt',origen + 'img/estrellaNoAct.png');		

		break;
		case 4:
			star1.setAttribute('src',origen + 'img/estrellaAct.png');
			star2.setAttribute('src',origen + 'img/estrellaAct.png');
			star3.setAttribute('src',origen + 'img/estrellaAct.png');
			star4.setAttribute('src',origen + 'img/estrellaAct.png');
			star5.setAttribute('src',origen + 'img/estrellaNoAct.png');

			star1.setAttribute('alt',origen + 'img/estrellaAct.png');
			star2.setAttribute('alt',origen + 'img/estrellaAct.png');
			star3.setAttribute('alt',origen + 'img/estrellaAct.png');
			star4.setAttribute('alt',origen + 'img/estrellaAct.png');
			star5.setAttribute('alt',origen + 'img/estrellaNoAct.png');
		break;
		case 5:
			star1.setAttribute('src',origen + 'img/estrellaAct.png');
			star2.setAttribute('src',origen + 'img/estrellaAct.png');
			star3.setAttribute('src',origen + 'img/estrellaAct.png');
			star4.setAttribute('src',origen + 'img/estrellaAct.png');
			star5.setAttribute('src',origen + 'img/estrellaAct.png');

			star1.setAttribute('alt',origen + 'img/estrellaAct.png');
			star2.setAttribute('alt',origen + 'img/estrellaAct.png');
			star3.setAttribute('alt',origen + 'img/estrellaAct.png');
			star4.setAttribute('alt',origen + 'img/estrellaAct.png');
			star5.setAttribute('alt',origen + 'img/estrellaAct.png');
		break;
		default:
			star1.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star2.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star3.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star4.setAttribute('src',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('src',origen + 'img/estrellaNoAct.png');		

			star1.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star2.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star3.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star4.setAttribute('alt',origen + 'img/estrellaNoAct.png');
			star5.setAttribute('alt',origen + 'img/estrellaNoAct.png');			
		break;
	}
}