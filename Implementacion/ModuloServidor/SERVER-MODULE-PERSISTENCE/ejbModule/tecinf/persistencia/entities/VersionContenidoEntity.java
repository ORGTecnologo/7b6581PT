package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_contenido", nullable=false)
	private ContenidoEntity contenido;
	
	@Column(name="estado_version", nullable=false, length=30)
	private String estadoVersion;
	
	@Column(name="descripcion",nullable=false, length=50)
	private String descripcion;
	
	@Column(name="fecha_subida",nullable=false)
	private Date fechaSubida;

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

	public String getEstadoVersion() {
		return estadoVersion;
	}

	public void setEstadoVersion(String estadoVersion) {
		this.estadoVersion = estadoVersion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
	
}
