package tecinf.negocio;

import java.io.File;

import javax.ejb.Stateless;

@Stateless
public class NegocioArchivosImpl implements NegocioArchivos {
	
	public NegocioArchivosImpl(){
				
	}
	
	public File responderImagen(String ruta) throws Exception {
		
		return new File(ruta);		
	}

}
