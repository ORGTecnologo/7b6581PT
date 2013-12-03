package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="auditoria")

@NamedQueries( {
	
	@NamedQuery(name = "AuditoriaEntity.findByOperacion", 
		query = "SELECT e FROM AuditoriaEntity e WHERE e.operacion.id = :idOperacion ORDER BY e.fechaOperacion DESC"),
})

public class AuditoriaEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"auditoria_secuencia") 
	@SequenceGenerator(name = "auditoria_secuencia", sequenceName = "auditoria_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="fecha_operacion")
	private Date fechaOperacion;
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private UsuarioEntity usuario;
	
	@OneToOne
	@JoinColumn(name="id_operacion",nullable=false)
	private AuditoriaOperacionEntity operacion;
	
	@OneToOne
	@JoinColumn(name="id_objeto",nullable=false)
	private AuditoriaObjetoEntity objeto;
	
	@Column(name="id_objeto_sistema")
	private String idObjetoSistema;

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

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public String getIdObjetoSistema() {
		return idObjetoSistema;
	}

	public void setIdObjetoSistema(String idObjetoSistema) {
		this.idObjetoSistema = idObjetoSistema;
	}
	
	
}
