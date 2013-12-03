package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="reclamos")
public class ReclamoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"reclamo_secuencia") 
	@SequenceGenerator(name = "reclamo_secuencia", sequenceName = "reclamo_seq", allocationSize = 1)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="id_categoria")
	private CategoriaReclamoEntity categoria;
	
	@Column(name="fecha_reclamo", nullable=false)
	private Date fechaReclamo;
	
	@Column(name="descripcion", nullable=false, length=1000)
	private String descripcion;
	
	@Column(name="titulo", nullable=false, length=200)
	private String titulo;
	
	@Column(name="estado", nullable=false, length=50)
	private String estado;
	
	@Column(name="fecha_resulto", nullable=true)
	private Date fechaResuelto;
	
	@OneToOne
	@JoinColumn(name="id_descarga", nullable=false)
	private UsuarioDescargaContenidoEntity descarga;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategoriaReclamoEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaReclamoEntity categoria) {
		this.categoria = categoria;
	}

	public Date getFechaReclamo() {
		return fechaReclamo;
	}

	public void setFechaReclamo(Date fechaReclamo) {
		this.fechaReclamo = fechaReclamo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public UsuarioDescargaContenidoEntity getDescarga() {
		return descarga;
	}

	public void setDescarga(UsuarioDescargaContenidoEntity descarga) {
		this.descarga = descarga;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
