package tecinf.servicios.procesos;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jboss.logging.Logger;

public class InicioProcesos implements ServletContextListener
{     
      public static long applicationInitialized =    0L;
      private static final Logger logger = Logger.getLogger(InicioProcesos.class);

      /* Application Startup Event */
      public void contextInitialized(ServletContextEvent ce) 
      {           
            try 
            {
            	
        		logger.info("Tareas iniciadas automaticamente:");                           
            	new DemonioSesion(20);
            	
            }
            catch (Exception e) 
            {
            	e.printStackTrace();
            }           
      }

      /* Application Shutdown Event */
      public void contextDestroyed(ServletContextEvent ce) {
    	  
      }
}