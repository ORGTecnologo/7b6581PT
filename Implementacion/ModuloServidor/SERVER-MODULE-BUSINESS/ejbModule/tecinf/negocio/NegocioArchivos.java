package tecinf.negocio;

import java.io.File;

import javax.ejb.Local;

import tecinf.negocio.dtos.FileDataType;

@Local
public interface NegocioArchivos {
	
		
	public File responderImagen(String ruta) throws Exception;
	
	public FileDataType responderArchivo(String ruta) throws Exception;
	
	
}
