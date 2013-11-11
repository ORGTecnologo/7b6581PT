package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ListaFiltrosDataType;

@Local
public interface NegocioContenido {

	public ContenidoDataType obtenerDatosContenido(int idContenido);	
	
	public void actualizarDatosContenidos();
	
	public List<ContenidoDataType> filtrarContenidos(ListaFiltrosDataType filtros);
	
}
