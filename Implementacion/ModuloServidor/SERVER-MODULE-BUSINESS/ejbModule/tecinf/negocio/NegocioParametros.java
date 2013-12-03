package tecinf.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tecinf.negocio.dtos.ParametroValorDataType;

@Local
public interface NegocioParametros {
	
	public String obtenerParametroPorNombre(String nombre);
	
	public List<ParametroValorDataType> obtenerTodos();
	
	public List<ParametroValorDataType> obtenerTodosPorFiltros(Map filtros);
	
	public void actualizarParametro(ParametroValorDataType dt);
	
}
