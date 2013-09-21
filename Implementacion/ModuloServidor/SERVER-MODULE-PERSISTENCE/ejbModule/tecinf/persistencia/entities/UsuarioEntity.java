package tecinf.persistencia.entities;

import java.io.Serializable;

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
})


public class UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usuario", length=30)
	private String usuario;
	
	@Column(name="contrasenia",length=40)
	private String contrasenia;
	
	@Column(name="nombres",length=100)
	private String nombres;

	@Column(name="apellidos",length=100)
	private String apellidos;
	
	@OneToOne()
	@JoinColumn(name="id_rol", nullable=false)
	private RolEntity rol;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(RolEntity rol) {
		this.rol = rol;
	}
			
}
