package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
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
@Table(name = "sub_categorias_contenido")
@NamedQueries( {
	
	@NamedQuery(name = "SubCategoriaContenidoEntity.findAll", 
		query = "SELECT e FROM SubCategoriaContenidoEntity e ORDER BY e.nombre") ,
			
	@NamedQuery(name = "SubCategoriaContenidoEntity.findByCategoria", 
		query = "SELECT e FROM SubCategoriaContenidoEntity e WHERE e.categoria.id = :idCategoria and e.habilitado = :habilitado ORDER BY e.nombre") ,
})
public class SubCategoriaContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subcatcontenido_secuencia")
	@SequenceGenerator(name = "subcatcontenido_secuencia", sequenceName = "subcatcontenido_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "descripcion", length = 255, nullable = true)
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name="id_categoria", nullable=false)
	private CategoriaContenidoEntity categoria;
	
	@Column(name = "habilitado", nullable = true)
	private Boolean habilitado;
	
	@Column(name = "ruta_imagen", length = 255, nullable = true)
	private String rutaImagen;
	
	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

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

	public CategoriaContenidoEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaContenidoEntity categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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