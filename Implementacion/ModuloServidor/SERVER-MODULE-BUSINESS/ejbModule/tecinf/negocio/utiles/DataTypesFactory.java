package tecinf.negocio.utiles;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.dtos.AuditoriaDataType;
import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoLibroDataType;
import tecinf.negocio.dtos.ContenidoSoftwareDataType;
import tecinf.negocio.dtos.ContenidoTemaMusicalDataType;
import tecinf.negocio.dtos.ContenidoVideoDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.persistencia.entities.AuditoriaEntity;
import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.ContenidoEntity;
import tecinf.persistencia.entities.ContenidoFotoEntity;
import tecinf.persistencia.entities.ContenidoLibroEntity;
import tecinf.persistencia.entities.ContenidoSoftwareEntity;
import tecinf.persistencia.entities.ContenidoTemaMusicalEntity;
import tecinf.persistencia.entities.ContenidoVideoEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;
import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.utiles.EnumTiposContenido;

public class DataTypesFactory {
	
	private static Logger logger = Logger.getLogger(DataTypesFactory.class);
	
	public static UsuarioDataType getUsuarioDataType(UsuarioEntity u){
		UsuarioDataType dt = null;		
		if (u.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE)){
			dt = new UsuarioClienteDataType();
			dt.setApellidos(u.getApellidos());
			dt.setContrasenia(u.getContrasenia());
			dt.setCorreoElectronico(u.getCorreoElectronico());
			dt.setFechaNacimientoDate(u.getFechaNacimiento());
			dt.setNombres(u.getNombres());
			dt.setSexo(u.getSexo());
			dt.setTelefonoMovil(u.getTelefonoMovil());	
			dt.setUsuario(u.getUsuario());
		}
		return dt;
	}
	
	public static CategoriaContenidoDataType getCategoriaContenidoDataType(CategoriaContenidoEntity e){
		CategoriaContenidoDataType dt = new CategoriaContenidoDataType();
		
		dt.setDescripcion(e.getDescripcion());
		dt.setId(e.getId());
		dt.setNombre(e.getNombre());
		dt.setRutaImagen(e.getRutaImagen());
		dt.setHabilitada(e.getHabilitado());
		
		return dt;
	}
	
	public static SubCategoriaContenidoDataType getSubCategoriaContenidoDataType(SubCategoriaContenidoEntity e){
		SubCategoriaContenidoDataType dt = new SubCategoriaContenidoDataType();
		
		dt.setDescripcion(e.getDescripcion());
		dt.setId(e.getId());
		dt.setNombre(e.getNombre());
		dt.setRutaImagen(e.getRutaImagen());
		dt.setCategoria(getCategoriaContenidoDataType(e.getCategoria()));
		dt.setHabilitada(e.getHabilitado());
		
		return dt;
	} 
	
	public static ContenidoDataType getContenidoDataType(ContenidoEntity c, List<ContenidoFotoEntity> fotos){
		ContenidoDataType dt = null;
		
		if (c.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_LIBRO)){
			dt = new ContenidoLibroDataType();
			c = (ContenidoLibroEntity)c;
			((ContenidoLibroDataType)dt).setAutor( ((ContenidoLibroEntity)c).getAutor() );
			((ContenidoLibroDataType)dt).setFechaPublicacion(( ((ContenidoLibroEntity)c).getFecha_publicacion()) );
			((ContenidoLibroDataType)dt).setAutor( ((ContenidoLibroEntity)c).getAutor() );
			Integer cantPag = ((ContenidoLibroEntity)c).getCantidadPaginas();
			((ContenidoLibroDataType)dt).setCantidadPaginas(cantPag == null ? 0 : cantPag);
		} else if (c.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_SOFTWARE)){
			dt = new ContenidoSoftwareDataType();
			((ContenidoSoftwareDataType)dt).setRequisitosMinimos(((ContenidoSoftwareEntity)c).getRequisitosMinimos());
			((ContenidoSoftwareDataType)dt).setEsTrial(((ContenidoSoftwareEntity)c).getEsTrial());
			((ContenidoSoftwareDataType)dt).setDesarrollador(((ContenidoSoftwareEntity)c).getDesarrollador());
		} else if (c.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_VIDEO)){
			dt = new ContenidoVideoDataType();
			((ContenidoVideoDataType)dt).setCalidadVideo(((ContenidoVideoEntity)c).getCalidadVideo());
			((ContenidoVideoDataType)dt).setDuracionVideo(((ContenidoVideoEntity)c).getDuracionVideo());
			((ContenidoVideoDataType)dt).setFormatoVideo(((ContenidoVideoEntity)c).getFormatoVideo());
		} else if (c.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_TEMA)){
			dt = new ContenidoTemaMusicalDataType();
			((ContenidoTemaMusicalDataType)dt).setDuracionTema(((ContenidoTemaMusicalEntity)c).getDuracionTema());
			((ContenidoTemaMusicalDataType)dt).setArtistaTema(((ContenidoTemaMusicalEntity)c).getArtistaTema());
			((ContenidoTemaMusicalDataType)dt).setAlbumTema(((ContenidoTemaMusicalEntity)c).getAlbumTema()); 
		}
		
		if (fotos != null && fotos.size() > 0){
			for (ContenidoFotoEntity f : fotos){
				dt.getFotos().add("/SERVER-MODULE-PRESENTATION/Images?" + f.getUrlFoto());
			}
		}
		
		dt.setTipoContenido(c.getTipoContenido());
		dt.setPrecio(c.getPrecio() == null ? 0 : c.getPrecio());
		dt.setCalificacion(c.getCalificacion() == null ? 0 : c.getCalificacion());
		dt.setDescripcionContenido(c.getDescripcion());
		dt.setIdContenido(c.getId());
		dt.setNombreContenido(c.getNombre());
		FileSystemUtils fsu = null;
		try { fsu = new FileSystemUtils(); } catch (NamingException e) { logger.error(e.getMessage() , e); }
		dt.setTamanioContenido(fsu.getFileSize(c.getRutaArchivoContenido()));
		dt.setUrlArchivoContenido("/SERVER-MODULE-PRESENTATION/FileDispatcherServlet?" + c.getRutaArchivoContenido());
		dt.setCantidadDescargas(c.getCantidadDescargas() == null ? 0 : c.getCantidadDescargas()); 
		
		return dt;
	}
	
	public static AuditoriaDataType getAuditoriaDataType(AuditoriaEntity e){
		AuditoriaDataType dt = new AuditoriaDataType();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dt.setFechaOperacion(sdf.format(e.getFechaOperacion()));
		dt.setObjeto(e.getObjeto().getDescripcion());
		dt.setOperacion(e.getOperacion().getDescripcion()); 
		dt.setUsuario(e.getUsuario().getUsuario());
		dt.setObjetoSistema(e.getIdObjetoSistema()); 
		
		return dt;
	}
	
	public static ComentarioDataType getComentarioDataType(UsuarioDescargaContenidoEntity e){
		ComentarioDataType dt = new ComentarioDataType();
		
		dt.setIdDescarga(e.getId()); 
		dt.setComentario(e.getDescripcionValoracion() == null ? "" : e.getDescripcionValoracion());
		dt.setIdContenido(e.getContenido().getId());
		dt.setUsuario(e.getUsuarioCliente().getUsuario());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dt.setFecha(e.getFechaValoracion() == null ? "" : sdf.format(e.getFechaValoracion()));
		
		return dt;
	}
	
}
