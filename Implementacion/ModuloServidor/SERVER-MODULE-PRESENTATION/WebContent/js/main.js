$(document).ready(function(){

  obtenerTopContenidos(confProy.TIPO_CONTENIDO_LIBRO);
  obtenerTopContenidos(confProy.TIPO_CONTENIDO_MUSICA);
  obtenerTopContenidos(confProy.TIPO_CONTENIDO_APP);
  obtenerTopContenidos(confProy.TIPO_CONTENIDO_VIDEO);

});

function cargarIndexTop(tipo){

  var idElem = "";
  var topAux = new Array();

  switch(tipo){
    case confProy.TIPO_CONTENIDO_MUSICA:
      idElem = "containerMusica";   
      topAux = jsonProy.top_musica;
    break;
    case confProy.TIPO_CONTENIDO_APP:
      idElem = "containerApp";
      topAux = jsonProy.top_software;
    break;
    case confProy.TIPO_CONTENIDO_VIDEO:
      idElem = "containerVideo";
      topAux = jsonProy.top_videos;
    break;
    case confProy.TIPO_CONTENIDO_LIBRO:
      idElem = "containerLibro";
      topAux = jsonProy.top_libros;
    break;
  }
  cargarHtmlTop(topAux,idElem);
}

function cargarHtmlTop(topAux,idElem,pag){
  pag || (pag = 0);

  var lengthFor = confProy.CANT_CONTENIDOS_INDEX;
  var html = "";
  if (topAux.length < confProy.CANT_CONTENIDOS_INDEX)
    lengthFor = topAux.length;

  for (var i = (pag*10 + 0); i < (pag*10 + 10); i++) {

    if (!(i >= lengthFor)) {

      var idContenido   = topAux[i].id;
      var descripcion   = topAux[i].descripcionContenido;
      var nombre        = topAux[i].nombreContenido;
      var precio        = topAux[i].precio;
      var calificacion  = topAux[i].calificacion;      
      var foto          = '/SERVER-MODULE-PRESENTATION/Images?' + topAux[i].listaFotos[0];
      var rutaDescarga  = "";

      html += "<div class='col-sm-5 col-md-3'><div class='thumbnail'>"
            + "<img src='" + foto + "' class='img-thumbnail miniatura img-responsive'>"
            + "<div class='caption'><h2>" + nombre + "</h2>"
            + "<p>" + descripcion + "</p>"
            + "<p class='precio'><a href='" + confProy.URL_CONTENIDO + idContenido + "' class='btn btn-primary' role='button'>Ver</a>"
            + "<a href='" + rutaDescarga + "' class='btn btn-default' role='button'>Comprar</a>"  
            + "UYU " + precio + "</p></div></div></div>";
    };
  };
  var container = document.getElementById(idElem);
      container.innerHTML = html;

}

function cargarTopEnMemoria(tipo,msg){

  switch(tipo){
    case confProy.TIPO_CONTENIDO_MUSICA:
      jsonProy.top_musica = msg;
    break;
    case confProy.TIPO_CONTENIDO_APP:
      jsonProy.top_software = msg;
    break;
    case confProy.TIPO_CONTENIDO_VIDEO:
      jsonProy.top_video = msg;
    break;
    case confProy.TIPO_CONTENIDO_LIBRO:
      jsonProy.top_libros = msg;
    break;
  }
}