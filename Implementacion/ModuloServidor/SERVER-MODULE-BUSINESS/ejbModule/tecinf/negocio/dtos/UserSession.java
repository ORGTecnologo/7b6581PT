package tecinf.negocio.dtos;

import java.io.Serializable;

public class UserSession implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String tipoUsuario;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
