package tecinf.negocio.dtos;

import java.io.Serializable;

public class CambiarContraseniaDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String contraseniaAnterior;
	private String contraseniaNueva;
	private String confirmacionContraseniaNueva;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseniaAnterior() {
		return contraseniaAnterior;
	}
	public void setContraseniaAnterior(String contraseniaAnterior) {
		this.contraseniaAnterior = contraseniaAnterior;
	}
	public String getContraseniaNueva() {
		return contraseniaNueva;
	}
	public void setContraseniaNueva(String contraseniaNueva) {
		this.contraseniaNueva = contraseniaNueva;
	}
	public String getConfirmacionContraseniaNueva() {
		return confirmacionContraseniaNueva;
	}
	public void setConfirmacionContraseniaNueva(String confirmacionContraseniaNueva) {
		this.confirmacionContraseniaNueva = confirmacionContraseniaNueva;
	}
}
