package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioContenido;
import tecinf.negocio.dtos.AprobarContenidoDataType;
import tecinf.negocio.utiles.EnumEstadosVersionContenido;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.negocio.utiles.ValidationUtil;

@ManagedBean
@ViewScoped
public class ContenidoMB implements Serializable {
	
	private static Logger logger = Logger.getLogger(ContenidoMB.class);

	private static final long serialVersionUID = 1L;
	
	private List<AprobarContenidoDataType> listaVersionesPendientes = null;
	private NegocioContenido negocioContenido = null;
	
	private Boolean activoPanelAprobar = false;
	private Boolean activoPanelRechazar = false;
	
	@SuppressWarnings("rawtypes")
	private Map filtros = new HashMap<String , Object>();
	
	private String filtroUsuario;
	private String filtroNombre;
	private String filtroTipo;
	
	private AprobarContenidoDataType versionActual = null;
	
	public ContenidoMB() throws NamingException { 
		
		negocioContenido = NegocioFactory.getNegocioContenido();
		listaVersionesPendientes = negocioContenido.obtenerContenidosAAprobar(filtros);
		
	}
	
	public void mostrarPanelAprobar(){ activoPanelAprobar = true; }
	public void ocultarPanelAprobar(){	activoPanelAprobar = false; }
	
	public void mostrarPanelRechazar(){ activoPanelRechazar = true; }
	public void ocultarPanelRechazar(){ activoPanelRechazar = false; }
	
	public Boolean getActivoPanelAprobar() {
		return activoPanelAprobar;
	}

	public void setActivoPanelAprobar(Boolean activoPanelAprobar) {
		this.activoPanelAprobar = activoPanelAprobar;
	}

	public Boolean getActivoPanelRechazar() {
		return activoPanelRechazar;
	}

	public void setActivoPanelRechazar(Boolean activoPanelRechazar) {
		this.activoPanelRechazar = activoPanelRechazar;
	}

	public List<AprobarContenidoDataType> getListaVersionesPendientes() {
		return listaVersionesPendientes;
	}

	public void setListaVersionesPendientes(
			List<AprobarContenidoDataType> listaVersionesPendientes) {
		this.listaVersionesPendientes = listaVersionesPendientes;
	}

	public AprobarContenidoDataType getVersionActual() {
		return versionActual;
	}

	public void setVersionActual(AprobarContenidoDataType versionActual) {
		this.versionActual = versionActual;
	}
	
	
	public void aprobar(){
		try {
			negocioContenido.cambiarEstadoVersion(this.versionActual.getIdVersion(), EnumEstadosVersionContenido.APROBADA);
			listaVersionesPendientes = negocioContenido.obtenerContenidosAAprobar(filtros);
			activoPanelAprobar = false;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}		
	}
	
	public void rechazar(){
		try {
			negocioContenido.cambiarEstadoVersion(this.versionActual.getIdVersion(), EnumEstadosVersionContenido.RECHAZADA);
			listaVersionesPendientes = negocioContenido.obtenerContenidosAAprobar(filtros);
			activoPanelRechazar = false;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}				
	}
	
	@SuppressWarnings("unchecked")
	public void filtrar(){
		filtros.clear();
		if (!ValidationUtil.isNullOrEmpty(filtroNombre))
			filtros.put("nombre", filtroNombre);
		if (!ValidationUtil.isNullOrEmpty(filtroTipo))
			filtros.put("tipo", filtroTipo);
		if (!ValidationUtil.isNullOrEmpty(filtroUsuario))
			filtros.put("usuario", filtroUsuario);
		listaVersionesPendientes = negocioContenido.obtenerContenidosAAprobar(filtros);
	}

	public String getFiltroUsuario() {
		return filtroUsuario;
	}

	public void setFiltroUsuario(String filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroTipo() {
		return filtroTipo;
	}

	public void setFiltroTipo(String filtroTipo) {
		this.filtroTipo = filtroTipo;
	}
	
}
