package tecinf.persistencia.utiles;

import java.util.Hashtable;

import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.daos.ParametroValorDaoImpl;
import tecinf.persistencia.daos.UsuarioClienteDao;
import tecinf.persistencia.daos.UsuarioClienteDaoImpl;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.daos.UsuarioDaoImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PersistenciaFactory {
	
	
	public static UsuarioDao getUsuarioDao() throws NamingException{
		return (UsuarioDao) lookup(UsuarioDaoImpl.class.getSimpleName(),UsuarioDao.class.getName());
	}
	
	public static UsuarioClienteDao getUsuarioClienteDao() throws NamingException{
		return (UsuarioClienteDao) lookup(UsuarioClienteDaoImpl.class.getSimpleName(),UsuarioClienteDao.class.getName());
	}
	
	public static ParametroValorDao getParametroValorDao() throws NamingException{
		return (ParametroValorDao) lookup(ParametroValorDaoImpl.class.getSimpleName(),ParametroValorDao.class.getName());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object lookup(String beanName, String viewClassName) throws NamingException{
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);		 
		final String appName = "SERVER-MODULE";		 
		final String moduleName = "SERVER-MODULE-PERSISTENCE";		 
		final String distinctName = "";	
		return context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
	
}
