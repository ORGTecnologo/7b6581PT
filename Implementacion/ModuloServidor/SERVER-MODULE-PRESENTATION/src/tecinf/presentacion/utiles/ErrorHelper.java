package tecinf.presentacion.utiles;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ErrorHelper {
	
	public void setErrorMessage(String idComponente, String mensaje){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje,mensaje);
		FacesContext.getCurrentInstance().addMessage(idComponente, message);
	}

}
