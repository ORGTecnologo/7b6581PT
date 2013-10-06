package tecinf.servicios.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.GenericJsonResponse;
import tecinf.negocio.dtos.LoginDataType;
import tecinf.negocio.dtos.LoginRespDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.EnumRespuestas;
import tecinf.negocio.utiles.NegocioFactory;		
import tecinf.servicios.utiles.JSonUtils;
import tecinf.servicios.utiles.session.Session;
import tecinf.servicios.utiles.session.SessionManager;


@Path("/usuarios")
public class RWSUsuarios {
	
	private static Logger logger = Logger.getLogger(RWSUsuarios.class);
	
	@GET
	@Path("/listarUsuarios")
	@Produces("application/json")
	public List<UsuarioClienteDataType> obtenerTodos() {
		List<UsuarioClienteDataType> listaUsuarios = null;
		try {
			NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
			listaUsuarios = negocioUsuario.obtenerTodosClientes();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		} 
		return listaUsuarios;
 
	}
	
	//@GET
	@POST
	@Path("/loginCliente")
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
			logger.error(e.getMessage() , e);
		}
		return resp;
	}
	
	@GET
	@Path("/existeUsuario")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericJsonResponse existeUsuario(String usr) {
		GenericJsonResponse resp = new GenericJsonResponse();
		try {		
			NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
			if (negocioUsuario.existeUsuario(usr))
				resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_EXITO);
			else
				resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_FALLA);
		} catch (Exception e){
			logger.error(e.getMessage() , e);
		}
		return resp;
	}
	
	@GET
	@Path("/existeUsuarioPorMail")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericJsonResponse existeUsuarioPorMail(String param) { 
		GenericJsonResponse resp = new GenericJsonResponse();
		try {
			//JsonObject obj = (JsonObject)param;
			//String mailStr = mail.get("mail").getAsString();
			NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
			if (negocioUsuario.existeUsuarioPorMail(""))
				resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_EXITO);
			else
				resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_FALLA);
		} catch (Exception e){
			logger.error(e.getMessage() , e);
		}
		return resp;
	}
	
	@PUT
	@Path("/modificarUsuario")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericJsonResponse modificarUsuario(UsuarioDataType ud){
		GenericJsonResponse resp = new GenericJsonResponse();
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
	
	@PUT
	@Path("/registrarUsuario")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericJsonResponse registrarUsuario(UsuarioClienteDataType ud){
		GenericJsonResponse resp = new GenericJsonResponse();
		try {
			
			NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
			negocioUsuario.registroUsuarioCliente(ud); 
			
			resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_EXITO);
		} catch (Exception e) {
			resp.setResultadoOperacion(JSonUtils.RESULTADO_OPERACION_FALLA);
			resp.setMensageOperacion(e.getMessage());
		}				
		return resp;
	}
	
	
}
