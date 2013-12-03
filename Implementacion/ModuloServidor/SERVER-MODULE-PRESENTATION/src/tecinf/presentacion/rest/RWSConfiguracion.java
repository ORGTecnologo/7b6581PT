package tecinf.presentacion.rest;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import tecinf.negocio.NegocioParametros;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.NegocioFactory;

@Path("/configuracion")
public class RWSConfiguracion {
	
	private static Logger logger = Logger.getLogger(RWSCategoriasContenido.class);
	private NegocioParametros negocioParametros = null;
	
	public RWSConfiguracion() {
		
		try {
			negocioParametros = NegocioFactory.getNegocioParametros();
		} catch (NamingException e) {
			logger.error(e.getMessage() , e);
		}
		
	}
	
	@GET
	@Path("/obtenerCantidadContenidosListaTop")
	public String obtenerCategoriasYSubcategorias() {
		JSONObject cantidadContenidos = new JSONObject();
		try {			
			String cantidadContenidosTop = negocioParametros.obtenerParametroPorNombre(EnumParametrosValor.CANTIDAD_TOP_CONTENIDOS);			
			cantidadContenidos.put("cantidad", Integer.valueOf(cantidadContenidosTop));			
		} catch (Exception e) {
			try {
				cantidadContenidos.put("resultado", "ERROR_AL_OBTENER_PARAMETRO");
				cantidadContenidos.put("descripcion", e.getMessage());
			} catch (JSONException e1) {
				logger.error(e.getMessage() , e);
			}			
			logger.error(e.getMessage() , e); 
		}
		return cantidadContenidos.toString();
	}
	
}
