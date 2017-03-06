package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"}
)
public class AutorRepositoryImplTest extends AbstractRepositoryImplTest<Long, Autor> {

	
	 @Autowired
	    private AutorRepository sut;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	    }

	    @Override
	    public Autor getInstanceDeTParaNuevo() {
	    	
	    	Autor autor = new Autor();
	        autor.setNombre("nombre1");
	        autor.setFechaNacimiento(1234);
	        
	        return autor;
	    }

	    @Override
	    public Autor getInstanceDeTParaLectura() {
	    	Autor autor = new Autor();
	        autor.setNombre("nombre1");
	        autor.setFechaNacimiento(1234);
	        
	        return autor;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Autor getInstanceDeTParaModificar(Long clave) {
	    	Autor autor = getInstanceDeTParaLectura();
	    	autor.setId(clave);
	        autor.setNombre("nombre1");
	        autor.setFechaNacimiento(1234);
	       
	        return autor;
	    }

	    @Override
	    public IRepository<Long, Autor> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Autor t1, Autor t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getNombre().equals(t2.getNombre())) {
				return false;
			}
			
			if (!(t1.getFechaNacimiento()==(t2.getFechaNacimiento()))) {
				return false;
			}
			
	        return true;
	    }
	}