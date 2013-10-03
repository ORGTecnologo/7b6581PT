package tecinf.negocio.dtos;

public abstract class MensajeCertificado {
	
	protected String usuario_autenticacion;
	protected String contrasenia_autenticacion;
	
	public String getUsuario_autenticacion() {
		return usuario_autenticacion;
	}
	public void setUsuario_autenticacion(String usuario_autenticacion) {
		this.usuario_autenticacion = usuario_autenticacion;
	}
	public String getContrasenia_autenticacion() {
		return contrasenia_autenticacion;
	}
	public void setContrasenia_autenticacion(String contrasenia_autenticacion) {
		this.contrasenia_autenticacion = contrasenia_autenticacion;
	}
	
}
