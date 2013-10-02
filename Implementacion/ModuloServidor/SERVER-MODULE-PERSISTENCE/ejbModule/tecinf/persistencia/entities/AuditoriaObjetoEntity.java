package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auditoria_objeto")
public class AuditoriaObjetoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Column(name="descripcion", length=50, nullable=false)
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
