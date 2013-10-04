package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_video")
public class ContenidoVideoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@OneToOne
	@JoinColumn(name="fk_contenido",unique=true,nullable=false)
	private ContenidoEntity contenido;
	
	@Column(name = "duracion_tema", length = 20, nullable = false)
	private String duracionTema;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDuracionTema() {
		return duracionTema;
	}

	public void setDuracionTema(String duracionTema) {
		this.duracionTema = duracionTema;
	}

	public ContenidoEntity getContenido() {
		return contenido;
	}

	public void setContenido(ContenidoEntity contenido) {
		this.contenido = contenido;
	}

}
