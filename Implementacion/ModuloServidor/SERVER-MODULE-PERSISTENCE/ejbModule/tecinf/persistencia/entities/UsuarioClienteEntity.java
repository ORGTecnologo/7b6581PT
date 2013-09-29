package tecinf.persistencia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="usuarios_cliente")

@NamedQueries( {
	
})
public class UsuarioClienteEntity {
	
	@Id
	@Column(name="id")
	private String id;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private UsuarioEntity usuario;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private TipoRegistroEntity tipoRegistro;
	
	@Column(name="ruta_imagen_perfil")
	private String rutaImagenPerfil;
	
	@Column(name="sitio_web")
	private String sitioWeb;

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
