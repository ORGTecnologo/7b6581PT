package tecinf.servicios.procesos;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import org.jboss.logging.Logger;

import tecinf.negocio.NegocioParametros;
import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.NegocioFactory;

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
        	NegocioParametros negocioParametros = null;
        	try {
        		
        		negocioParametros = NegocioFactory.getNegocioParametros();
        		
        		logger.info("Ejecucion de demonio de chequeo de sesiones");
        		NegocioUsuario negocioUsuario = NegocioFactory.getNegocioUsuario();
        		negocioUsuario.executeSessionsRefresh();
        		
                
			} catch (Exception e) {
				logger.error(e.getMessage() , e);
			} finally {
				timer.cancel();
				new DemonioSesion(Integer.valueOf(negocioParametros.obtenerParametroPorNombre(EnumParametrosValor.LAPSO_EJECUCION_CHEQUEO_SESIONES)));
			}
        }

    }

}