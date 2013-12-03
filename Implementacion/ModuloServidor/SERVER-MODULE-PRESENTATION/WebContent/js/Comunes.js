//Comunes.js
function checkearSesionAbierta(){

	var usuario = window.localStorage.getItem(confProy.sessionStorageUser);

	if (usuario === null)
		return false;

	varsProy.nick = usuario;

	existeSesionServ(varsProy.nick);
}

function mostrarElementosSegunUsuario(tipoUsuario){

	switch(tipoUsuario){
		case confProy.ROL_ADMINISTRADOR:
			$('#opt_irAltaContenido').show();
			$('#opt_irAdministracion').show();
		break;
		case confProy.ROL_PROVEEDOR:
			$('#opt_irAltaContenido').show();
			$('#opt_irAdministracion').hide();				
		break;
		case confProy.ROL_CLIENTE:
			$('#opt_irAltaContenido').hide();
			$('#opt_irAdministracion').hide();				
		break;
	}
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

function confirmar(){
	if(confirm('Estas a punto de descargar este contenido, deseas continuar?'))
		return true;
	else
		return false;
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
	alertify.success('Parametros de busqueda actualizados');
	//ocultarElemento('configurarBusqueda');
}

function cargarComboCategorias(idSelect){
	idSelect || (idSelect = 'id_categoria');

	var select = document.getElementById(idSelect);
	if (select != null){
		var largo = select.options.length;

		for (var i = 0; i < largo; i++){
			//select.options.remove();
			//Vaciado del select
			select.options.remove(select.options.item())
		}

		for (var i = 0; i < jsonProy.categorias.length; i++) {
			var option1 = document.createElement("option");
				option1.text = jsonProy.categorias[i].nombre;
				option1.value = jsonProy.categorias[i].id;
			select.add(option1,select.options[null]);
		};
	}
	cargarComboSubCategorias(select);
	$('.selectpicker').selectpicker('refresh');
}

function cargarComboMultCategorias(idSelect){
	idSelect || (idSelect = 'id_categoria');

	var select = document.getElementById(idSelect);
	
	if (select != null){
		var largo = select.options.length;

		for (var i = 0; i < largo; i++){
			//select.options.remove();
			//Vaciado del select
			select.options.remove(select.options.item())
		}

		for (var i = 0; i < jsonProy.categorias.length; i++) {
			var option1 = document.createElement("option");
				option1.text = jsonProy.categorias[i].nombre;
				option1.value = jsonProy.categorias[i].id;
				option1.selected = true;
			select.add(option1,select.options[null]);
		};
	}

	$('.selectpicker').selectpicker('refresh');
}

function cargarComboSubCategorias(elem){

	var select = document.getElementById('id_subcategoria');
	if (select != null){

		var idCat = parseInt(elem.value);
		var fila = jsonProy.categorias.obtenerPosicionPorID(idCat);
		var subcategorias = jsonProy.categorias[fila].subcategorias;

		var largo = select.options.length;

		for (var i = 0; i < largo; i++)
			select.options.remove(select.options.item())

		for (var i = 0; i < subcategorias.length; i++) {
			var option1 = document.createElement("option");
				option1.text = subcategorias[i].nombre;
				option1.value = jsonProy.categorias[i].id;
			select.add(option1,select.options[null]);
		};
	}	
	$('.selectpicker').selectpicker('refresh');
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
		alertify.error('Ha dejado campos sin completar!!!');
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
				if (contrasenia.validarPassword())
					registroUsuario(usuario,contrasenia,contrasenia2,mail,nombre,apellido,sexo,nacimiento,cel);
				else
					alertify.error("La contraseña no es segura. La misma debe tener un largo de entre 8 y 15 caracteres;"
						+ " debe contener por lo menos 1 numero, 1 caracter especial y 1 mayuscula");
			}
			else{
				document.getElementById('inputPass').value = "";
				document.getElementById('inputPass2').value = "";
				alertify.error('Las contrasenas no coinciden, intentelo nuevamente!!');
			}
		}
		else{
			var email = document.getElementById('inputCorreo');
			email.parentElement.setAttribute('class','has-error');
			alertify.error('El correo no es valido!!');
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
				if (contrasenia.validarPassword())
					registroProveedor(usuario,contrasenia,contrasenia2,mail,nombre,apellido,sexo,nacimiento,cel,sitioWeb);
				else
				 	alertify.error("La contraseña no es segura. La misma debe tener un largo de entre 8 y 15 caracteres;"
				 		+ " debe contener por lo menos 1 numero, 1 caracter especial y 1 mayuscula");
			}
			else{
				document.getElementById('inputPass').value = "";
				document.getElementById('inputPass2').value = "";
				alertify.error('Las contrasenas no coinciden, intentelo nuevamente!!');
			}
		}
		else{
			var email = document.getElementById('inputCorreo');
			email.parentElement.setAttribute('class','has-error');
			alertify.error('El correo no es valido!!');
		}
	}
}

