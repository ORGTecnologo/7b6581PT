package tecinf.presentacion.utiles;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ErrorHelper {
	
	public void setErrorMessage(String idComponente, String mensage){
		FacesContext.getCurrentInstance().addMessage(idComponente, new FacesMessage(mensage));		
	}

}
