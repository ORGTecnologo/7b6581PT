package tecinf.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.AuditoriaDataType;
import tecinf.persistencia.daos.AuditoriaDao;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioAuditoriaImpl implements NegocioAuditoria {
	
	private AuditoriaDao auditoriaDao;
	
	public NegocioAuditoriaImpl() throws NamingException{
		
		auditoriaDao = PersistenciaFactory.getAuditoriaDao();
		
	}
	
	public List<AuditoriaDataType> obtenerRegistroDeIngresos(){
		
		
		return new ArrayList<AuditoriaDataType>();
	}
	
	
	
	

}
