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
	this.Imagen = "";
	this.Calificacion = 0;
	this.Source = "";
	this.Moneda = "UYU";
	this.Tamanio = 0;
	this.Descargas = 0;
	this.Precio = 0.0;
	this.tipoContenido = "";

	//MUSICA
	this.duracionTema = "00.00";
	this.artistaTema  = "";
	this.albumTema    = "";
	//APP
	this.esTrial = false;
	this.requisitosMinimos = null;
	//LIBROS
	this.fechaPublicacion = 1254967200000,
	this.autor = "";
	this.Paginas = 0;
	//VIDEO
	this.duracionVideo = "03:32",
	this.formatoVideo
	this.calidadVideo
}