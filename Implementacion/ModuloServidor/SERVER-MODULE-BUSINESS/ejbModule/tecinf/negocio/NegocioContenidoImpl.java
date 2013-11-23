package tecinf.negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.dtos.AprobarContenidoDataType;
import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoIngresoDataType;
import tecinf.negocio.dtos.ContenidoMinimalDataType;
import tecinf.negocio.dtos.DescargaDataType;
import tecinf.negocio.dtos.FiltrosContenidoDataType;
import tecinf.negocio.utiles.CripterDecripter;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.negocio.utiles.EnumEstadosDescarga;
import tecinf.negocio.utiles.EnumEstadosVersionContenido;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.FileSystemUtils;
import tecinf.negocio.utiles.RandomString;
import tecinf.negocio.utiles.TimeUtils;
import tecinf.negocio.utiles.ValidationUtil;
import tecinf.persistencia.daos.ContenidoDao;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.daos.UsuarioDescargaContenidoDao;
import tecinf.persistencia.daos.UsuarioSubeContenidoDao;
import tecinf.persistencia.daos.VersionContenidoDao;
import tecinf.persistencia.entities.ContenidoEntity;
import tecinf.persistencia.entities.ContenidoFotoEntity;
import tecinf.persistencia.entities.ContenidoLibroEntity;
import tecinf.persistencia.entities.ContenidoSoftwareEntity;
import tecinf.persistencia.entities.ContenidoTemaMusicalEntity;
import tecinf.persistencia.entities.ContenidoVideoEntity;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.entities.UsuarioSubeContenidoEntity;
import tecinf.persistencia.entities.VersionContenidoEntity;
import tecinf.persistencia.utiles.EnumTiposContenido;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioContenidoImpl implements NegocioContenido {
	
	private Logger logger = Logger.getLogger(NegocioContenidoImpl.class);
	
	private ContenidoDao contenidoDao = null;
	private ParametroValorDao parametroValorDao = null;
	private UsuarioDescargaContenidoDao usuarioDescargaContenidoDao = null;
	private UsuarioSubeContenidoDao usuarioSubeContenidoDao = null;
	private UsuarioDao usuarioDao = null;
	private VersionContenidoDao versionContenidoDao = null;
	
	private FileSystemUtils fSU = new FileSystemUtils();
	
	public NegocioContenidoImpl() throws NamingException { 
		
		contenidoDao = PersistenciaFactory.getContenidoDao();
		usuarioDescargaContenidoDao = PersistenciaFactory.getUsuarioDescargaContenidoDao();
		parametroValorDao = PersistenciaFactory.getParametroValorDao();
		usuarioDao = PersistenciaFactory.getUsuarioDao();
		usuarioSubeContenidoDao = PersistenciaFactory.getUsuarioSubeContenidoDao();
		versionContenidoDao = PersistenciaFactory.getVersionContenidoDao();
		
	}
	
	public ContenidoDataType obtenerDatosContenido(int idContenido) {
		ContenidoDataType contenido = null;		
		ContenidoEntity cont = contenidoDao.findByID(idContenido);
		if (cont != null){
			//List<ContenidoFotoEntity> fotos = contenidoFotosDao.getAllByContenido(idContenido);		
			contenido = DataTypesFactory.getContenidoDataType(cont);
		}
		return contenido;
	}
	
	public void actualizarDatosContenidos(){
		
		List<ContenidoEntity> listaContenidos = contenidoDao.findAll();
		
		if (listaContenidos != null){			
			// Para cada contenido actualizo los datos
			for (ContenidoEntity c : listaContenidos){
				List<UsuarioDescargaContenidoEntity> listaDescargas = usuarioDescargaContenidoDao.getContentDownloads(c.getId());
				c.setCantidadDescargas(listaDescargas == null ? 0 : listaDescargas.size());
				if (listaDescargas != null){
					Integer cantDescargasConCalificacion = 0;
					Integer sumaCalificacion = 0;
					for (UsuarioDescargaContenidoEntity udc : listaDescargas) {
						if (udc.getCalificacionDescarga() != null) {
							sumaCalificacion += udc.getCalificacionDescarga();
							cantDescargasConCalificacion++;
						}						
					}
					c.setCalificacion(cantDescargasConCalificacion == 0 ? 0 : sumaCalificacion / cantDescargasConCalificacion);
					contenidoDao.merge(c);
				}
			}
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ContenidoMinimalDataType> filtrarContenidos(FiltrosContenidoDataType filtros) {		
		List<ContenidoMinimalDataType> listaItemsContenido = new ArrayList<ContenidoMinimalDataType>();	
		
		Map filtrosMap = new HashMap<String, Object>();
		
		if (filtros.getLibros())
			filtrosMap.put("libros", EnumTiposContenido.TIPO_CONTENIDO_LIBRO);
		if (filtros.getApps())
			filtrosMap.put("software", EnumTiposContenido.TIPO_CONTENIDO_SOFTWARE);
		if (filtros.getMusica())
			filtrosMap.put("temas", EnumTiposContenido.TIPO_CONTENIDO_TEMA);
		if (filtros.getVideos())
			filtrosMap.put("videos", EnumTiposContenido.TIPO_CONTENIDO_VIDEO);
		
		if (!ValidationUtil.isNullOrEmpty(filtros.getKeyword()))
			filtrosMap.put("keyword", "%" + filtros.getKeyword().trim() + "%");
		
		if (!ValidationUtil.isNullOrEmpty(filtros.getCategorias()) && !filtros.getCategorias().equals("all"))
			filtrosMap.put("categorias", filtros.getCategorias());
		
		
		List<ContenidoEntity> listaContE = contenidoDao.findByFiltros(filtrosMap);
		if (listaContE != null && listaContE.size() > 0){
			for (ContenidoEntity c : listaContE)
				listaItemsContenido.add(DataTypesFactory.getContenidoMinimalDataType(c));
		}		
		return listaItemsContenido;
	}
	
	public List<ContenidoMinimalDataType> obtenerTopContenidos(Integer cantidad, String tipo) throws Exception {
		List<ContenidoMinimalDataType> listaItemsContenido = new ArrayList<ContenidoMinimalDataType>();
		String[] tiposValidos = {EnumTiposContenido.TIPO_CONTENIDO_LIBRO , EnumTiposContenido.TIPO_CONTENIDO_SOFTWARE, EnumTiposContenido.TIPO_CONTENIDO_TEMA, EnumTiposContenido.TIPO_CONTENIDO_VIDEO};  
		
		if (!Arrays.asList(tiposValidos).contains(tipo))
			throw new Exception("TIPO_NO_VALIDO");
		if (cantidad == null)
			throw new Exception("CANTIDAD_NO_VALIDA");
		
		List<ContenidoEntity> listaContE = contenidoDao.findTopContents(cantidad, tipo);
		if (listaContE != null){
			for (ContenidoEntity e : listaContE)
				listaItemsContenido.add(DataTypesFactory.getContenidoMinimalDataType(e));
		}
		
		return listaItemsContenido;
	}
	
	public List<ComentarioDataType> obtenerComentariosAAprobar(){
		List<ComentarioDataType> comentariosPendientes = new ArrayList<>();
		
		List<UsuarioDescargaContenidoEntity> listaDescargasPendientes = usuarioDescargaContenidoDao.getDonwloadsByState(EnumEstadosDescarga.PENDIENTE_VALIDACION);
		if (listaDescargasPendientes != null){
			for (UsuarioDescargaContenidoEntity e : listaDescargasPendientes)
				comentariosPendientes.add(DataTypesFactory.getComentarioDataType(e));
		}	
		return comentariosPendientes;
	}
	
	public List<ComentarioDataType> obtenerComentariosDeContenido(Integer idContenido){
		List<ComentarioDataType> comentariosContenido = new ArrayList<>();
		
		List<UsuarioDescargaContenidoEntity> listaDescargasContenido = usuarioDescargaContenidoDao.getContentDonwloadsByState(idContenido, EnumEstadosDescarga.VALIDADO_OK);
		if (listaDescargasContenido != null){
			for (UsuarioDescargaContenidoEntity e : listaDescargasContenido)
				comentariosContenido.add(DataTypesFactory.getComentarioDataType(e));
		}	
		return comentariosContenido;
	}
	
	public void actualizarDatosDescargas(){
		
		List<UsuarioDescargaContenidoEntity> descargas = usuarioDescargaContenidoDao.getDonwloadsByState(EnumEstadosDescarga.VALORACION_NO_HABILITADA);
		if (descargas != null){
			
			ParametroValorEntity horasValoracionNoHabilitada = parametroValorDao.findByID(EnumParametrosValor.HORAS_VALORACION_NO_HABILITADA);
			TimeUtils tu = new TimeUtils();
			Date ahora = new Date();
			
			for (UsuarioDescargaContenidoEntity e : descargas){
				Date fechaComienzo = tu.addTimeToDate(e.getFechaDescarga(), Integer.valueOf(horasValoracionNoHabilitada.getValorParametro()), Calendar.HOUR);
				if (fechaComienzo.before(ahora)){
					e.setEstadoDescarga(EnumEstadosDescarga.VALORACION_HABILITADA);
					usuarioDescargaContenidoDao.merge(e);
				}
			}
		}
		
	}
	
	public List<DescargaDataType> obtenerDescargasACalificar(String usuario){
		List<DescargaDataType> listaDescargasACalificar = new ArrayList<>();
		
		List<UsuarioDescargaContenidoEntity> descargas = usuarioDescargaContenidoDao.getDonwloadsByUserAndState(usuario, EnumEstadosDescarga.VALORACION_HABILITADA);
		if (descargas != null){
			for (UsuarioDescargaContenidoEntity d : descargas)
				DataTypesFactory.getDescargaDataType(d);
		}
		
		return listaDescargasACalificar;
	}
	
	public Integer ingresarNuevoContendo(ContenidoIngresoDataType dt, String usuario) throws Exception {
		
		ContenidoEntity nC = null;
		String directorioTipoContenido = "";
		
		/* CARGO DATOS DEL CONTENIDO */
		if (dt.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_LIBRO)){
			nC = new ContenidoLibroEntity();
			
			
			directorioTipoContenido = FileSystemUtils.DIRECTORIO_CONTENIDO_LIBRO;
			nC.setTipoContenido(EnumTiposContenido.TIPO_CONTENIDO_LIBRO);
		} else if (dt.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_SOFTWARE)){
			nC = new ContenidoSoftwareEntity();
			((ContenidoSoftwareEntity)nC).setEsTrial(dt.getEsTrial().equals("on") ? true : false);
			((ContenidoSoftwareEntity)nC).setRequisitosMinimos(dt.getRequisitosMinimos());
			
			directorioTipoContenido = FileSystemUtils.DIRECTORIO_CONTENIDO_SOFTWARE;
			nC.setTipoContenido(EnumTiposContenido.TIPO_CONTENIDO_SOFTWARE);
		} else if (dt.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_TEMA)){
			nC = new ContenidoTemaMusicalEntity();
			((ContenidoTemaMusicalEntity)nC).setAlbumTema(dt.getAlbumTema());
			((ContenidoTemaMusicalEntity)nC).setArtistaTema(dt.getArtistaTema());
			
			
			directorioTipoContenido = FileSystemUtils.DIRECTORIO_CONTENIDO_TEMA;
			nC.setTipoContenido(EnumTiposContenido.TIPO_CONTENIDO_TEMA);
		} else if (dt.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_VIDEO)){
			nC = new ContenidoVideoEntity();
			((ContenidoVideoEntity)nC).setCalidadVideo(dt.getCalidadVideo());
			((ContenidoVideoEntity)nC).setDuracionVideo(dt.getDuracionVideo());
			
			directorioTipoContenido = FileSystemUtils.DIRECTORIO_CONTENIDO_VIDEO;
			nC.setTipoContenido(EnumTiposContenido.TIPO_CONTENIDO_VIDEO);
		}
		
		nC.setCalificacion(0);
		nC.setCantidadDescargas(0);
		nC.setDescripcion(dt.getDescripcion());
		nC.setNombre(dt.getNombre());
		nC.setPrecio(Float.valueOf(/*dt.getPrecio()*/"5.0"));
		nC.setVersion("1.0");		
		
		String nuevoDirectorio = (new RandomString(10)).nextString();
		String dirCont = fSU.crearDirectorioContenido(usuario, directorioTipoContenido, nuevoDirectorio);
		String tmpDir = fSU.obtenerDirectorioUsuario(FileSystemUtils.DIRECTORIO_USUARIOS_PROVEEDORES, FileSystemUtils.DIRECTORIO_TEMPORALES, usuario);
				
		nC.setTamanio(fSU.getFileSize(tmpDir + "/" + dt.getSource()));	
				
		String rFrom = tmpDir + "/" + dt.getSource();
		String rTo = dirCont + "/" + dt.getSource();
		try { 
			fSU.copyFile(rFrom, rTo);
			fSU.deleteFile(rFrom);
		} catch (Exception e) { 
			logger.error(e.getMessage() , e); 
		}
		nC.setRutaArchivoContenido(CripterDecripter.encrypt(rTo)); 
		
		/* AGREGO LAS FOTOS DEL CONTENIDO */
		if (dt.getImagenes() != null) {	
			for (int x = 0; x < dt.getImagenes().length ; x++){
				rFrom = tmpDir + "/" + dt.getImagenes()[x];
				rTo = dirCont + "/" + "foto_" + x;
				try { 
					fSU.copyFile(rFrom, rTo);
					fSU.deleteFile(rFrom);	
				} catch (Exception e) { 
					logger.error(e.getMessage() , e); 
				}
				ContenidoFotoEntity cf = new ContenidoFotoEntity();
				cf.setContenido(nC);
				cf.setUrlFoto(CripterDecripter.encrypt(rTo));
				nC.getFotos().add(cf);
			}
		}	
		
		/*AGREGO LA VERSION DEL CONTENIDO Y QUEDA PENDIENTE A APROBAR*/
		
		VersionContenidoEntity vC = new VersionContenidoEntity();
		vC.setEstadoVersion(EnumEstadosVersionContenido.PENDIENTE_REVISION);
		vC.setFechaSubida(new Date());
		vC.setDescripcion("X");
		vC.setContenido(nC);
		nC.getVersiones().add(vC);		
		
		//contenidoDao.persist(nC);
		
		/* AGREGO LA CLASE ASOCIATIVA USUARIO-SUBE-CONTENIDO */
		UsuarioSubeContenidoEntity usc = new UsuarioSubeContenidoEntity();
		usc.setFechaSubida(new Date());
		usc.setContenido(nC);
		usc.setPrecioSubida(Float.valueOf("0.0"));
		UsuarioEntity ue = usuarioDao.findByID(usuario);
		if (ue == null)
			throw new Exception("Error al recuperar usuario.");
		usc.setUsuarioCliente(ue);
		
		usuarioSubeContenidoDao.persist(usc);
		return nC.getId();
	}
	
	public List<AprobarContenidoDataType> obtenerContenidosAAprobar(){
		List<AprobarContenidoDataType> listaContenidos = new ArrayList<AprobarContenidoDataType>();
		
		List<ContenidoEntity> listaContenidosE = contenidoDao.findAll();
		if (listaContenidosE != null){
			for (ContenidoEntity c : listaContenidosE){
				if (c.tieneVersionConEstado(EnumEstadosVersionContenido.PENDIENTE_REVISION))
					listaContenidos.add(DataTypesFactory.getAprobarContenidoDataType(c));
				
			}
		}
		return listaContenidos;
	}
	
	public void cambiarEstadoVersion(Integer idVersion, String estado) throws Exception {
		
		VersionContenidoEntity v = versionContenidoDao.findByID(idVersion);
		v.setEstadoVersion(estado);
		versionContenidoDao.merge(v);
		
	}
	
	public void registrarDescaraContenido(Integer idContenido, String usuario) throws Exception {
		
		ContenidoEntity c = contenidoDao.findByID(idContenido);
		UsuarioEntity u = usuarioDao.findByID(usuario);
		
		Boolean correspondeRegistro = false;
		UsuarioDescargaContenidoEntity udcCheck = usuarioDescargaContenidoDao.getDownloadByUserAndContent(usuario, idContenido);
		if (udcCheck == null){
			correspondeRegistro = true;
		} else if (udcCheck.getVersionContenido().getId() != c.obtenerVersionConEstado(EnumEstadosVersionContenido.APROBADA).getId())
			correspondeRegistro = true;
			
		if (correspondeRegistro) {
			
			UsuarioDescargaContenidoEntity udc = new UsuarioDescargaContenidoEntity();
			udc.setVersionContenido(c.obtenerVersionConEstado(EnumEstadosVersionContenido.APROBADA));
			udc.setCalificacionDescarga(null);
			udc.setContenido(c);
			udc.setDescripcionValoracion("");
			udc.setEstadoDescarga(EnumEstadosDescarga.VALORACION_NO_HABILITADA);
			udc.setFechaDescarga(new Date());
			udc.setUsuarioCliente(u);
			c.getBajadas().add(udc);
			
			usuarioDescargaContenidoDao.persist(udc);
		}
		
	}
	
}
