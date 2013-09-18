package tecinf.servicios.rest;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.servicios.utiles.CustomJsonResponse;


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
	
	/*
	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response rsWsGestionUsuarios(List<Usuario> usuarios){
		
		String result = "Usuarios : " + usuarios;
		return Response.status(201).entity(result).build();
		
	}
	*/
	
	@PUT
	@Path("/modificarUsuario")
	@Produces("application/json")
	public CustomJsonResponse modificarUsuario(){
		CustomJsonResponse resp = new CustomJsonResponse();
		
		
		
		return resp;
	}
	
	
}
