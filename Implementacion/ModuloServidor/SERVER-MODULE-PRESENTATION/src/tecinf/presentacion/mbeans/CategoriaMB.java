package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioCategoriaContenido;
import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.negocio.utiles.ValidationUtil;
import tecinf.presentacion.utiles.ErrorHelper;

@ManagedBean
@SessionScoped
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(CategoriaMB.class);
	
	private NegocioCategoriaContenido negocioCategoria = null;
	private List<CategoriaContenidoDataType> listaCategorias = null;
	private CategoriaContenidoDataType currentCategoria = new CategoriaContenidoDataType();
	private CategoriaContenidoDataType nuevaCategoria = new CategoriaContenidoDataType();
	
	private Boolean activoPanelIngreso = false;
	private Boolean activoPanelEditar = false;
	private Boolean activoPanelEliminar = false;
	
	private String filtroNombre;
	private String filtroDescripcion;
	private String filtroEstado;
		
	@SuppressWarnings("rawtypes")
	private Map filtros = new HashMap<String , Object>();
	
	private ErrorHelper eH = new ErrorHelper();
	
	public CategoriaMB() throws NamingException{
		
		negocioCategoria = NegocioFactory.getNegocioCategoriaContenido();		
		listaCategorias = negocioCategoria.obtenerCategoriasPorFiltros(filtros);
		
	}

	public List<CategoriaContenidoDataType> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<CategoriaContenidoDataType> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public CategoriaContenidoDataType getCurrentCategoria() {
		return currentCategoria;
	}

	public void setCurrentCategoria(CategoriaContenidoDataType currentCategoria) {
		this.currentCategoria = currentCategoria;
	}
	
	public void mostrarPanelIngreso(){	
		activoPanelIngreso = true; 
		}
	public void ocultarPanelIngreso(){	
		activoPanelIngreso = false; 
		}
	
	public void mostrarPanelEditar(){ activoPanelEditar = true; }
	public void ocultarPanelEditar(){	activoPanelEditar = false; }
	
	public void mostrarPanelEliminar(){ activoPanelEliminar = true; }
	public void ocultarPanelEliminar(){ activoPanelEliminar = false; }
	
	public Boolean getActivoPanelIngreso() {
		return activoPanelIngreso;
	}	
	
	public CategoriaContenidoDataType getNuevaCategoria() {
		return nuevaCategoria;
	}

	public void setNuevaCategoria(CategoriaContenidoDataType nuevaCategoria) {
		this.nuevaCategoria = nuevaCategoria;
	}

	public Boolean getActivoPanelEditar() {
		return activoPanelEditar;
	}

	public void setActivoPanelEditar(Boolean activoPanelEditar) {
		this.activoPanelEditar = activoPanelEditar;
	}

	public Boolean getActivoPanelEliminar() {
		return activoPanelEliminar;
	}

	public void setActivoPanelEliminar(Boolean activoPanelElimiar) {
		this.activoPanelEliminar = activoPanelElimiar;
	}

	public void setActivoPanelIngreso(Boolean activoPanelIngreso) {
		this.activoPanelIngreso = activoPanelIngreso;
	}

	public void crearCategoria(){
		try {			
			negocioCategoria.ingresarCategoria(this.nuevaCategoria);		
			listaCategorias = negocioCategoria.obtenerTodasCategoriasYSubcategorias();
			activoPanelIngreso = false;
		} catch (Exception e) {
			eH.setErrorMessage("btnConfirmarIngreso", e.getMessage());
		}
		
	}
	
	public void editarCategoria(){
		try {			
			negocioCategoria.modificarCategoria(this.currentCategoria);		
			listaCategorias = negocioCategoria.obtenerTodasCategoriasYSubcategorias();		
			activoPanelEditar = false;
		} catch (Exception e) {
			eH.setErrorMessage("btnConfirmarModificacion", e.getMessage());
		}		
	}
	
	public void cambiarEstadoCategoria(){
		try {			
			negocioCategoria.cambiarEstadoCategoria(this.currentCategoria);	
			listaCategorias = negocioCategoria.obtenerTodasCategoriasYSubcategorias();		
			activoPanelEliminar = false;
		} catch (Exception e) {
			eH.setErrorMessage("", e.getMessage());
		}		
	}
	
	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroDescripcion() {
		return filtroDescripcion;
	}

	public void setFiltroDescripcion(String filtroDescripcion) {
		this.filtroDescripcion = filtroDescripcion;
	}

	public String getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	@SuppressWarnings("unchecked")
	public void filtrar(){
		filtros.clear();
		if (!ValidationUtil.isNullOrEmpty(filtroNombre))
			filtros.put("nombre", filtroNombre);
		if (!ValidationUtil.isNullOrEmpty(filtroDescripcion))
			filtros.put("nombre", filtroDescripcion);
		if (!ValidationUtil.isNullOrEmpty(filtroEstado)){
			if (filtroEstado.equals("H"))
				filtros.put("habilitada", true);
			else if (filtroEstado.equals("D"))
				filtros.put("habilitada", false);
		}
		listaCategorias = negocioCategoria.obtenerCategoriasPorFiltros(filtros);
	}
}
