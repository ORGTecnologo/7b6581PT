package tecinf.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.AuditoriaDataType;
import tecinf.negocio.utiles.ConstantesAuditoria;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.persistencia.daos.AuditoriaDao;
import tecinf.persistencia.daos.AuditoriaObjetoDao;
import tecinf.persistencia.daos.AuditoriaOperacionDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.entities.AuditoriaEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioAuditoriaImpl implements NegocioAuditoria {
	
	private AuditoriaDao auditoriaDao = null;
	private UsuarioDao usuarioDao = null;
	private AuditoriaObjetoDao auditoriaObjetoDao = null;
	private AuditoriaOperacionDao auditoriaOperacionDao = null;
	
	public NegocioAuditoriaImpl() throws NamingException{
		
		auditoriaDao = PersistenciaFactory.getAuditoriaDao();
		usuarioDao = PersistenciaFactory.getUsuarioDao();
		auditoriaObjetoDao = PersistenciaFactory.getAuditoriaObjetoDao();
		auditoriaOperacionDao = PersistenciaFactory.getAuditoriaOperacionDao();
		
	}
	
	public List<AuditoriaDataType> obtenerRegistroDeIngresos(){
		List<AuditoriaDataType> listaIngresos = new ArrayList<AuditoriaDataType>(); 
		
		List<AuditoriaEntity> listaIngresosE = auditoriaDao.getHistoryByOperation(ConstantesAuditoria.ID_OPERACION_LOGIN);
		if (listaIngresosE != null){
			for (AuditoriaEntity a : listaIngresosE)
				listaIngresos.add(DataTypesFactory.getAuditoriaDataType(a));
		}		
		
		return listaIngresos;
	}
	
	public void registrarAuditoria(String usuario, Date fechaHora, Integer idObjetoAuditoria, Integer idOperacionAuditoria, String idObjetoSistema){
		AuditoriaEntity a = new AuditoriaEntity();
		
		a.setUsuario(usuarioDao.findByID(usuario));
		a.setFechaOperacion(fechaHora);
		a.setObjeto(auditoriaObjetoDao.findByID(idObjetoAuditoria));
		a.setOperacion(auditoriaOperacionDao.findByID(idOperacionAuditoria));
		a.setIdObjetoSistema(usuario);
		
		auditoriaDao.persist(a);
	}	

}
