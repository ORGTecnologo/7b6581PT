package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioParametros;
import tecinf.negocio.dtos.ParametroValorDataType;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.negocio.utiles.ValidationUtil;

@ManagedBean
@ViewScoped
public class ParametroMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ParametroMB.class);
	
	private NegocioParametros negocioParametros = null;
	private List<ParametroValorDataType> listaParametros = null;
	
	private String filtroNombre;
	
	public void mostrarPanelModificar(){ activoPanelModificar = true; }
	public void ocultarPanelModificar(){	activoPanelModificar = false; }
	
	private Boolean activoPanelModificar = false;
	
	private ParametroValorDataType parametroActual = null;
	
	@SuppressWarnings("rawtypes")
	private Map filtros = new HashMap<String , Object>();
	
	public ParametroMB() throws NamingException{
		
		negocioParametros = NegocioFactory.getNegocioParametros();
		listaParametros = negocioParametros.obtenerTodosPorFiltros(filtros);
		
	}
	
	public void modificar(){
		try {
			negocioParametros.actualizarParametro(parametroActual);
			listaParametros = negocioParametros.obtenerTodosPorFiltros(filtros);
			activoPanelModificar = false;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}		
	}
	
	public List<ParametroValorDataType> getListaParametros() {
		return listaParametros;
	}
	public void setListaParametros(List<ParametroValorDataType> listaParametros) {
		this.listaParametros = listaParametros;
	}
	public Boolean getActivoPanelModificar() {
		return activoPanelModificar;
	}
	public void setActivoPanelModificar(Boolean activoPanelModificar) {
		this.activoPanelModificar = activoPanelModificar;
	}
	public ParametroValorDataType getParametroActual() {
		return parametroActual;
	}
	public void setParametroActual(ParametroValorDataType parametroActual) {
		this.parametroActual = parametroActual;
	}
	
	@SuppressWarnings("unchecked")
	public void filtrar(){
		filtros.clear();
		if (!ValidationUtil.isNullOrEmpty(filtroNombre))
			filtros.put("nombre", filtroNombre);
		listaParametros = negocioParametros.obtenerTodosPorFiltros(filtros);
	}
	public String getFiltroNombre() {
		return filtroNombre;
	}
	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}
	
}
