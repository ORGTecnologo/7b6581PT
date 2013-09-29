package tecinf.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.persistencia.daos.RolDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.entities.RolEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioUsuarioImpl implements NegocioUsuario  {
	
	private UsuarioDao usuarioDao = null;
	private RolDao rolDao = null;
	
	public NegocioUsuarioImpl() throws NamingException{
		
		usuarioDao = PersistenciaFactory.getUsuarioDao();
		rolDao = PersistenciaFactory.getRolDao();
		
	}
	
	
	public List<UsuarioDataType> obtenerTodos(){
		
		List<UsuarioDataType> listaDtos = new ArrayList<UsuarioDataType>();
		List<UsuarioEntity> listEntities = usuarioDao.findAll();
		if (listEntities != null) {
			for (UsuarioEntity ue : listEntities)
				listaDtos.add(DataTypesFactory.getUsuarioDataType(ue));
		}
		return listaDtos;
		
	}
	
	public void modificarUsuario(UsuarioDataType u) throws Exception {
		
		UsuarioEntity ue = usuarioDao.findById(u.getUsuario());
		if (ue == null)
			throw new Exception("El usuario no existe");
		
		RolEntity re = (u.getRol() == null ? null : rolDao.findById(u.getRol().getId()) );
		if (re == null)
			throw new Exception("El rol no existe");
		
		ue.setRol(re);
		ue.setApellidos(u.getApellidos());
		ue.setContrasenia(u.getContrasenia());
		ue.setNombres(u.getNombres());
		
		usuarioDao.merge(ue);
		
	}
	
	public Boolean loginUsuario(String usuario, String contrasenia){
		
		
		return true;
	}
	
}
