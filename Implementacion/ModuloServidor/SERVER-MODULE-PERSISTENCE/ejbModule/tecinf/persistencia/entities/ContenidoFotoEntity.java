package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.Column; 
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_foto")

@NamedQueries( {
	
	@NamedQuery(name = "ContenidoFotoEntity.findByContenido", 
		query = "SELECT e FROM ContenidoFotoEntity e WHERE e.contenido.id = :idContenido ") ,
})

public class ContenidoFotoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contenido_foto_secuencia")
	@SequenceGenerator(name = "contenido_foto_secuencia", sequenceName = "contenido_foto_seq", allocationSize = 1)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY, targetEntity = ContenidoEntity.class,cascade=CascadeType.ALL)
	@JoinColumn(name="fk_contenido", nullable=false)
	private ContenidoEntity contenido;	
	
	@Column(name = "url_foto", length = 200, nullable=false)
	private String urlFoto;

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

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	
}
