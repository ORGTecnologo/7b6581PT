package tecinf.servicios.procesos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioContenido;
import tecinf.negocio.utiles.NegocioFactory;

public class DemonioActualizarDatosContenidos {
	
    private Timer timer;
    private static final Logger logger = Logger.getLogger(DemonioActualizarDatosContenidos.class);

    public DemonioActualizarDatosContenidos(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	}

    class RemindTask extends TimerTask {   	
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    	
        public void run() {
        	try {
        		
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        		logger.info("Ejecucion de demonio de actualizacion de datos de contenidos " + sdf.format(new Date()));  
        		NegocioContenido negocioContenido = NegocioFactory.getNegocioContenido();
        		negocioContenido.actualizarDatosContenidos();
                
			} catch (Exception e) {
				logger.error(e.getMessage() , e);
			} finally {
				timer.cancel();
				new DemonioActualizarDatosContenidos(LapsosProcesos.LAPSO_EJECUCION_DEMONIO_ACTUALIZACION_DATOS_CONTENIDOS);
			}
        }

    }

}