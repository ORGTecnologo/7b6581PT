package tecinf.persistencia.utiles;

import java.util.Hashtable;

import tecinf.persistencia.daos.AuditoriaDao;
import tecinf.persistencia.daos.AuditoriaDaoImpl;
import tecinf.persistencia.daos.AuditoriaObjetoDao;
import tecinf.persistencia.daos.AuditoriaObjetoDaoImpl;
import tecinf.persistencia.daos.AuditoriaOperacionDao;
import tecinf.persistencia.daos.AuditoriaOperacionDaoImpl;
import tecinf.persistencia.daos.CategoriaContenidoDao;
import tecinf.persistencia.daos.CategoriaContenidoDaoImpl;
import tecinf.persistencia.daos.EstadoUsuarioDao;
import tecinf.persistencia.daos.EstadoUsuarioDaoImpl;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.daos.ParametroValorDaoImpl;
import tecinf.persistencia.daos.SubCategoriaContenidoDao;
import tecinf.persistencia.daos.SubCategoriaContenidoDaoImpl;
import tecinf.persistencia.daos.TipoRegistroDao;
import tecinf.persistencia.daos.TipoRegistroDaoImpl;
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
	
	public static AuditoriaDao getAuditoriaDao() throws NamingException{
		return (AuditoriaDao) lookup(AuditoriaDaoImpl.class.getSimpleName(),AuditoriaDao.class.getName());
	}
	
	public static AuditoriaObjetoDao getAuditoriaObjetoDao() throws NamingException{
		return (AuditoriaObjetoDao) lookup(AuditoriaObjetoDaoImpl.class.getSimpleName(),AuditoriaObjetoDao.class.getName());
	}
	
	public static AuditoriaOperacionDao getAuditoriaOperacionDao() throws NamingException{
		return (AuditoriaOperacionDao) lookup(AuditoriaOperacionDaoImpl.class.getSimpleName(),AuditoriaOperacionDao.class.getName());
	}
	
	public static EstadoUsuarioDao getEstadoUsuarioDao() throws NamingException{
		return (EstadoUsuarioDao) lookup(EstadoUsuarioDaoImpl.class.getSimpleName(),EstadoUsuarioDao.class.getName());
	}
	
	public static TipoRegistroDao getTipoRegistroDao() throws NamingException{
		return (TipoRegistroDao) lookup(TipoRegistroDaoImpl.class.getSimpleName(),TipoRegistroDao.class.getName());
	}
	
	public static CategoriaContenidoDao getCategoriaContenidoDao() throws NamingException{
		return (CategoriaContenidoDao) lookup(CategoriaContenidoDaoImpl.class.getSimpleName(),CategoriaContenidoDao.class.getName());
	}
	
	public static SubCategoriaContenidoDao getSubCategoriaContenidoDao() throws NamingException{
		return (SubCategoriaContenidoDao) lookup(SubCategoriaContenidoDaoImpl.class.getSimpleName(),SubCategoriaContenidoDao.class.getName());
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
