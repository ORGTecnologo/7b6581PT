package tecinf.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.negocio.utiles.Encriptacion;
import tecinf.persistencia.daos.UsuarioClienteDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.entities.UsuarioClienteEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioUsuarioImpl implements NegocioUsuario  {
	
	private UsuarioClienteDao usuarioClienteDao = null;
	
	public NegocioUsuarioImpl() throws NamingException{
		
		usuarioClienteDao = PersistenciaFactory.getUsuarioClienteDao();
		
	}
	
	
	public List<UsuarioClienteDataType> obtenerTodosClientes(){
		
		List<UsuarioClienteDataType> listaDtos = new ArrayList<UsuarioClienteDataType>();
		List<UsuarioClienteEntity> listEntities = usuarioClienteDao.findAll();
		if (listEntities != null) {
			for (UsuarioClienteEntity ue : listEntities)
				listaDtos.add(DataTypesFactory.getUsuarioClienteDataType(ue));
		}
		return listaDtos;
		
	}
	
	public void modificarUsuario(UsuarioDataType u) throws Exception {
		
		/*
		UsuarioEntity ue = usuarioDao.findById(u.getUsuario());
		if (ue == null)
			throw new Exception("El usuario no existe");
		
		ue.setApellidos(u.getApellidos());
		ue.setContrasenia(u.getContrasenia());
		ue.setNombres(u.getNombres());
		
		usuarioDao.merge(ue);
		*/
	}
	
	public Boolean loginUsuarioCliente(String usuario, String contrasenia){
		
		String hashedPassword = Encriptacion.encriptarMD5(contrasenia);
		if ( usuarioClienteDao.findByUserAndPassword(usuario, hashedPassword) == null)
			return false;
		else
			return true;
		
	}


	@Override
	public Boolean loginUsuarioAdmin(String usuario, String contrasenia) {
		
		
		
		return null;
	}
	
}
