package tecinf.servicios.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import tecinf.negocio.dtos.IContenidoDataType;

@Path("/contenidos")
public class RWSContenidos {


	@GET
	@Path("/listarTopTenDescargas")
	@Produces("application/json")
	public List<IContenidoDataType> obtenerTopTenPorDescargas() {
		List<IContenidoDataType> listaContenidos = null;
		try {
			
		} catch (Exception e) {
			
		}
		return listaContenidos;
	}
	
}
