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