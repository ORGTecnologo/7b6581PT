package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_video")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ContenidoVideoEntity extends ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "duracion_video", length = 20, nullable = false)
	private String duracionVideo;
	
	@Column(name = "formato_video", length = 20, nullable = false)
	private String formatoVideo;
	
	@Column(name = "calidad_video", length = 20, nullable = false)
	private String calidadVideo;

	public ContenidoVideoEntity(){
		super();
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
