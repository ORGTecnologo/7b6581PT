package tecinf.presentacion.procesos;

import java.util.Timer;
import java.util.TimerTask;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioContenido;
import tecinf.negocio.NegocioParametros;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.NegocioFactory;

public class DemonioActualizacionDatosDescargas {
	
    private Timer timer;
    private static final Logger logger = Logger.getLogger(DemonioActualizacionDatosDescargas.class);

    public DemonioActualizacionDatosDescargas(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	}

    class RemindTask extends TimerTask {   	
    	
        public void run() {
        	NegocioParametros negocioParametros = null;
        	try {
        		
        		negocioParametros = NegocioFactory.getNegocioParametros();        		
        		logger.info("Ejecucion de demonio de actualizacion de datos de contenidos");  
        		NegocioContenido negocioContenido = NegocioFactory.getNegocioContenido();
        		negocioContenido.actualizarDatosDescargas();
                
			} catch (Exception e) {
				logger.error(e.getMessage() , e);
			} finally {
				timer.cancel();
				new DemonioActualizacionDatosDescargas(Integer.valueOf(negocioParametros.obtenerParametroPorNombre(EnumParametrosValor.LAPSO_EJECUCION_ACTUALIZACION_INFO_CONTENIDOS)));
			}
        }

    }

}