-- ESTE SCRIPT CONTIENE TODOS LOS DATOS NECESARIOS PARA LA CORRECTA EJECUCION DE LA APLICACION.
-- 29/09/2013

-- Definicion del usuario administrador por defecto
-- Usuario: admin
-- Contraseña: pt2013


-- Parametros y valores para el sistema
insert into parametros (nombre_parametro, valor_parametro) values ('rutaBaseMultimedia' , '/BaseDatosRecursos');

-- Tipos de registro definidos para el sistema
Insert into tipos_registro(id , descripcion) values (1, 'WEB');
Insert into tipos_registro(id , descripcion) values (2, 'FACEBOOK');
Insert into tipos_registro(id , descripcion) values (3, 'TWITTER');

-- Estados definidos para los usuarios del sistema
Insert into estados_usuario (id , descripcion) values ('H', 'Habilitado');
Insert into estados_usuario (id , descripcion) values ('D', 'Deshabilitado');
Insert into estados_usuario (id , descripcion) values ('A', 'Aprobado');
Insert into estados_usuario (id , descripcion) values ('R', 'Rechazado');

-- Sexos definidos para los usuarios
insert into sexo_usuario(id,descripcion) values (1,'Masculino');
insert into sexo_usuario(id,descripcion) values (2,'Femenino');
insert into sexo_usuario(id,descripcion) values (3,'NoSabeNoContesta');

-- Operaciones definidas para la auditoria
Insert into auditoria_operacion (id , descripcion) values (1 , 'Login');
Insert into auditoria_operacion (id , descripcion) values (2 , 'Logout');
Insert into auditoria_operacion (id , descripcion) values (3 , 'Alta');
Insert into auditoria_operacion (id , descripcion) values (4 , 'Baja');
Insert into auditoria_operacion (id , descripcion) values (5 , 'Modificación');
Insert into auditoria_operacion (id , descripcion) values (6 , 'Publicación');

-- Objetos definidos para la auditoria
Insert into auditoria_objeto (id , descripcion) values (1 , 'Usuario');
Insert into auditoria_objeto (id , descripcion) values (2 , 'Contenido');

--- USUARIOS DE PRUEBA

--PELO
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,id_sexo)
values('pelo8888','fontes','cd1b67cecf6581172a9b39e5c1155824','pelo8888@gmail.com','1980-06-16','alejandro','095632145','H',1);
insert into usuarios_cliente (id,ruta_imagen_perfil,id_tipo_registro,fk_usuario) 
values('pelo8888','ruta',1,'pelo8888');

--ANDRES
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,id_sexo)
values('aaldao','aldao','fb10b586a378f8abc224f8390346be6d','aaldao@gmail.com','1980-06-16','andres','095874587','H',1);
insert into usuarios_cliente (id,ruta_imagen_perfil,id_tipo_registro,fk_usuario) 
values('aaldao','ruta',1,'aaldao');

--MAURICIO
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,id_sexo)
values('mrodriguez','rodriguez','08ed5c4b5499407be0a438654984da36','mau.rod.81090@gmail.com','1980-06-16','mauricio','099321458','H',1);
insert into usuarios_cliente (id,ruta_imagen_perfil,id_tipo_registro,fk_usuario) 
values('mrodriguez','ruta',1,'mrodriguez');