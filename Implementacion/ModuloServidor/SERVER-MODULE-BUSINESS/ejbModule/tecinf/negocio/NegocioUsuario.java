package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.LoginRespDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;

@Local
public interface NegocioUsuario {
	
	public List<UsuarioClienteDataType> obtenerTodosClientes();
	
	public void modificarUsuario(UsuarioDataType u) throws Exception;
	
	public LoginRespDataType loginUsuario(String usuario, String contrasenia);
	
	public Boolean loginUsuarioAdmin(String usuario, String contrasenia);
	
	public void registroUsuarioCliente(UsuarioClienteDataType dt) throws Exception;
	
	public Boolean existeUsuario(String usr); 
	
	public Boolean existeUsuarioPorMail(String mail);
	
}

