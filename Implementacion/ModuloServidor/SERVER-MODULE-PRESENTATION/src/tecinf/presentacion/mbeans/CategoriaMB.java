package tecinf.presentacion.mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="categoriaBean")
@RequestScoped
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String algo = "algoooo!!!!";

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}
}
