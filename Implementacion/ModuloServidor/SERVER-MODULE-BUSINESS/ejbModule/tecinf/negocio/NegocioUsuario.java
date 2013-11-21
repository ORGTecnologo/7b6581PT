package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.LoginRespDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.dtos.UsuarioProveedorDataType;

@Local
public interface NegocioUsuario {
	
	public List<UsuarioClienteDataType> obtenerTodosClientes();
	
	public void modificarUsuario(UsuarioDataType u) throws Exception;
	
	public LoginRespDataType loginUsuario(String usuario, String contrasenia);
	
	public Boolean loginUsuarioAdmin(String usuario, String contrasenia);
	
	public LoginRespDataType registroUsuarioCliente(UsuarioClienteDataType dt) throws Exception;
	
	public LoginRespDataType registroUsuarioProveedor(UsuarioProveedorDataType dt) throws Exception;
	
	public Boolean existeUsuario(String usr); 
	
	public Boolean existeUsuarioPorMail(String mail);
	
	public void executeSessionsRefresh();
	
	public Boolean checkUserSession(String usuario, String token);
	
	public Boolean logout(String usuario, String token);
	
	public List<UsuarioDataType> buscarPorFiltros(String tipoUsuario, String nick, String email);
	
	public void cambiarEstadoUsuario(UsuarioDataType u) throws Exception;
	
}

