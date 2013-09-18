package tecinf.negocio.utiles;

import javax.naming.NamingException;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.NegocioUsuarioImpl;

public class NegocioFactory {
	
	public static NegocioUsuario getNegocioUsuario() throws NamingException{
		return new NegocioUsuarioImpl();
	}
	
	
}
