package tecinf.negocio.dtos;

public class UsuarioDataType {
	
	private String usuario;
	private String contrasenia;
	private String nombres;
	private String apellidos;
	private RolDataType rol;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public RolDataType getRol() {
		return rol;
	}
	public void setRol(RolDataType rol) {
		this.rol = rol;
	}
	
}
