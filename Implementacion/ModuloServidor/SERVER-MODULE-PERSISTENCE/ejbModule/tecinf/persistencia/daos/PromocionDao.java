package tecinf.persistencia.daos;

import java.util.Date;

import tecinf.persistencia.entities.PromocionEntity;

public interface PromocionDao extends Dao<Integer , PromocionEntity> {
	
	public PromocionEntity getPromocionVigenteAFechaPorContenido(Integer idContenido, Date fecha);	
	
}
