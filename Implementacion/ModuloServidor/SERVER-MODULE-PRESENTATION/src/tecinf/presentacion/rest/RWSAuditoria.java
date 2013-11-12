package tecinf.presentacion.rest;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioAuditoria;
import tecinf.negocio.dtos.AuditoriaDataType;
import tecinf.negocio.utiles.NegocioFactory;

@Path("/auditoria")
public class RWSAuditoria {
	
	private static Logger logger = Logger.getLogger(RWSAuditoria.class);
	private NegocioAuditoria negocioAuditoria = null;
	
	public RWSAuditoria() throws NamingException { 
		negocioAuditoria = NegocioFactory.getNegocioAuditoria();	
	}
	
	@GET
	@Path("/listarIngresosAlSistema")
	@Produces("application/json")
	public List<AuditoriaDataType> obtenerCategoriasYSubcategorias() {
		List<AuditoriaDataType> listaIngresos = null;
		try {
			
			listaIngresos = negocioAuditoria.obtenerRegistroDeIngresos();
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		return listaIngresos;
	}

}
