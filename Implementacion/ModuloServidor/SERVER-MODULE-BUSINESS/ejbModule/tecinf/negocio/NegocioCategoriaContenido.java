package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;

@Local
public interface NegocioCategoriaContenido {
	
	public List<CategoriaContenidoDataType> obtenerCategoriasYSubcategorias();
	
	public List<SubCategoriaContenidoDataType> obtenerSubCategorias();	
	
	public Integer ingresarCategoria(CategoriaContenidoDataType dt) throws Exception;
	
	public Integer ingresarSubCategoria(SubCategoriaContenidoDataType dt) throws Exception;
	
	public Integer modificarCategoria(CategoriaContenidoDataType dt) throws Exception;
	
	public Integer modificarSubCategoria(SubCategoriaContenidoDataType dt) throws Exception;
	
	public Integer eliminarSubCategoria(SubCategoriaContenidoDataType dt) throws Exception;
	
	public Integer eliminarCategoria(CategoriaContenidoDataType dt) throws Exception;
	
}
