package tecinf.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.persistencia.daos.ContenidoDao;
import tecinf.persistencia.daos.ContenidoFotoDao;
import tecinf.persistencia.entities.ContenidoEntity;
import tecinf.persistencia.entities.ContenidoFotoEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioContenidoImpl implements NegocioContenido {
	
	private ContenidoDao contenidoDao = null;
	private ContenidoFotoDao contenidoFotosDao = null;
	
	public NegocioContenidoImpl() throws NamingException { 
		
		contenidoDao = PersistenciaFactory.getContenidoDao();
		contenidoFotosDao = PersistenciaFactory.getContenidoFotoDao();
		
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
	
	
}
