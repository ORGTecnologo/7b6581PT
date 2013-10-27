package tecinf.negocio.dtos;

public class UsuarioClienteDataType extends UsuarioDataType {

	private static final long serialVersionUID = 1L;
	
	private byte[] imagenPerfil;

	public byte[] getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}	
	
}
