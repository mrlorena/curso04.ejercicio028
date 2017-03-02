package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso04.ejercicio028.backend.repository.IRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"}
)
public class CategoriaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Categoria> {

	
	 @Autowired
	    private CategoriaRepository sut;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	    }

	    @Override
	    public Categoria getInstanceDeTParaNuevo() {
	    	
	    	Categoria categoria = new Categoria();
	    	
	        categoria.setNombreCategoria("dibujo");
	        
	        return categoria;
	    }

	    @Override
	    public Categoria getInstanceDeTParaLectura() {
	    
	    	Categoria categoria = new Categoria();
	    	
	        categoria.setNombreCategoria("dibujo");
	        
	        return categoria;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Categoria getInstanceDeTParaModificar(Long clave) {
	    	Categoria categoria = getInstanceDeTParaLectura();
	    	categoria.setId(clave);
	    	categoria.setNombreCategoria("dibujo");
	       
	        return categoria;
	    }

	    @Override
	    public IRepository<Long, Categoria> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Categoria t1, Categoria t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getNombreCategoria().equals(t2.getNombreCategoria())) {
				return false;
			}
			
		 
	        
	        return true;
	    }
	}