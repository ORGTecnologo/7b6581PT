//Objetos.js
//Aqui definimos todos los JSON's del sistema

//Objeto usario
function Usuario(nick,nombre,apellido){
	this.User 	= nick;
	this.Nombre	= nombre;
	this.Apellido	= apellido;
	this.Descripcion = "--";
}

function Rol(id,Nombre){
	this.id = id;
	this.descripcion = Nombre;
}

function Contenido(){

	this.Id = "";
	this.Nombre = "";	
	this.Descripcion = "";	
	this.Precio
	this.Imagen = "";
	this.Calificacion = "";
	this.Source = "";
// 	this. = "";
// 	this. = "";
}