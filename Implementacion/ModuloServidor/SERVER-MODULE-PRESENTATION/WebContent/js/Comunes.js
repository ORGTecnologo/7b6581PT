//Comunes.js
function checkearSesionAbierta(){

	var usuario = window.localStorage.getItem(confProy.sessionStorageUser);

	if (usuario === null)
		return false;

	varsProy.nick = usuario;

	existeSesionServ(varsProy.nick);
}

function bloquearPantalla(){
    'use strict';
    $.blockUI({ message: '<p><img src="../img/busy.gif" /> Cargando...</p>' ,
        css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        }
    });
}

function desbloquearPantalla(){
    'use strict';
    $.unblockUI();
}


function cargarConfigBusqueda(idSelect){
	obtenerCategoriasySubcategorias('multiplesCat');
}

function actualizarParametrosBusqueda(){

	varsProy.PARAM_BUSQ_APPS = document.getElementById("id_search-Apps").checked;
	varsProy.PARAM_BUSQ_VIDEO = document.getElementById("id_search-Video").checked;
	varsProy.PARAM_BUSQ_MUSICA = document.getElementById("id_search-Musica").checked;
	varsProy.PARAM_BUSQ_LIBROS = document.getElementById("id_search-Libros").checked;
	varsProy.PARAM_BUSQ_PAGAS = document.getElementById("id_search-Pagas").checked;

	var CatSelected = document.getElementById("multiplesCat").selectedOptions;
	var categorias = "";

	for (var i = 0; i < CatSelected.length; i++) {
		if (i!=0)
			categorias += '|';

		categorias += CatSelected[i].value;
	}

	varsProy.PARAM_BUSQ_CATEGORIAS = categorias;

	ocultarElemento('configurarBusqueda');
}

function cargarComboCategorias(idSelect){
	idSelect || (idSelect = 'id_categoria');

	var select = document.getElementById(idSelect);

	for (var i = 0; i < select.options.length; i++)
		select.options.remove();

	for (var i = 0; i < jsonProy.categorias.length; i++) {
		var option1 = document.createElement("option");
			option1.text = jsonProy.categorias[i].nombre;
			option1.value = i;//jsonProy.categorias[i].id;
		select.add(option1,select.options[null]);
	};

	$('.selectpicker').selectpicker('refresh');
}

function cargarComboMultCategorias(idSelect){
	idSelect || (idSelect = 'id_categoria');

	//TODO PELO, combo multiples categorias

	var select = document.getElementById(idSelect);

	for (var i = 0; i < select.options.length; i++)
		select.options.remove();

	for (var i = 0; i < jsonProy.categorias.length; i++) {
		var option1 = document.createElement("option");
			option1.text = jsonProy.categorias[i].nombre;
			option1.value = i;//jsonProy.categorias[i].id;
		select.add(option1,select.options[null]);
	};

	$('.selectpicker').selectpicker('refresh');
}

function cargarComboSubCategorias(elem){

	fila = parseInt(elem.value);
	var subcategorias = jsonProy.categorias[fila].subcategorias;

	var select = document.getElementById('id_subcategoria');

	for (var i = 0; i < select.options.length; i++)
		select.options.remove();

	for (var i = 0; i < subcategorias.length; i++) {
		var option1 = document.createElement("option");
			option1.text = subcategorias[i].nombre;
			option1.value = i;//jsonProy.categorias[i].id;
		select.add(option1,select.options[null]);
	};	
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
				if (!contrasenia.validarPassword()) console.warn('Desactive esta validacion!!!!');
//				if (contrasenia.validarPassword())
					registroUsuario(usuario,contrasenia,contrasenia2,mail,nombre,apellido,sexo,nacimiento,cel);
//				else
//					alert("La contraseña no es segura. La misma debe tener un largo de entre 8 y 15 caracteres;"
//						+ " debe contener por lo menos 1 numero, 1 caracter especial y 1 mayuscula");

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
				if (!contrasenia.validarPassword()) console.warn('Desactive esta validacion!!!!');
//				if (contrasenia.validarPassword())
					registroProveedor(usuario,contrasenia,contrasenia2,mail,nombre,apellido,sexo,nacimiento,cel,sitioWeb);
				// else
				// 	alert("La contraseña no es segura. La misma debe tener un largo de entre 8 y 15 caracteres;"
				// 		+ " debe contener por lo menos 1 numero, 1 caracter especial y 1 mayuscula");
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


function cargarResultadoBusqueda(pag){
    pag || (pag = 0);

	var lengthFor = 10;
	var html = "";
	if (jsonProy.resultadoBusqueda.length < 10)
		lengthFor = jsonProy.resultadoBusqueda.length;

	for (var i = (pag*10 + 0); i < (pag*10 + 10); i++) {

		if (!(i >= lengthFor)) {
			var id     = jsonProy.resultadoBusqueda[i].id;
			var nombre = jsonProy.resultadoBusqueda[i].nombreContenido;
			var desc   = jsonProy.resultadoBusqueda[i].descripcionContenido;
			var foto   = '/SERVER-MODULE-PRESENTATION/Images?' + jsonProy.resultadoBusqueda[i].listaFotos[0];
			var precio = jsonProy.resultadoBusqueda[i].precio;


	        html += "<tr><td><img src='" + foto + "' alt='" + foto + "' class='img-responsive' style='width:50px;height:50px'></td>"
						+ "<td><a href='views/content.html?id=" + id + "'>" + nombre + "</a></td>"
						+ "<td>" + desc + "</td>"
						+ "<td>" + precio + "</td>"
						+ "<td>" + 100 + "/5</td>"
		            + "</tr>";
		};
	};
	var tbody = document.getElementById('cuerpoBusqueda');
		tbody.innerHTML = html;
}

function cargarPaginadoDinamico(){

	var pagination = document.getElementById('paginadoBusqueda');

	var cantPaginas = jsonProy.resultadoBusqueda.length / 10;
		cantPaginas += 0.99;
	var indice = parseInt(cantPaginas.toFixed());

	var html = "";

	for (var i = 0; i < indice; i++) {
		var numero = i + 1;
		html += "<li><a href='#' onclick='cargarResultadoBusqueda('" + i + "')>" + numero +"</a></li>";
	};
	pagination.innerHTML = html;	
}
