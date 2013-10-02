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
@Table(name="usuarios_cliente")

@NamedQueries( {
	
	@NamedQuery(name = "UsuarioClienteEntity.findAll", 
			query = "SELECT e FROM UsuarioClienteEntity e ORDER BY e.id") ,
					
	@NamedQuery(name = "UsuarioClienteEntity.findById", 
		query = "SELECT e FROM UsuarioClienteEntity e WHERE e.usuario.usuario = :id") ,
	
	@NamedQuery(name = "UsuarioClienteEntity.findByUserAndPassword", 
		query = "SELECT e FROM UsuarioClienteEntity e WHERE e.usuario.usuario = :id and e.usuario.contrasenia = :pwd") ,
})
public class UsuarioClienteEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private String id;
	
	@OneToOne
	@JoinColumn(unique=true, name="fk_usuario", nullable=false)
	private UsuarioEntity usuario;
	
	@OneToOne
	@JoinColumn(name="id_tipo_registro",nullable=false)
	private TipoRegistroEntity tipoRegistro;
	
	@Column(name="ruta_imagen_perfil", length=255)
	private String rutaImagenPerfil;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public TipoRegistroEntity getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistroEntity tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getRutaImagenPerfil() {
		return rutaImagenPerfil;
	}

	public void setRutaImagenPerfil(String rutaImagenPerfil) {
		this.rutaImagenPerfil = rutaImagenPerfil;
	}
	
}
