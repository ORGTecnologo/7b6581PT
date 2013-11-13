package tecinf.negocio.utiles;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	public Date addTimeToDate(Date fechaHora, Integer cantidad, Integer medida){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaHora);
		cal.add(medida , cantidad);
		return cal.getTime();
	}
	
	
}
