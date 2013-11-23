package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contenidos")
@Inheritance(strategy = InheritanceType.JOINED)

@NamedQueries( {
	
	@NamedQuery(name = "ContenidoEntity.findAll", 
		query = "SELECT e FROM ContenidoEntity e ORDER BY e.id") ,
})


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
	
	@OneToOne
	@JoinColumn(name="fk_subcategoria", nullable=true)
	private SubCategoriaContenidoEntity subcategoria;
	
	@Column(name = "tamanio_contenido", nullable = false)
	private Integer tamanio;
	
	@Column(name = "ruta_archivo_contenido", length = 1000, nullable = false)
	private String rutaArchivoContenido;
	
	@Column(name = "tipo_contenido", length = 50, nullable = false)
	private String tipoContenido;
	
	@Column(name = "cantidad_descargas", nullable = true)
	private Integer cantidadDescargas;
	
	@Column(name = "calificacion", nullable = true)
	private Integer calificacion;
	
	@Column(name = "precio", nullable = true)
	private Float precio;
	
	@Column(name = "video_web", length = 500, nullable = true)
	private String videoWeb;

	@Column(name = "video_movil", length = 500, nullable = true)
	private String videoMovil;
	
	@OneToMany(targetEntity=ContenidoFotoEntity.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="contenido")
	private Set<ContenidoFotoEntity> fotos;
	
	@OneToMany(targetEntity=VersionContenidoEntity.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="contenido")
	private Set<VersionContenidoEntity> versiones;
	
	@OneToMany(targetEntity=UsuarioSubeContenidoEntity.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="contenido")
	private Set<UsuarioSubeContenidoEntity> subidas;
	
	@OneToMany(targetEntity=UsuarioDescargaContenidoEntity.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="contenido")
	private Set<UsuarioDescargaContenidoEntity> bajadas;
	
	public ContenidoEntity() {
		fotos = new HashSet<ContenidoFotoEntity>();
		versiones = new HashSet<VersionContenidoEntity>();
		subidas = new HashSet<UsuarioSubeContenidoEntity>();
		bajadas = new HashSet<UsuarioDescargaContenidoEntity>();
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

	public Integer getCantidadDescargas() {
		return cantidadDescargas;
	}

	public void setCantidadDescargas(Integer cantidadDescargas) {
		this.cantidadDescargas = cantidadDescargas;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Set<ContenidoFotoEntity> getFotos() {
		return fotos;
	}

	public void setFotos(Set<ContenidoFotoEntity> fotos) {
		this.fotos = fotos;
	}	
	
	public Set<VersionContenidoEntity> getVersiones() {
		return versiones;
	}

	public void setVersiones(Set<VersionContenidoEntity> versiones) {
		this.versiones = versiones;
	}

	public Set<UsuarioSubeContenidoEntity> getSubidas() {
		return subidas;
	}

	public void setSubidas(Set<UsuarioSubeContenidoEntity> subidas) {
		this.subidas = subidas;
	}	

	public Set<UsuarioDescargaContenidoEntity> getBajadas() {
		return bajadas;
	}

	public void setBajadas(Set<UsuarioDescargaContenidoEntity> bajadas) {
		this.bajadas = bajadas;
	}

	public Boolean tieneVersionConEstado(String estado){
		for (VersionContenidoEntity v : this.versiones){
			if (v.getEstadoVersion().equals(estado))
				return true;
		}
		return false;
	}
	
	public VersionContenidoEntity obtenerVersionConEstado(String estado){
		for (VersionContenidoEntity v : this.versiones){
			if (v.getEstadoVersion().equals(estado))
				return v;
		}
		return null;
	}

	public SubCategoriaContenidoEntity getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubCategoriaContenidoEntity subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getVideoWeb() {
		return videoWeb;
	}

	public void setVideoWeb(String videoWeb) {
		this.videoWeb = videoWeb;
	}

	public String getVideoMovil() {
		return videoMovil;
	}

	public void setVideoMovil(String videoMovil) {
		this.videoMovil = videoMovil;
	}
		
}