package tecinf.servicios.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioCategoriaContenido;
import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.GenericJsonResponse;
import tecinf.negocio.dtos.ItemGenericoDataType;
import tecinf.negocio.utiles.EnumRespuestas;
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
	@Path("/obtenerCategoriasYSubcategorias")
	@Produces("application/json")
	public List<CategoriaContenidoDataType> obtenerCategoriasYSubcategorias() {
		List<CategoriaContenidoDataType> listaCategorias = null;
		try {
			
			listaCategorias = negocioCategoriaContenido.obtenerCategoriasYSubcategorias();
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaCategorias;
	}
	
	@POST
	@Path("/ingresarCategoria")
	@Produces("application/json")
	public GenericJsonResponse ingresarCategoria(ItemGenericoDataType dt) {
		GenericJsonResponse resp = new GenericJsonResponse();
		try {			
			Integer id = negocioCategoriaContenido.ingresarCategoria(dt);			
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_OK);
			resp.setIdObjeto(String.valueOf(id));
		} catch (Exception e) {
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_FALLA);
			resp.setMensageOperacion(e.getMessage());
			logger.error(e.getMessage() , e); 
		}
		return resp;
	}
	
	@POST
	@Path("/ingresarSubCategoria")
	@Produces("application/json")
	public GenericJsonResponse ingresarSubCategoria(ItemGenericoDataType dt) {
		GenericJsonResponse resp = new GenericJsonResponse();
		try {			
			Integer id = negocioCategoriaContenido.ingresarSubCategoria(dt);			
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_OK);
			resp.setIdObjeto(String.valueOf(id));
		} catch (Exception e) {
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_FALLA);
			resp.setMensageOperacion(e.getMessage());
			logger.error(e.getMessage() , e); 
		}
		return resp;
	}
	
	@POST
	@Path("/modificarCategoria")
	@Produces("application/json")
	public GenericJsonResponse modificarCategoria(ItemGenericoDataType dt) {
		GenericJsonResponse resp = new GenericJsonResponse();
		try {			
			Integer id = negocioCategoriaContenido.modificarCategoria(dt);			
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_OK);
			resp.setIdObjeto(String.valueOf(id));
		} catch (Exception e) {
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_FALLA);
			resp.setMensageOperacion(e.getMessage());
			logger.error(e.getMessage() , e); 
		}
		return resp;
	}
	
	@POST
	@Path("/modificarSubCategoria")
	@Produces("application/json")
	public GenericJsonResponse modificarSubCategoria(ItemGenericoDataType dt) {
		GenericJsonResponse resp = new GenericJsonResponse();
		try {			
			Integer id = negocioCategoriaContenido.modificarSubCategoria(dt);			
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_OK);
			resp.setIdObjeto(String.valueOf(id));
		} catch (Exception e) {
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_FALLA);
			resp.setMensageOperacion(e.getMessage());
			logger.error(e.getMessage() , e); 
		}
		return resp;
	}
	
}
