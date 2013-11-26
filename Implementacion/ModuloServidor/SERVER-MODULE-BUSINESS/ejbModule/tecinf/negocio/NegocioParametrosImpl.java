package tecinf.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.ParametroValorDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioParametrosImpl implements NegocioParametros {

	private ParametroValorDao parametroDao;
	
	public NegocioParametrosImpl() throws NamingException { 
		
		parametroDao = PersistenciaFactory.getParametroValorDao();
		
	}
	
	@Override
	public String obtenerParametroPorNombre(String nombre) {
		return parametroDao.findByID(nombre).getValorParametro();
	}
	
	public List<ParametroValorDataType> obtenerTodos(){
		List<ParametroValorDataType> listaParametros = new ArrayList<ParametroValorDataType>();
		List<ParametroValorEntity> listaE = parametroDao.findAll();
		
		if (listaE != null){
			for (ParametroValorEntity e : listaE)
				listaParametros.add(DataTypesFactory.getParametroValorDataType(e));			
		}
		
		return listaParametros;
	}
	
	public List<ParametroValorDataType> obtenerTodosPorFiltros(Map filtros){
		List<ParametroValorDataType> listaParametros = new ArrayList<ParametroValorDataType>();
		List<ParametroValorEntity> listaE = parametroDao.findAllByFiltros(filtros);
		
		if (listaE != null){
			for (ParametroValorEntity e : listaE)
				listaParametros.add(DataTypesFactory.getParametroValorDataType(e));			
		}
		
		return listaParametros;
	}
	
	public void actualizarParametro(ParametroValorDataType dt){
		ParametroValorEntity p = parametroDao.findByID(dt.getNombre());
		p.setValorParametro(dt.getValor());
		parametroDao.merge(p);
	}

}
