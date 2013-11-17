package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity; 
import javax.persistence.Column; 
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_libro")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ContenidoLibroEntity extends ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "fecha_publicacion")
	private Date fecha_publicacion;
	
	@Column(name = "cantidad_paginas", nullable=true)
	private Integer cantidadPaginas;
	
	@Column(name = "autor", length = 50)
	private String autor;

	public ContenidoLibroEntity(){
		super();
	}
	
	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidadPaginas(Integer cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}
	
}
