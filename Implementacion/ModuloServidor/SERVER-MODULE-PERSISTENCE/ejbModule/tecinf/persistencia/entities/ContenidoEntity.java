package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contenidos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contenido_secuencia")
	@SequenceGenerator(name = "contenido_secuencia", sequenceName = "contenido_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "descripcion", length = 1000, nullable = false)
	private String descripcion;
	
	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;
	
	@Column(name = "version", length = 20, nullable = false)
	private String version;
	
	//TODO VER COMO MANEJAR EL TEMA DE LAS FOTOS
	
	@Column(name = "tamanio_contenido", nullable = false)
	private Integer tamanio;
	
	@Column(name = "ruta_archivo_contenido", length = 100, nullable = false)
	private String rutaArchivoContenido;
	
	@Column(name = "tipo_contenido", length = 50, nullable = false)
	private String tipoContenido;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getTamanio() {
		return tamanio;
	}

	public void setTamanio(Integer tamanio) {
		this.tamanio = tamanio;
	}

	public String getRutaArchivoContenido() {
		return rutaArchivoContenido;
	}

	public void setRutaArchivoContenido(String rutaArchivoContenido) {
		this.rutaArchivoContenido = rutaArchivoContenido;
	}

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

}