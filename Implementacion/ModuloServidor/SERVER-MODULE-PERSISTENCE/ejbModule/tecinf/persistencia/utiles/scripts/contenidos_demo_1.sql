

-- TEMA MUSICAL
insert into contenidos(id,descripcion,nombre,ruta_archivo_contenido,tamanio_contenido,tipo_contenido,version)
values(1,'Strange Dejavu - Dream Theater','Strange Dejavu','isp5aoZkgFP/pchdUJTnt28UeG4x51B+NaUGSv7c0qBagvN98WPJoF0BgCycSVgHsgl0WOZxXiQwlyfGod44t6E/O2rdnPZikPjNkor09py8jYB9q9vimmFHRwDJcK5WO19HgC3pigPix63FSco/DLlDonjiivYG',4000,'tipo_contenido_tema','1.0');
insert into contenido_tema_musical(album_tema,artista_tema,duracion_tema,id,fk_contenido) 
values ('Metropolis Pt. 2: Scenes from a Memory', 'Dream Theater', '08:00',1,1);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (1,'Z6hmuMfS3EY9PcFz0lbIoy5baWl7JwQuFRwpR+FRljeIQVuL8/lc2H6GDXRcLwZ/xQXqPwTkSjLugocEIkT4ncLXuLmGFQPT',1);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (2,'kf5RF9Jhp8VbVHJysDFbfOaaL4O5ksdTucwrqtYT3ckEMFsZop7in4plgXAMb/yIoHmpHGCFsH+WwliwX0Z/Eizbtpklh5ej',1);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (3,'5JOKdpHxytWLgHU7/oHS4l3+2+vKHU0OS5KG1i+RIoJtCeeEtnOBtAnUZ/hZkVGMOPEeMskl+p0Ma35OpTxe66V2Jb5Ul8O2',1);

-- LIBRO
insert into contenidos(id,descripcion,nombre,ruta_archivo_contenido,tamanio_contenido,tipo_contenido,version)
values(2,'Breve pdf con los conceptos básicos del lenguaje de programación Java','Introducción a Java','GD/ORLlVgAHBMCXdQQ9z0I3vrpyVCoxDsjJzxvitXhCF3gSu+gK/KLinORRsUwUmXJDtkvxnvnOAUneNs9QZlwdYomI+2NLUgS41ocD4Jdo=',4000,'tipo_contenido_libro','1.0');
insert into contenido_libro(autor , fecha_publicacion , id, fk_contenido)
values ('FRANCISCO ARAGÓN MESA' , '2009-10-8' , 2, 2);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (4,'1P56dHCRlKHjfzd10IpsyDEVh99ksHM0yC2VB1Fsoz5469JhsQvWiPRtP742XZVzNNK2fWTNSJgY74A7842prq2YQ0z7zb2R',2);

-- SOFTWARE
insert into contenidos(id,descripcion,nombre,ruta_archivo_contenido,tamanio_contenido,tipo_contenido,version)
values(3,'Java Development Kit 7 update 3','JDK 7u3','DFfevV+gZSCgcphKNs0FFp938Jm7PtfLYFztAHRvmav5PV+axM9FEcRA545FZyliW2p82yfEP3ZQ51qKgCVs13LMyQOnJpRJCmPDUJyDktCeC0AP4cEOYg==',4000,'tipo_contenido_software','1.0');
insert into contenido_software (es_trial, id, fk_contenido) values (false , 3, 3);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (5,'9sf+2Wvj1VdgzIC8yUBpIGHmf0O03kl9ce9y5ZTWZtbqXuvReKscg0IaZGHBe96hQBeE91/Nkb19fiEVq17BNbw3PwI4r82mOX6CrugKUXU=',3);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (6,'nQrbbtiumRt6KpkVOMLLrP6OUCyJA7rBljeACKtt21i3gDRW/CHCYVErulCzRLKgSYnKwo56G9x/l5GRvReSPXKygZfQ1l2fXMpQk6Nc7ck=',3);

-- VIDEO
insert into contenidos(id,descripcion,nombre,ruta_archivo_contenido,tamanio_contenido,tipo_contenido,version)
values(4,'Video - LifeLine - Acoustico - Papa Roach','Lifeline - Papa Roach','lyJBaiGI99SzAw6nDILcLRO6AJ1F7o9oX2e+K3joNak9StGYzDn1bgkpLPYpuU8ytqps66Ha10wWiWSN7J84Zn/xXp6ZGUseSjepBCgs31v/7TP10a4oNRIGfhkJD2nCv1RUjwS8wdo=',4000,'tipo_contenido_video','1.0');

insert into contenido_video (calidad_video, duracion_video, formato_video, id, fk_contenido) 
values ('buena' , '03:32', 'flv', 4,4);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (7,'+Eq33iruPlOX4V0WNKhvKaYbURX9lLqVhJaFQ09RLVowp2i1OE61tEgHtk/mL5DGskvMPvLxA7ag/abYyb9DpjvgLOklt6A6',4);
insert into contenido_foto(id,url_foto,fk_contenido) 
values (8,'vIA1BgJz/LofYzQK2o4jJSoEu6kPodftYuyn+rtFy98TwVboZtHncTcgn4usjGwDrNa8Tb2qncrf+cpvYjMnURler8Q/uC5R',4);



