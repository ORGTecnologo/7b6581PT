package tecinf.servicios.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@Path("/usuarios")
public class RWSUsuarios {
	
	
	@GET
	@Produces("application/json")
	public String rsWsGestionUsuarios(JsonObject parametros){
		
		Usuario u1 = new Usuario();
		u1.setApellido("perez");
		u1.setNombre("juan");
		u1.setUsuario("jperez");
        
		Usuario u2 = new Usuario();
		u2.setApellido("rodriguez");
		u2.setNombre("mauricio");
		u2.setUsuario("mrodriguez");
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u1);
		usuarios.add(u2);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		gson.toJson(usuarios);
		
		return gson.toString();
	}
	
	
	
	
	
	
}
