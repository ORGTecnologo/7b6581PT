package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.Date;

public class ReclamoDataType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idCategoria;
	private Date fechaReclamo;
	private String titulo;
	private String descripcion;
	private Integer idContenido;	
	private Integer idDescarga;
	private String autor;	
	
	public Integer getIdDescarga() {
		return idDescarga;
	}
	public void setIdDescarga(Integer idDescarga) {
		this.idDescarga = idDescarga;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getIdContenido() {
		return idContenido;
	}
	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Date getFechaReclamo() {
		return fechaReclamo;
	}
	public void setFechaReclamo(Date fechaReclamo) {
		this.fechaReclamo = fechaReclamo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}	
	
}
