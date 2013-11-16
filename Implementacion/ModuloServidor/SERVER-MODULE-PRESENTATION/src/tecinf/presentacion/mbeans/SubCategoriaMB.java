package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioCategoriaContenido;
import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.presentacion.utiles.ErrorHelper;

@ManagedBean
@SessionScoped
public class SubCategoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private static Logger logger = Logger.getLogger(CategoriaMB.class);
	
	private NegocioCategoriaContenido negocioCategoria = null;
	private List<SubCategoriaContenidoDataType> listaSubCategorias = null;
	private SubCategoriaContenidoDataType currentSubCategoria = new SubCategoriaContenidoDataType();
	private SubCategoriaContenidoDataType nuevaSubCategoria = new SubCategoriaContenidoDataType();
	private List<CategoriaContenidoDataType> listaCategorias = null;
	
	private Boolean activoPanelIngreso = false;
	private Boolean activoPanelEditar = false;
	private Boolean activoPanelEliminar = false;
	
	private ErrorHelper eH = new ErrorHelper();
	
	public SubCategoriaMB() throws NamingException{
		
		negocioCategoria = NegocioFactory.getNegocioCategoriaContenido();		
		listaSubCategorias = negocioCategoria.obtenerSubCategorias();
		listaCategorias = negocioCategoria.obtenerCategorias();
		
	}
	
	public void mostrarPanelIngreso(){	activoPanelIngreso = true; }
	public void ocultarPanelIngreso(){	activoPanelIngreso = false; 	}
	
	public void mostrarPanelEditar(){ activoPanelEditar = true; }
	public void ocultarPanelEditar(){	activoPanelEditar = false; }
	
	public void mostrarPanelEliminar(){ activoPanelEliminar = true; }
	public void ocultarPanelEliminar(){ activoPanelEliminar = false; }
	
	public Boolean getActivoPanelIngreso() {
		return activoPanelIngreso;
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

	public List<SubCategoriaContenidoDataType> getListaSubCategorias() {
		return listaSubCategorias;
	}

	public void setListaSubCategorias(
			List<SubCategoriaContenidoDataType> listaSubCategorias) {
		this.listaSubCategorias = listaSubCategorias;
	}

	public SubCategoriaContenidoDataType getCurrentSubCategoria() {
		return currentSubCategoria;
	}

	public void setCurrentSubCategoria(
			SubCategoriaContenidoDataType currentSubCategoria) {
		this.currentSubCategoria = currentSubCategoria;
	}

	public SubCategoriaContenidoDataType getNuevaSubCategoria() {
		return nuevaSubCategoria;
	}

	public void setNuevaSubCategoria(SubCategoriaContenidoDataType nuevaSubCategoria) {
		this.nuevaSubCategoria = nuevaSubCategoria;
	}
	

	public List<CategoriaContenidoDataType> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<CategoriaContenidoDataType> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public void crearSubCategoria(){
		try {			
			negocioCategoria.ingresarSubCategoria(this.nuevaSubCategoria);		
			listaSubCategorias = negocioCategoria.obtenerSubCategorias();
			activoPanelIngreso = false;
		} catch (Exception e) {
			eH.setErrorMessage("btnConfirmarIngreso", e.getMessage());
		}
		
	}
	
	public void editarSubCategoria(){
		try {			
			negocioCategoria.modificarSubCategoria(this.currentSubCategoria);		
			listaSubCategorias = negocioCategoria.obtenerSubCategorias();	
			activoPanelEditar = false;
		} catch (Exception e) {
			eH.setErrorMessage("btnConfirmarModificacion", e.getMessage());
		}
	}
	
	public void eliminarSubCategoria(){
		try {			
			negocioCategoria.cambiarEstadoSubCategoria(this.currentSubCategoria);	
			listaSubCategorias = negocioCategoria.obtenerSubCategorias();
			activoPanelEliminar = false;
		} catch (Exception e) {
			eH.setErrorMessage("", e.getMessage());
		}		
	}
}
