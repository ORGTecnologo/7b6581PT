package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estados_usuario")
public class EstadoUsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Column(name="descripcion", nullable=false, length=50)
	private String descripcion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
