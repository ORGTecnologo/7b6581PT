package tecinf.negocio.utiles;

import java.text.SimpleDateFormat;

import tecinf.negocio.dtos.AprobarContenidoDataType;
import tecinf.negocio.dtos.AuditoriaDataType;
import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoLibroDataType;
import tecinf.negocio.dtos.ContenidoMinimalDataType;
import tecinf.negocio.dtos.ContenidoSoftwareDataType;
import tecinf.negocio.dtos.ContenidoTemaMusicalDataType;
import tecinf.negocio.dtos.ContenidoVideoDataType;
import tecinf.negocio.dtos.DescargaDataType;
import tecinf.negocio.dtos.ParametroValorDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;
import tecinf.negocio.dtos.UsuarioAdministradorDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.dtos.UsuarioProveedorDataType;
import tecinf.negocio.dtos.VersionContenidoDataType;
import tecinf.persistencia.entities.AuditoriaEntity;
import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.ContenidoEntity;
import tecinf.persistencia.entities.ContenidoFotoEntity;
import tecinf.persistencia.entities.ContenidoLibroEntity;
import tecinf.persistencia.entities.ContenidoSoftwareEntity;
import tecinf.persistencia.entities.ContenidoTemaMusicalEntity;
import tecinf.persistencia.entities.ContenidoVideoEntity;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;
import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.entities.VersionContenidoEntity;
import tecinf.persistencia.utiles.EnumTiposContenido;

public class DataTypesFactory {
	
	//private static Logger logger = Logger.getLogger(DataTypesFactory.class);
	
	public static ParametroValorDataType getParametroValorDataType(ParametroValorEntity e){
		ParametroValorDataType dt = new ParametroValorDataType();
		
		dt.setNombre(e.getNombreParametro());
		dt.setValor(e.getValorParametro());
		
		return dt;
	}
	
	public static UsuarioDataType getUsuarioDataType(UsuarioEntity u){
		UsuarioDataType dt = null;	
		
		if (u.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE)){
			dt = new UsuarioClienteDataType();
		} else if (u.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_PROVEEDOR)){
			dt = new UsuarioProveedorDataType();			
		} else if (u.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_ADMINISTRADOR)){
			dt = new UsuarioAdministradorDataType();
		}
		
		dt.setApellidos(u.getApellidos());
		dt.setContrasenia(u.getContrasenia());
		dt.setCorreoElectronico(u.getCorreoElectronico());
		dt.setFechaNacimientoTimeStamp(u.getFechaNacimiento() == null ? 0 : u.getFechaNacimiento().getTime());
		dt.setNombres(u.getNombres());
		dt.setSexo(u.getSexo());
		dt.setTelefonoMovil(u.getTelefonoMovil());	
		dt.setUsuario(u.getUsuario());
		dt.setHabilitado(u.getHabilitado());
		
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
	
	public static ContenidoDataType getContenidoDataType(ContenidoEntity c){
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
		
		if (c.getFotos() != null && c.getFotos().size() > 0) {
			for (ContenidoFotoEntity f : c.getFotos())
				dt.getFotos().add("/SERVER-MODULE-PRESENTATION/Images?" + f.getUrlFoto());
		}
		
		if (c.getVersiones() != null && c.getVersiones().size() > 0) {
			for (VersionContenidoEntity v : c.getVersiones())
				dt.getVersiones().add(getVersionContenidoDataType(v));
		}
		
		dt.setVideoMovil(c.getVideoMovil() == null ? "" : c.getVideoMovil());
		dt.setVideoWeb(c.getVideoWeb() == null ? "" : c.getVideoWeb());
		
		dt.setTipoContenido(c.getTipoContenido());
		dt.setPrecio(c.getPrecio() == null ? 0 : c.getPrecio());
		dt.setCalificacion(c.getCalificacion() == null ? 0 : c.getCalificacion());
		dt.setDescripcionContenido(c.getDescripcion());
		dt.setIdContenido(c.getId());
		dt.setNombreContenido(c.getNombre());
		dt.setTamanioContenido(c.getTamanio());
		dt.setUrlArchivoContenido("/SERVER-MODULE-PRESENTATION/FileDispatcherServlet?idContenido=" + c.getId() + "&rutaContenido=" + c.getRutaArchivoContenido());
		dt.setCantidadDescargas(c.getCantidadDescargas() == null ? 0 : c.getCantidadDescargas());
		
		return dt;
	}
	
	public static VersionContenidoDataType getVersionContenidoDataType(VersionContenidoEntity e){
		VersionContenidoDataType dt = new VersionContenidoDataType();
		
		dt.setDescripcion(e.getDescripcion());
		dt.setEstadoVersion(e.getEstadoVersion());
		dt.setFechaSubida(e.getFechaSubida());
		dt.setId(e.getId()); 
		
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
		String tipoUsuario = "";
		if (e.getUsuario().getTipoUsuario().equals(EnumTipoUsuario.USUARIO_ADMINISTRADOR)) 
			tipoUsuario = "Administrador";
		else if (e.getUsuario().getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE))
			tipoUsuario = "Cliente";
		else if (e.getUsuario().getTipoUsuario().equals(EnumTipoUsuario.USUARIO_PROVEEDOR))
			tipoUsuario = "Proveedor";
			
		dt.setTipoUsuario(tipoUsuario);
		
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
	
	public static AprobarContenidoDataType getAprobarContenidoDataType(ContenidoEntity c){
		AprobarContenidoDataType dt = new AprobarContenidoDataType();
		
		VersionContenidoEntity v = c.obtenerVersionConEstado(EnumEstadosVersionContenido.PENDIENTE_REVISION);
		dt.setIdContenido(c.getId());
		dt.setDescripcionVersion(v.getDescripcion());
		dt.setNombreContenido(c.getNombre());
		dt.setVersion(v.getVersion());
		dt.setIdVersion(v.getId());
		dt.setProveedor(c.getProveedorContenido());
		
		return dt;
	}
	
	public static ContenidoMinimalDataType getContenidoMinimalDataType(ContenidoEntity c){
		ContenidoMinimalDataType dt = new ContenidoMinimalDataType();
		
		dt.setId(c.getId());
		dt.setNombreContenido(c.getNombre());
		dt.setDescripcionContenido(c.getDescripcion());
		dt.setPrecio(c.getPrecio() == null || c.getPrecio() == Float.valueOf("0.0") ? "Gratis" : c.getPrecio().toString()); 
		if (c.getFotos() != null && c.getFotos().size() > 0){
			for (ContenidoFotoEntity f : c.getFotos())
				dt.getListaFotos().add(f.getUrlFoto());
		}
		dt.setCalificacion(c.getCalificacion() == null ? 0 : c.getCalificacion());
		
		return dt;
	}
	
	public static DescargaDataType getDescargaDataType(UsuarioDescargaContenidoEntity e){
		DescargaDataType dt = new DescargaDataType();
		
		dt.setFechaDescarga(e.getFechaDescarga() == null ? "" : String.valueOf(e.getFechaDescarga().getTime())); 
		dt.setIdContenido(e.getContenido().getId());
		dt.setIdDescarga(e.getId());
		dt.setNombreContenido(e.getContenido().getNombre());
		if (e.getContenido().getFotos() != null){
			for (ContenidoFotoEntity f : e.getContenido().getFotos()){
				dt.setFoto(f.getUrlFoto());
				break;
			}
		} else {
			dt.setFoto("");
		}
		dt.setCalificacion(e.getContenido().getCalificacion());
		dt.setTipoContenido(e.getContenido().getTipoContenido());
		
		return dt;
	}
	
}
