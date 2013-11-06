package tecinf.negocio.dtos;

import java.io.Serializable;

public class ContenidoVideoDataType extends ContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;

	private String duracionVideo;
	private String formatoVideo;
	private String calidadVideo;
	
	public ContenidoVideoDataType(){
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
