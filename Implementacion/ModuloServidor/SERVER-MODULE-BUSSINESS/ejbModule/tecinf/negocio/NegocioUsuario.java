package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.UsuarioDataType;

@Local
public interface NegocioUsuario {
	
	public List<UsuarioDataType> obtenerTodos();
	
	public void modificarUsuario(UsuarioDataType u) throws Exception;
	
}
