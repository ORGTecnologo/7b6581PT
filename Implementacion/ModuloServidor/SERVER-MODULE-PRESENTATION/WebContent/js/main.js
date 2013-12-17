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

      var formatPrice = "USD " + precio;
      if (precio == 0){
        formatPrice = 'Gratis';
        html += "<div class='col-sm-5 col-md-3'><div class='thumbnail'>"
              + "<img src='" + foto + "' class='img-thumbnail miniatura img-responsive' style='width:200px;height:200px;'>"
              + "<div class='caption'><h2 class='tituloTops'>" + nombre + "</h2>"
              + "<p class='descripcionTops'>" + descripcion + "</p>"
              + "<p class='precio'><a href='" + confProy.URL_CONTENIDO + idContenido + "' class='btn btn-primary' role='button'>Ver</a>"
              + "<a href='" + rutaDescarga + "' onclick='return confirmar();' class='btn btn-default' role='button'>Descargar</a>"  
              + formatPrice + "</p></div></div></div>";
      }
      else{
        var valor = getCodePayPal(precio);
        html += "<div class='col-sm-5 col-md-3'><div class='thumbnail'>"
              + "<img src='" + foto + "' class='img-thumbnail miniatura img-responsive' style='width:200px;height:200px;'>"
              + "<div class='caption'><h2 class='tituloTops'>" + nombre + "</h2>"
              + "<p class='descripcionTops'>" + descripcion + "</p>"
              + "<div class='row'>" 
              + "<div class='col-md-5'>" 
              + "<form action='https://www.paypal.com/cgi-bin/webscr' method='post' target='_top'>"
              + "<input type='hidden' name='cmd' value='_s-xclick'>"
              + "<input type='hidden' name='hosted_button_id' value='" + valor + "'>"
              + "<input type='image' src='https://www.paypalobjects.com/en_US/i/btn/btn_buynow_SM.gif' border='0' name='submit' alt='PayPal - The safer, easier way to pay online!'>"
              + "<img alt='' border='0' src='https://www.paypalobjects.com/en_US/i/scr/pixel.gif' width='1' height='1'>"
              + "</form>"
              + "</div>"              
              + "<div class='col-md-7'>" 
              + "<p class='precio'><a href='" + confProy.URL_CONTENIDO + idContenido + "' class='btn btn-primary' role='button'>Ver</a>"
              + formatPrice + "</p>"
              + "</div>"
              + "</div>"
              + "</div></div></div>";
      }

    };
  };
  var container = document.getElementById(idElem);
      container.innerHTML = html;

}

function getCodePayPal(precio){
  console.log("ENTRE EN La FUNCION GET CODE PAYPAL_5")
  switch(precio){
    case "50.0":
      return confProy.PAYPAL_50;
    break;
    case "20.0":
      return confProy.PAYPAL_20;    
    break;
    case "10.0":
      return confProy.PAYPAL_10;    
    break;
    case "5.0":
      return confProy.PAYPAL_5;    
    break;
    case "2.0":
      return confProy.PAYPAL_2;    
    break;
    case "1.0":
      return confProy.PAYPAL_1;    
    break;
    default:
      return confProy.PAYPAL_10;
    break; 
  }
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