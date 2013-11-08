package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "categorias_contenido")
@NamedQueries( {
	
	@NamedQuery(name = "CategoriaContenidoEntity.findAll", 
			query = "SELECT e FROM CategoriaContenidoEntity e WHERE e.habilitado = :habilitado ORDER BY e.nombre") ,
})
public class CategoriaContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catcontenido_secuencia")
	@SequenceGenerator(name = "catcontenido_secuencia", sequenceName = "catcontenido_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "descripcion", length = 255, nullable = true)
	private String descripcion;
	
	@Column(name = "habilitado", nullable = true)
	private Boolean habilitado;

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

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}	
	
}
