

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
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo,tipo_usuario)
values('pelo8888','fontes','cd1b67cecf6581172a9b39e5c1155824','pelo8888@gmail.com','1980-06-16','alejandro','095632145','H','Masculino','usuario_cliente');
insert into usuarios_cliente (usuario,ruta_imagen_perfil,id_tipo_registro) 
values('pelo8888','ruta',1);

--ANDRES
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo,tipo_usuario)
values('aaldao','aldao','fb10b586a378f8abc224f8390346be6d','aaldao@gmail.com','1980-06-16','andres','095874587','H','Masculino','usuario_cliente');
insert into usuarios_cliente (usuario,ruta_imagen_perfil,id_tipo_registro) 
values('aaldao','ruta',1);

--MAURICIO
insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo,tipo_usuario)
values('mrodriguez','rodriguez','08ed5c4b5499407be0a438654984da36','mau.rod.81090@gmail.com','1980-06-16','mauricio','099321458','H','Masculino','usuario_cliente');
insert into usuarios_cliente (usuario,ruta_imagen_perfil,id_tipo_registro) 
values('mrodriguez','ruta',1);

-- CATEGORIAS DEFINIDAS PARA EL SISTEMA
insert into categorias_contenido(id,nombre) values (1,'Deportes');
insert into categorias_contenido(id,nombre) values (2,'Educación');
insert into categorias_contenido(id,nombre) values (3,'Finanzas');
insert into categorias_contenido(id,nombre) values (4,'Alimentación');
insert into categorias_contenido(id,nombre) values (5,'Noticias');
insert into categorias_contenido(id,nombre) values (6,'Viajes');
insert into categorias_contenido(id,nombre) values (7,'Salud y bienestar');

-- SUBCATEGORIAS DEFINIDAS PARA EL SISTEMA
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Fútbol',1);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Basketball',1);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Rugby',1);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Natación',1);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Running',1);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Matemáticas',2);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Historia',2);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Filosofía',2);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Química',2);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Dietas',4);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Consejos',4);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Nacional',6);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Internacional',6);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Centros de salud',7);
insert into sub_categorias_contenido (id,nombre,id_categoria) values (nextval('subcatcontenido_seq'),'Consejos y tips',7);

-- Session timeout
insert into parametros(nombre_parametro, valor_parametro) values('timeOutSesion' , '30');

-- ACTUALIZACIONES

update parametros set valor_parametro = 'contenido_libro;contenido_video;contenido_musica;contenido_sofware' where nombre_parametro = 'directoriosUsuariosProveedores';

ALTER TABLE usuarios_proveedor ALTER COLUMN codigo_qr DROP NOT NULL;

alter table contenido_tema_musical ALTER COLUMN album_tema TYPE varchar(100);

alter table contenidos ALTER COLUMN nombre TYPE varchar(100);

alter table contenidos ALTER COLUMN descripcion TYPE varchar(1000);

alter table contenidos ALTER COLUMN ruta_archivo_contenido TYPE varchar(1000);

update categorias_contenido set habilitado=true;

update sub_categorias_contenido set habilitado=true;

insert into parametros (nombre_parametro, valor_parametro) values('lapsoEjecucionChequeoSesiones' , '20');

insert into parametros (nombre_parametro, valor_parametro) values('lapsoEjecucionActualizacionInfoContenidos' , '30');

update parametros set valor_parametro='contenido_libro;contenido_video;contenido_musica;contenido_sofware;tmp' where nombre_parametro='directoriosUsuariosProveedores';

insert into parametros (nombre_parametro, valor_parametro) values('horasValoracionNoHabilitada' , '72');

insert into parametros (nombre_parametro, valor_parametro) values('lapsoEjecucionActualizacionInfoDescargas' , '30');

alter table usuarios ALTER COLUMN tipo_usuario TYPE varchar(50); 

insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo,tipo_usuario)
values('admin','admin','21232f297a57a5a743894a0e4a801fc3','admin@admin.com','1980-06-16','juan','095632145','H','Masculino','usuario_administrador');
insert into usuarios_administrador (usuario) values('admin');

insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo,tipo_usuario)
values('proveedor','proveedor','ed53a12cecc92e4014e5f0438e17185a','proveedor@proveedor.com','1980-06-16','proveedor','095632145','H','Masculino','usuario_proveedor');
insert into usuarios_proveedor (codigo_qr , sitio_web, usuario) values('','www.proveedor.com' , 'proveedor');

insert into usuarios (usuario,apellidos,contrasenia,correo_electronico,fecha_nacimiento,nombres,telefono_movil,id_estado,sexo,tipo_usuario)
values('cliente','cliente','4983a0ab83ed86e0e7213c8783940193','cliente@cliente.com','1980-06-16','cliente','095632145','H','Masculino','usuario_cliente');
insert into usuarios_cliente(ruta_imagen_perfil, usuario, id_tipo_registro) values ('ruta','cliente',1);

update parametros set valor_parametro = 'contenido_libro;contenido_video;contenido_musica;contenido_software;tmp' where nombre_parametro = 'directoriosUsuariosProveedores';

update usuarios set habilitado = true ;

insert into categoria_reclamo (id,descripcion) values (1, 'Categoria generica');

insert into parametros(nombre_parametro,valor_parametro) values ('cantidadTopContenidos' , '4');


