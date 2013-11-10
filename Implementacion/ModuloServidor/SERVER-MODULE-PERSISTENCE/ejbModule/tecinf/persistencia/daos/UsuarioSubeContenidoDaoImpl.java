package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;

import tecinf.persistencia.entities.UsuarioSubeContenidoEntity;

@Stateless
public class UsuarioSubeContenidoDaoImpl  extends DaoImpl<Integer, UsuarioSubeContenidoEntity> implements UsuarioSubeContenidoDao {

	@Override
	public List<UsuarioSubeContenidoEntity> findAll() {

		return null;
	}

}
