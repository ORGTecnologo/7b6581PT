package tecinf.persistencia.utiles;

import java.util.Hashtable;

import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.daos.UsuarioDaoImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PersistenciaFactory {
	
	
	public static UsuarioDao getUsuarioDao() throws NamingException{
		return (UsuarioDao) lookup(UsuarioDaoImpl.class.getSimpleName(),UsuarioDao.class.getName());
	}
	
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
