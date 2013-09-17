package tecinf.negocio.utiles;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.NegocioUsuarioImpl;

public class NegocioFactory {
	
	public static NegocioUsuario getNegocioUsuario(){
		return new NegocioUsuarioImpl();
	}
	
	
}
