package tecinf.presentacion.mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.NegocioFactory;

@ManagedBean
@ViewScoped
public class CambiarContraseniaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(CambiarContraseniaMB.class);
	private NegocioUsuario negocioUsuario = null;
	private UsuarioDataType usuarioActual = null;
	
	public CambiarContraseniaMB() throws NamingException {
		
		negocioUsuario = NegocioFactory.getNegocioUsuario();
		
	}
	
	public void cambiarContrasenia(){
		try {
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
		}
		
	}
	
	
	
	
}
