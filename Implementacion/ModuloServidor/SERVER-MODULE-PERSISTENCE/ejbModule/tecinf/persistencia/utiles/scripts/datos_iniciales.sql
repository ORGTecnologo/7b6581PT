-- ESTE SCRIPT CONTIENE TODOS LOS DATOS NECESARIOS PARA LA CORRECTA EJECUCION DE LA APLICACION.
-- 29/09/2013

-- Definicion del usuario administrador por defecto
-- Usuario: admin
-- Contraseña: pt2013


-- Parametros y valores para el sistema
insert into parametros (nombre_parametro, valor_parametro) values ('rutaBaseMultimedia' , '/BaseDatosRecursos');
insert into parametros (nombre_parametro, valor_parametro) values ('directoriosUsuariosProveedores' , 'contenidos_libro;contenido_video;contenido;contenido_musica;contenido_sofware');

-- Tipos de registro definidos para el sistema
Insert into tipos_registro(id , descripcion) values (1, 'WEB');
Insert into tipos_registro(id , descripcion) values (2, 'FACEBOOK');
Insert into tipos_registro(id , descripcion) values (3, 'TWITTER');

-- Estados definidos para los usuarios del sistema
Insert into estados_usuario (id , descripcion) values ('H', 'Habilitado');
Insert into estados_usuario (id , descripcion) values ('D', 'Deshabilitado');
Insert into estados_usuario (id , descripcion) values ('A', 'Aprobado');
Insert into estados_usuario (id , descripcion) values ('R', 'Rechazado');

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
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo)
values('pelo8888','fontes','cd1b67cecf6581172a9b39e5c1155824','pelo8888@gmail.com','1980-06-16','alejandro','095632145','H','Masculino');
insert into usuarios_cliente (id,ruta_imagen_perfil,id_tipo_registro,fk_usuario) 
values('pelo8888','ruta',1,'pelo8888');

--ANDRES
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo)
values('aaldao','aldao','fb10b586a378f8abc224f8390346be6d','aaldao@gmail.com','1980-06-16','andres','095874587','H','Masculino');
insert into usuarios_cliente (id,ruta_imagen_perfil,id_tipo_registro,fk_usuario) 
values('aaldao','ruta',1,'aaldao');

--MAURICIO
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo)
values('mrodriguez','rodriguez','08ed5c4b5499407be0a438654984da36','mau.rod.81090@gmail.com','1980-06-16','mauricio','099321458','H','Masculino');
insert into usuarios_cliente (id,ruta_imagen_perfil,id_tipo_registro,fk_usuario) 
values('mrodriguez','ruta',1,'mrodriguez');

-- CATEGORIAS DEFINIDAS PARA EL SISTEMA
insert into categorias_contenido(id,nombre,descripcion) values (1,'Deportes','descripcion');
insert into categorias_contenido(id,nombre,descripcion) values (2,'Educación','descripcion');
insert into categorias_contenido(id,nombre,descripcion) values (3,'Finanzas','descripcion');
insert into categorias_contenido(id,nombre,descripcion) values (4,'Alimentación','descripcion');
insert into categorias_contenido(id,nombre,descripcion) values (5,'Noticias','descripcion');
insert into categorias_contenido(id,nombre,descripcion) values (6,'Viajes','descripcion');
insert into categorias_contenido(id,nombre,escripcion) values (7,'Salud y bienestar','descripcion');

-- SUBCATEGORIAS DEFINIDAS PARA EL SISTEMA
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Fútbol',1,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Basketball',1,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Rugby',1,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Natación',1,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Running',1,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Matemáticas',2,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Historia',2,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Filosofía',2,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Química',2,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Dietas',4,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Consejos',4,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Nacional',6,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Internacional',6,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Centros de salud',7,'descripcion');
insert into sub_categorias_contenido (id,nombre,id_categoria,descripcion) values (nextval('subcatcontenido_seq'),'Consejos y tips',7,'descripcion');

-- ESTADOS PARA LAS VERSIONES DE LOS CONTENIDOS
insert into estado_version_contenido(id, descripcion) values (1 , 'Aprobada');
insert into estado_version_contenido(id, descripcion) values (2 , 'Rechazada');
