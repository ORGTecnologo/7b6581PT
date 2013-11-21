/**
 * Archivo de arranque de la aplicacion
 */
$('#openBtn').click(function(){
	$('#myModal').modal({show:true})
});

$(document).ready(function(){

	inicializarPrototipos();

    $( "#inputFecha" ).datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: "dd-mm-yy",
	      maxDate: 0
    });

    $( "#registroUsuario").hide();
    $( "#loginUsuario").hide();
    $( "#Nick-Logout-Div").hide();

	cargarConfigBusqueda('multiplesCat');

	checkearSesionAbierta();
  //   if (checkearSesionAbierta()){
		// var nick = document.getElementById("Nick-Logout-Div").getElementsByClassName("nick")[0];
		// 	nick.innerText = varsProy.nick;

	 //    mostrarElemento('Nick-Logout-Div');
	 //    ocultarElemento('Login-Registro-Div');
  //   }
	
   	$('.selectpicker').selectpicker();
});