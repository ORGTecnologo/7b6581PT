package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn; 
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_video")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ContenidoVideoEntity extends ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name="fk_contenido",unique=true,nullable=false)
	private ContenidoEntity contenido;
	
	@Column(name = "duracion_video", length = 20, nullable = false)
	private String duracionVideo;
	
	@Column(name = "formato_video", length = 20, nullable = false)
	private String formatoVideo;
	
	@Column(name = "calidad_video", length = 20, nullable = false)
	private String calidadVideo;

	public ContenidoEntity getContenido() {
		return contenido;
	}

	public void setContenido(ContenidoEntity contenido) {
		this.contenido = contenido;
	}

	public String getDuracionVideo() {
		return duracionVideo;
	}

	public void setDuracionVideo(String duracionVideo) {
		this.duracionVideo = duracionVideo;
	}

	public String getFormatoVideo() {
		return formatoVideo;
	}

	public void setFormatoVideo(String formatoVideo) {
		this.formatoVideo = formatoVideo;
	}

	public String getCalidadVideo() {
		return calidadVideo;
	}

	public void setCalidadVideo(String calidadVideo) {
		this.calidadVideo = calidadVideo;
	}
	

}
