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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries( {
	
	@NamedQuery(name = "UsuarioDescargaContenidoEntity.findAllByContenido", 
		query = "SELECT e FROM UsuarioDescargaContenidoEntity e WHERE e.contenido.id = :idContenido") ,
		
	@NamedQuery(name = "UsuarioDescargaContenidoEntity.findAllByContenidoAndEstado", 
		query = "SELECT e FROM UsuarioDescargaContenidoEntity e WHERE e.contenido.id = :idContenido and e.estadoDescarga = :estado") ,
		
	@NamedQuery(name = "UsuarioDescargaContenidoEntity.findAllByEstado", 
		query = "SELECT e FROM UsuarioDescargaContenidoEntity e WHERE e.estadoDescarga = :estado") ,
	
	@NamedQuery(name = "UsuarioDescargaContenidoEntity.findAllByUsuarioAndEstado", 
		query = "SELECT e FROM UsuarioDescargaContenidoEntity e WHERE e.estadoDescarga = :estado and e.usuarioCliente.usuario = :usuario") ,	
	
	@NamedQuery(name = "UsuarioDescargaContenidoEntity.findByUsuarioAndContenido", 
		query = "SELECT e FROM UsuarioDescargaContenidoEntity e WHERE e.usuarioCliente.usuario = :usuario and e.contenido.id = :idContenido") ,
		
})

@Entity
@Table(name="usuario_descarga_contenido")
public class UsuarioDescargaContenidoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"usrdsccont_secuencia") 
	@SequenceGenerator(name = "usrdsccont_secuencia", sequenceName = "usrdsccont_seq", allocationSize = 1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="usuario", nullable=false)
	private UsuarioEntity usuarioCliente;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="contenido", nullable=false)
	private ContenidoEntity contenido;
	
	@OneToOne
	@JoinColumn(name="reclamo", nullable=true)
	private ReclamoEntity reclamo;
	
	@Column(name="calificacion_descarga" , nullable=true)
	private Integer calificacionDescarga;
	
	@Column(name="descripcion_valoracion" , nullable=true, length=1000)
	private String descripcionValoracion;
	
	@Column(name="fecha_descarga" , nullable=false)
	private Date fechaDescarga;
	
	@Column(name="fecha_valoracion" , nullable=true)
	private Date fechaValoracion;
	
	@Column(name="estado_descarga" , nullable=true, length=100)
	private String estadoDescarga;
	
	@OneToOne
	@JoinColumn(name="version_contenido" , nullable=true)
	private VersionContenidoEntity versionContenido;
	
	public VersionContenidoEntity getVersionContenido() {
		return versionContenido;
	}

	public void setVersionContenido(VersionContenidoEntity versionContenido) {
		this.versionContenido = versionContenido;
	}

	public String getEstadoDescarga() {
		return estadoDescarga;
	}

	public void setEstadoDescarga(String estadoDescarga) {
		this.estadoDescarga = estadoDescarga;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioEntity getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(UsuarioEntity usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public ContenidoEntity getContenido() {
		return contenido;
	}

	public void setContenido(ContenidoEntity contenido) {
		this.contenido = contenido;
	}

	public ReclamoEntity getReclamo() {
		return reclamo;
	}

	public void setReclamo(ReclamoEntity reclamo) {
		this.reclamo = reclamo;
	}

	public Integer getCalificacionDescarga() {
		return calificacionDescarga;
	}

	public void setCalificacionDescarga(Integer calificacionDescarga) {
		this.calificacionDescarga = calificacionDescarga;
	}

	public String getDescripcionValoracion() {
		return descripcionValoracion;
	}

	public void setDescripcionValoracion(String descripcionValoracion) {
		this.descripcionValoracion = descripcionValoracion;
	}

	public Date getFechaDescarga() {
		return fechaDescarga;
	}

	public void setFechaDescarga(Date fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}

	public Date getFechaValoracion() {
		return fechaValoracion;
	}

	public void setFechaValoracion(Date fechaValoracion) {
		this.fechaValoracion = fechaValoracion;
	}
	
}