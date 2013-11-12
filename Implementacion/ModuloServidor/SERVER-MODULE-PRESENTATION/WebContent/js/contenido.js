//contenido.js

$(document).ready(function(){
    
    $( "#registroUsuario").hide();
    $( "#loginUsuario").hide();
    $( "#Nick-Logout-Div").hide();

	inicializarPrototipos();

    cargarDatosDelContenido();

    $( "#inputFecha" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: "dd-mm-yy",
	      maxDate: 0
    });
   
    if (checkearSesionAbierta()){
		var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
			nick.innerText = varsProy.nick;

	    mostrarElemento('Nick-Logout-Div');
	    ocultarElemento('Login-Registro-Div');
    }
});

function cargarDatosDelContenido(){

	// VERSION 1 -- DATOS PASADOS EN LA URL ATRAVES DE ?id=1&tk=hjdsanjda
	var ArrayQuery = window.location.search.split('?')[1].split('&');

	for (var i = 0; i < ArrayQuery.length; i++) {
		var pareja = ArrayQuery[i].split('=');
		switch(pareja[0]){
			case 'id':
				varsProy.idContenido = pareja[1];
			break;
			case 'tk':
				varsProy.token = pareja[1];
			break;
		}
	}	
	obtenerContenidoPorId(varsProy.idContenido);
}

function esconderEspecificosOtros(tipoContenido){

	switch(tipoContenido){
		case(confProy.TIPO_CONTENIDO_MUSICA):
			$("#espMusica").show();
			$("#espApp").hide();
			$("#espVideo").hide();
			$("#espLibro").hide();
		break;
		case(confProy.TIPO_CONTENIDO_APP):
			$("#espMusica").hide();
			$("#espApp").show();
			$("#espVideo").hide();
			$("#espLibro").hide();
		break;
		case(confProy.TIPO_CONTENIDO_VIDEO):
			$("#espMusica").hide();
			$("#espApp").hide();
			$("#espVideo").show();
			$("#espLibro").hide();
		break;
		case(confProy.TIPO_CONTENIDO_LIBRO):
			$("#espMusica").hide();
			$("#espApp").hide();
			$("#espVideo").hide();
			$("#espLibro").show();
		break;
	}
}

function setearEspecificos(content){

	var divEsp, aux;

	switch(content.tipoContenido){
		case(confProy.TIPO_CONTENIDO_MUSICA):
			divEsp = $("#espMusica");
			divEsp.children()[0].innerText = "Duracion:  " + content.duracionTema;
			divEsp.children()[1].innerText = "Artista :  " + content.artistaTema;
			divEsp.children()[2].innerText = "Album   :  " + content.albumTema;
		break;
		case(confProy.TIPO_CONTENIDO_APP):
			divEsp = $("#espApp").show();
			
			if (content.requisitosMinimos === null) aux = "";	
			else aux = content.requisitosMinimos;

			divEsp.children()[0].innerText = "Es Trial     :  " + content.esTrial;
			divEsp.children()[1].innerText = "Req. Minimos :  " + content.requisitosMinimos;			
			divEsp.children()[1].innerText = "Desarrollador:  " + content.proveedor;			
		break;
		case(confProy.TIPO_CONTENIDO_VIDEO):
			divEsp = $("#espVideo").show();
			divEsp.children()[0].innerText = "Duracion:  " + content.duracionVideo;
			divEsp.children()[1].innerText = "Formato :  " + content.formatoVideo;
			divEsp.children()[2].innerText = "Calidad :  " + content.calidadVideo;
		break;
		case(confProy.TIPO_CONTENIDO_LIBRO):
			divEsp = $("#espLibro").show();
			
			aux = new Date(content.fechaPublicacion).toLocaleDateString();
			divEsp.children()[0].innerText = "Fecha Publicacion:  " + aux;
			divEsp.children()[1].innerText = "Autor            :  " + content.autor;
			divEsp.children()[2].innerText = "Paginas          :  " + content.Paginas;
		break;
	}
}

function armarContenidoHTML(){

	//CARGA LA PARTE VISUAL DEL CONTENT.HTML
	var Content = varsProy.contenidoActual;

	var divContent = document.getElementById('MP_Content');
	var DescContent = document.getElementById('MP_Descripcion');
	var PrecioContent = document.getElementById('MP_Precio');

	var thumbnailContent = document.createElement('div')
	var imgContent = document.createElement('img')
	var divCaption = document.createElement('div')
	var h3Content = document.createElement('h3')
	var srcContent = document.createElement('a')

	var comprarBtn = document.getElementById('comprarBtn');
		comprarBtn.setAttribute('href',Content.Source);

	var pTam = document.getElementById('tamanioCont');
		pTam.innerText = 'Tamaño:  ' + Content.Tamanio;
	
	var pDesc = document.getElementById('cantDescargas');
		pDesc.innerText = 'Cantidad de Descargas:  ' + Content.Descargas;


	//ARMO EL ESQUEMA
	if (Content.Precio.toString() === 'gratis')
		PrecioContent.innerText = Content.Precio.toString();
	else
		PrecioContent.innerText = Content.Moneda + " " + Content.Precio.toString();

	DescContent.innerText = Content.Descripcion;
	h3Content.innerText = Content.Nombre;

	divCaption.setAttribute('class','caption');
	divCaption.appendChild(h3Content);

	imgContent.setAttribute('src',confProy.HOST + Content.Imagenes[0]);
	imgContent.setAttribute('alt',confProy.HOST + Content.Imagenes[0]);
	imgContent.setAttribute('class','img-circle miniatura');
	imgContent.setAttribute('style','width: 250px;height: 250px;');


	thumbnailContent.setAttribute('class','thumbnail');
	thumbnailContent.appendChild(imgContent);
	thumbnailContent.appendChild(divCaption);

	divContent.appendChild(thumbnailContent);

	cargarPuntuacion(Content.Calificacion,"../");
	esconderEspecificosOtros(Content.tipoContenido);
	setearEspecificos(Content);
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