package tecinf.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoIngresoDataType;
import tecinf.negocio.dtos.DescargaDataType;
import tecinf.negocio.dtos.ListaFiltrosDataType;
import tecinf.negocio.utiles.CripterDecripter;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.negocio.utiles.EnumEstadosDescarga;
import tecinf.negocio.utiles.EnumEstadosVersionContenido;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.FileSystemUtils;
import tecinf.negocio.utiles.RandomString;
import tecinf.negocio.utiles.TimeUtils;
import tecinf.persistencia.daos.ContenidoDao;
import tecinf.persistencia.daos.ContenidoFotoDao;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.daos.UsuarioDescargaContenidoDao;
import tecinf.persistencia.daos.UsuarioSubeContenidoDao;
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
	private ContenidoFotoDao contenidoFotosDao = null;
	private ParametroValorDao parametroValorDao = null;
	private UsuarioDescargaContenidoDao usuarioDescargaContenidoDao = null;
	private UsuarioSubeContenidoDao usuarioSubeContenidoDao = null;
	private UsuarioDao usuarioDao = null;
	
	private FileSystemUtils fSU = new FileSystemUtils();
	
	public NegocioContenidoImpl() throws NamingException { 
		
		contenidoDao = PersistenciaFactory.getContenidoDao();
		contenidoFotosDao = PersistenciaFactory.getContenidoFotoDao();
		usuarioDescargaContenidoDao = PersistenciaFactory.getUsuarioDescargaContenidoDao();
		parametroValorDao = PersistenciaFactory.getParametroValorDao();
		usuarioDao = PersistenciaFactory.getUsuarioDao();
		usuarioSubeContenidoDao = PersistenciaFactory.getUsuarioSubeContenidoDao();
		
	}
	
	public ContenidoDataType obtenerDatosContenido(int idContenido) {
		ContenidoDataType contenido = null;		
		ContenidoEntity cont = contenidoDao.findByID(idContenido);
		if (cont != null){
			List<ContenidoFotoEntity> fotos = contenidoFotosDao.getAllByContenido(idContenido);		
			contenido = DataTypesFactory.getContenidoDataType(cont, fotos);
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
	
	public List<ContenidoDataType> filtrarContenidos(ListaFiltrosDataType filtros) {
		
		
		
		
		return new ArrayList<ContenidoDataType>();
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
		
		List<UsuarioDescargaContenidoEntity> descargas = usuarioDescargaContenidoDao.getDonwloadsByUserAndState(usuario, EnumEstadosDescarga.VALORACION_NO_HABILITADA);
			
		
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
		
		nC.setRutaArchivoContenido(CripterDecripter.encrypt(nuevoDirectorio)); 
		nC.setTamanio(fSU.getFileSize(tmpDir + "/" + dt.getSource()));	
				
		String rFrom = tmpDir + "/" + dt.getSource();
		String rTo = dirCont + "/" + dt.getSource();
		try { 
			fSU.copyFile(rFrom, rTo);
			fSU.deleteFile(rFrom);
		} catch (Exception e) { 
			logger.error(e.getMessage() , e); 
		}
		
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
		
		/* AGREGO LA CLASE ASOCIATIVA USUARIO-SUBE-CONTENIDO */
		UsuarioSubeContenidoEntity usc = new UsuarioSubeContenidoEntity();
		usc.setFechaSubida(new Date());
		usc.setContenido(nC);
		usc.setPrecioSubida(/*dt.getPrecio()*/Float.valueOf("0.0"));
		UsuarioEntity ue = usuarioDao.findByID(usuario);
		if (ue == null)
			throw new Exception("Error al recuperar usuario.");
		usc.setUsuarioCliente(ue);
		
		/*AGREGO LA VERSION DEL CONTENIDO Y QUEDA PENDIENTE A APROBAR*/
		VersionContenidoEntity vC = new VersionContenidoEntity();
		vC.setEstadoVersion(EnumEstadosVersionContenido.PENDIENTE_REVISION);
		vC.setFechaSubida(new Date());
		vC.setDescripcion("");
		nC.getVersiones().add(vC);
		
		usuarioSubeContenidoDao.persist(usc);
		contenidoDao.persist(nC);
		return nC.getId();
	}
	
}
