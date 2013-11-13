package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.DescargaDataType;
import tecinf.negocio.dtos.ListaFiltrosDataType;

@Local
public interface NegocioContenido {

	public ContenidoDataType obtenerDatosContenido(int idContenido);	
	
	public void actualizarDatosContenidos();
	
	public void actualizarDatosDescargas();
	
	public List<ContenidoDataType> filtrarContenidos(ListaFiltrosDataType filtros);
	
	public List<ComentarioDataType> obtenerComentariosAAprobar();
	
	public List<ComentarioDataType> obtenerComentariosDeContenido(Integer idContenido);
	
	public List<DescargaDataType> obtenerDescargasACalificar(String usuario);
	
}
