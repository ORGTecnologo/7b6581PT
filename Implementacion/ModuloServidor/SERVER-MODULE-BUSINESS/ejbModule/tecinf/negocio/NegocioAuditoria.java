package tecinf.negocio;

import javax.ejb.Local;

import tecinf.negocio.dtos.AuditoriaDataType;

import java.util.List;

@Local
public interface NegocioAuditoria {
	
	
	public List<AuditoriaDataType> obtenerRegistroDeIngresos();
	
	
}
