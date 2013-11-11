package tecinf.servicios.procesos;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioParametros;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.NegocioFactory;
	
public class InicioProcesos implements ServletContextListener {     
      
	public static long applicationInitialized =    0L;
	private static final Logger logger = Logger.getLogger(InicioProcesos.class);
	
	public void contextInitialized(ServletContextEvent ce) {           
		try 
		{			
			logger.info("Inicio de tareas automaticas");         
			NegocioParametros negocioParametros = NegocioFactory.getNegocioParametros();
			
			new DemonioSesion(Integer.valueOf(negocioParametros.obtenerParametroPorNombre(EnumParametrosValor.LAPSO_EJECUCION_CHEQUEO_SESIONES)));
			new DemonioActualizarDatosContenidos(Integer.valueOf(negocioParametros.obtenerParametroPorNombre(EnumParametrosValor.LAPSO_EJECUCION_ACTUALIZACION_INFO_CONTENIDOS)));
		}
		catch (Exception e) 
		{
			logger.error(e.getMessage() , e);
		}           
	}
	
	public void contextDestroyed(ServletContextEvent ce) {
	    	  
	}
}