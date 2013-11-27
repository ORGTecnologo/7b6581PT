package tecinf.presentacion.mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.UsuarioAdministradorDataType;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.presentacion.utiles.JsfMessagesHelper;

@ManagedBean
@ViewScoped
public class AdministradorMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AdministradorMB.class);
	
	private NegocioUsuario negocioUsuario = null;
	private UsuarioAdministradorDataType adminActual = new UsuarioAdministradorDataType();
	private JsfMessagesHelper eH = new JsfMessagesHelper();
	
	public AdministradorMB() throws NamingException {
		
		negocioUsuario = NegocioFactory.getNegocioUsuario();
		
	}
	
	public void registrar(){
		try {
			negocioUsuario.registroUsuarioAdministrador(adminActual);
			adminActual = new UsuarioAdministradorDataType();
			eH.setMessage("btnConfirmar", "Administrador ingresado con éxito");
		} catch (Exception e) {
			eH.setErrorMessage("btnConfirmar", e.getMessage());
			logger.error(e.getMessage() , e);
		}
	}

	public UsuarioAdministradorDataType getAdminActual() {
		return adminActual;
	}

	public void setAdminActual(UsuarioAdministradorDataType adminActual) {
		this.adminActual = adminActual;
	}
	
}
