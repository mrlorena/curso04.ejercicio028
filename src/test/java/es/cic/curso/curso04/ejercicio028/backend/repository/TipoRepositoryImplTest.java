package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso04.ejercicio028.backend.dominio.estilo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"}
)
public class TipoRepositoryImplTest extends AbstractRepositoryImplTest<Long, estilo> {

	
	 @Autowired
	    private TipoRepository sut;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	    }

	    @Override
	    public estilo getInstanceDeTParaNuevo() {
	    	
	    	estilo tipo = new estilo();
	        
	        tipo.setNombreTipo("cuadro");
	        
	        return tipo;
	    }

	    @Override
	    public estilo getInstanceDeTParaLectura() {
	    	
	    	estilo tipo = new estilo();
	        
	        tipo.setNombreTipo("cuadro");
	        
	        return tipo;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public estilo getInstanceDeTParaModificar(Long clave) {
	    	estilo tipo = getInstanceDeTParaLectura();
	        tipo.setId(clave);
	        tipo.setNombreTipo("escultura");
	       
	        return tipo;
	    }

	    @Override
	    public IRepository<Long, estilo> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(estilo t1, estilo t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getNombreTipo().equals(t2.getNombreTipo())) {
				return false;
			}
			
	        return true;
	    }
	}