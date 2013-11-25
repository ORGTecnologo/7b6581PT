package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioContenido;
import tecinf.negocio.dtos.ReclamoDataType;
import tecinf.negocio.utiles.NegocioFactory;

@ManagedBean
@ViewScoped
public class ReclamoMB implements Serializable {
	
	private static Logger logger = Logger.getLogger(ReclamoMB.class);

	private static final long serialVersionUID = 1L;
	
	private NegocioContenido negocioContenido = null;
	private List<ReclamoDataType> listaReclamos = null;
	private ReclamoDataType reclamoActual = null;
	
	private Boolean activoPanelResolver = false;
	
	public ReclamoMB() throws NamingException {
		
		negocioContenido = NegocioFactory.getNegocioContenido();
		listaReclamos = negocioContenido.obtenerReclamosPendientes();
		
	}
	
	public void resolverReclamo(){
		try {
			negocioContenido.resolverReclamo(reclamoActual);
			listaReclamos = negocioContenido.obtenerReclamosPendientes();
			activoPanelResolver = false;
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
		}
		
	}

	public List<ReclamoDataType> getListaReclamos() {
		return listaReclamos;
	}

	public void setListaReclamos(List<ReclamoDataType> listaReclamos) {
		this.listaReclamos = listaReclamos;
	}

	public ReclamoDataType getReclamoActual() {
		return reclamoActual;
	}

	public void setReclamoActual(ReclamoDataType reclamoActual) {
		this.reclamoActual = reclamoActual;
	}

	public Boolean getActivoPanelResolver() {
		return activoPanelResolver;
	}

	public void setActivoPanelResolver(Boolean activoPanelResolver) {
		this.activoPanelResolver = activoPanelResolver;
	}
	
	
}
