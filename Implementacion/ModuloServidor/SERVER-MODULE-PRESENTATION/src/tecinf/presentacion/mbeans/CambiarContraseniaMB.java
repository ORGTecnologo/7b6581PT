package tecinf.presentacion.mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.CambiarContraseniaDataType;
import tecinf.negocio.dtos.UserSession;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.presentacion.utiles.ConstantesSession;
import tecinf.presentacion.utiles.ErrorHelper;

@ManagedBean
@ViewScoped
public class CambiarContraseniaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(CambiarContraseniaMB.class);
	private NegocioUsuario negocioUsuario = null;
	private CambiarContraseniaDataType cambiarContraseniaDataType =  new CambiarContraseniaDataType();
	
	private ErrorHelper eH = new ErrorHelper();
	
	public CambiarContraseniaMB() throws NamingException {
		
		negocioUsuario = NegocioFactory.getNegocioUsuario();
		
	}
	
	public void cambiarContrasenia(){
		try {			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			UserSession uSession = (UserSession)session.getAttribute(ConstantesSession.keyUsuarioSession);
			negocioUsuario.cambiarContrasenia(uSession.getUsuario() , cambiarContraseniaDataType);
		} catch (Exception e) {
			eH.setErrorMessage("btnConfirmar", e.getMessage());
			logger.error(e.getMessage() , e);
		}
	}

	public CambiarContraseniaDataType getCambiarContraseniaDataType() {
		return cambiarContraseniaDataType;
	}

	public void setCambiarContraseniaDataType(
			CambiarContraseniaDataType cambiarContraseniaDataType) {
		this.cambiarContraseniaDataType = cambiarContraseniaDataType;
	}
	
}
