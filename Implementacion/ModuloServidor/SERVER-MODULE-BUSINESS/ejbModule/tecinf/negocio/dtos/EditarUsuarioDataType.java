package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.Date;

public class EditarUsuarioDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String sitioWeb;
	private String nombres;
	private String apellidos;
	private String sexo;
	private String fechaNacimiento;
	private Date fechaNacimientoDate;
	private String telefonoMovil;
	private String tipoUsuario;
	
	
	public String getSitioWeb() {
		return sitioWeb;
	}
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
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
	public Date getFechaNacimientoDate() {
		return fechaNacimientoDate;
	}
	public void setFechaNacimientoDate(Date fechaNacimientoDate) {
		this.fechaNacimientoDate = fechaNacimientoDate;
	}
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