function IniciarSesion(){
	
	var correo 	= document.getElementById('loginCorreo').value;
	var pass = document.getElementById('loginPass').value;

	if (correo.validarMail())
		loginUsuario(correo,pass);
	else
		alertify.error("Correo Invalido");
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

	var html = "";
	var aMostrar = jsonProy.resultadoBusqueda.length % 10;;
	var expresion = jsonProy.resultadoBusqueda.length - aMostrar - (10 * pag);
	var lengthFor = 10;
	if (expresion <= 0)
		lengthFor = aMostrar;

	if((pag == aMostrar-1) && (pag > 0))
		lengthFor = aMostrar;

	for (var i = (pag*10 + 0); i < (pag*10 + 10); i++) {

		if (!(i >= (pag*10 + lengthFor))) {
			var id     = jsonProy.resultadoBusqueda[i].id;
			var nombre = jsonProy.resultadoBusqueda[i].nombreContenido;
			var desc   = jsonProy.resultadoBusqueda[i].descripcionContenido;
			var foto   = '/SERVER-MODULE-PRESENTATION/Images?' + jsonProy.resultadoBusqueda[i].listaFotos[0];
			var precio = jsonProy.resultadoBusqueda[i].precio;
			var calificacion = jsonProy.resultadoBusqueda[i].calificacion;


	        html += "<tr><td><img src='" + foto + "' alt='" + foto + "' class='img-responsive' style='width:50px;heigth:50px'></td>"
						+ "<td><a href='" + confProy.URL_CONTENIDO + id + "'>" + nombre + "</a></td>"
						+ "<td>" + desc + "</td>"
						+ "<td>" + precio + "</td>"
						+ "<td>" + calificacion + "/5</td>"
		            + "</tr>";
		};
	};
	var tbody = document.getElementById('cuerpoBusqueda');
		tbody.innerHTML = html;
}

function cargarPaginadoDinamico(){

	var pagination = document.getElementById('paginadoBusqueda');

	var cantPaginas = jsonProy.resultadoBusqueda.length / 10;
	
	var indice = parseInt(cantPaginas.toFixed());
	if(cantPaginas > indice)
		indice++;

	var html = "";

	for (var i = 0; i < indice; i++) {
		var numero = i + 1;
		html += "<li><a href='#' onclick='cargarResultadoBusqueda(" + i + ")'>" + numero +"</a></li>";
	};
	pagination.innerHTML = html;	
}

function cargarPaginadoPendientes(){

	var pagination = document.getElementById('paginadoPendientes');

	var cantPaginas = jsonProy.contentidosACalificar.length / 10;
	
	var indice = parseInt(cantPaginas.toFixed());
	if(cantPaginas > indice)
		indice++;

	var html = "";

	for (var i = 0; i < indice; i++) {
		var numero = i + 1;
		html += "<li><a href='#' onclick='cargarTablaPendientesCalificacion(" + i + ")'>" + numero +"</a></li>";
	};
	pagination.innerHTML = html;	
}

function cargarPaginadoMisContenidos(){

	var pagination = document.getElementById('paginadoMisContenidos');

	var cantPaginas = jsonProy.misContenidos.length / 10;
	
	var indice = parseInt(cantPaginas.toFixed());
	if(cantPaginas > indice)
		indice++;

	var html = "";

	for (var i = 0; i < indice; i++) {
		var numero = i + 1;
		html += "<li><a href='#' onclick='cargarTablaMisContenidos(" + i + ")'>" + numero +"</a></li>";
	};
	pagination.innerHTML = html;	
}