package tecinf.negocio.dtos;

import java.io.Serializable;

public class UsuarioProveedorDataType extends UsuarioDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String sitioWeb;

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	
}
