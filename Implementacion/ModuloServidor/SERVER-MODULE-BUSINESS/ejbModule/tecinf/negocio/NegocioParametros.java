package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.ParametroValorDataType;

@Local
public interface NegocioParametros {
	
	public String obtenerParametroPorNombre(String nombre);
	
	public List<ParametroValorDataType> obtenerTodos();
	
	public void actualizarParametro(ParametroValorDataType dt);
	
}
