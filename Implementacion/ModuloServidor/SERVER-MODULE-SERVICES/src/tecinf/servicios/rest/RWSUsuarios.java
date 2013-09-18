package tecinf.servicios.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.servicios.utiles.CustomJsonResponse;
import tecinf.servicios.utiles.JSonUtils;


@Path("/usuarios")
public class RWSUsuarios {
	
	
	@GET
	@Path("/listarUsuarios")
	@Produces("application/json")
	public List<UsuarioDataType> obtenerTodos() throws NamingException {
 
		
		NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
		List<UsuarioDataType> listaUsuarios = negocioUsuario.obtenerTodos();
 
		return listaUsuarios; 
 
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
