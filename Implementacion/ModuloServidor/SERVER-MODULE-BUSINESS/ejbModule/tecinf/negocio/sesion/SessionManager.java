package tecinf.negocio.sesion;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;


public class SessionManager { 
	
	private Prevayler<SessionDataBase> prevayler = null;
	
	private SessionManager() throws Exception{
	
		this.prevayler = PrevaylerFactory.createPrevayler(new SessionDataBase(), "target/PrevalenceBase_" + System.currentTimeMillis());
		
	}
}
