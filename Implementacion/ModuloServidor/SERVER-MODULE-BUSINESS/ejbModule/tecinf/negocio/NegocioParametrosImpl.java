package tecinf.negocio;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioParametrosImpl implements NegocioParametros {

	private ParametroValorDao parametroDao;
	
	public NegocioParametrosImpl() throws NamingException { 
		
		parametroDao = PersistenciaFactory.getParametroValorDao();
		
	}
	
	@Override
	public String obtenerParametroPorNombre(String nombre) {
		return parametroDao.findByID(nombre).getValorParametro();		
	}

}
