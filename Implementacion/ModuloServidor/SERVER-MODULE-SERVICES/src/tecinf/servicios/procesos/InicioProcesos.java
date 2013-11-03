package tecinf.servicios.procesos;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jboss.logging.Logger;
	
public class InicioProcesos implements ServletContextListener {     
      
	public static long applicationInitialized =    0L;
	private static final Logger logger = Logger.getLogger(InicioProcesos.class);
	
	public void contextInitialized(ServletContextEvent ce) {           
		try 
		{			
			logger.info("Inicio de tareas automaticas");                           
			new DemonioSesion(20);			
		}
		catch (Exception e) 
		{
			logger.error(e.getMessage() , e);
		}           
	}
	
	public void contextDestroyed(ServletContextEvent ce) {
	    	  
	}
}