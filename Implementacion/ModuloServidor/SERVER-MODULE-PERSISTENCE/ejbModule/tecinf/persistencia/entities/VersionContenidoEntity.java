package tecinf.persistencia.entities;

import java.io.Serializable;

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
@Table(name="version_contenido")
public class VersionContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "version_contenido_secuencia")
	@SequenceGenerator(name = "version_contenido_secuencia", sequenceName = "version_contenido_seq", allocationSize = 1)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="fk_contenido", nullable=false)
	private ContenidoEntity contenido;
	
	@OneToOne
	@JoinColumn(name="fk_estado_version", nullable=false)
	private EstadoVersionContenidoEntity estadoVersion;
	
	@Column(name="descripcion",nullable=false, length=50)
	private String descripcion;
	
	@Column(name="fecha_subida",nullable=false, length=50)
	private String fechaSubida;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ContenidoEntity getContenido() {
		return contenido;
	}

	public void setContenido(ContenidoEntity contenido) {
		this.contenido = contenido;
	}

	public EstadoVersionContenidoEntity getEstadoVersion() {
		return estadoVersion;
	}

	public void setEstadoVersion(EstadoVersionContenidoEntity estadoVersion) {
		this.estadoVersion = estadoVersion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(String fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
	
}
