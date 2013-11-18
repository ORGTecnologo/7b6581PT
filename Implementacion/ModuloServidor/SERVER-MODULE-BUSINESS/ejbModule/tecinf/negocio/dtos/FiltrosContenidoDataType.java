package tecinf.negocio.dtos;

import java.io.Serializable;

public class FiltrosContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
/*	
JSONstring.libros = varsProy.PARAM_BUSQ_LIBROS;
JSONstring.musica = varsProy.PARAM_BUSQ_MUSICA;
JSONstring.apps = varsProy.PARAM_BUSQ_APPS;
JSONstring.videos = varsProy.PARAM_BUSQ_VIDEO;
JSONstring.categorias = varsProy.PARAM_BUSQ_CATEGORIAS;
JSONstring.keyword = document.getElementById("input-busqueda").value; 
*/
	
	private Boolean libros;
	private Boolean musica;
	private Boolean apps;
	private Boolean videos;
	private String categorias;
	private String keyword;
	
	
	public Boolean getLibros() {
		return libros;
	}
	public void setLibros(Boolean libros) {
		this.libros = libros;
	}
	public Boolean getMusica() {
		return musica;
	}
	public void setMusica(Boolean musica) {
		this.musica = musica;
	}
	public Boolean getApps() {
		return apps;
	}
	public void setApps(Boolean apps) {
		this.apps = apps;
	}
	public Boolean getVideos() {
		return videos;
	}
	public void setVideos(Boolean videos) {
		this.videos = videos;
	}
	public String getCategorias() {
		return categorias;
	}
	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	
		
}