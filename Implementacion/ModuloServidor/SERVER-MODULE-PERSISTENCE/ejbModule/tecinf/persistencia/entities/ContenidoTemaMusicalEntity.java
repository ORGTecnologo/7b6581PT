package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_tema_musical")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ContenidoTemaMusicalEntity extends ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "duracion_tema", length = 10)
	private String duracionTema;
	
	@Column(name = "artista_tema", length = 100)
	private String artistaTema;
	
	@Column(name = "album_tema", length = 20)
	private String albumTema;

	public ContenidoTemaMusicalEntity(){
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
