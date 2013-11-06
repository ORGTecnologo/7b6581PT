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
	this.Precio = 0.0;
	this.Imagen = "";
	this.Calificacion = 0;
	this.Source = "";
	this.Moneda = 1;//DOLARES
// 	this. = "";
// 	this. = "";
}