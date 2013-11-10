package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;

import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;

@Stateless
public class UsuarioDescargaContenidoDaoImpl  extends DaoImpl<Integer, UsuarioDescargaContenidoEntity> implements UsuarioDescargaContenidoDao {

	@Override
	public List<UsuarioDescargaContenidoEntity> findAll() {

		return null;
	}

}
