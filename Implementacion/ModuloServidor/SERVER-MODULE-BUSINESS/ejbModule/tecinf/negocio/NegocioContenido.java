package tecinf.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tecinf.negocio.dtos.AprobarContenidoDataType;
import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoIngresoDataType;
import tecinf.negocio.dtos.ContenidoMinimalDataType;
import tecinf.negocio.dtos.DescargaDataType;
import tecinf.negocio.dtos.FiltrosContenidoDataType;
import tecinf.negocio.dtos.ReclamoDataType;
@Local
public interface NegocioContenido {

	public ContenidoDataType obtenerDatosContenido(int idContenido, Boolean fullPrivileges);	
	
	public void actualizarDatosContenidos();
	
	public void actualizarDatosDescargas();
	
	public List<ContenidoMinimalDataType> filtrarContenidos(FiltrosContenidoDataType filtros);
	
	public List<ContenidoMinimalDataType> obtenerTopContenidos(Integer cantidad, String tipo) throws Exception;
	
	public List<ComentarioDataType> obtenerComentariosAAprobar();
	
	public List<ComentarioDataType> obtenerComentariosDeContenido(Integer idContenido);
	
	public List<DescargaDataType> obtenerDescargasACalificar(String usuario);
	
	public List<DescargaDataType> obtenerTodasLasDescargas(String usuario);
	
	public List<AprobarContenidoDataType> obtenerContenidosAAprobar(@SuppressWarnings("rawtypes") Map filtros);
	
	public Integer ingresarNuevoContendo(ContenidoIngresoDataType dt, String usuario) throws Exception;
	
	public void cambiarEstadoVersion(Integer idVersion, String estado) throws Exception;
	
	public void registrarDescaraContenido(Integer idContenido, String usuario) throws Exception;
	
	public void calificarDescaraContenido(DescargaDataType dt, String usuario) throws Exception;
	
	public void registrarReclamo(ReclamoDataType dt) throws Exception;
	
	public List<ReclamoDataType> obtenerReclamosPendientes();
	
	public void resolverReclamo(ReclamoDataType dt) throws Exception;
	
}
