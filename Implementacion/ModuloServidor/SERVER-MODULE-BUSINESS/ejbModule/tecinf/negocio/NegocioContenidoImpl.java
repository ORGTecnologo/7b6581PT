package tecinf.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ListaFiltrosDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.persistencia.daos.ContenidoDao;
import tecinf.persistencia.daos.ContenidoFotoDao;
import tecinf.persistencia.daos.UsuarioDescargaContenidoDao;
import tecinf.persistencia.entities.ContenidoEntity;
import tecinf.persistencia.entities.ContenidoFotoEntity;
import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioContenidoImpl implements NegocioContenido {
	
	private ContenidoDao contenidoDao = null;
	private ContenidoFotoDao contenidoFotosDao = null;
	private UsuarioDescargaContenidoDao usuarioDescargaContenidoDao = null;
	
	public NegocioContenidoImpl() throws NamingException { 
		
		contenidoDao = PersistenciaFactory.getContenidoDao();
		contenidoFotosDao = PersistenciaFactory.getContenidoFotoDao();
		usuarioDescargaContenidoDao = PersistenciaFactory.getUsuarioDescargaContenidoDao();
		
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
		
		return new ArrayList<>();
	}
	
	public List<ComentarioDataType> obtenerComentariosDeContenido(){
		
		
		return new ArrayList<>();
	}
	
}
