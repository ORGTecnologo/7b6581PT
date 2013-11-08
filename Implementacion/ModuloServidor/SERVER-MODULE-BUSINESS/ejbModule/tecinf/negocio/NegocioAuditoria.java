package tecinf.negocio;

import javax.ejb.Local;

import tecinf.negocio.dtos.AuditoriaDataType;

import java.util.Date;
import java.util.List;

@Local
public interface NegocioAuditoria {
	
	public List<AuditoriaDataType> obtenerRegistroDeIngresos();
	
	public void registrarAuditoria(String usuario, Date fechaHora, Integer idObjetoAuditoria, Integer idOperacionAuditoria, String idObjetoSistema);
	
}
