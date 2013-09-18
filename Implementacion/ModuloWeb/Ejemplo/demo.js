//Objeto usario
function Usuario(nick,nombre,apellido){
	this.User 	= nick;
	this.Nombre	= nombre;
	this.Apellido	= apellido;
	this.Descripcion = "--";
}

var usuarios = new Array();
//  -> /usuarios
//  -> GET
//Cargo unos usuarios de prueba
usuarios[0] = new Usuario("Pelo","Alejandro","Fontes");
usuarios[1] = new Usuario("Chileno","Mauricio","Rodriguez");
usuarios[2] = new Usuario("Canario","Andres","Aldao");

$(document).ready(function(){
	tbody = document.getElementById("usersTable").getElementsByTagName("TBODY")[0];
})

function imprimirUsuariosEnPantalla(){
for (var j=0; j<usuarios.length; j++){
	var tr = document.createElement("tr");

	for (var i=0; i<4; i++){

		var td = document.createElement("td");

		switch (i){
			case 0:
				td.appendChild(document.createTextNode(usuarios[j].User));
			break;
			case 1:
				td.appendChild(document.createTextNode(usuarios[j].Nombre));
			break;
			case 2:
				td.appendChild(document.createTextNode(usuarios[j].Apellido));
			break;
			case 3:
				td.appendChild(document.createTextNode(usuarios[j].Descripcion));
			break;
}
		tr.appendChild(td);
		}

	tbody.appendChild(tr);	
	}
	
}

function actualizarTabla(){

imprimirUsuariosEnPantalla();
}

//Llamadas Ajax
//modificacion de datos
/*function modificarUsuarioX(nick,nombre,apellido){
	$.ajax({
		url: '/modificarUsuario',
		type: 'POST',
		data:JSON.stringify({
			'nick'	     : nick,
			'nombre'     : nombre,
			'apellido'   : apellido
		}),
		datatype: "json",
		contentType: "application/json",
	})
	.done(function(msg){
		alert('Guardado con exito!!');
	})
	.fail(function(){
		alert('Fallo al guardar!!');
	})
}
*/
//Pido datos del servidor
/*function obtenerUsuariosServ(){
	$.ajax({
	   url: '/usuarios',
	   type: 'GET',
	   data: '',
	   datatype: "json",
	   contentType: "application/json",
	})
	.done(function(msg) {
		//Listo los miembros del grupo (suponiendo que recibi esto)
	})
	.fail(function() {
		//control de error
	})
}*/
