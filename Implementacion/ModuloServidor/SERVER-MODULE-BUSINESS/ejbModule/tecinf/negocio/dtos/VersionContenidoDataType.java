package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.Date;

import tecinf.persistencia.entities.ContenidoEntity;

public class VersionContenidoDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private ContenidoEntity contenido;
	private String estadoVersion;
	private String descripcion;
	private Date fechaSubida;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ContenidoEntity getContenido() {
		return contenido;
	}
	public void setContenido(ContenidoEntity contenido) {
		this.contenido = contenido;
	}
	public String getEstadoVersion() {
		return estadoVersion;
	}
	public void setEstadoVersion(String estadoVersion) {
		this.estadoVersion = estadoVersion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaSubida() {
		return fechaSubida;
	}
	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
	
}
