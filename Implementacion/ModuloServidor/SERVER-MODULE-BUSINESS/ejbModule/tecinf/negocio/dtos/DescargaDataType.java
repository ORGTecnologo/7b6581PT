package tecinf.negocio.dtos;

import java.io.Serializable;

public class DescargaDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer idDescarga;
	private Integer idContenido;
	private String nombreContenido;
	private String fechaDescarga;
	private String tipoContenido;
	private String foto;
	private Integer calificacion;
	
	
	public Integer getIdDescarga() {
		return idDescarga;
	}
	public void setIdDescarga(Integer idDescarga) {
		this.idDescarga = idDescarga;
	}
	public Integer getIdContenido() {
		return idContenido;
	}
	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}
	public String getNombreContenido() {
		return nombreContenido;
	}
	public void setNombreContenido(String nombreContenido) {
		this.nombreContenido = nombreContenido;
	}
	public String getFechaDescarga() {
		return fechaDescarga;
	}
	public void setFechaDescarga(String fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}
	public String getTipoContenido() {
		return tipoContenido;
	}
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Integer getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	
}
