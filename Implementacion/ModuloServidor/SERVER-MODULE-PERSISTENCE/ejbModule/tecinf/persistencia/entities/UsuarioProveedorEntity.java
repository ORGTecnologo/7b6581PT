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
	@JoinColumn(nullable=false)
	private UsuarioEntity usuario;
	
	@Column(name="codigo_qr",nullable=false)
	private String codigoQr;

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
	
	
	
}
