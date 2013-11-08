package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.ItemGenericoDataType;

@Local
public interface NegocioCategoriaContenido {
	
	public List<CategoriaContenidoDataType> obtenerCategoriasYSubcategorias();	
	
	public Integer ingresarCategoria(ItemGenericoDataType dt) throws Exception;
	
	public Integer ingresarSubCategoria(ItemGenericoDataType dt) throws Exception;
	
	public Integer modificarCategoria(ItemGenericoDataType dt) throws Exception;
	
	public Integer modificarSubCategoria(ItemGenericoDataType dt) throws Exception;
	
}
