//admin.js

$(document).ready(function(){
    
	inicializarPrototipos();

    $( "#inputFecha" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: "dd-mm-yy",
	      maxDate: 0
    });
   
    $( "#div_altaServicio").hide();

    if (checkearSesionAbierta()){
		var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
			nick.innerText = varsProy.nick;

	    mostrarElemento('Nick-Logout-Div');
	    ocultarElemento('Login-Registro-Div');
    }
});



/* funciones que cargan los abms en el backend del administrador */

function loadABMCategorias(){
	$("#wrapperAdministracion").load("/SERVER-MODULE-PRESENTATION/admin/partialCategorias.xhtml");	
}
