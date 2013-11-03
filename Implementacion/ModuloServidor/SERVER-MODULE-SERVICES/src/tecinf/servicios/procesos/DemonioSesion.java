package tecinf.servicios.procesos;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        		
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        		logger.info("Ejecucion de demonio de session " + sdf.format(new Date()));
        		SessionManager sm = SessionManager.getInstance();
        		sm.executeSessionsRefresh();                
                
			} catch (Exception e) {
				logger.error(e.getMessage() , e);
			} finally {
				timer.cancel();
				new DemonioSesion(20);
			}
        }

    }

}