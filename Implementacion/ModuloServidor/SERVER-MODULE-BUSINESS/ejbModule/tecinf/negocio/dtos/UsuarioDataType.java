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
	protected Date fechaNacimientoDate;
	protected String telefonoMovil;
	protected String correoElectronico;
	
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Date getFechaNacimientoDate() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return (this.fechaNacimiento == null ? null : sdf.parse(this.fechaNacimiento) ); 
	}
	public void setFechaNacimientoDate(Date fechaNacimientoDate) {
		this.fechaNacimientoDate = fechaNacimientoDate;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.setFechaNacimiento(fechaNacimientoDate == null ? "null" : sdf.format(fechaNacimientoDate));
	}
	public String getContrasenia2() {
		return contrasenia2;
	}
	public void setContrasenia2(String contrasenia2) {
		this.contrasenia2 = contrasenia2;
	}	
	
}
