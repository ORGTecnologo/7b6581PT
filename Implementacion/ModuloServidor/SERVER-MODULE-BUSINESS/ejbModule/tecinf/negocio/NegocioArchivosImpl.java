package tecinf.negocio;

import java.io.File;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.utiles.CripterDecripter;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioArchivosImpl implements NegocioArchivos {
	
	private ParametroValorDao parametroValorDao = null;
	
	public NegocioArchivosImpl() throws NamingException { 
			
		parametroValorDao = PersistenciaFactory.getParametroValorDao();
		
	}
	
	public File responderImagen(String ruta) throws Exception {
		
		String rutaDesencriptada = CripterDecripter.decrypt(ruta);
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
		
		return new File(rutaBase.getValorParametro() + rutaDesencriptada);
	}

}
