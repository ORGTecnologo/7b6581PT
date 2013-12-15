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

function ParametrosBusqueda(){
	this.keyword = "";
	this.libros = true;
	this.musica = true;
	this.apps = true;
	this.videos = true;
	this.pagas = true;
	this.categorias = "all";
}

function Contenido(){

	this.Id = "";
	this.Nombre = "";
	this.Descripcion = "";
	this.Imagen = "";
	this.Calificacion = 0;
	this.Source = "";
	this.Moneda = "USD";
	this.Tamanio = 0;
	this.Descargas = 0;
	this.Precio = 0.0;
	this.tipoContenido = "";
	this.Categoria = ""
	this.Subcategoria = "";
	this.desarrollador = "";

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
	this.formatoVideo = "";
	this.calidadVideo = "";
}

function Categoria(){
	this.id = '';
    this.nombre = '';
    this.descripcion = '';
    this.rutaImagen = '';
    this.subcategorias = new Array();
}

function Subcategoria(){
	this.id = '';
	this.nombre = '';
	this.descripcion = '';
	this.rutaImagen = '';
}


//CARGA DE JSONs

function cargarCategoriasMemoria(msg){

	for (var i = 0; i < msg.length; i++) {
		var Cat = new Categoria();
		Cat.id = msg[i].id;
		Cat.nombre = msg[i].nombre;
		Cat.descripcion = msg[i].descripcion;
		Cat.rutaImagen = msg[i].rutaImagen;

		for (var j = 0; j < msg[i].subcategorias.length; j++) {
			var SC = new Subcategoria();

			SC.id = msg[i].subcategorias[j].id;
			SC.nombre = msg[i].subcategorias[j].nombre;
			SC.descripcion = msg[i].subcategorias[j].descripcion;
			SC.rutaImagen = msg[i].subcategorias[j].rutaImagen;
			Cat.subcategorias.push(SC);
		};
		jsonProy.categorias.push(Cat);
	};
}