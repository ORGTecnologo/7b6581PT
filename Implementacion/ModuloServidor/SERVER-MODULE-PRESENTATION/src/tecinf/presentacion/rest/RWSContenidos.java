package tecinf.presentacion.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioContenido;
import tecinf.negocio.dtos.ComentarioDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoIngresoDataType;
import tecinf.negocio.dtos.ContenidoMinimalDataType;
import tecinf.negocio.dtos.DescargaDataType;
import tecinf.negocio.dtos.FiltrosContenidoDataType;
import tecinf.negocio.dtos.GenericJsonResponse;
import tecinf.negocio.dtos.IContenidoDataType;
import tecinf.negocio.dtos.UserSession;
import tecinf.negocio.utiles.EnumRespuestas;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.presentacion.utiles.ConstantesSession;
import tecinf.presentacion.utiles.RightsChecker;

import org.codehaus.jackson.map.ObjectMapper;

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
	@Path("/filtrarContenidos/{filtros}")
	@Produces("application/json")
	public List<ContenidoMinimalDataType> filtrarContenidos(@PathParam("filtros") String filtros) {
		List<ContenidoMinimalDataType> listaContenidos = null;
		try {
			
			ObjectMapper mapper = new ObjectMapper();			
			FiltrosContenidoDataType filtrosDt = mapper.readValue(filtros, FiltrosContenidoDataType.class); 			
			listaContenidos = negocioContenido.filtrarContenidos(filtrosDt);
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaContenidos;
	}
	
	@GET
	@Path("/obtenerTopContenidos")
	@Produces("application/json")
	public List<ContenidoMinimalDataType> obtenerTopContenidos(@QueryParam("cantidad") String cantidadContenidos, @QueryParam("tipo") String tipoContenido) {
		List<ContenidoMinimalDataType> listaContenidos = null;
		try {
					
			listaContenidos = negocioContenido.obtenerTopContenidos(Integer.valueOf(cantidadContenidos), tipoContenido);
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaContenidos;
	}
	
	@GET
	@Path("/obtenerComentariosAAprobar")
	@Produces("application/json")
	public List<ComentarioDataType> obtenerComentariosAAprobar(@Context HttpServletRequest req) {
		List<ComentarioDataType> listaComentarios = null;
		try {
			
			(new RightsChecker()).checkAdminRights((UserSession)req.getSession().getAttribute(ConstantesSession.keyUsuarioSession));
			listaComentarios = negocioContenido.obtenerComentariosAAprobar();
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaComentarios;
	}
	
	@GET
	@Path("/obtenerComentariosDeContenido/{idContenido}")
	@Produces("application/json")
	public List<ComentarioDataType> obtenerComentariosDeContenido(@PathParam("idContenido") String idContenido) {
		List<ComentarioDataType> listaComentarios = null;
		try {
			
			listaComentarios = negocioContenido.obtenerComentariosDeContenido(Integer.valueOf(idContenido)); 
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaComentarios;
	}
	
	@GET
	@Path("/obtenerDescargasACalificar")
	@Produces("application/json")
	public List<DescargaDataType> obtenerDescargasACalificar(@Context HttpServletRequest req) {
		List<DescargaDataType> listaDescargas = null;
		try {
			UserSession uSession = (UserSession)req.getSession().getAttribute(ConstantesSession.keyUsuarioSession);
			(new RightsChecker()).checkCustomerRights(uSession);
			listaDescargas = negocioContenido.obtenerDescargasACalificar(uSession.getUsuario()); 
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaDescargas;
	}
	
	@POST
	@Path("/altaContenido")
	@Consumes("application/json")
	@Produces("application/json")
	public GenericJsonResponse altaContenido(@Context HttpServletRequest req, ContenidoIngresoDataType dt) {
		GenericJsonResponse resp = new GenericJsonResponse();
		try {
			UserSession uSession = (UserSession)req.getSession().getAttribute(ConstantesSession.keyUsuarioSession);
			(new RightsChecker()).checkSupplierRights(uSession);
			Integer id = negocioContenido.ingresarNuevoContendo(dt, uSession.getUsuario()); 
			resp.setIdObjeto(String.valueOf(id));
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_OK);
		} catch (Exception e) {
			resp.setResultadoOperacion(EnumRespuestas.RESPUESTA_FALLA);
			resp.setMensageOperacion(e.getMessage()); 
			logger.error(e.getMessage() , e); 
		}
		return resp;
	}
	
}
