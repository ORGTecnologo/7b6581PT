package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id; 
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_software")
public class ContenidoSoftwareEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="fk_contenido",unique=true,nullable=false)
	private ContenidoEntity contenido;	
	
	@Column(name = "es_trial", nullable = false)
	private Boolean descripcion;

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

	public Boolean getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(Boolean descripcion) {
		this.descripcion = descripcion;
	}
		

}