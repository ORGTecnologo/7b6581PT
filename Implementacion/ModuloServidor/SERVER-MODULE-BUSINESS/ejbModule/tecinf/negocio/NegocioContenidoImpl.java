package tecinf.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ListaFiltrosDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.negocio.utiles.EnumEstadosDescarga;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.TimeUtils;
import tecinf.persistencia.daos.ContenidoDao;
import tecinf.persistencia.daos.ContenidoFotoDao;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.daos.UsuarioDescargaContenidoDao;
import tecinf.persistencia.entities.ContenidoEntity;
import tecinf.persistencia.entities.ContenidoFotoEntity;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioContenidoImpl implements NegocioContenido {
	
	private ContenidoDao contenidoDao = null;
	private ContenidoFotoDao contenidoFotosDao = null;
	private ParametroValorDao parametroValorDao = null;
	private UsuarioDescargaContenidoDao usuarioDescargaContenidoDao = null;
	
	public NegocioContenidoImpl() throws NamingException { 
		
		contenidoDao = PersistenciaFactory.getContenidoDao();
		contenidoFotosDao = PersistenciaFactory.getContenidoFotoDao();
		usuarioDescargaContenidoDao = PersistenciaFactory.getUsuarioDescargaContenidoDao();
		parametroValorDao = PersistenciaFactory.getParametroValorDao();
		
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
	
}
