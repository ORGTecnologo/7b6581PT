    // "id": 2,
    // "nombreContenido": "Las Sombas de Grey",
    // "descripcionContenido": "lalal lalla alsdmasdma das gdfgsdf sdfsdff   dasd  asda da\n asdas dasd ",
    // "listaFotos": [
    //   "EUt0XIBTl9K6h1pZ2m7sSnDaof+DjJ7SSsUBvWuasR6rXSiKjF99KtgbPS2lo1Onx8XXk0ZWhEze6d2cqB9iSJX719yjbbB9",
    //   "hySGUm4ph3F/jTHy/rZ7gyLs+PGFqRa2/S+ZpXzsCTWsA8cxfTiqLZ7S4iQU+PlVgKoaBCMfHLn8D2UCUuZtH20Wi2mYUksM",
    //   "jXMQXkCYwJiPd6P/qDotaD8aYmApq0OvTy75jP5Vc6lqodmCEBoXi1PP3T9KQ53I0PJuJngbKCK9la4ugUUGBUokG6KrCQt+",
    //   "uwCBdA2z5v3kKndRQPixR7OdSqo3eLQ4M6IhjeZgzMptA7GpByP27BfajgLwCj8uwlw1FgprjawewUmyRZC03D5gLeUl/upF"
    // ],
    // "precio": "5.0",
    // "calificacion": 0

function cargarIndexTop(tipo,cant){

  var idElem = "";

  switch(tipo){
    case confProy.TIPO_CONTENIDO_MUSICA:
      idElem = "containerMusica";   
    break;
    case confProy.TIPO_CONTENIDO_APP:
      idElem = "containerApp";
    break;
    case confProy.TIPO_CONTENIDO_VIDEO:
      idElem = "containerVideo";
    break;
    case confProy.TIPO_CONTENIDO_LIBRO:
      idElem = "containerLibro";
    break;
  }

  var lengthFor = cant;
  var html = "";
  if (jsonProy.misContenidos.length < cant)
    lengthFor = jsonProy.misContenidos.length;

  for (var i = (pag*10 + 0); i < (pag*10 + 10); i++) {

    if (!(i >= lengthFor)) {
      var idContenido   = jsonProy.misContenidos[i].idContenido;
      var idDescarga    = jsonProy.misContenidos[i].idDescarga;
      var nombre        = jsonProy.misContenidos[i].nombreContenido;
      var fechaDescarga = jsonProy.misContenidos[i].fechaDescarga;
      var calificacion  = jsonProy.contentidosACalificar[i].calificacion;      
      var foto          = '/SERVER-MODULE-PRESENTATION/Images?' + jsonProy.misContenidos[i].foto;
      var tipoContenido = jsonProy.misContenidos[i].tipoContenido;
          tipoContenido = getStringTipoContenido(tipoContenido);

      html += "<tr><td><img class='thumbnail' src='" + foto + "' style='width: 50px;height: 50px;'></td>"
           + "<td><a href='" + confProy.URL_CONTENIDO + idContenido + "'>" + nombre + "</a></td>"
           + "<td>" + tipoContenido + "</td>"
           + "<td><div class='row'><div class='col-xs-5'>" + calificacion + "/5</div>"
           + "<button title='Reclamo' class='btn btn-xs' id='generarReclamo_" + idDescarga + "_"+ idContenido +"' onclick='generarNuevoReclamo(this)'><i class='glyphicon glyphicon-question-sign'></i></button>"
           + "</div></td></tr>";
    };
  };
  var tbody = document.getElementById('tbody_contenidos');
    tbody.innerHTML = html;




  var html = "";

  html =+ "<div class='col-sm-5 col-md-3'><div class='thumbnail'>"
        + "<img src='" + rutaImagen + "' class='img-thumbnail miniatura img-responsive'>"
        + "<div class='caption'><h2>" + nombreContenido + "</h2>"
        + "<p>" + descripcionContenido + "</p>"
        + "<p class='precio'><a href='" + confProy.URL_CONTENIDO + idContenido + "' class='btn btn-primary' role='button'>Ver</a>"
        + "<a href='" + rutaDescarga + "' class='btn btn-default' role='button'>Comprar</a>"  
        + "UYU " + precio + "</p></div></div></div>";

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