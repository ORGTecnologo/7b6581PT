package tecinf.negocio;

import javax.ejb.Local;

import tecinf.negocio.dtos.ContenidoDataType;

@Local
public interface NegocioContenido {

	public ContenidoDataType obtenerDatosContenido(int idContenido);	
	
}
