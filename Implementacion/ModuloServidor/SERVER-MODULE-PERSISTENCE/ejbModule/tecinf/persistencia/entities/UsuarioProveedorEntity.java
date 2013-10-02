package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="usuarios_proveedor")
public class UsuarioProveedorEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private String id;
	
	@OneToOne
	@JoinColumn(unique=true, name="fk_usuario", nullable=false)
	private UsuarioEntity usuario;
	
	@Column(name="codigo_qr",nullable=false)
	private String codigoQr;
	
	@Column(name="sitio_web", length=100, nullable=false)
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

	public String getCodigoQr() {
		return codigoQr;
	}

	public void setCodigoQr(String codigoQr) {
		this.codigoQr = codigoQr;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	
}
