package tecinf.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.persistencia.daos.CategoriaContenidoDao;
import tecinf.persistencia.daos.SubCategoriaContenidoDao;
import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioCategoriaContenidoImpl implements NegocioCategoriaContenido {
	
	private static Logger logger = Logger.getLogger(NegocioCategoriaContenidoImpl.class);
	
	private CategoriaContenidoDao categoriaContenidoDao = null;
	private SubCategoriaContenidoDao subCategoriaContenidoDao = null;
	
	public NegocioCategoriaContenidoImpl(){
		
		try {
			categoriaContenidoDao = PersistenciaFactory.getCategoriaContenidoDao();
			subCategoriaContenidoDao = PersistenciaFactory.getSubCategoriaContenidoDao();
		} catch (NamingException e) {
			logger.error(e.getMessage(), e) ;
		}	
		
	}
	
	public List<CategoriaContenidoDataType> obtenerCategoriasYSubcategorias(){
		List<CategoriaContenidoDataType> listaCategorias = new ArrayList<CategoriaContenidoDataType>();		
		List<CategoriaContenidoEntity> listaCategoriasE = categoriaContenidoDao.findAll();
		for (CategoriaContenidoEntity cat : listaCategoriasE){
			CategoriaContenidoDataType dt = DataTypesFactory.getCategoriaContenidoDataType(cat);			
			List<SubCategoriaContenidoEntity> subCats = subCategoriaContenidoDao.getAllByCategoria(cat.getId());
			for (SubCategoriaContenidoEntity subCat : subCats)
				dt.getSubcategorias().add(DataTypesFactory.getSubCategoriaContenidoDataType(subCat));			
			listaCategorias.add(dt);
		}		
		return listaCategorias;
	}
	
}
