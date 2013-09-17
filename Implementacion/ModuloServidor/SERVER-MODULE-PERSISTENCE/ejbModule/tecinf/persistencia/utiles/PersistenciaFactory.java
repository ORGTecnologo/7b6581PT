package tecinf.persistencia.utiles;

import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.daos.UsuarioDaoImpl;

public class PersistenciaFactory {
	
	
	public static UsuarioDao getUsuarioDao(){
		return new UsuarioDaoImpl();
	}
	
	
}
