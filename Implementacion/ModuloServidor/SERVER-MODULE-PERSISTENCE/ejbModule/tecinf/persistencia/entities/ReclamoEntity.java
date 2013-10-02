package tecinf.persistencia.entities;

import java.util.Date;

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
@Table(name="reclamos")
public class ReclamoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"reclamo_secuencia") 
	@SequenceGenerator(name = "reclamo_secuencia", sequenceName = "reclamo_seq", allocationSize = 1)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="id_categoria")
	private CategoriaReclamoEntity categoria;
	
	@Column(name="fecha_reclamo", nullable=false)
	private Date fechaReclamo;
	
	@Column(name="descripcion", nullable=false, length=500)
	private String descripcion;

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
	
}
