/**
 * Archivo de arranque de la aplicacion
 */

$(document).ready(function(){
    $('.carousel').carousel({
        interval: 3000
    });
    
    rol = new Rol(1,"Usuario");
   // modificarUsuarioX("canario","mouse","contrasenia","apellido",rol);
    obtenerUsuariosServ();
});