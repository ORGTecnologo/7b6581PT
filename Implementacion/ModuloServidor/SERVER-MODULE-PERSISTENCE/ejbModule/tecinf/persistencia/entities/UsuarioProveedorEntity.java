package tecinf.persistencia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="usuarios_proveedor")
public class UsuarioProveedorEntity {
	
	@Id
	@Column(name="id")
	private String id;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private UsuarioEntity usuario;
	
	@Column(name="codigo_qr",nullable=false)
	private String codigoQr;
	
}
