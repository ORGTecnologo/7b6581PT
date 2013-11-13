package tecinf.negocio.dtos;

import java.io.Serializable;

public class ComentarioDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idContenido;
	private String usuario;
	private String comentario;
	
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
	
}
