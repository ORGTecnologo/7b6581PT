package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.AprobarContenidoDataType;
import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoIngresoDataType;
import tecinf.negocio.dtos.ContenidoMinimalDataType;
import tecinf.negocio.dtos.DescargaDataType;
import tecinf.negocio.dtos.FiltrosContenidoDataType;
@Local
public interface NegocioContenido {

	public ContenidoDataType obtenerDatosContenido(int idContenido);	
	
	public void actualizarDatosContenidos();
	
	public void actualizarDatosDescargas();
	
	public List<ContenidoMinimalDataType> filtrarContenidos(FiltrosContenidoDataType filtros);
	
	public List<ComentarioDataType> obtenerComentariosAAprobar();
	
	public List<ComentarioDataType> obtenerComentariosDeContenido(Integer idContenido);
	
	public List<DescargaDataType> obtenerDescargasACalificar(String usuario);
	
	public List<AprobarContenidoDataType> obtenerContenidosAAprobar();
	
	public Integer ingresarNuevoContendo(ContenidoIngresoDataType dt, String usuario) throws Exception;
	
	public void cambiarEstadoVersion(Integer idVersion, String estado) throws Exception;
	
}
