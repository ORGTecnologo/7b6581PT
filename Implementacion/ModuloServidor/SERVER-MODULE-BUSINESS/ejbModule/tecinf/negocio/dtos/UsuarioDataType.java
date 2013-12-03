package tecinf.negocio.dtos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class UsuarioDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected String usuario;
	protected String contrasenia;
	protected String contrasenia2;
	protected String nombres;
	protected String apellidos;
	protected String sexo;
	protected String fechaNacimiento;
	protected Long fechaNacimientoTimeStamp;
	protected String telefonoMovil;
	protected String correoElectronico;
	protected Boolean habilitado;
	
	
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
	public String getContrasenia2() {
		return contrasenia2;
	}
	public void setContrasenia2(String contrasenia2) {
		this.contrasenia2 = contrasenia2;
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Long getFechaNacimientoTimeStamp() {
		return fechaNacimientoTimeStamp;
	}
	public void setFechaNacimientoTimeStamp(Long fechaNacimientoTimeStamp) {
		this.fechaNacimientoTimeStamp = fechaNacimientoTimeStamp;
	}
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
}
