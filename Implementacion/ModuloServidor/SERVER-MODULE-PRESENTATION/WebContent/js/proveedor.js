//proveedor.js

$(document).ready(function(){

    $( "#id_fechaPublicacion" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: "dd-mm-yy",
	      maxDate: 0
    });
    $.when(
   		obtenerCategoriasySubcategorias()
    )
    .then (function(){
	    //cargarComboCategorias()
    });

});

function cargarComboCategorias(){

	var select = document.getElementById('id_categoria');

	for (var i = 0; i < select.options.length; i++)
		select.options.remove();

	for (var i = 0; i < jsonProy.categorias.length; i++) {
		var option1 = document.createElement("option");
			option1.text = jsonProy.categorias[i].nombre;
			option1.value = i;//jsonProy.categorias[i].id;
		select.add(option1,select.options[null]);
	};
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

			divVideo.classList.remove('show');
			divVideo.classList.add('hide');
		
			divLibro.classList.remove('hide');
			divLibro.classList.add('show');

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
//	content.Imagen 		  = document.getElementById("").value;
//	content.Source 		  = document.getElementById("").value;
//	content.Precio        = document.getElementById("").value;
	content.tipoContenido = document.getElementById("id_tipoContenido").value;
	content.Categoria 	  = document.getElementById("id_categoria").value;
	content.subcategoria  = document.getElementById("id_subcategoria").value;

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
}