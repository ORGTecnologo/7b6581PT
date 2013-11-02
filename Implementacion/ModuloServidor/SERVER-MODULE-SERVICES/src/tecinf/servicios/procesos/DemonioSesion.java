package tecinf.servicios.procesos;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import org.jboss.logging.Logger;

import tecinf.servicios.utiles.session.SessionManager;

/**
 * Simple demo that uses java.util.Timer to schedule a task 
 * to execute once 5 seconds have passed.
 */

public class DemonioSesion {
	
    private Timer timer;
    private static final Logger logger = Logger.getLogger(DemonioSesion.class);

    public DemonioSesion(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	}

    class RemindTask extends TimerTask {   	
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    	
        public void run() {
        	try {
        		
        		logger.error("Ejecucion de demonio de session");
        		SessionManager sm = SessionManager.getInstance();
        		sm.executeSessionsRefresh();
                timer.cancel();
                
			} catch (Exception e) {
				logger.error("Metodo : " + e.getStackTrace()[0].getMethodName() + " - linea: " + e.getStackTrace()[0].getLineNumber() + " - Mensaje: " + e);
				timer.cancel();
			} finally {
				new DemonioSesion(20);
			}
        }
        //comentario
    }

}