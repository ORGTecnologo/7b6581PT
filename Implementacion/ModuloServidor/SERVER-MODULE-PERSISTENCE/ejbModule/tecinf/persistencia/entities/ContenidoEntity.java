package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contenidos")
public class ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contenido_secuencia")
	@SequenceGenerator(name = "contenido_secuencia", sequenceName = "contenido_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;
	
	@Column(name = "nombre", length = 20, nullable = false)
	private String nombre;
	
	@Column(name = "version", length = 20, nullable = false)
	private String version;
	
	//TODO VER COMO MANEJAR EL TEMA DE LAS FOTOS
	
	@Column(name = "tamanio_contenido", nullable = false)
	private Integer tamanio;
	
	@Column(name = "ruta_archivo_contenido", length = 100, nullable = false)
	private String rutaArchivoContenido;

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

}