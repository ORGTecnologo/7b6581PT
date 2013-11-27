//proveedor.js

$(document).ready(function(){

    $( "#id_fechaPublicacion" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: "dd-mm-yy",
	      maxDate: 0
    });
});

function verEspecificos(elem){

	var divApp = $('#esp_app')[0];
	var divMusica = $('#esp_musica')[0];	
	var divVideo = $('#esp_video')[0];
	var divLibro = $('#esp_libro')[0];

	switch(elem.value){
		case confProy.TIPO_CONTENIDO_MUSICA:

			divMusica.classList.remove('hide');
			divMusica.classList.add('show');

			divApp.classList.remove('show');
			divApp.classList.add('hide');

			divVideo.classList.remove('show');
			divVideo.classList.add('hide');

			divLibro.classList.remove('show');
			divLibro.classList.add('hide');

		break;
		case confProy.TIPO_CONTENIDO_APP:

			divMusica.classList.remove('show');
			divMusica.classList.add('hide');

			divApp.classList.remove('hide');
			divApp.classList.add('show');

			divVideo.classList.remove('show');
			divVideo.classList.add('hide');

			divLibro.classList.remove('show');
			divLibro.classList.add('hide');
		
		break;
		case confProy.TIPO_CONTENIDO_VIDEO: 

			divMusica.classList.remove('show');
			divMusica.classList.add('hide');

			divApp.classList.remove('show');
			divApp.classList.add('hide');

			divVideo.classList.remove('hide');
			divVideo.classList.add('show');
		
			divLibro.classList.remove('show');
			divLibro.classList.add('hide');

		break;
		case confProy.TIPO_CONTENIDO_LIBRO:

			divMusica.classList.remove('show');
			divMusica.classList.add('hide');

			divApp.classList.remove('show');
			divApp.classList.add('hide');

			divVideo.classList.remove('show');
			divVideo.classList.add('hide');
		
			divLibro.classList.remove('hide');
			divLibro.classList.add('show');

		break;
	}
}

function enviarAltaContenido(){

	var content = new Contenido();

	content.Nombre 		  = document.getElementById("id_nombre").value;
	content.Descripcion   = document.getElementById("id_descripcion").value;

	var ListaArchivos = document.getElementById('fileupload');
	var arrArchivos = ListaArchivos.getElementsByTagName("TR");
	content.Imagen  = new Array();

	for (var i = 0; i < arrArchivos.length; i++) {
		var elem = arrArchivos[i];
		var span = elem.getElementsByTagName('a')[1];
			if (i===0)
				content.Source = span.innerText;
			else
				content.Imagen.push(span.innerText);
	}

	content.Precio        = document.getElementById("id_precio").value;
	content.tipoContenido = document.getElementById("id_tipoContenido").value;
	content.Categoria 	  = document.getElementById("id_categoria").value;
	content.Subcategoria  = document.getElementById("id_subcategoria").value;

	//MUSICA
	content.duracionTema  = document.getElementById("id_duracionMusica").value;
	content.artistaTema   = document.getElementById("id_artista").value;
	content.albumTema     = document.getElementById("id_album").value;
	//APP
	content.esTrial = document.getElementById("id_trial").value;
	content.requisitosMinimos = document.getElementById("id_req").value;
	content.desarrollador = document.getElementById("id_desarrollador").value;
	//LIBROS
	content.fechaPublicacion = document.getElementById("id_fechaPublicacion").value;
	content.autor = document.getElementById("id_autor").value;
	content.Paginas = document.getElementById("id_cantPag").value;
	//VIDEO
	content.duracionVideo = document.getElementById("id_duracion").value;
	content.formatoVideo  = document.getElementById("id_formato").value;
	content.calidadVideo  = document.getElementById("id_calidad").value;

	if (!validacionesGralesAltaContenido(content.Precio,content.tipoContenido,content.Categoria,content.Subcategoria))
		return false;
	else 
		if (!validarDatosEspecificos(content))
			return false;

	altaContenido(content);
}

function validacionesGralesAltaContenido(precio,tipo,cat,subcat){

	if(precio == null || tipo == null || cat == null || subcat == null){
		alertify.error('Todos los campos son obligatorios!!');
		return false;
	}
	else{
		if (!precio.isNumeric()){
			alertify.error('El precio no es valido');
			return false;
		}
	}
	return true;
}

function validarDatosEspecificos(content){

	switch(content.tipoContenido){
		case confProy.TIPO_CONTENIDO_MUSICA:
			if (content.duracionTema == "" || content.artistaTema == "" || content.albumTema == ""){
				alertify.error('Todos los campos son obligatorios!!');
				return false;
			}
			else {
				if(!content.duracionTema.isDuracion()){
					alertify.error("El formato de la duracion es el siguiente: '00:00'");
					return false;
				}
			}
		break;
		case confProy.TIPO_CONTENIDO_APP:
			if (content.desarrollador == "" || content.requisitosMinimos == ""){
				alertify.error('Todos los campos son obligatorios!!');
				return false;
			}
		break;
		case confProy.TIPO_CONTENIDO_VIDEO:		
			if (content.duracionVideo == "" || content.formatoVideo == "" || content.calidadVideo == ""){
				alertify.error('Todos los campos son obligatorios!!');
				return false;
			}
			if(!content.duracionVideo.isDuracion()){
				alertify.error("El formato de la duracion es el siguiente: '00:00'");
				return false;
			}
		break;
		case confProy.TIPO_CONTENIDO_LIBRO:
			if (content.Paginas == "" || content.autor == "" || content.fechaPublicacion == ""){
				alertify.error('Todos los campos son obligatorios!!');
				return false;
			}
			if(!content.Paginas.isNumeric()){
				alertify.error("El formato de la duracion es el siguiente: '00:00'");
				return false;
			}
		break;
	}
	return true;
}