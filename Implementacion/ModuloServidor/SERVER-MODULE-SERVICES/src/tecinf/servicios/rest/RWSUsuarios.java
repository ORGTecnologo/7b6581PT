package tecinf.servicios.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.LoginDataType;
import tecinf.negocio.dtos.LoginRespDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.EnumRespuestas;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.servicios.utiles.CustomJsonResponse;
import tecinf.servicios.utiles.JSonUtils;
import tecinf.servicios.utiles.session.Session;
import tecinf.servicios.utiles.session.SessionManager;


@Path("/usuarios")
public class RWSUsuarios {
	
	@GET
	@Path("/listarUsuarios")
	@Produces("application/json")
	public List<UsuarioClienteDataType> obtenerTodos() {
		List<UsuarioClienteDataType> listaUsuarios = null;
		try {
			NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
			listaUsuarios = negocioUsuario.obtenerTodosClientes();
		} catch (Exception e) {
			
		} 
		return listaUsuarios;
 
	}
	
	@GET
	@Path("/listarUsuarios")
	@Produces("application/json")
	@Consumes("application/json")
	public LoginRespDataType login(LoginDataType dt) { 
		LoginRespDataType resp = null;
		try {		
			NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
			resp = negocioUsuario.loginUsuarioCliente(dt.getUsuario(), dt.getContrasenia()); 
			if (resp.getRespuesta().equals(EnumRespuestas.RESPUESTA_OK)){
				SessionManager sm = SessionManager.getInstance();
				Session s = new Session();
				s.setUser(resp.getUsuario());					
				s.setToken(resp.getToken());
				sm.updateTimeStamp(s, SessionManager.timeOut);
				sm.addUserToSession(s);				
			}
		} catch (Exception e){
			
		}
		return resp;
	}
	
	@PUT
	@Path("/modificarUsuario")
	@Produces("application/json")
	@Consumes("application/json")
	public CustomJsonResponse modificarUsuario(UsuarioDataType ud){
		CustomJsonResponse resp = new CustomJsonResponse();
		try {
			
			NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
			negocioUsuario.modificarUsuario(ud); 
			
			resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_EXITO);
		} catch (Exception e) {
			resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_FALLA);
			resp.setMensageOperacion(e.getMessage());
		}
				
		return resp;
	}
	
	
}
