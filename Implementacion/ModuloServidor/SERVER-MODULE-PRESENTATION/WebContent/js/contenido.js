//contenido.js

$(document).ready(function(){
    
	inicializarPrototipos();

    cargarDatosDelContenido();

    $( "#inputFecha" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: "dd-mm-yy",
	      maxDate: 0
    });
   
    $( "#registroUsuario").hide();
    $( "#loginUsuario").hide();
    $( "#Nick-Logout-Div").hide();

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

	//ARMO EL ESQUEMA
	srcContent.setAttribute('href',Content.Source);
	srcContent.setAttribute('class','btn btn-default');
	srcContent.setAttribute('role','button');
	srcContent.innerText = 'Comprar';

	PrecioContent.innerText = Content.Moneda + " " + Content.Precio.toString();

	DescContent.innerText = Content.Descripcion;
	h3Content.innerText = Content.Nombre;

	divCaption.setAttribute('class','caption');
	divCaption.appendChild(h3Content);

	imgContent.setAttribute('src','../'+Content.Imagen);
	imgContent.setAttribute('alt','../'+Content.Imagen);
	imgContent.setAttribute('class','img-circle miniatura');

	thumbnailContent.setAttribute('class','thumbnail');
	thumbnailContent.appendChild(imgContent);
	thumbnailContent.appendChild(divCaption);

	divContent.appendChild(thumbnailContent);

	cargarPuntuacion(Content.Calificacion);

}

function cargarPuntuacion(puntuacion){

	var star1 = document.getElementById('star1');
	var star2 = document.getElementById('star2');
	var star3 = document.getElementById('star3');
	var star4 = document.getElementById('star4');
	var star5 = document.getElementById('star5');

	switch(puntuacion){
		case 1:
			star1.setAttribute('src','../img/estrellaAct.png');
			star2.setAttribute('src','../img/estrellaNoAct.png');
			star3.setAttribute('src','../img/estrellaNoAct.png');
			star4.setAttribute('src','../img/estrellaNoAct.png');
			star5.setAttribute('src','../img/estrellaNoAct.png');		

			star1.setAttribute('alt','../img/estrellaAct.png');
			star2.setAttribute('alt','../img/estrellaNoAct.png');
			star3.setAttribute('alt','../img/estrellaNoAct.png');
			star4.setAttribute('alt','../img/estrellaNoAct.png');
			star5.setAttribute('alt','../img/estrellaNoAct.png');		
		break;
		case 2:
			star1.setAttribute('src','../img/estrellaAct.png');
			star2.setAttribute('src','../img/estrellaAct.png');
			star3.setAttribute('src','../img/estrellaNoAct.png');
			star4.setAttribute('src','../img/estrellaNoAct.png');
			star5.setAttribute('src','../img/estrellaNoAct.png');

			star1.setAttribute('alt','../img/estrellaAct.png');
			star2.setAttribute('alt','../img/estrellaAct.png');
			star3.setAttribute('alt','../img/estrellaNoAct.png');
			star4.setAttribute('alt','../img/estrellaNoAct.png');
			star5.setAttribute('alt','../img/estrellaNoAct.png');

		break;
		case 3:
			star1.setAttribute('src','../img/estrellaAct.png');
			star2.setAttribute('src','../img/estrellaAct.png');
			star3.setAttribute('src','../img/estrellaAct.png');
			star4.setAttribute('src','../img/estrellaNoAct.png');
			star5.setAttribute('src','../img/estrellaNoAct.png');		

			star1.setAttribute('alt','../img/estrellaAct.png');
			star2.setAttribute('alt','../img/estrellaAct.png');
			star3.setAttribute('alt','../img/estrellaAct.png');
			star4.setAttribute('alt','../img/estrellaNoAct.png');
			star5.setAttribute('alt','../img/estrellaNoAct.png');		

		break;
		case 4:
			star1.setAttribute('src','../img/estrellaAct.png');
			star2.setAttribute('src','../img/estrellaAct.png');
			star3.setAttribute('src','../img/estrellaAct.png');
			star4.setAttribute('src','../img/estrellaAct.png');
			star5.setAttribute('src','../img/estrellaNoAct.png');

			star1.setAttribute('alt','../img/estrellaAct.png');
			star2.setAttribute('alt','../img/estrellaAct.png');
			star3.setAttribute('alt','../img/estrellaAct.png');
			star4.setAttribute('alt','../img/estrellaAct.png');
			star5.setAttribute('alt','../img/estrellaNoAct.png');
		break;
		case 5:
			star1.setAttribute('src','../img/estrellaAct.png');
			star2.setAttribute('src','../img/estrellaAct.png');
			star3.setAttribute('src','../img/estrellaAct.png');
			star4.setAttribute('src','../img/estrellaAct.png');
			star5.setAttribute('src','../img/estrellaAct.png');

			star1.setAttribute('alt','../img/estrellaAct.png');
			star2.setAttribute('alt','../img/estrellaAct.png');
			star3.setAttribute('alt','../img/estrellaAct.png');
			star4.setAttribute('alt','../img/estrellaAct.png');
			star5.setAttribute('alt','../img/estrellaAct.png');
		break;
		default:
			star1.setAttribute('src','../img/estrellaNoAct.png');
			star2.setAttribute('src','../img/estrellaNoAct.png');
			star3.setAttribute('src','../img/estrellaNoAct.png');
			star4.setAttribute('src','../img/estrellaNoAct.png');
			star5.setAttribute('src','../img/estrellaNoAct.png');		

			star1.setAttribute('alt','../img/estrellaNoAct.png');
			star2.setAttribute('alt','../img/estrellaNoAct.png');
			star3.setAttribute('alt','../img/estrellaNoAct.png');
			star4.setAttribute('alt','../img/estrellaNoAct.png');
			star5.setAttribute('alt','../img/estrellaNoAct.png');			
		break;
	}
}