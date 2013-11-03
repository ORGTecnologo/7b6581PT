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
import tecinf.persistencia.daos.EstadoUsuarioDao;
import tecinf.persistencia.daos.TipoRegistroDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.entities.AuditoriaEntity;
import tecinf.persistencia.entities.EstadoUsuarioEntity;
import tecinf.persistencia.entities.TipoRegistroEntity;
import tecinf.persistencia.entities.UsuarioClienteEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.utiles.EnumClavesEntidades;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioUsuarioImpl implements NegocioUsuario  {
	
	private UsuarioDao usuarioDao = null;
	private AuditoriaDao auditoriaDao = null;
	private AuditoriaObjetoDao auditoriaObjetoDao = null;
	private AuditoriaOperacionDao auditoriaOperacionDao = null;
	private EstadoUsuarioDao estadoUsuarioDao = null;
	private TipoRegistroDao tipoRegistroDao = null;
	
	public NegocioUsuarioImpl() throws NamingException{
		
		auditoriaDao = PersistenciaFactory.getAuditoriaDao();
		auditoriaObjetoDao = PersistenciaFactory.getAuditoriaObjetoDao();
		auditoriaOperacionDao = PersistenciaFactory.getAuditoriaOperacionDao();
		estadoUsuarioDao = PersistenciaFactory.getEstadoUsuarioDao();
		tipoRegistroDao = PersistenciaFactory.getTipoRegistroDao();
		usuarioDao = PersistenciaFactory.getUsuarioDao();
		
	}
	
	
	public List<UsuarioClienteDataType> obtenerTodosClientes(){
		
		List<UsuarioClienteDataType> listaDtos = new ArrayList<UsuarioClienteDataType>();
		List<UsuarioEntity> listEntities = usuarioDao.findAllByType(EnumTipoUsuario.USUARIO_CLIENTE);
		if (listEntities != null) {
			for (UsuarioEntity ue : listEntities)
				listaDtos.add((UsuarioClienteDataType)DataTypesFactory.getUsuarioDataType(ue));
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
	
	public LoginRespDataType loginUsuario(String usuario, String contrasenia){
		
		LoginRespDataType resp = new LoginRespDataType();
		String hashedPassword = Encriptacion.encriptarMD5(contrasenia);
		UsuarioEntity ue = usuarioDao.findByEmailAndPassword(usuario, hashedPassword); 
		if ( ue == null) {
			resp.setRespuesta(EnumRespuestas.RESPUESTA_FALLA);
		} else {			
			AuditoriaEntity a = new AuditoriaEntity();
			a.setFechaOperacion(new Date());
			a.setObjeto(auditoriaObjetoDao.findByID(EnumClavesEntidades.AUDITORIA_OBJETO_USUARIO));
			a.setOperacion(auditoriaOperacionDao.findByID(EnumClavesEntidades.AUDITORIA_OPERACION_LOGIN));
			a.setUsuario(ue);
			auditoriaDao.persist(a);
			resp.setRespuesta(EnumRespuestas.RESPUESTA_OK);
			String tkn = (new RandomString(20).nextString());
			resp.setToken(tkn);
			resp.setUsuario(ue.getUsuario());
			resp.setTipoUsuario(ue.getTipoUsuario());			
		}
		return resp;
	}

	@Override
	public Boolean loginUsuarioAdmin(String usuario, String contrasenia) {
		
		
		
		return null;
	}
	
	public void registroUsuarioCliente(UsuarioClienteDataType dt) throws Exception {
		
		UsuarioEntity ue;
		
		if (existeUsuario(dt.getUsuario()))
			throw new Exception("Nick ya utilizado");
		
		if (existeUsuarioPorMail(dt.getCorreoElectronico()))
			throw new Exception("Email ya utilizado");
		
		ue = new UsuarioClienteEntity();
		ue.setApellidos(dt.getApellidos());
		String pwdHash = Encriptacion.encriptarMD5(dt.getContrasenia());
		ue.setContrasenia(pwdHash);
		ue.setCorreoElectronico(dt.getCorreoElectronico());
		EstadoUsuarioEntity estado = estadoUsuarioDao.findByID(EnumClavesEntidades.ESTADO_USUARIO_HABILITADO);
		ue.setEstadoUsuario(estado);
		ue.setFechaNacimiento(dt.getFechaNacimientoDate());
		ue.setNombres(dt.getNombres()); 
		ue.setSexo(dt.getSexo());
		ue.setTelefonoMovil(dt.getTelefonoMovil());
		ue.setUsuario(dt.getUsuario());
		TipoRegistroEntity tipoRegistro = tipoRegistroDao.findByID(EnumClavesEntidades.TIPO_REGISTRO_WEB);
		((UsuarioClienteEntity)ue).setTipoRegistro(tipoRegistro);
		((UsuarioClienteEntity)ue).setRutaImagenPerfil("");
		ue.setTipoUsuario(EnumTipoUsuario.USUARIO_CLIENTE);
		
		usuarioDao.persist(ue);
	}
	
	public Boolean existeUsuario(String usr){
		UsuarioEntity ue = usuarioDao.findByID(usr);
		if (ue == null)
			return false;
		else
			return true;
	}
	
	public Boolean existeUsuarioPorMail(String mail){
		UsuarioEntity ue = usuarioDao.findByMail(mail);
		if (ue == null)
			return false;
		else
			return true;
	}
	
}
