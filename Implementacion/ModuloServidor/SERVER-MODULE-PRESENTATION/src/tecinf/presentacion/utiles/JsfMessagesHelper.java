package tecinf.presentacion.utiles;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfMessagesHelper {
	
	public void setErrorMessage(String idComponente, String mensaje){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje,mensaje);
		FacesContext.getCurrentInstance().addMessage(idComponente, message);
	}
	
	public void setMessage(String idComponente, String mensaje){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje);
		FacesContext.getCurrentInstance().addMessage(idComponente, message);
	}

}
