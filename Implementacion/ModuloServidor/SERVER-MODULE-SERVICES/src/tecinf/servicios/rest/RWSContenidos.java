package tecinf.servicios.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioContenido;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.IContenidoDataType;
import tecinf.negocio.dtos.ListaFiltrosDataType;
import tecinf.negocio.utiles.NegocioFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonParser;

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
	
	@GET
	@Path("/obtenerInfoContenido/{idContenido}")
	@Produces("application/json")
	public ContenidoDataType obtenerInfoContenido(@PathParam("idContenido") String idContenido) {
		ContenidoDataType cont = null;
		try {
			
			cont = negocioContenido.obtenerDatosContenido(Integer.valueOf(idContenido));
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return cont;
	}
	
	@GET
	@Path("/obtenerInfoContenido/{filtros}")
	@Produces("application/json")
	public List<ContenidoDataType> filtrarContenidos(@QueryParam("filtros") String filtros) {
		List<ContenidoDataType> listaContenidos = null;
		try {
			
			ObjectMapper mapper = new ObjectMapper();			
			ListaFiltrosDataType listaFiltros = mapper.readValue(filtros, ListaFiltrosDataType.class); 			
			listaContenidos = negocioContenido.filtrarContenidos(listaFiltros);
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaContenidos;
	}
	
}
