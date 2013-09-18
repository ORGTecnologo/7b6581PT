package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="roles")

@NamedQueries( {
	
	@NamedQuery(name = "RolEntity.findAll", 
			query = "SELECT e FROM RolEntity e ORDER BY e.descripcion") ,
					
	@NamedQuery(name = "RolEntity.findById", 
		query = "SELECT e FROM RolEntity e WHERE e.id = :id") ,
})

public class RolEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="descripcion")
	private String descripcion;

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
