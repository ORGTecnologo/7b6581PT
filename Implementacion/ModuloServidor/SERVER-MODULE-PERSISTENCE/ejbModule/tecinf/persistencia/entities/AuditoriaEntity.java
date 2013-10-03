package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="auditoria")
public class AuditoriaEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"auditoria_secuencia") 
	@SequenceGenerator(name = "auditoria_secuencia", sequenceName = "auditoria_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="fecha_operacion")
	private Date fechaOperacion;
	
	@OneToOne
	@JoinColumn(name="id_usuario_cliente")
	private UsuarioClienteEntity usuarioCliente;
	
	@OneToOne
	@JoinColumn(name="id_usuario_proveedor")
	private UsuarioProveedorEntity usuarioProveedor;
	
	@OneToOne
	@JoinColumn(name="id_operacion",nullable=false)
	private AuditoriaOperacionEntity operacion;
	
	@OneToOne
	@JoinColumn(name="id_objeto",nullable=false)
	private AuditoriaObjetoEntity objeto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public AuditoriaOperacionEntity getOperacion() {
		return operacion;
	}

	public void setOperacion(AuditoriaOperacionEntity operacion) {
		this.operacion = operacion;
	}

	public AuditoriaObjetoEntity getObjeto() {
		return objeto;
	}

	public void setObjeto(AuditoriaObjetoEntity objeto) {
		this.objeto = objeto;
	}

	public UsuarioClienteEntity getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(UsuarioClienteEntity usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public UsuarioProveedorEntity getUsuarioProveedor() {
		return usuarioProveedor;
	}

	public void setUsuarioProveedor(UsuarioProveedorEntity usuarioProveedor) {
		this.usuarioProveedor = usuarioProveedor;
	}
	
	
}
