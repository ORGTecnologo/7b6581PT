package tecinf.negocio.dtos;

import java.io.Serializable;

public class ContenidoTemaMusicalDataType extends ContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String duracionTema;
	private String artistaTema;
	private String albumTema;	
	
	public ContenidoTemaMusicalDataType(){
		super();
	}

	public String getDuracionTema() {
		return duracionTema;
	}


	public void setDuracionTema(String duracionTema) {
		this.duracionTema = duracionTema;
	}


	public String getArtistaTema() {
		return artistaTema;
	}


	public void setArtistaTema(String artistaTema) {
		this.artistaTema = artistaTema;
	}


	public String getAlbumTema() {
		return albumTema;
	}


	public void setAlbumTema(String albumTema) {
		this.albumTema = albumTema;
	}
	
}
