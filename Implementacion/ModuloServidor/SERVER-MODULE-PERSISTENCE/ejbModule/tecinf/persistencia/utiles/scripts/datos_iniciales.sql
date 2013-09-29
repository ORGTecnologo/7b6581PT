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