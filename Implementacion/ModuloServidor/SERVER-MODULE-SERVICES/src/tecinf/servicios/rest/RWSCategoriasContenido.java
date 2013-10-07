package tecinf.servicios.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioCategoriaContenido;
import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.utiles.NegocioFactory;

@Path("/categoriasContenido")
public class RWSCategoriasContenido {
	
	private static Logger logger = Logger.getLogger(RWSCategoriasContenido.class);
	private NegocioCategoriaContenido negocioCategoriaContenido = null;
	
	public RWSCategoriasContenido(){
		
		try {
			negocioCategoriaContenido = NegocioFactory.getNegocioCategoriaContenido();
		} catch (NamingException e) {
			logger.error(e.getMessage() , e); 
		}
		
	}
	
	@GET
	@Path("/listarTopTenDescargas")
	@Produces("application/json")
	public List<CategoriaContenidoDataType> obtenerTopTenPorDescargas() {
		List<CategoriaContenidoDataType> listaCategorias = null;
		try {
			
			listaCategorias = negocioCategoriaContenido.obtenerCategoriasYSubcategorias();
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaCategorias;
	}
	
	
	
	
	
	
}
