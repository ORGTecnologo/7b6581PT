package tecinf.negocio.dtos;

import java.io.Serializable;

public class ComentarioDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idDescarga;
	private Integer idContenido;
	private String usuario;
	private String comentario;
	private Boolean validado;
	private String fecha;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Integer getIdContenido() {
		return idContenido;
	}
	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}
	public Boolean getValidado() {
		return validado;
	}
	public void setValidado(Boolean validado) {
		this.validado = validado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getIdDescarga() {
		return idDescarga;
	}
	public void setIdDescarga(Integer idDescarga) {
		this.idDescarga = idDescarga;
	}
	
}
