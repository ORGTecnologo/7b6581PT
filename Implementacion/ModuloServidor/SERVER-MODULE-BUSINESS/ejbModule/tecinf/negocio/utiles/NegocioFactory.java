package tecinf.negocio.utiles;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tecinf.negocio.NegocioCategoriaContenido;
import tecinf.negocio.NegocioCategoriaContenidoImpl;
import tecinf.negocio.NegocioContenido;
import tecinf.negocio.NegocioContenidoImpl;
import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.NegocioUsuarioImpl;

public class NegocioFactory {
	
	public static NegocioUsuario getNegocioUsuario() throws NamingException{
		return (NegocioUsuario)lookup(NegocioUsuarioImpl.class.getSimpleName(), NegocioUsuario.class.getName());
	}
	
	public static NegocioCategoriaContenido getNegocioCategoriaContenido() throws NamingException{
		return (NegocioCategoriaContenido)lookup(NegocioCategoriaContenidoImpl.class.getSimpleName(), NegocioCategoriaContenido.class.getName());
	}
	
	public static NegocioContenido getNegocioContenido() throws NamingException{
		return (NegocioContenido)lookup(NegocioContenidoImpl.class.getSimpleName(), NegocioContenido.class.getName());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object lookup(String beanName, String viewClassName) throws NamingException{		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);		 
		final String appName = "SERVER-MODULE";		 
		final String moduleName = "SERVER-MODULE-BUSSINESS";		 
		final String distinctName = "";	
		return context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}
	
}
