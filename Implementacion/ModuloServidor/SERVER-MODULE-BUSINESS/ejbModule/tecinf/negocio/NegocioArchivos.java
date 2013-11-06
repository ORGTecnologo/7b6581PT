package tecinf.negocio;

import java.io.File;

import javax.ejb.Local;

@Local
public interface NegocioArchivos {
	
		
	public File responderImagen(String ruta) throws Exception;
	
	
}
