package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="usuarios")

@NamedQueries( {
	
	@NamedQuery(name = "UsuarioEntity.findAll", 
			query = "SELECT e FROM UsuarioEntity e ORDER BY e.usuario") ,
					
	@NamedQuery(name = "UsuarioEntity.findById", 
		query = "SELECT e FROM UsuarioEntity e WHERE e.usuario = :id") ,
	
	@NamedQuery(name = "UsuarioEntity.findByMail", 
		query = "SELECT e FROM UsuarioEntity e WHERE e.correoElectronico = :mail") ,
})


public class UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usuario", length=30)
	private String usuario;
	
	@Column(name="contrasenia",length=40, nullable=false)
	private String contrasenia;
	
	@Column(name="nombres",length=100, nullable=false)
	private String nombres;

	@Column(name="apellidos",length=100, nullable=false)
	private String apellidos;

	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name="telefono_movil")
	private String telefonoMovil;
	
	@Column(name="correo_electronico",nullable=false,unique=true)
	private String correoElectronico;
	
	@Column(name="sexo",nullable=false)
	private String sexo;
	
	@OneToOne
	@JoinColumn(name="id_estado",nullable=false)
	private EstadoUsuarioEntity estadoUsuario;
	
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public EstadoUsuarioEntity getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(EstadoUsuarioEntity estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}	
			
}
