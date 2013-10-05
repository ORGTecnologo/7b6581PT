package tecinf.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import tecinf.negocio.dtos.LoginRespDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.negocio.utiles.Encriptacion;
import tecinf.negocio.utiles.EnumRespuestas;
import tecinf.negocio.utiles.EnumTipoUsuario;
import tecinf.negocio.utiles.RandomString;
import tecinf.persistencia.daos.AuditoriaDao;
import tecinf.persistencia.daos.AuditoriaObjetoDao;
import tecinf.persistencia.daos.AuditoriaOperacionDao;
import tecinf.persistencia.daos.UsuarioClienteDao;
import tecinf.persistencia.entities.AuditoriaEntity;
import tecinf.persistencia.entities.UsuarioClienteEntity;
import tecinf.persistencia.utiles.EnumClavesEntidades;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioUsuarioImpl implements NegocioUsuario  {
	
	private UsuarioClienteDao usuarioClienteDao = null;
	private AuditoriaDao auditoriaDao = null;
	private AuditoriaObjetoDao auditoriaObjetoDao = null;
	private AuditoriaOperacionDao auditoriaOperacionDao = null;
	
	public NegocioUsuarioImpl() throws NamingException{
		
		usuarioClienteDao = PersistenciaFactory.getUsuarioClienteDao();
		auditoriaDao = PersistenciaFactory.getAuditoriaDao();
		auditoriaObjetoDao = PersistenciaFactory.getAuditoriaObjetoDao();
		auditoriaOperacionDao = PersistenciaFactory.getAuditoriaOperacionDao();
		
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
	
	public LoginRespDataType loginUsuarioCliente(String usuario, String contrasenia){
		
		LoginRespDataType resp = new LoginRespDataType();
		String hashedPassword = Encriptacion.encriptarMD5(contrasenia);
		UsuarioClienteEntity ue = usuarioClienteDao.findByUserAndPassword(usuario, hashedPassword); 
		if ( ue == null) {
			resp.setRespuesta(EnumRespuestas.RESPUESTA_FALLA);
		} else {			
			AuditoriaEntity a = new AuditoriaEntity();
			a.setFechaOperacion(new Date());
			a.setObjeto(auditoriaObjetoDao.findByID(EnumClavesEntidades.AUDITORIA_OBJETO_USUARIO));
			a.setOperacion(auditoriaOperacionDao.findByID(EnumClavesEntidades.AUDITORIA_OPERACION_LOGIN));
			a.setUsuarioCliente(ue);
			auditoriaDao.persist(a);
			resp.setRespuesta(EnumRespuestas.RESPUESTA_OK);
			String tkn = (new RandomString(20).nextString());
			resp.setToken(tkn);
			resp.setUsuario(ue.getUsuario().getUsuario());
			resp.setTipoUsuario(EnumTipoUsuario.USUARIO_CLIENTE);			
		}
		return resp;
	}


	@Override
	public Boolean loginUsuarioAdmin(String usuario, String contrasenia) {
		
		
		
		return null;
	}
	
}
