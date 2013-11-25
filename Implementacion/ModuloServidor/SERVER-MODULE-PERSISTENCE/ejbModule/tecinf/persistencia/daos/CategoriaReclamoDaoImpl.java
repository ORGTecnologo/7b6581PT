package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;

import tecinf.persistencia.entities.CategoriaReclamoEntity;

@Stateless
public class CategoriaReclamoDaoImpl extends DaoImpl<Integer , CategoriaReclamoEntity> implements CategoriaReclamoDao {

	@Override
	public List<CategoriaReclamoEntity> findAll() {

		return null;
	}

}
