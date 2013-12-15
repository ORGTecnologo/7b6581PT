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
      topAux = jsonProy.top_video;
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
  
  if (topAux === undefined)
    topAux = new Array();

  if (topAux.length < confProy.CANT_CONTENIDOS_INDEX)
    lengthFor = topAux.length;

  for (var i = (pag*10 + 0); i < (pag*10 + 10); i++) {

    if (!(i >= lengthFor)) {

      var idContenido   = topAux[i].id;
      var descripcion   = topAux[i].descripcionContenido;
      var nombre        = topAux[i].nombreContenido;
      var precio        = topAux[i].precio;
      var calificacion  = topAux[i].calificacion;      
      var foto          = '/Images?' + topAux[i].listaFotos[0];
      var rutaDescarga  = topAux[i].urlDescarga;

      var formatPrice = "UYU " + precio;
      if (precio == 0)
        formatPrice = 'Gratis';

      html += "<div class='col-sm-5 col-md-3'><div class='thumbnail'>"
            + "<img src='" + foto + "' class='img-thumbnail miniatura img-responsive' style='width:200px;height:200px;'>"
            + "<div class='caption'><h2 class='tituloTops'>" + nombre + "</h2>"
            + "<p class='descripcionTops'>" + descripcion + "</p>"
            + "<p class='precio'><a href='" + confProy.URL_CONTENIDO + idContenido + "' class='btn btn-primary' role='button'>Ver</a>"
            + "<a href='" + rutaDescarga + "' onclick='return confirmar();' class='btn btn-default' role='button'>Descargar</a>"  
            + formatPrice + "</p></div></div></div>";
    };
  };
  var container = document.getElementById(idElem);
      container.innerHTML = html;

}

function cargarTopEnMemoria(tipo,msg){
  
  if (msg === undefined)
    msg = new Array();

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