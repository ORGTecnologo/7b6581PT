package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenidoIngresoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;	
	private String descripcion;
	private String[] imagenes;
	private String calificacion;
	private String source;
	private String precio;
	private String tipoContenido;
	private String categoria;
	private String subcategoria;
	private String desarrollador;
	private String duracionTema;
	private String artistaTema;
	private String albumTema;

	//MUSICA
	private String esTrial;
	private String requisitosMinimos;
	private String fechaPublicacion;
	private String autor;
	private String paginas;
	private String duracionVideo;
	private String formatoVideo;
	private String calidadVideo;
	
	public ContenidoIngresoDataType(){
		this.imagenes = new String[5];	
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public String[] getImagenes() {
		return imagenes;
	}

	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}

	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getTipoContenido() {
		return tipoContenido;
	}
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getDesarrollador() {
		return desarrollador;
	}
	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
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
	public String getEsTrial() {
		return esTrial;
	}
	public void setEsTrial(String esTrial) {
		this.esTrial = esTrial;
	}
	public String getRequisitosMinimos() {
		return requisitosMinimos;
	}
	public void setRequisitosMinimos(String requisitosMinimos) {
		this.requisitosMinimos = requisitosMinimos;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getPaginas() {
		return paginas;
	}
	public void setPaginas(String paginas) {
		this.paginas = paginas;
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
