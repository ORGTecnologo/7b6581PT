
$(document).ready(function(){

    cargarDatosDeUsuario();

});

function cargarDatosDeUsuario(){

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
}

function cargarDatosdeUsuario(msg){
    //foto = 
    document.getElementById('id_img_perfil');

    document.getElementById('id_nick_perfil').innerText = msg.usuario;

    document.getElementById('id_nombre_perfil').value = msg.nombres;
    document.getElementById('id_nombre_perfil').placeholder = msg.nombres;

    document.getElementById('id_apellido_perfil').value = msg.apellidos;
    document.getElementById('id_apellido_perfil').placeholder = msg.apellidos;

    document.getElementById('id_sexo_perfil');
    
    var date = new Date(msg.fechaNacimientoTimeStamp);
    var fecha = date.getDate() + "/" + date.getMonth() + "/" + date.getYear();
    
    document.getElementById('id_fechaNac_perfil').value = fecha;
    document.getElementById('id_fechaNac_perfil').placeholder = fecha;
    
    document.getElementById('id_telefono_perfil').value = msg.telefonoMovil;
    document.getElementById('id_telefono_perfil').placeholder = msg.telefonoMovil;
    
    document.getElementById('id_mail_perfil').value = msg.correoElectronico;
    document.getElementById('id_mail_perfil').placeholder = msg.correoElectronico;
    
    document.getElementById('id_hibilitado_perfil');
    
    document.getElementById('id_sitioWeb_perfil').value = msg.sitioWeb;
    document.getElementById('id_sitioWeb_perfil').placeholder = msg.sitioWeb;
    
      // msg.contrasenia
      // msg.contrasenia2
      // msg.sexo
      // msg.fechaNacimiento //Null?
      // msg.habilitado
}