package tecinf.servicios.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioContenido;
import tecinf.negocio.dtos.IContenidoDataType;
import tecinf.negocio.utiles.NegocioFactory;

@Path("/contenidos")
public class RWSContenidos {

	private static Logger logger = Logger.getLogger(RWSContenidos.class);
	private NegocioContenido negocioContenido = null;
	
	public RWSContenidos(){
		
		try {
			negocioContenido = NegocioFactory.getNegocioContenido();
		} catch (NamingException e) {
			logger.error(e.getMessage() , e);
		}
		
	}
	
	
	@GET
	@Path("/listarTopTenDescargas")
	@Produces("application/json")
	public List<IContenidoDataType> obtenerTopTenPorDescargas() {
		List<IContenidoDataType> listaContenidos = null;
		try {
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaContenidos;
	}
	
}
