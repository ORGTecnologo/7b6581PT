
$(document).ready(function(){

    cargarDatosDeUsuario();

    $( "#id_fechaNac_perfil").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd-mm-yy",
        maxDate: 0
    });
});

function cargarDatosDeUsuario(){

    bloquearPantalla();
    var ArrayQuery = window.location.search.split('?')[1].split('&');

    for (var i = 0; i < ArrayQuery.length; i++) {
        var pareja = ArrayQuery[i].split('=');
        switch(pareja[0]){
            case 'nick':
                varsProy.nick = pareja[1];
            break;
        }
    }   
    obtenerDatosdeUsuario(varsProy.nick);
    desbloquearPantalla();
}

function cargarDatosdeUsuario(msg){

    bloquearPantalla();

    document.getElementById('id_img_perfil');

    document.getElementById('id_nick_perfil').innerText = msg.usuario;

    document.getElementById('id_nombre_perfil').value = msg.nombres;
    document.getElementById('id_nombre_perfil').placeholder = msg.nombres;

    document.getElementById('id_apellido_perfil').value = msg.apellidos;
    document.getElementById('id_apellido_perfil').placeholder = msg.apellidos;

    document.getElementById('id_sexo_perfil');
    
    var date  = new Date(msg.fechaNacimientoTimeStamp);
    var anio  = date.getYear()+ 1900;
    var fecha = date.getDate() + "/" + date.getMonth() + "/" + anio;
    
    document.getElementById('id_fechaNac_perfil').value = fecha;
    document.getElementById('id_fechaNac_perfil').placeholder = fecha;
    
    document.getElementById('id_telefono_perfil').value = msg.telefonoMovil;
    document.getElementById('id_telefono_perfil').placeholder = msg.telefonoMovil;
    
    document.getElementById('id_mail_perfil').value = msg.correoElectronico;
    document.getElementById('id_mail_perfil').placeholder = msg.correoElectronico;
    
    document.getElementById('id_hibilitado_perfil');
    
    if (window.localStorage.getItem(confProy.sessionStorageRol) === 'usuario_proveedor'){
      document.getElementById('id_sitioWeb_perfil').value = msg.sitioWeb;
      document.getElementById('id_sitioWeb_perfil').placeholder = msg.sitioWeb;
    }
    else{
      document.getElementById('id_sw_title').hidden = true;
    }

    desbloquearPantalla();
      // msg.contrasenia
      // msg.contrasenia2
      // msg.sexo
      // msg.fechaNacimiento //Null?
      // msg.habilitado
}

function habilitarEdicion(){

  bloquearPantalla();

  mostrarElemento('div_datosUsuario');
  mostrarElemento('btnGuardar');
  ocultarElemento('div_contenidosUsuario');
  ocultarElemento('div_contenidosPendientes');

  document.getElementById('id_img_perfil');

  document.getElementById('id_nombre_perfil').disabled = false;
  document.getElementById('id_apellido_perfil').disabled = false;
  document.getElementById('id_sexo_perfil').disabled = false;
  document.getElementById('id_fechaNac_perfil').disabled = false;

  document.getElementById('id_telefono_perfil').disabled = false;
  document.getElementById('id_hibilitado_perfil');
  document.getElementById('id_sitioWeb_perfil').disabled = false;

  desbloquearPantalla();
}


function mostrarVerPerfil(){

  bloquearPantalla();

  mostrarElemento('div_datosUsuario');
  ocultarElemento('btnGuardar');
  ocultarElemento('div_contenidosUsuario');
  ocultarElemento('div_contenidosPendientes');

  document.getElementById('id_nombre_perfil').disabled = true;
  document.getElementById('id_apellido_perfil').disabled = true;
  document.getElementById('id_sexo_perfil').disabled = true;
  document.getElementById('id_fechaNac_perfil').disabled = true;

  document.getElementById('id_telefono_perfil').disabled = true;
  document.getElementById('id_hibilitado_perfil');
  document.getElementById('id_sitioWeb_perfil').disabled = true;

  desbloquearPantalla();
}

function editarPerfil(){

  bloquearPantalla();

  var nombre = document.getElementById('id_nombre_perfil').value;
  var apellido = document.getElementById('id_apellido_perfil').value;
  var sexo = document.getElementById('id_sexo_perfil').value;
  var fechaNacimiento = document.getElementById('id_fechaNac_perfil').value;

  var tel = document.getElementById('id_telefono_perfil').value;
  var sitioWeb = document.getElementById('id_sitioWeb_perfil').value;

  guardarCambiosPerfil(nombre,apellido,sexo,fechaNacimiento,tel,sitioWeb);

  desbloquearPantalla();
}

function verContenidosUsuario(){
  
   bloquearPantalla();

   ocultarElemento('div_datosUsuario');
   ocultarElemento('btnGuardar');
   ocultarElemento('div_contenidosPendientes');
   mostrarElemento('div_contenidosUsuario');

   desbloquearPantalla();
}

function verCalificacionesPendientes(){

   bloquearPantalla();

   ocultarElemento('div_datosUsuario');
   ocultarElemento('btnGuardar');
   ocultarElemento('div_contenidosUsuario');
   mostrarElemento('div_contenidosPendientes');

   desbloquearPantalla();
}

function cargarMisContenidos(pag){
    pag || (pag = 0);

  var lengthFor = 10;
  var html = "";
  if (jsonProy.misContenidos.length < 10)
    lengthFor = jsonProy.misContenidos.length;

  for (var i = (pag*10 + 0); i < (pag*10 + 10); i++) {

    if (!(i >= lengthFor)) {
      var id     = jsonProy.misContenidos[i].id;
      var nombre = jsonProy.misContenidos[i].nombreContenido;
      var desc   = jsonProy.misContenidos[i].descripcionContenido;
      var foto   = '/SERVER-MODULE-PRESENTATION/Images?' + jsonProy.misContenidos[i].listaFotos[0];
      var precio = jsonProy.misContenidos[i].precio;


          html += "<tr><td><img src='" + foto + "' alt='" + foto + "' class='img-responsive' style='width:50px;heigth:50px'></td>"
            + "<td><a href='views/content.html?id=" + id + "'>" + nombre + "</a></td>"
            + "<td>" + desc + "</td>"
            + "<td>" + precio + "</td>"
            + "<td>" + 100 + "/5</td>"
                + "</tr>";
    };
  };
  var tbody = document.getElementById('cuerpoBusqueda');
    tbody.innerHTML = html;
}
