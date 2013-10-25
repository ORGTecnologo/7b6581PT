package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name="usuarios_proveedor")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class UsuarioProveedorEntity extends UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo_qr",nullable=false)
	private String codigoQr;
	
	@Column(name="sitio_web", length=100, nullable=false)
	private String sitioWeb;

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
