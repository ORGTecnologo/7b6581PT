package tecinf.negocio;

import javax.ejb.Local;

@Local
public interface NegocioParametros {
	
	public String obtenerParametroPorNombre(String nombre);
	
}
