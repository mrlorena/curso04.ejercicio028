package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.repository.IRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"}
)
public class TipoRepositoryImplTest extends AbstractRepositoryImplTest<Long, Tipo> {

	
	 @Autowired
	    private TipoRepository sut;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	    }

	    @Override
	    public Tipo getInstanceDeTParaNuevo() {
	    	
	    	Tipo tipo = new Tipo();
	        
	        tipo.setNombreTipo("cuadro");
	        
	        return tipo;
	    }

	    @Override
	    public Tipo getInstanceDeTParaLectura() {
	    	
	    	Tipo tipo = new Tipo();
	        
	        tipo.setNombreTipo("cuadro");
	        
	        return tipo;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Tipo getInstanceDeTParaModificar(Long clave) {
	    	Tipo tipo = getInstanceDeTParaLectura();
	        tipo.setId(clave);
	        tipo.setNombreTipo("escultura");
	       
	        return tipo;
	    }

	    @Override
	    public IRepository<Long, Tipo> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Tipo t1, Tipo t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getNombreTipo().equals(t2.getNombreTipo())) {
				return false;
			}
			
	        return true;
	    }
	}