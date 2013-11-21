package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.NegocioFactory;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(UsuarioMB.class);
	
	
	private NegocioUsuario negocioUsuario = null;
	private UsuarioDataType usuarioActual;
	private List<UsuarioDataType> listaUsuarios = null;
	
	//Filtros
	private String filtroNick;
	private String filtroEmail;
	private String filtroTipoUsuario;	
	
	private Boolean activoPanelModificar = false;
	
	public UsuarioMB() throws NamingException { 
		
		negocioUsuario = NegocioFactory.getNegocioUsuario();
		listaUsuarios = negocioUsuario.buscarPorFiltros("todos", "", "");
		
	}
	
	public void mostrarPanelModificar() {activoPanelModificar = true;}
	public void ocultarPanelModificar() {activoPanelModificar = false;}
	
	public void cambiarEstado(){
		try {
			negocioUsuario.cambiarEstadoUsuario(usuarioActual);
			listaUsuarios = negocioUsuario.buscarPorFiltros("todos", "", "");
			activoPanelModificar = false;
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
	}
	
	public void filtrarUsuarios(){
		try {
			listaUsuarios = negocioUsuario.buscarPorFiltros(filtroTipoUsuario, filtroNick, filtroEmail);
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}		
	}

	public Boolean getActivoPanelModificar() {
		return activoPanelModificar;
	}

	public void setActivoPanelModificar(Boolean activoPanelModificar) {
		this.activoPanelModificar = activoPanelModificar;
	}

	public UsuarioDataType getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(UsuarioDataType usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public List<UsuarioDataType> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioDataType> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getFiltroNick() {
		return filtroNick;
	}

	public void setFiltroNick(String filtroNick) {
		this.filtroNick = filtroNick;
	}

	public String getFiltroEmail() {
		return filtroEmail;
	}

	public void setFiltroEmail(String filtroEmail) {
		this.filtroEmail = filtroEmail;
	}

	public String getFiltroTipoUsuario() {
		return filtroTipoUsuario;
	}

	public void setFiltroTipoUsuario(String filtroTipoUsuario) {
		this.filtroTipoUsuario = filtroTipoUsuario;
	}

}
