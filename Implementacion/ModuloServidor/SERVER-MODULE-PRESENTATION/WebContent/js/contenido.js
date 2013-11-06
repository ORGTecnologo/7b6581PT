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

	var thumbnailContent = document.createElement('div')
	var imgContent = document.createElement('img')
	var divCaption = document.createElement('div')
	var h3Content = document.createElement('h3')
	var DescContent = document.createElement('p')
	var PrecioContent = document.createElement('p')
	var srcContent = document.createElement('a')

	//ARMO EL ESQUEMA
	srcContent.setAttribute('href',Content.Source);
	srcContent.setAttribute('class','btn btn-default');
	srcContent.setAttribute('role','button');
	srcContent.innerText = 'Comprar';

	PrecioContent.setAttribute('class','precio');
	PrecioContent.appendChild(srcContent);	
	PrecioContent.innerText = Content.Moneda + " " + Content.Precio.toString();

	DescContent.innerText = Content.Descripcion;
	h3Content.innerText = Content.Nombre;

	divCaption.setAttribute('class','caption');
	divCaption.appendChild(h3Content);
	divCaption.appendChild(DescContent);
	divCaption.appendChild(PrecioContent);

	imgContent.setAttribute('src','../'+Content.Imagen);
	imgContent.setAttribute('alt','../'+Content.Imagen);
	imgContent.setAttribute('class','img-circle miniatura');

	thumbnailContent.setAttribute('class','thumbnail');
	thumbnailContent.appendChild(imgContent);
	thumbnailContent.appendChild(divCaption);

	divContent.appendChild(thumbnailContent);

// 	<div class="thumbnail">
// 	<img src="img/mu1.jpg" alt="img/mu1.jpg" class="img-circle miniatura">
// 	<div class="caption">
// 		<h3 id="MP_ContentName">Aplicacion 4</h3>
// 		<p id="MP_ContentDesc">Laslasdkamskldn dkla sjdklasd aklsjdkl ajskldjaskl djklasjd</p>
// 		<p id="MP_ContentPrice" class="precio">
// 			<a id="MP_ContentSrc" href="" class="btn btn-default" role="button">Comprar</a>						
// 			USD 2.0
// 		</p>
// 	</div>
// </div>
}