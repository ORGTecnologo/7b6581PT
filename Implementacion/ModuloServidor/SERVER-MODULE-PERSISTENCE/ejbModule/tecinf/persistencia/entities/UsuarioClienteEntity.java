package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="usuarios_cliente")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

@NamedQueries( {
	
	@NamedQuery(name = "UsuarioClienteEntity.findAll", 
			query = "SELECT e FROM UsuarioClienteEntity e ORDER BY e.usuario") ,
					
	@NamedQuery(name = "UsuarioClienteEntity.findById", 
		query = "SELECT e FROM UsuarioClienteEntity e WHERE e.usuario = :id") ,
	
	@NamedQuery(name = "UsuarioClienteEntity.findByUserAndPassword", 
		query = "SELECT e FROM UsuarioClienteEntity e WHERE e.usuario = :id and e.contrasenia = :pwd") ,
})

public class UsuarioClienteEntity extends UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="id_tipo_registro",nullable=false)
	private TipoRegistroEntity tipoRegistro;
	
	@Column(name="ruta_imagen_perfil", length=255)
	private String rutaImagenPerfil;

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
